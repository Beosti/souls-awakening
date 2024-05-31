package com.yuanno.soulsawakening.mixins;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.AbilityDisplayInfo;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DisplayInfo.class)
public class DisplayInfoMixin {
    @Shadow
    @Final
    private boolean announceChat;

    @Shadow
    @Final
    private boolean showToast;

    @Inject(method = "fromJson", at = @At("HEAD"), cancellable = true)
    private static void fromJson(JsonObject pObject, CallbackInfoReturnable<DisplayInfo> callback)
    {
        ITextComponent title = ITextComponent.Serializer.fromJson(pObject.get("title"));
        ITextComponent desc = ITextComponent.Serializer.fromJson(pObject.get("description"));
        if (title != null && desc != null)
        {
            JsonObject iconObj = JSONUtils.getAsJsonObject(pObject, "icon");
            if (iconObj.has("ability"))
            {
                Ability icon = getAbility(iconObj);
                ResourceLocation bg = pObject.has("background") ? new ResourceLocation(JSONUtils.getAsString(pObject, "background")) : null;
                FrameType frame = pObject.has("frame") ? FrameType.byName(JSONUtils.getAsString(pObject, "frame")) : FrameType.TASK;
                boolean flag = JSONUtils.getAsBoolean(pObject, "show_toast", true);
                boolean flag1 = JSONUtils.getAsBoolean(pObject, "announce_to_chat", true);
                boolean flag2 = JSONUtils.getAsBoolean(pObject, "hidden", false);
                callback.setReturnValue(new AbilityDisplayInfo(icon, title, desc, bg, frame, flag, flag1, flag2));
            }
        }
        else
        {
            throw new JsonSyntaxException("Both title and description must be set");
        }
    }

    private static Ability getAbility(JsonObject json)
    {
        Ability ability = (Ability) GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(JSONUtils.getAsString(json, "ability")));
        return ability;
    }

    @Inject(method = "fromNetwork", at = @At("HEAD"), cancellable = true)
    private static void fromNetwork(PacketBuffer buf, CallbackInfoReturnable<DisplayInfo> callback)
    {
        int v = buf.readInt();
        if(v == 1)
        {
            ResourceLocation rs = buf.readResourceLocation();
            Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(rs);
            ITextComponent itextcomponent = buf.readComponent();
            ITextComponent itextcomponent1 = buf.readComponent();
            ItemStack itemstack = buf.readItem();
            FrameType frametype = buf.readEnum(FrameType.class);
            int i = buf.readInt();
            ResourceLocation resourcelocation = (i & 1) != 0 ? buf.readResourceLocation() : null;
            boolean flag = (i & 2) != 0;
            boolean flag1 = (i & 4) != 0;
            AbilityDisplayInfo displayinfo = new AbilityDisplayInfo(ability, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, false, flag1);
            displayinfo.setLocation(buf.readFloat(), buf.readFloat());
            callback.setReturnValue(displayinfo);
        }
    }
    @Inject(method = "serializeIcon", at = @At("HEAD"), cancellable = true)
    private void serializeIcon(CallbackInfoReturnable<JsonObject> callback)
    {
        DisplayInfo info = ((DisplayInfo) (Object) this);
        if (info instanceof AbilityDisplayInfo)
        {
            AbilityDisplayInfo ablInfo = (AbilityDisplayInfo) info;
            if (ablInfo.getAbility() != null)
            {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("ability", ablInfo.getAbility().getRegistryName().toString());
                callback.setReturnValue(jsonobject);
            }
        }
    }
}
