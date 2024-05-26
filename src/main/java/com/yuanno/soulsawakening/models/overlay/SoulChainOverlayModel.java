package com.yuanno.soulsawakening.models.overlay;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class SoulChainOverlayModel extends EntityModel<Entity> {
	private final ModelRenderer soulchain;
	private final ModelRenderer chainsection1;
	private final ModelRenderer chainsection2;
	private final ModelRenderer chainsection3;

	public SoulChainOverlayModel() {
        texWidth = 128;
		texHeight = 128;

		soulchain = new ModelRenderer(this);
		soulchain.setPos(0.0F, 3.0F, -2.0F);
		setRotationAngle(soulchain, 0.0F, 0.0F, 0.7854F);
		soulchain.texOffs(121, 12).addBox(-1.0F, -1.0F, -0.15F, 2.0F, 2.0F, 1.0F, 0.3F, false);

		chainsection1 = new ModelRenderer(this);
		chainsection1.setPos(0.0F, 0.0F, -0.45F);
		soulchain.addChild(chainsection1);
		setRotationAngle(chainsection1, 0.0F, 0.1745F, 0.7854F);
		chainsection1.texOffs(121, 0).addBox(0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);

		chainsection2 = new ModelRenderer(this);
		chainsection2.setPos(2.5F, 0.0F, 0.0F);
		chainsection1.addChild(chainsection2);
		setRotationAngle(chainsection2, 0.0F, -0.0873F, 0.0F);
		chainsection2.texOffs(117, 4).addBox(0.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, 0.0F, false);

		chainsection3 = new ModelRenderer(this);
		chainsection3.setPos(3.5F, 0.0F, 0.0F);
		chainsection2.addChild(chainsection3);
		setRotationAngle(chainsection3, 0.0F, -0.0436F, 0.0F);
		chainsection3.texOffs(121, 8).addBox(0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float v, float v1, float v2, float v3, float v4) {

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.soulchain.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}