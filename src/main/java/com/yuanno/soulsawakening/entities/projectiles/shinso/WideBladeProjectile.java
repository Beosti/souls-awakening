package com.yuanno.soulsawakening.entities.projectiles.shinso;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WideBladeProjectile extends AbilityProjectileEntity {

    public WideBladeProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public WideBladeProjectile(World world, LivingEntity player)
    {
        super(ShinsoProjectiles.WIDE_BLADE.get(), world, player);
        this.setDamage(18);
        this.setMaxLife(100);
        this.setPhysical(true);
        this.setPassThroughBlocks();
        this.onBlockImpactEvent = this::onHitBlock;
    }

    private void onHitBlock(BlockPos hit)
    {
        World world = this.level;
        world.setBlock(hit, Blocks.AIR.defaultBlockState(), 2);
    }
}
