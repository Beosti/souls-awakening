package com.yuanno.soulsawakening.client.renderers.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.hollow.BeastEntity;
import com.yuanno.soulsawakening.models.hollow.BeastModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BeastRenderer extends MobRenderer<BeastEntity, BeastModel<BeastEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "");

    public BeastRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new BeastModel(), 3f);
    }

    @Override
    public void render(BeastEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<BeastEntity>
    {
        @Override
        public EntityRenderer<? super BeastEntity> createRenderFor(EntityRendererManager manager)
        {
            return new BeastRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(BeastEntity entity)
    {
        return TEXTURE;
    }
}
