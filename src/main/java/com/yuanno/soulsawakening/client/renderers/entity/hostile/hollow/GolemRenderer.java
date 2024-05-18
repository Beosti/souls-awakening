package com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.GolemEntity;
import com.yuanno.soulsawakening.models.hollow.GolemModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class GolemRenderer extends MobRenderer<GolemEntity, GolemModel<GolemEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/golemtexture.png");

    public GolemRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new GolemModel(), 1f);
    }

    @Override
    public void render(GolemEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<GolemEntity>
    {
        @Override
        public EntityRenderer<? super GolemEntity> createRenderFor(EntityRendererManager manager)
        {
            return new GolemRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(GolemEntity entity)
    {
        return TEXTURE;
    }
}
