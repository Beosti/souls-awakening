package com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.ThornsEntity;
import com.yuanno.soulsawakening.models.hollow.ThornsModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ThornsRenderer extends MobRenderer<ThornsEntity, ThornsModel<ThornsEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entities/hostile/thornstexture.png");

    public ThornsRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new ThornsModel(), 1f);
    }

    @Override
    public void render(ThornsEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<ThornsEntity>
    {
        @Override
        public EntityRenderer<? super ThornsEntity> createRenderFor(EntityRendererManager manager)
        {
            return new ThornsRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ThornsEntity entity)
    {
        return TEXTURE;
    }
}
