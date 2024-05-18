package com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.ApeEntity;
import com.yuanno.soulsawakening.models.hollow.ApeModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ApeRenderer extends MobRenderer<ApeEntity, ApeModel<ApeEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/apetexture.png");

    public ApeRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new ApeModel(), 3f);
    }

    @Override
    public void render(ApeEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<ApeEntity>
    {
        @Override
        public EntityRenderer<? super ApeEntity> createRenderFor(EntityRendererManager manager)
        {
            return new ApeRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ApeEntity entity)
    {
        return TEXTURE;
    }
}
