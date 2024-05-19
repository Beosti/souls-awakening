package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entities.hollow.FlyingEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class FlyingModel<T extends FlyingEntity> extends EntityModel<T> {
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
	private final ModelRenderer leftwing;
	private final ModelRenderer leftwingsection1;
	private final ModelRenderer leftwingspine1section2;
	private final ModelRenderer leftwingspine1section3;
	private final ModelRenderer rightarm;
	private final ModelRenderer upperrightarm;
	private final ModelRenderer lowerrightarm;
	private final ModelRenderer rightwing;
	private final ModelRenderer rightwingsection1;
	private final ModelRenderer rightwingspine1section2;
	private final ModelRenderer rightwingspine1section3;
	private final ModelRenderer headsection1;
	private final ModelRenderer furoverlay1;
	private final ModelRenderer furoverlay2;
	private final ModelRenderer furoverlay3;
	private final ModelRenderer headsection2;
	private final ModelRenderer mask;
	private final ModelRenderer uppermask;
	private final ModelRenderer uppermaskleftdetail;
	private final ModelRenderer uppermaskrightdetail;
	private final ModelRenderer uppermaskfrontdetailsection1;
	private final ModelRenderer uppermaskfrontdetailsection2;
	private final ModelRenderer lowermask;
	private final ModelRenderer leftleg;
	private final ModelRenderer lowerleftleg;
	private final ModelRenderer leftfoot;
	private final ModelRenderer rightleg;
	private final ModelRenderer lowerrightleg;
	private final ModelRenderer rightfoot;

	public FlyingModel() {
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
		bodysection1.setPos(0.0F, 4.25F, 0.0F);
		bodysection1.texOffs(54, 171).addBox(-6.0F, -5.5F, -3.5F, 12.0F, 7.0F, 7.0F, 0.0F, false);

		bodysection2 = new ModelRenderer(this);
		bodysection2.setPos(0.0F, -2.0F, 0.0F);
		bodysection1.addChild(bodysection2);
		setRotationAngle(bodysection2, 0.2182F, 0.0F, 0.0F);
		bodysection2.texOffs(5, 182).addBox(-7.0F, -9.25F, -4.0F, 14.0F, 9.0F, 8.0F, 0.2F, false);

		bodysection3 = new ModelRenderer(this);
		bodysection3.setPos(0.0F, -4.0F, 0.0F);
		bodysection2.addChild(bodysection3);
		setRotationAngle(bodysection3, 0.0873F, 0.0F, 0.0F);
		bodysection3.texOffs(5, 167).addBox(-7.0F, -4.75F, -4.0F, 14.0F, 5.0F, 8.0F, 0.5F, false);

		bodysection4 = new ModelRenderer(this);
		bodysection4.setPos(0.0F, -2.75F, 0.0F);
		bodysection3.addChild(bodysection4);
		setRotationAngle(bodysection4, 0.0873F, 0.0F, 0.0F);
		bodysection4.texOffs(1, 151).addBox(-8.0F, -4.0F, -5.0F, 16.0F, 4.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(1, 133).addBox(-8.0F, -16.0F, -5.0F, 16.0F, 7.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(58, 155).addBox(-8.0F, -9.0F, -5.0F, 5.0F, 5.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(58, 139).addBox(3.0F, -9.0F, -5.0F, 5.0F, 5.0F, 10.0F, 0.0F, false);
		bodysection4.texOffs(51, 186).addBox(-7.0F, -17.0F, -4.0F, 14.0F, 5.0F, 8.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setPos(7.75F, -11.5F, 0.0F);
		bodysection4.addChild(leftarm);
		setRotationAngle(leftarm, 0.0F, 0.0F, 0.1745F);
		leftarm.texOffs(102, 2).addBox(-0.7766F, -3.8039F, -4.0F, 5.0F, 7.0F, 8.0F, 0.3F, false);

		upperleftarm = new ModelRenderer(this);
		upperleftarm.setPos(2.8315F, -0.0463F, 0.0F);
		leftarm.addChild(upperleftarm);
		setRotationAngle(upperleftarm, 0.0F, 0.0F, -0.3491F);
		upperleftarm.texOffs(103, 40).addBox(-2.5927F, -1.7356F, -3.5F, 5.0F, 13.0F, 7.0F, 0.3F, false);

		lowerleftarm = new ModelRenderer(this);
		lowerleftarm.setPos(-0.0427F, 11.3144F, -0.05F);
		upperleftarm.addChild(lowerleftarm);
		setRotationAngle(lowerleftarm, -0.7854F, 0.0F, 0.0F);
		lowerleftarm.texOffs(103, 18).addBox(-2.5F, -1.25F, -3.5F, 5.0F, 14.0F, 7.0F, 0.0F, false);

		leftwing = new ModelRenderer(this);
		leftwing.setPos(0.0F, 0.0F, 0.0F);
		lowerleftarm.addChild(leftwing);
		leftwing.texOffs(64, 1).addBox(1.75F, 0.25F, -1.5F, 1.0F, 11.0F, 3.0F, 0.0F, false);

		leftwingsection1 = new ModelRenderer(this);
		leftwingsection1.setPos(2.6F, 9.75F, 0.0F);
		leftwing.addChild(leftwingsection1);
		setRotationAngle(leftwingsection1, 0.0F, 0.0F, -0.2182F);
		leftwingsection1.texOffs(73, 11).addBox(-0.85F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 0.2F, false);
		leftwingsection1.texOffs(78, 46).addBox(-0.65F, -15.0F, 0.0F, 12.0F, 14.0F, 0.0F, 0.0F, false);

		leftwingspine1section2 = new ModelRenderer(this);
		leftwingspine1section2.setPos(11.35F, 1.225F, 0.0F);
		leftwingsection1.addChild(leftwingspine1section2);
		setRotationAngle(leftwingspine1section2, 0.0F, 0.0F, -0.3491F);
		leftwingspine1section2.texOffs(75, 1).addBox(0.2F, -2.225F, -1.0F, 11.0F, 2.0F, 2.0F, 0.2F, false);
		leftwingspine1section2.texOffs(79, 31).addBox(0.0F, -16.225F, 0.0F, 11.0F, 14.0F, 0.0F, 0.0F, false);

		leftwingspine1section3 = new ModelRenderer(this);
		leftwingspine1section3.setPos(11.3543F, -1.2774F, 0.0F);
		leftwingspine1section2.addChild(leftwingspine1section3);
		setRotationAngle(leftwingspine1section3, 0.0F, 0.0F, -0.2182F);
		leftwingspine1section3.texOffs(75, 6).addBox(-0.3477F, -0.9465F, -1.0F, 11.0F, 2.0F, 2.0F, 0.0F, false);
		leftwingspine1section3.texOffs(79, 16).addBox(-0.3477F, -14.9465F, 0.0F, 11.0F, 14.0F, 0.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setPos(-7.75F, -11.5F, 0.0F);
		bodysection4.addChild(rightarm);
		setRotationAngle(rightarm, 0.0F, 0.0F, -0.1745F);
		rightarm.texOffs(173, 2).addBox(-4.2234F, -3.8039F, -4.0F, 5.0F, 7.0F, 8.0F, 0.3F, false);

		upperrightarm = new ModelRenderer(this);
		upperrightarm.setPos(-2.8315F, -0.0463F, 0.0F);
		rightarm.addChild(upperrightarm);
		setRotationAngle(upperrightarm, 0.0F, 0.0F, 0.3491F);
		upperrightarm.texOffs(174, 40).addBox(-2.4073F, -1.7356F, -3.5F, 5.0F, 13.0F, 7.0F, 0.3F, false);

		lowerrightarm = new ModelRenderer(this);
		lowerrightarm.setPos(0.0427F, 11.3144F, -0.05F);
		upperrightarm.addChild(lowerrightarm);
		setRotationAngle(lowerrightarm, -0.7854F, 0.0F, 0.0F);
		lowerrightarm.texOffs(174, 18).addBox(-2.5F, -1.25F, -3.5F, 5.0F, 14.0F, 7.0F, 0.0F, false);

		rightwing = new ModelRenderer(this);
		rightwing.setPos(0.0F, 0.0F, 0.0F);
		lowerrightarm.addChild(rightwing);
		rightwing.texOffs(135, 1).addBox(-2.75F, 0.25F, -1.5F, 1.0F, 11.0F, 3.0F, 0.0F, false);

		rightwingsection1 = new ModelRenderer(this);
		rightwingsection1.setPos(-2.6F, 9.75F, 0.0F);
		rightwing.addChild(rightwingsection1);
		setRotationAngle(rightwingsection1, 0.0F, 0.0F, 0.2182F);
		rightwingsection1.texOffs(144, 11).addBox(-11.15F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 0.2F, false);
		rightwingsection1.texOffs(149, 46).addBox(-11.35F, -15.0F, 0.0F, 12.0F, 14.0F, 0.0F, 0.0F, false);

		rightwingspine1section2 = new ModelRenderer(this);
		rightwingspine1section2.setPos(-11.35F, 1.225F, 0.0F);
		rightwingsection1.addChild(rightwingspine1section2);
		setRotationAngle(rightwingspine1section2, 0.0F, 0.0F, 0.3491F);
		rightwingspine1section2.texOffs(146, 6).addBox(-11.2F, -2.225F, -1.0F, 11.0F, 2.0F, 2.0F, 0.2F, false);
		rightwingspine1section2.texOffs(150, 31).addBox(-11.0F, -16.225F, 0.0F, 11.0F, 14.0F, 0.0F, 0.0F, false);

		rightwingspine1section3 = new ModelRenderer(this);
		rightwingspine1section3.setPos(-11.3543F, -1.2774F, 0.0F);
		rightwingspine1section2.addChild(rightwingspine1section3);
		setRotationAngle(rightwingspine1section3, 0.0F, 0.0F, 0.2182F);
		rightwingspine1section3.texOffs(146, 1).addBox(-10.6523F, -0.9465F, -1.0F, 11.0F, 2.0F, 2.0F, 0.0F, false);
		rightwingspine1section3.texOffs(150, 16).addBox(-10.6523F, -14.9465F, 0.0F, 11.0F, 14.0F, 0.0F, 0.0F, false);

		headsection1 = new ModelRenderer(this);
		headsection1.setPos(0.0F, -13.9711F, -2.6253F);
		bodysection4.addChild(headsection1);
		setRotationAngle(headsection1, -0.48F, 0.0F, 0.0F);
		headsection1.texOffs(26, 23).addBox(-4.5F, -4.5F, -3.0F, 9.0F, 8.0F, 6.0F, 0.0F, false);

		furoverlay1 = new ModelRenderer(this);
		furoverlay1.setPos(0.0F, -1.0F, -2.0F);
		headsection1.addChild(furoverlay1);
		setRotationAngle(furoverlay1, -0.1745F, 0.0F, 0.0F);
		furoverlay1.texOffs(1, 43).addBox(-9.5F, -7.5F, 0.0F, 19.0F, 15.0F, 0.0F, 0.0F, false);

		furoverlay2 = new ModelRenderer(this);
		furoverlay2.setPos(0.0F, -1.0F, -1.0F);
		headsection1.addChild(furoverlay2);
		setRotationAngle(furoverlay2, -0.2618F, 0.0F, 0.0F);
		furoverlay2.texOffs(1, 59).addBox(-9.5F, -7.5F, 0.0F, 19.0F, 15.0F, 0.0F, 0.0F, false);

		furoverlay3 = new ModelRenderer(this);
		furoverlay3.setPos(0.0F, -1.0F, 0.0F);
		headsection1.addChild(furoverlay3);
		setRotationAngle(furoverlay3, -0.2618F, 0.0F, 0.0F);
		furoverlay3.texOffs(1, 75).addBox(-9.5F, -7.5F, 0.0F, 19.0F, 15.0F, 0.0F, 0.0F, false);

		headsection2 = new ModelRenderer(this);
		headsection2.setPos(0.0F, 0.0F, -3.0F);
		headsection1.addChild(headsection2);
		setRotationAngle(headsection2, 0.1745F, 0.0F, 0.0F);
		headsection2.texOffs(28, 10).addBox(-4.0F, -4.0F, -3.0F, 8.0F, 7.0F, 5.0F, 0.0F, false);

		mask = new ModelRenderer(this);
		mask.setPos(0.0F, 0.0F, -2.5F);
		headsection2.addChild(mask);
		mask.texOffs(3, 20).addBox(-4.0F, -3.5021F, 0.4674F, 8.0F, 8.0F, 2.0F, 0.15F, false);

		uppermask = new ModelRenderer(this);
		uppermask.setPos(0.0F, 1.0F, -0.5F);
		mask.addChild(uppermask);
		uppermask.texOffs(1, 31).addBox(-4.0F, -5.1521F, -2.0326F, 8.0F, 6.0F, 4.0F, 0.2F, false);

		uppermaskleftdetail = new ModelRenderer(this);
		uppermaskleftdetail.setPos(3.2F, -5.4021F, -0.7326F);
		uppermask.addChild(uppermaskleftdetail);
		setRotationAngle(uppermaskleftdetail, -0.1745F, 0.0F, 0.2182F);
		uppermaskleftdetail.texOffs(12, 7).addBox(-0.9927F, -0.512F, -0.9802F, 2.0F, 2.0F, 2.0F, 0.2F, false);

		uppermaskrightdetail = new ModelRenderer(this);
		uppermaskrightdetail.setPos(-3.2F, -5.4021F, -0.7326F);
		uppermask.addChild(uppermaskrightdetail);
		setRotationAngle(uppermaskrightdetail, -0.1745F, 0.0F, -0.2182F);
		uppermaskrightdetail.texOffs(12, 2).addBox(-1.0073F, -0.512F, -0.9802F, 2.0F, 2.0F, 2.0F, 0.2F, false);

		uppermaskfrontdetailsection1 = new ModelRenderer(this);
		uppermaskfrontdetailsection1.setPos(0.0F, -2.3271F, -0.4076F);
		uppermask.addChild(uppermaskfrontdetailsection1);
		setRotationAngle(uppermaskfrontdetailsection1, -0.7854F, 0.0F, 0.0F);
		uppermaskfrontdetailsection1.texOffs(5, 6).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.4F, false);

		uppermaskfrontdetailsection2 = new ModelRenderer(this);
		uppermaskfrontdetailsection2.setPos(0.0F, 0.0F, -1.0F);
		uppermaskfrontdetailsection1.addChild(uppermaskfrontdetailsection2);
		setRotationAngle(uppermaskfrontdetailsection2, 0.6414F, 0.0F, 0.0F);
		uppermaskfrontdetailsection2.texOffs(5, 1).addBox(-1.0F, -1.4172F, -0.9559F, 2.0F, 3.0F, 1.0F, 0.4F, false);

		lowermask = new ModelRenderer(this);
		lowermask.setPos(0.0F, 0.9979F, -0.5326F);
		mask.addChild(lowermask);
		lowermask.texOffs(2, 12).addBox(-4.0F, 1.25F, -2.0F, 8.0F, 3.0F, 3.0F, 0.2F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(4.0F, 4.75F, 0.5F);
		setRotationAngle(leftleg, -0.6545F, -0.1745F, 0.0F);
		leftleg.texOffs(171, 144).addBox(-3.0F, -1.0F, -4.5F, 6.0F, 14.0F, 8.0F, 0.0F, false);

		lowerleftleg = new ModelRenderer(this);
		lowerleftleg.setPos(-0.5F, 9.5F, -1.75F);
		leftleg.addChild(lowerleftleg);
		setRotationAngle(lowerleftleg, 1.4835F, 0.0349F, 0.0F);
		lowerleftleg.texOffs(174, 167).addBox(-2.0F, -1.0F, -4.5F, 5.0F, 14.0F, 6.0F, 0.0F, false);

		leftfoot = new ModelRenderer(this);
		leftfoot.setPos(0.5F, 12.25F, -3.75F);
		lowerleftleg.addChild(leftfoot);
		setRotationAngle(leftfoot, -0.829F, 0.0F, 0.0F);
		leftfoot.texOffs(143, 188).addBox(-2.5F, -1.5F, -4.0F, 5.0F, 3.0F, 8.0F, 0.3F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(-4.0F, 4.75F, 0.5F);
		setRotationAngle(rightleg, -0.6545F, 0.1745F, 0.0F);
		rightleg.texOffs(142, 144).addBox(-3.0F, -1.0F, -4.5F, 6.0F, 14.0F, 8.0F, 0.0F, false);

		lowerrightleg = new ModelRenderer(this);
		lowerrightleg.setPos(0.5F, 9.5F, -1.75F);
		rightleg.addChild(lowerrightleg);
		setRotationAngle(lowerrightleg, 1.4835F, -0.0349F, 0.0F);
		lowerrightleg.texOffs(145, 167).addBox(-3.0F, -1.0F, -4.5F, 5.0F, 14.0F, 6.0F, 0.0F, false);

		rightfoot = new ModelRenderer(this);
		rightfoot.setPos(-0.5F, 12.25F, -3.75F);
		lowerrightleg.addChild(rightfoot);
		setRotationAngle(rightfoot, -0.829F, 0.0F, 0.0F);
		rightfoot.texOffs(172, 188).addBox(-2.5F, -1.5F, -4.0F, 5.0F, 3.0F, 8.0F, 0.3F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.headsection1.xRot = headPitch * ((float)Math.PI / 180F);
		this.headsection1.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.rightarm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftarm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1F * limbSwingAmount;
		this.leftleg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1F * limbSwingAmount;
		this.lowerrightleg.xRot = MathHelper.cos(limbSwing * 0.03662F) * 0.6F * limbSwingAmount;
		this.lowerleftleg.xRot = MathHelper.cos(limbSwing * 0.03662F + (float)Math.PI) * 0.6F * limbSwingAmount;
		//this.rightfoot.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		//this.leftfoot.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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