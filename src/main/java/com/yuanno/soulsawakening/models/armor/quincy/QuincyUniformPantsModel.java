package com.yuanno.soulsawakening.models.armor.quincy;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class QuincyUniformPantsModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer quincyuniform;
	private final ModelRenderer quincyuniformleftleg;
	private final ModelRenderer quincyuniformrightleg;

	public QuincyUniformPantsModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;
		
		quincyuniform = new ModelRenderer(this);
		quincyuniform.setPos(0.0F, 24.0F, 0.0F);
		

		quincyuniformleftleg = new ModelRenderer(this);
		quincyuniformleftleg.setPos(1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformleftleg);
		quincyuniformleftleg.texOffs(59, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.35F, false);

		quincyuniformrightleg = new ModelRenderer(this);
		quincyuniformrightleg.setPos(-1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformrightleg);
		quincyuniformrightleg.texOffs(77, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.35F, false);

	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.quincyuniformleftleg.copyFrom(this.leftLeg);
		this.quincyuniformleftleg.render(matrixStack, buffer, packedLight, packedOverlay);
		this.quincyuniformrightleg.copyFrom(this.rightLeg);
		this.quincyuniformrightleg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}