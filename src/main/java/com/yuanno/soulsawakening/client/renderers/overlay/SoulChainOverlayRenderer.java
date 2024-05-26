package com.yuanno.soulsawakening.client.renderers.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModTags;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.models.overlay.SoulChainOverlayModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoulChainOverlayRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final SoulChainOverlayModel MODEL = new SoulChainOverlayModel();
    private static final ResourceLocation SOUL_TEXTURE = new ResourceLocation(Main.MODID, "textures/overlay/soulchain_overlay_texture.png");

    public SoulChainOverlayRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }


    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (!(entity instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) entity;
        if (EntityStatsCapability.get(player).getRace().equals(ModValues.SPIRIT))
        {
            //this.getParentModel().copyPropertiesTo(MODEL);
            MODEL.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            MODEL.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            MODEL.renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityTranslucent(SOUL_TEXTURE)), packedLight, LivingRenderer.getOverlayCoords(entity, 0.0F), 1f, 1f, 1f, 1f);

        }
    }
}
