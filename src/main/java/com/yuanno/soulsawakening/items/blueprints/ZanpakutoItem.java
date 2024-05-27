package com.yuanno.soulsawakening.items.blueprints;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.entity.shinigami.ShinigamiStats;
import com.yuanno.soulsawakening.events.projectiles.AbilityProjectileHurtEvent;
import com.yuanno.soulsawakening.init.*;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;

public class ZanpakutoItem extends SwordItem {

    public ZanpakutoItem() {
        super(ModTiers.WEAPON, 5, -2.55f, new Item.Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
    }

    public ZanpakutoItem(int i, float v) {
        super(ModTiers.WEAPON, i, v, new Item.Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));

    }

    /**
     * You can cancel projectiles made from reishi as long as your zanjutsu is 2 x theirs
     * @param event
     */
    @SubscribeEvent
    public static void onAttackSpellProjectile(AbilityProjectileHurtEvent event)
    {
        Entity entity = event.getAttacker();
        if (!(entity instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) entity;
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI))
            return;
        ItemStack itemStack = player.getMainHandItem();
        if (!(itemStack.getItem().asItem() instanceof ZanpakutoItem))
            return;
        if (!(event.getProjectile() instanceof AbilityProjectileEntity))
            return;
        AbilityProjectileEntity abilityProjectileEntity = (AbilityProjectileEntity) event.getProjectile();
        ShinigamiStats shinigamiStats = entityStats.getShinigamiStats();
        if (shinigamiStats.getZanjutsuPoints() < abilityProjectileEntity.getDamage() * 2)
            return;
        abilityProjectileEntity.remove();
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown() && stack.getTag().getString("spirit").isEmpty())
        {
            tooltip.add(new TranslationTextComponent("§6A blade made out of spiritual material"));int elementalPoints = stack.getTag().getInt("element");
            tooltip.add(new StringTextComponent("Elemental points: " + elementalPoints));

            int normalPoints = stack.getTag().getInt(ModValues.NORMAL);
            tooltip.add(new StringTextComponent("§7normal: " + normalPoints));
            int poisonPoints = stack.getTag().getInt(ModValues.DARK);
            tooltip.add(new StringTextComponent("§8dark: " + poisonPoints));
            int waterPoints = stack.getTag().getInt(ModValues.WATER);
            tooltip.add(new StringTextComponent("§bwater: " + waterPoints));
            int airPoints = stack.getTag().getInt(ModValues.WIND);
            tooltip.add(new StringTextComponent("§aair: " + airPoints));
            int firePoints = stack.getTag().getInt(ModValues.FIRE);
            tooltip.add(new StringTextComponent("§cfire: " + firePoints));

        }
        else if (!Screen.hasShiftDown() && stack.getTag().getString("spirit").isEmpty())
        {
            tooltip.add(new TranslationTextComponent("§6Hold " + "§eSHIFT " + "§6" + "for more Information!"));
        }
        String state = stack.getTag().getString("zanpakutoState");
        tooltip.add(new TranslationTextComponent("§l§4STATE: " + "§r" + state));

        if (stack.getTag().getString("spirit").isEmpty())
        {
            super.appendHoverText(stack, world, tooltip, flagIn);
            return;
        }
        if (Screen.hasShiftDown() && !stack.getTag().getString("spirit").isEmpty()) {
            tooltip.add(new TranslationTextComponent("§6A rare sword linked by the spirit with the owner of it"));
        }
        else if (!Screen.hasShiftDown() && !stack.getTag().getString("spirit").isEmpty()){
            tooltip.add(new TranslationTextComponent("§6Hold " + "§eSHIFT " + "§6" + "for more Information!"));
        }


        if (stack.getTag().getString("owner").isEmpty()) {
            super.appendHoverText(stack, world, tooltip, flagIn);
            return;
        }
        else
        {
            String currentOwner = stack.getTag().getString("owner");
            tooltip.add(new StringTextComponent("§6Owner: " + currentOwner));
        }
        if (stack.getTag().getInt("element") == 0)
        {
            return;
        }
        else if (Screen.hasAltDown())
        {
            int elementalPoints = stack.getTag().getInt("element");
            tooltip.add(new StringTextComponent("Elemental points: " + elementalPoints));

            int normalPoints = stack.getTag().getInt(ModValues.NORMAL);
            tooltip.add(new StringTextComponent("§7normal: " + normalPoints));
            int poisonPoints = stack.getTag().getInt(ModValues.DARK);
            tooltip.add(new StringTextComponent("§8dark: " + poisonPoints));
            int waterPoints = stack.getTag().getInt(ModValues.WATER);
            tooltip.add(new StringTextComponent("§bwater: " + waterPoints));
            int airPoints = stack.getTag().getInt(ModValues.WIND);
            tooltip.add(new StringTextComponent("§aair: " + airPoints));
            int firePoints = stack.getTag().getInt(ModValues.FIRE);
            tooltip.add(new StringTextComponent("§cfire: " + firePoints));


        }
        else
        {
            tooltip.add(new TranslationTextComponent("§6Hold " + "§fALT " +"§6" + "for information about elemental points!"));

        }


        super.appendHoverText(stack, world, tooltip, flagIn);
    }


    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity owner) {
        String currentOwner = itemStack.getOrCreateTag().getString("owner");
        if (currentOwner.isEmpty())
            return false;
        else {
            itemStack.hurtAndBreak(0, target, (p_220045_0_) -> {
                p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
            return true;
        }
    }
}
