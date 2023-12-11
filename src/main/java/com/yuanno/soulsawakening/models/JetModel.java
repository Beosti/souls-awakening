package com.yuanno.soulsawakening.models;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entity.JetEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class JetModel<T extends JetEntity> extends EntityModel<T> {
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;

	public JetModel() {
		texWidth = 64;
		texHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(0, 5).addBox(-3.0F, -17.0F, -1.0F, 6.0F, 3.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(0, 4).addBox(-2.0F, -16.9F, 3.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		bb_main.texOffs(0, 18).addBox(-1.0F, -16.8F, 4.0F, 2.0F, 2.0F, 18.0F, 0.0F, false);
		bb_main.texOffs(0, 1).addBox(-3.0F, -17.0F, -5.1F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(0, 20).addBox(-0.7F, -16.7F, 21.0F, 1.0F, 1.0F, 20.0F, 0.0F, false);
		bb_main.texOffs(7, 12).addBox(-0.4621F, -16.9512F, 23.6823F, 1.0F, 0.0F, 12.0F, 0.0F, false);
		bb_main.texOffs(0, 6).addBox(-10.0F, -16.0F, -3.0F, 7.0F, 1.0F, 6.0F, 0.0F, false);
		bb_main.texOffs(0, 6).addBox(3.0F, -16.0F, -3.0F, 7.0F, 1.0F, 6.0F, 0.0F, true);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(3.5F, -12.0F, -1.5F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.5236F);
		cube_r1.texOffs(0, 1).addBox(-2.5F, -1.0F, 1.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		cube_r1.texOffs(0, 1).addBox(-1.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		cube_r1.texOffs(0, 1).addBox(-0.8F, -2.0F, -2.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-3.5F, -12.0F, -1.5F);
		bb_main.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.5236F);
		cube_r2.texOffs(0, 1).addBox(-0.2F, -2.0F, -2.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cube_r2.texOffs(0, 1).addBox(1.5F, -1.0F, 1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r2.texOffs(0, 1).addBox(0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, -17.0F, -7.0F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.6109F, 0.0F, 0.0F);
		cube_r3.texOffs(0, 2).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(2.9016F, -15.4524F, -8.8587F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.5517F, 0.1866F, 0.1137F);
		cube_r4.texOffs(0, 6).addBox(-2.1F, -1.05F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, true);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(-2.9016F, -15.4524F, -8.8587F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.5517F, -0.1866F, -0.1137F);
		cube_r5.texOffs(0, 6).addBox(0.1F, -1.05F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, -15.35F, -10.0F);
		bb_main.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.5411F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 6).addBox(-2.0F, -0.65F, -2.0F, 4.0F, 2.0F, 6.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, -15.3475F, -6.7567F);
		bb_main.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.0873F, 0.0F, 0.0F);
		cube_r7.texOffs(0, 4).addBox(-1.1F, -0.4F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.0F, -15.0F, -7.0F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.1745F, 0.0F, 0.0F);
		cube_r8.texOffs(0, 4).addBox(-1.0F, -1.3F, -1.7F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(4.2604F, -17.5F, -3.4024F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, -0.6109F, 0.0F);
		cube_r9.texOffs(0, 7).addBox(-0.5F, -0.2F, 0.0F, 1.0F, 1.0F, 7.0F, 0.0F, true);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(-8.8317F, -16.6493F, -2.4057F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.2161F, 1.4387F, 0.0865F);
		cube_r10.texOffs(0, 7).addBox(0.0F, -0.5F, -3.1F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(20.6428F, -15.5F, 1.6134F);
		bb_main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0252F, -0.523F, -0.0504F);
		cube_r11.texOffs(0, 6).addBox(-5.0F, -0.6F, -3.5F, 14.0F, 1.0F, 7.0F, 0.0F, true);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(-30.1691F, -15.5F, 7.1134F);
		bb_main.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0622F, 0.9586F, 0.076F);
		cube_r12.texOffs(0, 6).addBox(-6.5F, -0.8F, -2.5F, 9.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(14.4074F, -15.5F, -1.1014F);
		bb_main.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.0097F, 0.218F, -0.0447F);
		cube_r13.texOffs(7, 6).addBox(-5.5F, -0.5F, -2.5F, 10.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(-20.6428F, -15.5F, 1.6134F);
		bb_main.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0252F, 0.523F, 0.0504F);
		cube_r14.texOffs(0, 6).addBox(-9.0F, -0.6F, -3.5F, 14.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(-14.4074F, -15.5F, -1.1014F);
		bb_main.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.0097F, -0.218F, 0.0447F);
		cube_r15.texOffs(7, 6).addBox(-4.5F, -0.5F, -2.5F, 10.0F, 1.0F, 6.0F, 0.0F, true);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(30.1691F, -15.5F, 7.1134F);
		bb_main.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0622F, -0.9586F, -0.076F);
		cube_r16.texOffs(0, 6).addBox(-3.0F, -0.8F, -2.5F, 9.0F, 1.0F, 6.0F, 0.0F, true);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(4.2604F, -17.5F, -3.4024F);
		bb_main.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.6545F, 0.0F);
		cube_r17.texOffs(0, 4).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		cube_r17.texOffs(0, 1).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 5.0F, 1.0F, 0.0F, true);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(-4.2604F, -17.5F, -3.4024F);
		bb_main.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, -0.6545F, 0.0F);
		cube_r18.texOffs(0, 1).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		cube_r18.texOffs(0, 4).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(23.8343F, -15.2955F, -5.1415F);
		bb_main.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0467F, -0.5667F, -0.0251F);
		cube_r19.texOffs(0, 2).addBox(-6.0F, -1.1F, 2.0F, 16.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(-33.294F, -15.6633F, 4.1976F);
		bb_main.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0686F, 0.9588F, 0.0561F);
		cube_r20.texOffs(0, 1).addBox(-7.5F, -0.9F, 1.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(1.7621F, -17.2F, 5.1987F);
		bb_main.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.0436F, 0.0F, 0.0F);
		cube_r21.texOffs(7, 7).addBox(-2.3F, -0.3F, -3.5F, 1.0F, 1.0F, 7.0F, 0.0F, true);
		cube_r21.texOffs(7, 7).addBox(-2.2243F, -0.3F, -3.5F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(10.6349F, -15.2093F, -2.25F);
		bb_main.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.04F, 0.1744F, 0.0069F);
		cube_r22.texOffs(0, 2).addBox(-3.5F, -1.2F, -1.55F, 12.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(-23.8343F, -15.2955F, -5.1415F);
		bb_main.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0467F, 0.5667F, 0.0251F);
		cube_r23.texOffs(0, 2).addBox(-10.0F, -1.1F, 2.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(6.5F, -14.0F, -2.25F);
		bb_main.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, 0.096F);
		cube_r24.texOffs(0, 2).addBox(-3.5F, -2.6F, -1.15F, 5.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(-10.6349F, -15.2093F, -2.25F);
		bb_main.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.04F, -0.1744F, -0.0069F);
		cube_r25.texOffs(0, 2).addBox(-8.5F, -1.2F, -1.55F, 12.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(6.5F, -14.0F, -2.25F);
		bb_main.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, 0.0F, -0.2182F);
		cube_r26.texOffs(0, 2).addBox(-2.5F, -2.0F, -1.25F, 5.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(-6.5F, -14.0F, -2.25F);
		bb_main.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 0.0F, -0.096F);
		cube_r27.texOffs(0, 2).addBox(-1.5F, -2.6F, -1.15F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(33.294F, -15.6633F, 4.1976F);
		bb_main.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0686F, -0.9588F, -0.0561F);
		cube_r28.texOffs(0, 1).addBox(-4.5F, -0.9F, 1.0F, 12.0F, 1.0F, 1.0F, 0.0F, true);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(-6.5F, -14.0F, -2.25F);
		bb_main.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.0F, 0.2182F);
		cube_r29.texOffs(0, 2).addBox(-2.5F, -2.0F, -1.25F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setPos(0.0379F, -16.6512F, 16.1823F);
		bb_main.addChild(cube_r30);
		setRotationAngle(cube_r30, -0.0175F, 0.0F, 0.0F);
		cube_r30.texOffs(7, 14).addBox(-0.5F, -0.5F, -7.5F, 1.0F, 1.0F, 15.0F, 0.0F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setPos(-4.2604F, -17.5F, -3.4024F);
		bb_main.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.0F, 0.6109F, 0.0F);
		cube_r31.texOffs(0, 7).addBox(-0.5F, -0.2F, 0.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		cube_r31.texOffs(0, 7).addBox(-0.5F, -0.2F, 0.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setPos(8.8317F, -16.6493F, -2.4057F);
		bb_main.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.2161F, -1.4387F, -0.0865F);
		cube_r32.texOffs(0, 7).addBox(-1.0F, -0.5F, -3.1F, 1.0F, 1.0F, 7.0F, 0.0F, true);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setPos(1.0F, -14.0F, -3.0F);
		bb_main.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0F, -0.48F, 0.0F);
		cube_r33.texOffs(6, 7).addBox(0.8F, -3.2F, -3.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setPos(-1.0F, -14.0F, -3.0F);
		bb_main.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.0F, 0.48F, 0.0F);
		cube_r34.texOffs(0, 7).addBox(-4.0F, -3.2F, -3.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		/*

		 */

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		//Lleg.render(matrixStack, buffer, packedLight, packedOverlay);
		//Rleg.render(matrixStack, buffer, packedLight, packedOverlay);

	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}