package com.yuanno.soulsawakening.entity.hollow;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class HollowEntity extends CreatureEntity {
    String element;
    protected HollowEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
    }

    @Override
    public void die(DamageSource source)
    {
        super.die(source);

        if (!(source.getEntity() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) source.getEntity();
        if (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem))
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !entityStats.getRace().equals(ModValues.FULLBRINGER))
            return;
        ItemStack zanpakutoItem = player.getMainHandItem();
        int chancePercentage = 5;

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        if (randomNumber <= chancePercentage) {
            addPoint(zanpakutoItem, this.element);
        }
    }

    void addPoint(ItemStack zanpakutoItem, String element)
    {
        CompoundNBT tagCompound = zanpakutoItem.getTag();
        int elementalPoints = tagCompound.getInt("element");
        if (elementalPoints >= 5)
            return;
        int thunderPoint = tagCompound.getInt(element);
        tagCompound.putInt(element, thunderPoint + 1);
        tagCompound.putInt("element", elementalPoints + 1);
        zanpakutoItem.setTag(tagCompound);
    }
}
