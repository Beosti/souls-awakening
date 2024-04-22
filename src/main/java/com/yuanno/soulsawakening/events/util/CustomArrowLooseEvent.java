package com.yuanno.soulsawakening.events.util;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class CustomArrowLooseEvent extends PlayerEvent {
   private final ItemStack bow;
   private final World world;
   private final float power;
   private AbilityProjectileEntity abilityProjectileEntity;

   public CustomArrowLooseEvent(PlayerEntity player, ItemStack bow, World world, float power, AbilityProjectileEntity abilityProjectileEntity)
   {
       super(player);
       this.bow = bow;
       this.world = world;
       this.power = power;
       this.abilityProjectileEntity = abilityProjectileEntity;
   }

   public ItemStack getBow()
   {
       return this.bow;
   }
   public World getWorld()
   {
       return this.world;
   }
   public float getPower()
   {
       return this.power;
   }
   public AbilityProjectileEntity getProjectile()
   {
       return this.abilityProjectileEntity;
   }
}
