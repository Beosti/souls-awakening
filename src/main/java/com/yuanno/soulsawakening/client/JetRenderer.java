package com.yuanno.soulsawakening.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.JetEntity;
import com.yuanno.soulsawakening.models.JetModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class JetRenderer extends MobRenderer<JetEntity, JetModel<JetEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "");

    public JetRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new JetModel(), 1f);
    }

    @Override
    public void render(JetEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight)
    {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public static class Factory implements IRenderFactory<JetEntity>
    {
        @Override
        public EntityRenderer<? super JetEntity> createRenderFor(EntityRendererManager manager)
        {
            return new JetRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(JetEntity entity)
    {
        return TEXTURE;
    }
}
