package com.yuanno.soulsawakening.client.renderers.entity.summons.shadow;

import com.yuanno.soulsawakening.client.renderers.HumanoidRenderer;
import com.yuanno.soulsawakening.entities.summons.shadowsummons.ShadowCloneEntity;
import com.yuanno.soulsawakening.models.HumanoidModel;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ShadowCloneRenderer extends HumanoidRenderer<ShadowCloneEntity, BipedModel<ShadowCloneEntity>> {

    public ShadowCloneRenderer(EntityRendererManager manager)
    {
        super(manager, new HumanoidModel());
    }

    @Override
    public ResourceLocation getTextureLocation(ShadowCloneEntity entity)
    {
        if(entity.getOwnerUUID() == null) {
            return DefaultPlayerSkin.getDefaultSkin(entity.getUUID());
        }
        PlayerEntity player = entity.level.getPlayerByUUID(entity.getOwnerUUID());
        if(player != null)
            return ((AbstractClientPlayerEntity) player).getSkinTextureLocation();
        else
            return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
    }

    public static class Factory implements IRenderFactory<ShadowCloneEntity>
    {
        public Factory() {}

        @Override
        public EntityRenderer<? super ShadowCloneEntity> createRenderFor(EntityRendererManager manager)
        {
            return new ShadowCloneRenderer(manager);
        }
    }
}
     