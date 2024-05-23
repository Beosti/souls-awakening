package com.yuanno.soulsawakening.events.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class CustomArrowNockEvent extends PlayerEvent {
    private final ItemStack bow;
    private final Hand hand;
    private final World world;
    private ActionResult<ItemStack> action;

    public CustomArrowNockEvent(PlayerEntity player, @Nonnull ItemStack item, Hand hand, World world) {
        super(player);
        this.bow = item;
        this.hand = hand;
        this.world = world;
    }

    @Nonnull
    public ItemStack getBow() {
        return this.bow;
    }

    public World getWorld() {
        return this.world;
    }

    public Hand getHand() {
        return this.hand;
    }


    public ActionResult<ItemStack> getAction() {
        return this.action;
    }

    public void setAction(ActionResult<ItemStack> action) {
        this.action = action;
    }
}