package com.yuanno.soulsawakening.client.renderers.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.hollow.CentipedeEntity;
import com.yuanno.soulsawakening.models.hollow.CentipedeModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class CentipedeRenderer extends MobRenderer<CentipedeEntity, CentipedeModel<CentipedeEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "");

    public CentipedeRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new CentipedeModel(), 1f);
    }

    @Override
    public void render(CentipedeEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<CentipedeEntity>
    {
        @Override
        public EntityRenderer<? super CentipedeEntity> createRenderFor(EntityRendererManager manager)
        {
            return new CentipedeRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(CentipedeEntity entity)
    {
        return TEXTURE;
    }
}
