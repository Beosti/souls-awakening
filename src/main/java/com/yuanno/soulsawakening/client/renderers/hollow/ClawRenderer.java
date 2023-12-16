package com.yuanno.soulsawakening.client.renderers.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.hollow.ClawEntity;
import com.yuanno.soulsawakening.models.hollow.ClawModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClawRenderer extends MobRenderer<ClawEntity, ClawModel<ClawEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "");

    public ClawRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new ClawModel(), 1f);
    }

    @Override
    public void render(ClawEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<ClawEntity>
    {
        @Override
        public EntityRenderer<? super ClawEntity> createRenderFor(EntityRendererManager manager)
        {
            return new ClawRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClawEntity entity)
    {
        return TEXTURE;
    }
}
