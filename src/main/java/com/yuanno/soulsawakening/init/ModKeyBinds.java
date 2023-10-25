package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.events.ZanpakutoChangeEvent;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CChangeZanpakutoStatePacket;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ModKeyBinds {

    public static KeyBinding infoCard;
    public static KeyBinding zanpakutoStateNext;
    public static void init()
    {
        infoCard = new KeyBinding("keys.soulsawakening.info_card", 40, "keys.soulsawakening.gui");
        ClientRegistry.registerKeyBinding(infoCard);
        zanpakutoStateNext = new KeyBinding("keys.soalsawakening.zanpakuto_state", 41, "keys.soulsawakening.zanpakuto");
        ClientRegistry.registerKeyBinding(zanpakutoStateNext);
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseInputEvent event)
    {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerEntity player = minecraft.player;
        if (player == null || event.getAction() == GLFW.GLFW_RELEASE)
            return;

        ModKeyBinds.checkKeybindings(player);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event)
    {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerEntity player = minecraft.player;
        if (player == null)
            return;

        ModKeyBinds.checkKeybindings(player);
    }

    private static void checkKeybindings(PlayerEntity player)
    {
        if (infoCard.isDown())
        {
            if (Minecraft.getInstance().screen != null)
                return;
            PacketHandler.sendToServer(new COpenPlayerScreenPacket());
        }
        if (zanpakutoStateNext.isDown())
        {
            if (player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
            {
                ZanpakutoItem zanpakuto = (ZanpakutoItem) player.getMainHandItem().getItem();
                switch (zanpakuto.getZanpakutoState())
                {
                    case SEALED:
                        zanpakuto.setZanpakutoState(ModResources.STATE.SHIKAI);
                        PacketHandler.sendToServer(new CChangeZanpakutoStatePacket(ModResources.STATE.SHIKAI));
                        break;
                    case SHIKAI:
                        zanpakuto.setZanpakutoState(ModResources.STATE.BANKAI);
                        PacketHandler.sendToServer(new CChangeZanpakutoStatePacket(ModResources.STATE.BANKAI));
                        break;
                    case BANKAI:
                        zanpakuto.setZanpakutoState(ModResources.STATE.SEALED);
                        PacketHandler.sendToServer(new CChangeZanpakutoStatePacket(ModResources.STATE.SEALED));
                        break;
                }
                ZanpakutoItem zanpakutoChange = (ZanpakutoItem) player.getMainHandItem().getItem();
                player.sendMessage(new TranslationTextComponent("Your zanpakuto entered the " + zanpakutoChange.getZanpakutoState().toString().toLowerCase() + " stage"), Util.NIL_UUID);
                Event zanpakutoChangeEvent = new ZanpakutoChangeEvent(player, zanpakuto);
                MinecraftForge.EVENT_BUS.post(zanpakutoChangeEvent);
            }
        }
    }
}
