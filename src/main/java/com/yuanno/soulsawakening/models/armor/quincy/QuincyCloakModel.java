package com.yuanno.soulsawakening.models.armor.quincy;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class QuincyCloakModel <T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer quincycloak;
	private final ModelRenderer goldbuttonchain;
	private final ModelRenderer leftshoulder;
	private final ModelRenderer rightshoulder;

	public QuincyCloakModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;


		quincycloak = new ModelRenderer(this);
		quincycloak.setPos(0.0F, 1.0F, 0.0F);
		quincycloak.texOffs(94, 0).addBox(-5.0F, -2.0F, -2.5F, 10.0F, 7.0F, 5.0F, 0.3F, false);

		goldbuttonchain = new ModelRenderer(this);
		goldbuttonchain.setPos(0.0F, -1.75F, -2.8F);
		quincycloak.addChild(goldbuttonchain);
		setRotationAngle(goldbuttonchain, -0.2182F, 0.0F, 0.0F);
		goldbuttonchain.texOffs(95, 25).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, false);

		leftshoulder = new ModelRenderer(this);
		leftshoulder.setPos(4.75F, -0.9F, 0.0F);
		quincycloak.addChild(leftshoulder);
		setRotationAngle(leftshoulder, 0.0F, 0.0F, 0.0873F);
		leftshoulder.texOffs(90, 13).addBox(0.0F, -1.0F, -2.5F, 4.0F, 6.0F, 5.0F, 0.3F, false);

		rightshoulder = new ModelRenderer(this);
		rightshoulder.setPos(-4.75F, -0.9F, 0.0F);
		quincycloak.addChild(rightshoulder);
		setRotationAngle(rightshoulder, 0.0F, 0.0F, -0.0873F);
		rightshoulder.texOffs(110, 13).addBox(-4.0F, -1.0F, -2.5F, 4.0F, 6.0F, 5.0F, 0.3F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		quincycloak.copyFrom(this.body);
		quincycloak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}

