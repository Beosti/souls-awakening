package com.yuanno.soulsawakening.client.renderers.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZanpakutoOverlayRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    public ZanpakutoOverlayRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (!(entitylivingbaseIn instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
        IMiscData miscData = MiscDataCapability.get(player);
        if (!miscData.getRenderZanpakutoOverlay())
            return;
        if (player.inventory.contains(ModTags.Items.ZANPAKUTO) && !player.getMainHandItem().getItem().is(ModTags.Items.ZANPAKUTO) && !player.getOffhandItem().getItem().is(ModTags.Items.ZANPAKUTO))
        {
            ItemStack itemStack = new ItemStack(ModItems.ZANPAKUTO.get());
            for (int i = 0; i < player.inventory.getContainerSize(); i++)
            {
                if (player.inventory.getItem(i).getItem().is(ModTags.Items.ZANPAKUTO))
                    itemStack = player.inventory.getItem(i);
            }
            if (miscData.getZanpakutoStyle().equals("basic"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0, 0.6, 0.2);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("basic_right"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0, 0.6, -0.2);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_horizontal"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(0F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0.1, 0.45, 0.14);
                matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(-45));
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_horizontal_right"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0.1, 0.45, -0.1);
                matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(-45F));
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_horizontal_diagonal_left"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0.1, 0.45, 0.14);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_horizontal_diagonal_right"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0.1, 0.45, -0.1);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_vertical"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(0F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0, 0.30, 0.14);
                matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(45));
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_vertical_right"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0, 0.45, -0.1);
                matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(45));
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_vertical_diagonal_left"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0.1, 0.15, 0.14);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
            else if (miscData.getZanpakutoStyle().equals("back_vertical_diagonal_right"))
            {
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180F));
                matrixStackIn.scale(1.4f, 1.4f, 1.4f);
                matrixStackIn.translate(0.1, 0.15, -0.1);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
        }
    }
}
