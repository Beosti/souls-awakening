package com.yuanno.soulsawakening.models.armor.ichigorogue;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class RogueCapeModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer cloak;
	private final ModelRenderer leftshoulder;
	private final ModelRenderer rightshoulder;
	private final ModelRenderer cape;

	public RogueCapeModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;

		cloak = new ModelRenderer(this);
		cloak.setPos(0.0F, 1.0F, 0.0F);
		cloak.texOffs(94, 8).addBox(-5.0F, -2.0F, -2.5F, 10.0F, 4.0F, 5.0F, 0.0F, false);

		leftshoulder = new ModelRenderer(this);
		leftshoulder.setPos(4.75F, -0.9F, 0.0F);
		cloak.addChild(leftshoulder);
		setRotationAngle(leftshoulder, 0.0F, 0.0F, 0.0873F);
		leftshoulder.texOffs(90, 0).addBox(0.0F, -1.0F, -2.5F, 4.0F, 2.0F, 5.0F, 0.0F, false);

		rightshoulder = new ModelRenderer(this);
		rightshoulder.setPos(-4.75F, -0.9F, 0.0F);
		cloak.addChild(rightshoulder);
		setRotationAngle(rightshoulder, 0.0F, 0.0F, -0.0873F);
		rightshoulder.texOffs(110, 0).addBox(-4.0F, -1.0F, -2.5F, 4.0F, 2.0F, 5.0F, 0.0F, true);

		cape = new ModelRenderer(this);
		cape.setPos(0.0F, 1.75F, 2.45F);
		cloak.addChild(cape);
		cape.texOffs(95, 18).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 17.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.cloak.copyFrom(this.body);
		this.cloak.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}