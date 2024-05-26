package com.yuanno.soulsawakening.models.armor.shinigami;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class CaptainCapeModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer goteicloak;
	private final ModelRenderer goteicloakbody;
	private final ModelRenderer goteicloakrightarm;
	private final ModelRenderer goteicloakleftarm;
	private final ModelRenderer goteicloakbottom;

	public CaptainCapeModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;

		goteicloak = new ModelRenderer(this);
		goteicloak.setPos(0.0F, 24.0F, 0.0F);
		

		goteicloakbody = new ModelRenderer(this);
		goteicloakbody.setPos(0.0F, -24.0F, 0.0F);
		goteicloak.addChild(goteicloakbody);
		goteicloakbody.texOffs(5, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.32F, false);

		goteicloakrightarm = new ModelRenderer(this);
		goteicloakrightarm.setPos(-5.0F, -22.0F, 0.0F);
		goteicloak.addChild(goteicloakrightarm);
		goteicloakrightarm.texOffs(18, 17).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.31F, false);

		goteicloakleftarm = new ModelRenderer(this);
		goteicloakleftarm.setPos(5.0F, -22.0F, 0.0F);
		goteicloak.addChild(goteicloakleftarm);
		goteicloakleftarm.texOffs(0, 17).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.31F, false);

		goteicloakbottom = new ModelRenderer(this);
		goteicloakbottom.setPos(0.0F, -11, -2.325F);
		goteicloak.addChild(goteicloakbottom);
		goteicloakbottom.texOffs(5, 32).addBox(-4.0F, 0.3F, 0.325F, 8.0F, 6.0F, 4.0F, 0.32F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.goteicloakbody.copyFrom(this.body);
		this.goteicloakbody.render(matrixStack, buffer, packedLight, packedOverlay);
		this.goteicloakleftarm.copyFrom(this.leftArm);
		this.goteicloakleftarm.render(matrixStack, buffer, packedLight, packedOverlay);
		this.goteicloakrightarm.copyFrom(this.rightArm);
		this.goteicloakrightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		this.goteicloakbottom.copyFrom(this.body);
		this.goteicloakbottom.setPos(0, 11, -2);
		this.goteicloakbottom.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}