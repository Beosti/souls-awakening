package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.client.screen.VisualOptionScreen;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CChangeZanpakutoStatePacket;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.client.CSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.client.CUseSpellPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ModKeyBinds {

    public static KeyBinding infoCard;
    public static KeyBinding zanpakutoStateNext;
    public static KeyBinding useSpell;
    public static KeyBinding config;
    public static void init()
    {
        infoCard = new KeyBinding("keys.soulsawakening.info_card", GLFW.GLFW_KEY_T, "keys.soulsawakening.gui");
        ClientRegistry.registerKeyBinding(infoCard);
        zanpakutoStateNext = new KeyBinding("keys.soulsawakening.zanpakuto_state", GLFW.GLFW_KEY_V, "keys.soulsawakening.combat");
        ClientRegistry.registerKeyBinding(zanpakutoStateNext);
        useSpell = new KeyBinding("keys.soulsawakening.use_spell", GLFW.GLFW_KEY_R, "keys.soulsawakening.combat");
        ClientRegistry.registerKeyBinding(useSpell);
        config = new KeyBinding("keys.soulsawakening.config", GLFW.GLFW_KEY_B, "keys.soulsawakening.gui");
        ClientRegistry.registerKeyBinding(config);

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
        if (useSpell.consumeClick())
        {
            IAbilityData abilityData = AbilityDataCapability.get(player);
            if (!abilityData.getAbilitiesInBar().isEmpty())
                PacketHandler.sendToServer(new CUseSpellPacket(abilityData.getSelectionAbility()));
        }
        if (config.isDown())
        {
            if(Minecraft.getInstance().screen != null)
                return;
            VisualOptionScreen.open();
        }
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollEvent event)
    {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerEntity player = minecraft.player;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        if (abilityData.getAbilitiesInBar().isEmpty())
            return;
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_CONTROL)) {
            event.setCanceled(true);
            ModKeyBinds.checkKeybindingsScrollingKido(player, event.getScrollDelta());
        }
    }

    private static void checkKeybindingsScrollingKido(PlayerEntity player, double deltaScrolling)
    {
        IAbilityData abilityDataProps = AbilityDataCapability.get(player);
        int newSelected = (int) (abilityDataProps.getSelectionAbility() + deltaScrolling);
        if (deltaScrolling == 1 && newSelected >= abilityDataProps.getAbilitiesInBar().size()) {
            newSelected = 0;
        }
        else if (deltaScrolling == -1 && newSelected < 0)
        {

            newSelected = abilityDataProps.getAbilitiesInBar().size() - 1;
        }
        abilityDataProps.setSelectedAbility(newSelected);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityDataProps));

    }
}
