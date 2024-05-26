package com.yuanno.soulsawakening.models.armor.quincy;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class QuincyUniformBootsModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer quincyuniform;
	private final ModelRenderer quincyuniformleftboot;
	private final ModelRenderer quincyuniformrightboot;

	public QuincyUniformBootsModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;
		
		quincyuniform = new ModelRenderer(this);
		quincyuniform.setPos(0.0F, 24.0F, 0.0F);

		quincyuniformleftboot = new ModelRenderer(this);
		quincyuniformleftboot.setPos(1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformleftboot);
		quincyuniformleftboot.texOffs(59, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);

		quincyuniformrightboot = new ModelRenderer(this);
		quincyuniformrightboot.setPos(-1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformrightboot);
		quincyuniformrightboot.texOffs(77, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.quincyuniformleftboot.copyFrom(this.leftLeg);
		this.quincyuniformleftboot.render(matrixStack, buffer, packedLight, packedOverlay);
		this.quincyuniformrightboot.copyFrom(this.rightLeg);
		this.quincyuniformrightboot.render(matrixStack, buffer, packedLight, packedOverlay);

	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}