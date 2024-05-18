package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entities.hollow.ApeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ApeModel<T extends ApeEntity> extends EntityModel<T> {
	private final ModelRenderer Minecraft_Placeholder;
	private final ModelRenderer MinecraftLeftLeg;
	private final ModelRenderer MinecraftRightLeg;
	private final ModelRenderer MinecraftLeftArm;
	private final ModelRenderer MinecraftRightArm;
	private final ModelRenderer MinecraftBody;
	private final ModelRenderer MinecraftHead;
	private final ModelRenderer bodysection1;
	private final ModelRenderer bodysection2;
	private final ModelRenderer bodysection3;
	private final ModelRenderer bodysection4;
	private final ModelRenderer leftarm;
	private final ModelRenderer upperleftarm;
	private final ModelRenderer lowerleftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer upperrightarm;
	private final ModelRenderer lowerrightarm;
	private final ModelRenderer headsection1;
	private final ModelRenderer headsection2;
	private final ModelRenderer mask;
	private final ModelRenderer uppermask;
	private final ModelRenderer uppermaskleftdetailsection1;
	private final ModelRenderer uppermaskleftdetailsection2;
	private final ModelRenderer uppermaskleftdetailsection3;
	private final ModelRenderer uppermaskrightdetailsection1;
	private final ModelRenderer uppermaskrightdetailsection2;
	private final ModelRenderer uppermaskrightdetailsection3;
	private final ModelRenderer lowermask;
	private final ModelRenderer leftleg;
	private final ModelRenderer lowerleftleg;
	private final ModelRenderer leftfoot;
	private final ModelRenderer rightleg;
	private final ModelRenderer lowerrightleg;
	private final ModelRenderer rightfoot;

	public ApeModel() {
		texWidth = 200;
		texHeight = 200;

		Minecraft_Placeholder = new ModelRenderer(this);
		Minecraft_Placeholder.setPos(0.0F, 16.0F, 0.0F);


		MinecraftLeftLeg = new ModelRenderer(this);
		MinecraftLeftLeg.setPos(1.9F, -4.0F, 0.0F);
		Minecraft_Placeholder.addChild(MinecraftLeftLeg);
		MinecraftLeftLeg.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftLeftLeg.texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftRightLeg = new ModelRenderer(this);
		MinecraftRightLeg.setPos(-1.9F, -4.0F, 0.0F);
		Minecraft_Placeholder.addChild(MinecraftRightLeg);
		MinecraftRightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftRightLeg.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftLeftArm = new ModelRenderer(this);
		MinecraftLeftArm.setPos(5.0F, -14.0F, 0.0F);
		Minecraft_Placeholder.addChild(MinecraftLeftArm);
		setRotationAngle(MinecraftLeftArm, 1.5708F, 0.0F, 0.0F);
		MinecraftLeftArm.texOffs(32, 48).addBox(-1.0F, -2.0F, -10.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);
		MinecraftLeftArm.texOffs(48, 48).addBox(-1.0F, -2.0F, -10.0F, 4.0F, 4.0F, 12.0F, 0.25F, false);

		MinecraftRightArm = new ModelRenderer(this);
		MinecraftRightArm.setPos(-5.0F, -14.0F, 0.0F);
		Minecraft_Placeholder.addChild(MinecraftRightArm);
		setRotationAngle(MinecraftRightArm, 1.5708F, 0.0F, 0.0F);
		MinecraftRightArm.texOffs(40, 16).addBox(-3.0F, -2.0F, -10.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);
		MinecraftRightArm.texOffs(40, 32).addBox(-3.0F, -2.0F, -10.0F, 4.0F, 4.0F, 12.0F, 0.25F, false);

		MinecraftBody = new ModelRenderer(this);
		MinecraftBody.setPos(0.0F, -16.0F, 0.0F);
		Minecraft_Placeholder.addChild(MinecraftBody);
		MinecraftBody.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftBody.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftHead = new ModelRenderer(this);
		MinecraftHead.setPos(0.0F, -16.0F, 0.0F);
		Minecraft_Placeholder.addChild(MinecraftHead);
		MinecraftHead.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		MinecraftHead.texOffs(32, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		bodysection1 = new ModelRenderer(this);
		bodysection1.setPos(0.0F, 11.75F, 0.0F);
		bodysection1.texOffs(48, 174).addBox(-4.0F, -5.0F, -3.5F, 8.0F, 7.0F, 7.0F, 0.0F, false);

		bodysection2 = new ModelRenderer(this);
		bodysection2.setPos(0.0F, -2.5F, 0.0F);
		bodysection1.addChild(bodysection2);
		setRotationAngle(bodysection2, 0.2618F, 0.0F, 0.0F);
		bodysection2.texOffs(6, 185).addBox(-5.0F, -7.25F, -4.0F, 10.0F, 7.0F, 8.0F, 0.2F, false);

		bodysection3 = new ModelRenderer(this);
		bodysection3.setPos(0.0F, -4.0F, 0.0F);
		bodysection2.addChild(bodysection3);
		setRotationAngle(bodysection3, 0.0873F, 0.0F, 0.0F);
		bodysection3.texOffs(6, 171).addBox(-5.0F, -4.75F, -4.0F, 10.0F, 5.0F, 8.0F, 0.5F, false);

		bodysection4 = new ModelRenderer(this);
		bodysection4.setPos(0.0F, -2.75F, 0.0F);
		bodysection3.addChild(bodysection4);
		setRotationAngle(bodysection4, 0.2618F, 0.0F, 0.0F);
		bodysection4.texOffs(2, 156).addBox(-6.0F, -4.0F, -5.0F, 12.0F, 4.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(0, 137).addBox(-7.0F, -15.0F, -5.0F, 14.0F, 8.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(48, 160).addBox(-7.0F, -7.0F, -5.0F, 5.0F, 3.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(48, 146).addBox(2.0F, -7.0F, -5.0F, 5.0F, 3.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(43, 189).addBox(-6.0F, -15.5F, -4.0F, 12.0F, 3.0F, 8.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setPos(7.75F, -9.5F, 0.0F);
		bodysection4.addChild(leftarm);
		setRotationAngle(leftarm, -0.3491F, 0.0F, 0.1309F);
		leftarm.texOffs(174, 0).addBox(-1.5266F, -2.8039F, -3.0F, 7.0F, 6.0F, 6.0F, 0.3F, false);

		upperleftarm = new ModelRenderer(this);
		upperleftarm.setPos(3.3315F, 0.2037F, 0.0F);
		leftarm.addChild(upperleftarm);
		setRotationAngle(upperleftarm, -0.5236F, 0.0F, -0.3491F);
		upperleftarm.texOffs(178, 13).addBox(-2.5927F, -0.7356F, -2.5F, 4.0F, 16.0F, 5.0F, 0.5F, false);

		lowerleftarm = new ModelRenderer(this);
		lowerleftarm.setPos(-0.0427F, 14.3144F, -0.05F);
		upperleftarm.addChild(lowerleftarm);
		setRotationAngle(lowerleftarm, 0.0F, 0.0F, 0.3927F);
		lowerleftarm.texOffs(178, 35).addBox(-2.5F, -0.75F, -2.5F, 4.0F, 16.0F, 5.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setPos(-7.75F, -9.5F, 0.0F);
		bodysection4.addChild(rightarm);
		setRotationAngle(rightarm, -0.3491F, 0.0F, -0.1309F);
		rightarm.texOffs(147, 0).addBox(-5.4734F, -2.8039F, -3.0F, 7.0F, 6.0F, 6.0F, 0.3F, true);

		upperrightarm = new ModelRenderer(this);
		upperrightarm.setPos(-3.3315F, 0.2037F, 0.0F);
		rightarm.addChild(upperrightarm);
		setRotationAngle(upperrightarm, -0.5236F, 0.0F, 0.3491F);
		upperrightarm.texOffs(151, 13).addBox(-1.4073F, -0.7356F, -2.5F, 4.0F, 16.0F, 5.0F, 0.5F, true);

		lowerrightarm = new ModelRenderer(this);
		lowerrightarm.setPos(0.0427F, 14.3144F, -0.05F);
		upperrightarm.addChild(lowerrightarm);
		setRotationAngle(lowerrightarm, 0.0F, 0.0F, -0.3927F);
		lowerrightarm.texOffs(151, 35).addBox(-1.5F, -0.75F, -2.5F, 4.0F, 16.0F, 5.0F, 0.0F, true);

		headsection1 = new ModelRenderer(this);
		headsection1.setPos(0.0F, -11.2211F, -3.8753F);
		bodysection4.addChild(headsection1);
		setRotationAngle(headsection1, -0.48F, 0.0F, 0.0F);
		headsection1.texOffs(1, 56).addBox(-5.0F, -4.5F, -3.0F, 10.0F, 7.0F, 6.0F, 0.0F, false);

		headsection2 = new ModelRenderer(this);
		headsection2.setPos(0.0F, 0.125F, -2.75F);
		headsection1.addChild(headsection2);
		setRotationAngle(headsection2, 0.1745F, 0.0F, 0.0F);
		headsection2.texOffs(3, 44).addBox(-4.5F, -4.1302F, -3.2386F, 9.0F, 6.0F, 5.0F, 0.0F, false);

		mask = new ModelRenderer(this);
		mask.setPos(0.0F, -0.8802F, -2.4886F);
		headsection2.addChild(mask);
		mask.texOffs(2, 21).addBox(-5.5F, -4.3521F, -1.6076F, 11.0F, 6.0F, 4.0F, 0.1F, false);

		uppermask = new ModelRenderer(this);
		uppermask.setPos(0.0F, 3.5F, -0.25F);
		mask.addChild(uppermask);
		uppermask.texOffs(0, 32).addBox(-6.0F, -6.6521F, -3.0326F, 12.0F, 6.0F, 5.0F, 0.2F, false);

		uppermaskleftdetailsection1 = new ModelRenderer(this);
		uppermaskleftdetailsection1.setPos(3.8635F, -4.8791F, 0.7121F);
		uppermask.addChild(uppermaskleftdetailsection1);
		setRotationAngle(uppermaskleftdetailsection1, 0.0F, -0.0873F, -0.1309F);
		uppermaskleftdetailsection1.texOffs(17, 10).addBox(-1.4729F, -1.4851F, -1.0F, 4.0F, 2.0F, 2.0F, 0.5F, false);

		uppermaskleftdetailsection2 = new ModelRenderer(this);
		uppermaskleftdetailsection2.setPos(2.2771F, -0.1351F, 0.0F);
		uppermaskleftdetailsection1.addChild(uppermaskleftdetailsection2);
		setRotationAngle(uppermaskleftdetailsection2, 0.0F, 0.0F, 0.0436F);
		uppermaskleftdetailsection2.texOffs(17, 5).addBox(-0.1F, -1.5F, -1.0F, 4.0F, 2.0F, 2.0F, 0.3F, false);

		uppermaskleftdetailsection3 = new ModelRenderer(this);
		uppermaskleftdetailsection3.setPos(3.875F, -0.225F, 0.0F);
		uppermaskleftdetailsection2.addChild(uppermaskleftdetailsection3);
		setRotationAngle(uppermaskleftdetailsection3, 0.0F, 0.0F, 0.0436F);
		uppermaskleftdetailsection3.texOffs(17, 0).addBox(-0.125F, -1.5F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		uppermaskrightdetailsection1 = new ModelRenderer(this);
		uppermaskrightdetailsection1.setPos(-3.8635F, -4.8791F, 0.7121F);
		uppermask.addChild(uppermaskrightdetailsection1);
		setRotationAngle(uppermaskrightdetailsection1, 0.0F, 0.0873F, 0.1309F);
		uppermaskrightdetailsection1.texOffs(4, 10).addBox(-2.5271F, -1.4851F, -1.0F, 4.0F, 2.0F, 2.0F, 0.5F, true);

		uppermaskrightdetailsection2 = new ModelRenderer(this);
		uppermaskrightdetailsection2.setPos(-2.2771F, -0.1351F, 0.0F);
		uppermaskrightdetailsection1.addChild(uppermaskrightdetailsection2);
		setRotationAngle(uppermaskrightdetailsection2, 0.0F, 0.0F, -0.0436F);
		uppermaskrightdetailsection2.texOffs(4, 5).addBox(-3.9F, -1.5F, -1.0F, 4.0F, 2.0F, 2.0F, 0.3F, true);

		uppermaskrightdetailsection3 = new ModelRenderer(this);
		uppermaskrightdetailsection3.setPos(-3.875F, -0.225F, 0.0F);
		uppermaskrightdetailsection2.addChild(uppermaskrightdetailsection3);
		setRotationAngle(uppermaskrightdetailsection3, 0.0F, 0.0F, -0.0436F);
		uppermaskrightdetailsection3.texOffs(4, 0).addBox(-3.875F, -1.5F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, true);

		lowermask = new ModelRenderer(this);
		lowermask.setPos(0.0F, 2.9729F, -0.0326F);
		mask.addChild(lowermask);
		lowermask.texOffs(33, 25).addBox(-5.0F, 0.275F, -3.25F, 10.0F, 2.0F, 4.0F, 0.2F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(4.0F, 12.25F, 0.5F);
		setRotationAngle(leftleg, -1.789F, -0.1745F, 0.0F);
		leftleg.texOffs(128, 160).addBox(-3.0F, -1.2705F, -2.7796F, 6.0F, 14.0F, 5.0F, 0.2F, false);

		lowerleftleg = new ModelRenderer(this);
		lowerleftleg.setPos(-0.5F, 11.4795F, 0.9704F);
		leftleg.addChild(lowerleftleg);
		setRotationAngle(lowerleftleg, 0.7854F, 0.0349F, 0.0F);
		lowerleftleg.texOffs(119, 180).addBox(-1.93F, -2.1819F, -2.5147F, 5.0F, 5.0F, 15.0F, 0.0F, false);

		leftfoot = new ModelRenderer(this);
		leftfoot.setPos(0.5785F, 0.8488F, 11.8221F);
		lowerleftleg.addChild(leftfoot);
		setRotationAngle(leftfoot, -0.5672F, 0.0F, 0.0F);
		leftfoot.texOffs(132, 149).addBox(-2.0086F, -1.2332F, -0.5905F, 4.0F, 7.0F, 3.0F, 0.3F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(-4.0F, 12.25F, 0.5F);
		setRotationAngle(rightleg, -1.789F, 0.1745F, 0.0F);
		rightleg.texOffs(169, 160).addBox(-3.0F, -1.2705F, -2.7796F, 6.0F, 14.0F, 5.0F, 0.2F, true);

		lowerrightleg = new ModelRenderer(this);
		lowerrightleg.setPos(0.5F, 11.4795F, 0.9704F);
		rightleg.addChild(lowerrightleg);
		setRotationAngle(lowerrightleg, 0.7854F, -0.0349F, 0.0F);
		lowerrightleg.texOffs(160, 180).addBox(-3.07F, -2.1819F, -2.5147F, 5.0F, 5.0F, 15.0F, 0.0F, true);

		rightfoot = new ModelRenderer(this);
		rightfoot.setPos(-0.5785F, 0.8488F, 11.8221F);
		lowerrightleg.addChild(rightfoot);
		setRotationAngle(rightfoot, -0.5672F, 0.0F, 0.0F);
		rightfoot.texOffs(173, 149).addBox(-1.9914F, -1.2332F, -0.5905F, 4.0F, 7.0F, 3.0F, 0.3F, true);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		//Minecraft_Placeholder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bodysection1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}