package com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.FlyingEntity;
import com.yuanno.soulsawakening.models.hollow.FlyingModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FlyingRenderer extends MobRenderer<FlyingEntity, FlyingModel<FlyingEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/flyingtexture.png");

    public FlyingRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new FlyingModel(), 1f);
    }

    @Override
    public void render(FlyingEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<FlyingEntity>
    {
        @Override
        public EntityRenderer<? super FlyingEntity> createRenderFor(EntityRendererManager manager)
        {
            return new FlyingRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingEntity entity)
    {
        return TEXTURE;
    }
}
