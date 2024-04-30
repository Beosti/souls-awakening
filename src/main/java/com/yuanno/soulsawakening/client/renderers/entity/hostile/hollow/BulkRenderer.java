package com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.BulkEntity;
import com.yuanno.soulsawakening.models.hollow.BulkModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BulkRenderer extends MobRenderer<BulkEntity, BulkModel<BulkEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "");

    public BulkRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new BulkModel(), 1f);
    }

    @Override
    public void render(BulkEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<BulkEntity>
    {
        @Override
        public EntityRenderer<? super BulkEntity> createRenderFor(EntityRendererManager manager)
        {
            return new BulkRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(BulkEntity entity)
    {
        return TEXTURE;
    }
}
