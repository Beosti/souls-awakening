package com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.SpiderEntity;
import com.yuanno.soulsawakening.models.hollow.SpiderModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SpiderRenderer extends MobRenderer<SpiderEntity, SpiderModel<SpiderEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/spidertexture.png");

    public SpiderRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new SpiderModel(), 1f);
    }

    @Override
    public void render(SpiderEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<SpiderEntity>
    {
        @Override
        public EntityRenderer<? super SpiderEntity> createRenderFor(EntityRendererManager manager)
        {
            return new SpiderRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderEntity entity)
    {
        return TEXTURE;
    }
}
