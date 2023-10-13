package com.yuanno.soulsawakening.items.blueprints;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModItemGroup;
import com.yuanno.soulsawakening.init.ModTiers;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZanpakutoItem extends SwordItem {
    private ELEMENT zanpakutoElement = ELEMENT.NONE;
    private STATE zanpakutoState = STATE.SEALED;
    private TYPE zanpakutoType;
    private ItemStack stack;

    List<Ability> abilities = new ArrayList<Ability>();
    public ZanpakutoItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().rarity(Rarity.RARE).tab(ModItemGroup.SOULS_AWAKENINGS_WEAPONS).stacksTo(1));
        this.zanpakutoState = STATE.SEALED;
        //this.zanpakutoElement = ELEMENT.getRandomElement();
        //this.zanpakutoType = TYPE.getRandomType();
        //FireAttackAbility fireAttackAbility = new FireAttackAbility();
        //abilities.add(fireAttackAbility);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        // Store the item stack
        this.stack = stack;

    }
    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity owner) {
        String currentOwner = itemStack.getOrCreateTag().getString("owner");
        if (!currentOwner.isEmpty()) {
            super.hurtEnemy(itemStack, target, owner);
            return true;
        }
        else
            return false;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        if (player.level.isClientSide)
            return ActionResult.fail(player.getItemInHand(hand));

        ItemStack itemStack = player.getItemInHand(hand);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!itemStack.hasTag())
            itemStack.setTag(new CompoundNBT());

        String currentOwner = itemStack.getOrCreateTag().getString("owner");
        if (currentOwner.isEmpty()) {
            itemStack.getTag().putString("owner", player.getDisplayName().getString());
            itemStack.getTag().putString("zanpakutoElement", ELEMENT.getRandomElement().name());
            itemStack.getTag().putString("zanpakutoType", TYPE.getRandomType().name());
            itemStack.getTag().putString("zanpakutoState", STATE.SEALED.name());
            if (entityStats.getRace().equals(ModValues.SPIRIT)) {
                entityStats.setRace(ModValues.SHINIGAMI);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                return ActionResult.success(itemStack);
            }
        }
        else if (!currentOwner.equals(player.getDisplayName().getString()) || !entityStats.getRace().equals(ModValues.SHINIGAMI))
            return ActionResult.fail(itemStack);

        return ActionResult.success(itemStack);
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

    public enum TYPE {
        TYPE_1, TYPE_2;

        public static TYPE getRandomType()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum ELEMENT {
        NONE, DARK, FIRE, HEAL, LIGHTNING, LUNAR, NORMAL, POISON, WATER, WIND;

        public static ELEMENT getRandomElement()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
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
    public ELEMENT getZanpakutoElement() {
        String elementName = stack.getTag().getString("zanpakutoElement");
        return ELEMENT.valueOf(elementName);
    }

    public TYPE getZanpakutoType() {
        String typeName = stack.getTag().getString("zanpakutoType");
        return TYPE.valueOf(typeName);
    }

    public STATE getZanpakutoState() {
        if (stack != null) {
            String stateName = stack.getTag().getString("zanpakutoState");

            // Handle cases where the state name is invalid or not present
            try {
                return STATE.valueOf(stateName);
            } catch (IllegalArgumentException e) {
                // Log the error or handle it accordingly
                // For now, we'll return SEALED in case of an invalid state
                return STATE.SEALED;
            }
        } else {
            return STATE.SEALED;
        }
    }

    public void setZanpakutoElement(ELEMENT element) {
        stack.getTag().putString("zanpakutoElement", element.name());
    }

    public void setZanpakutoType(TYPE type) {
        stack.getTag().putString("zanpakutoType", type.name());
    }

    public void setZanpakutoState(STATE state) {
        stack.getTag().putString("zanpakutoState", state.name());
    }

    public List<Ability> getAbilities() {
        return this.abilities;
    }
}
