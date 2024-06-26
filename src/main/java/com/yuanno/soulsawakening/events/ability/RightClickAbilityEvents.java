package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;

import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CRightClickEmptyPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

/**
 * Here all the event abilities handled.
 * For the abilities that are on-hit {@link IAttackAbility}
 * Everything happens in the {@link AttackAbilityEvents}, when the player attacks the entity and it has {@link IAttackAbility} it does what it has to do
 * Possibly could add cooldown and other stuff to those type of abilities
 *
 * For the abilities with right-clicking {@link IRightClickAbility}
 * They first get funneled through vanilla events to a custom event and there all the logic is handled.
 * The interaction is there because if not there to check, it'll fire multiple events at the same time and we don't want that tl;dr makes it harder and more complicated
 *
 * An empty right click is only client side that's why you funnel it a server side custom event that then goes back to where the logic is handled
 * {@link #onRightClickItem(PlayerInteractEvent.RightClickItem)} -> {@link CRightClickEmptyPacket} -> check if not interacting with an entity -> {@link CustomInteractionEvent} -> {@link #customRightClickLogic(CustomInteractionEvent)}
 * For the right click with items just standard server side check and funnels to the custom event, so all the logic can be handled at one place
 * {@link #onRightClickItem(PlayerInteractEvent.RightClickItem)} -> {@link #customRightClickLogic(CustomInteractionEvent)}
 */
@Mod.EventBusSubscriber(modid = Main.MODID)
public class RightClickAbilityEvents {
    @SubscribeEvent
    public static void onRightClickEmpty(PlayerInteractEvent.RightClickEmpty event)
    {
        if (!event.getHand().equals(Hand.MAIN_HAND))
            return;
        PacketHandler.sendToServer(new CRightClickEmptyPacket());
    }

    // TODO when using a targeted ability to entity you also use self abilities fix that

    @SubscribeEvent
    public static void onRightClickInteraction(PlayerInteractEvent.EntityInteract event)
    {
        if (event.getPlayer().level.isClientSide)
            return;
        PlayerEntity player = event.getPlayer();
        if (!event.getHand().equals(Hand.MAIN_HAND))
            return;
        Entity entity = event.getTarget();
        if (!(entity instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) entity;
        double distance = player.distanceTo(livingEntity);
        CustomInteractionEvent customInteractionEvent = new CustomInteractionEvent(player, true, distance);
        MinecraftForge.EVENT_BUS.post(customInteractionEvent);
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event)
    {
        if (event.getPlayer().level.isClientSide)
            return;
        PlayerEntity player = event.getPlayer();
        if (!event.getHand().equals(Hand.MAIN_HAND))
            return;
        CustomInteractionEvent customInteractionEvent = new CustomInteractionEvent(player);
        MinecraftForge.EVENT_BUS.post(customInteractionEvent);
    }

    /**
     * method that handles the right click abilities {@link IRightClickAbility}
     * 1. Sorts and give priority for the entity interacting stuff {@link IEntityRayTrace}
     * 2. Menial checks, tl;dr ability ready, if shikai ability need shikai, actually a right click ability
     * 3. Logic handled of abilities
     * 4. Set on cooldown
     * @param event custom event where the other right click ability events funnel through to handle all the logic at one place
     */
    @SubscribeEvent
    public static void customRightClickLogic(CustomInteractionEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ArrayList<Ability> unlockedAbilities = (ArrayList<Ability>) abilityData.getUnlockedAbilities();
        unlockedAbilities.sort((ability1, ability2) -> {
            if (ability1 instanceof IEntityRayTrace && ability2 instanceof IEntityRayTrace) {
                return 0;
            } else if (ability1 instanceof IEntityRayTrace) {
                return -1;
            } else if (ability2 instanceof IEntityRayTrace) {
                return 1;
            } else {
                return 0;
            }
        });
        for (int i = 0; i < unlockedAbilities.size(); i++) {
            Ability ability = unlockedAbilities.get(i);
            if (!ability.getDependency().check(player))
                continue;
            if (!(ability instanceof IRightClickAbility)) // check if the ability is a right click ability
                continue;

            IRightClickAbility rightClickEmptyAbility = (IRightClickAbility) abilityData.getUnlockedAbilities().get(i);
            if (rightClickEmptyAbility.getAlt() ^ InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_ALT)) {
                continue;
            }
            if (rightClickEmptyAbility.getControl() ^ InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL)) {
                continue;
            }
            if (player.isCrouching() ^ rightClickEmptyAbility.getShift())
                continue;
            if ((ability instanceof IEntityRayTrace && !(((IEntityRayTrace) ability).gotTarget(player))))
                continue;
            if ((ability instanceof IEntityRayTrace) && event.getDistance() > (((IEntityRayTrace) ability).getDistance()))
                continue;
            if (!(ability instanceof IContinuousAbility)) // this is mostly there so the continuous logic can be handled by the ability itself to decide when it stops
            {
                AbilityUseEvent.Pre abilityUseEventPre = new AbilityUseEvent.Pre(player, ability);
                MinecraftForge.EVENT_BUS.post(abilityUseEventPre);
            }
            else if (!ability.getState().equals(Ability.STATE.CONTINUOUS) && ability.getState().equals(Ability.STATE.READY))
            {
                ((IContinuousAbility) ability).startContinuity(player, ability);
                return;
            }
            else
            {
                ((IContinuousAbility) ability).endContinuity(player, ability);
                return;
            }
            AbilityUseEvent.Per abilityUseEventPer = new AbilityUseEvent.Per(player, ability);
            if (!(ability instanceof IReleaseArrow))
                MinecraftForge.EVENT_BUS.post(abilityUseEventPer);
            AbilityUseEvent.Post abilityUseEventPost;
            if (ability instanceof IEntityRayTrace)
                abilityUseEventPost = new AbilityUseEvent.Post(player, ability, ((IEntityRayTrace) ability).getLivingEntity(player));
            else
                abilityUseEventPost = new AbilityUseEvent.Post(player, ability);
            if (!(ability instanceof IContinuousAbility)) // this is mostly there so the continuous logic can be handled by the ability itself to decide when it stops
            {
                MinecraftForge.EVENT_BUS.post(abilityUseEventPost);
            }
        }
    }
}
