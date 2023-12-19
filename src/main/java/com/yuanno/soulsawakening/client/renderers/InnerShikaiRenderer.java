package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.InnerShikaiEntity;
import com.yuanno.soulsawakening.models.HumanoidModel;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unchecked")
public class InnerShikaiRenderer extends MobRenderer<InnerShikaiEntity, HumanoidModel<InnerShikaiEntity>> {



    public InnerShikaiRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(InnerShikaiEntity InnerShikaiEntity) {
        if(InnerShikaiEntity.getOwnerUUID() == null) {
            return DefaultPlayerSkin.getDefaultSkin(InnerShikaiEntity.getUUID());
        }
        PlayerEntity player = InnerShikaiEntity.level.getPlayerByUUID(InnerShikaiEntity.getOwnerUUID());
        if(player != null)
            return ((AbstractClientPlayerEntity) player).getSkinTextureLocation();
        else
            return DefaultPlayerSkin.getDefaultSkin(InnerShikaiEntity.getOwnerUUID());
    }

    public static class Factory implements IRenderFactory<InnerShikaiEntity> {

        @Override
        public EntityRenderer<? super InnerShikaiEntity> createRenderFor(EntityRendererManager manager) {
            return new InnerShikaiRenderer(manager);
        }
    }
}
