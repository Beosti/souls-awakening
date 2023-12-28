package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.events.zanpakuto.ZanpakutoChangeEvent;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CChangeZanpakutoStatePacket;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import com.yuanno.soulsawakening.screens.PlayerOverviewScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
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
            if (player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem) {
                PacketHandler.sendToServer(new CChangeZanpakutoStatePacket());
            }

        }
    }
}
