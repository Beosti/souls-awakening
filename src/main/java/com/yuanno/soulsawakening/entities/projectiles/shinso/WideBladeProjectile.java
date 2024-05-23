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
        this.onTickEvent = this::onTick;
    }

    private void onTick()
    {
        World world = this.level;

        // gets the blocks around this entity and turns them into air
        double x0 = this.getX();
        double y0 = this.getY();
        double z0 = this.getZ();

        int radius = 1;

        for (double y = y0 - radius; y <= y0 + radius; y++)
        {
            for (double x = x0 - radius; x <= x0 + radius; x++)
            {
                for (double z = z0 - radius; z <= z0 + radius; z++)
                {
                    BlockPos pos = new BlockPos(x, y, z);
                    world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
    }
}
