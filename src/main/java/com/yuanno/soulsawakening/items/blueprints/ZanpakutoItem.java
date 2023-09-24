package com.yuanno.soulsawakening.items.blueprints;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModTiers;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ZanpakutoItem extends SwordItem {
    private ELEMENT zanpakutoElement = ELEMENT.FIRE;
    private STATE zanpakutoState = STATE.SEALED;
    public ZanpakutoItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }

    @Override
    public boolean hurtEnemy(ItemStack p_77644_1_, LivingEntity target, LivingEntity p_77644_3_) {
        p_77644_1_.hurtAndBreak(1, p_77644_3_, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });

        effectOnEnemy(target);
        return true;
    }

    void effectOnEnemy(LivingEntity livingEntity)
    {
        if (zanpakutoState.equals(STATE.SHIKAI)) // effect on the enemy when in shikai
        {
            switch (zanpakutoElement)
            {
                case FIRE:
                    livingEntity.setSecondsOnFire(3);
                    break;
                case POISON:
                    livingEntity.addEffect(new EffectInstance(Effects.POISON, 60, 0));
                    break;
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!itemStack.hasTag())
            itemStack.setTag(new CompoundNBT());

        String currentOwner = itemStack.getOrCreateTag().getString("owner");
        if (currentOwner.isEmpty()) {
            itemStack.getTag().putString("owner", player.getDisplayName().getString());
            if (entityStats.getRace().equals(ModValues.SPIRIT)) {
                entityStats.setRace(ModValues.SHINIGAMI);
                return ActionResult.success(itemStack);
            }
        }
        else if (!currentOwner.equals(player.getDisplayName().getString()) || !entityStats.getRace().equals(ModValues.SHINIGAMI))
            return ActionResult.fail(itemStack);
        else
            rightClickZanpakuto(world, player);

        return ActionResult.success(itemStack);
    }

    void rightClickZanpakuto(World world, PlayerEntity player)
    {
        if (zanpakutoState.equals(STATE.SHIKAI))
        {
            switch (zanpakutoElement)
            {
                case FIRE:

                    break;
                case POISON:
                    break;
            }
        }
    }

    public void setOwner(PlayerEntity player, ItemStack itemStack)
    {
        if (!itemStack.hasTag())
            itemStack.setTag(new CompoundNBT());
        String currentOwner = itemStack.getOrCreateTag().getString("owner");
        if (currentOwner.isEmpty())
            itemStack.getTag().putString("owner", player.getDisplayName().getString());
        else
            return;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4)
    {
        if (itemStack.getTag().getString("owner").isEmpty())
            return;
        else
        {
            String currentOwner = itemStack.getTag().getString("owner");
            list.add(new StringTextComponent("§4Owner: " + currentOwner));
        }
    }

    public enum ELEMENT {
        POISON, FIRE
    }

    public ZanpakutoItem.ELEMENT getZanpakutoElement()
    {
        return this.zanpakutoElement;
    }

    public enum STATE {
        SEALED, SHIKAI, BANKAI;
        public static STATE getRandomState()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public ZanpakutoItem.STATE getNextZanpakutoState(ZanpakutoItem.STATE currentState) {
        ZanpakutoItem.STATE[] states = ZanpakutoItem.STATE.values();
        int currentIndex = currentState.ordinal();
        int nextIndex = (currentIndex + 1) % states.length;  // Calculate the next index in a circular manner
        return states[nextIndex];
    }
    public ZanpakutoItem.STATE getZanpakutoState() {
        return zanpakutoState;
    }
    public void setZanpakutoState(STATE state)
    {
        this.zanpakutoState = state;
    }
}
