package com.yuanno.soulsawakening.achievements.dimensions;

import com.google.gson.JsonObject;
import com.yuanno.soulsawakening.Main;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.ResourceLocation;

public class HuecoMundoTrigger extends AbstractCriterionTrigger<HuecoMundoTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Main.MODID, "hueco_mundo");

    @Override
    public ResourceLocation getId()
    {
        return ID;
    }

    @Override
    public HuecoMundoTrigger.Instance createInstance(JsonObject jsonObject, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser)
    {
        return new HuecoMundoTrigger.Instance(entityPredicate);
    }

    public void trigger(ServerPlayerEntity player)
    {
        this.trigger(player, (instance) ->{
            return true;
        });
    }

    public static class Instance extends CriterionInstance
    {
        public Instance(EntityPredicate.AndPredicate entityPredicate)
        {
            super(HuecoMundoTrigger.ID, entityPredicate);
        }

        @Override
        public JsonObject serializeToJson(ConditionArraySerializer pConditions)
        {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            return jsonobject;
        }
    }
}
