package com.yuanno.soulsawakening.models;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entity.CentipedeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.ZombieEntity;

public class CentipedeModel<T extends CentipedeEntity> extends EntityModel<T> {
	private final ModelRenderer leftside;
	private final ModelRenderer Poot3Left_r1;
	private final ModelRenderer Poot2Left_r1;
	private final ModelRenderer Poot5Left_r1;
	private final ModelRenderer rightside;
	private final ModelRenderer Poot2Right_r1;
	private final ModelRenderer Poot4Right_r1;
	private final ModelRenderer Poot3Right_r1;
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

	public CentipedeModel() {
		texWidth = 64;
		texHeight = 64;

		leftside = new ModelRenderer(this);
		leftside.setPos(6.6213F, 19.0503F, 39.0F);
		

		Poot3Left_r1 = new ModelRenderer(this);
		Poot3Left_r1.setPos(2.0F, 0.0F, -18.0F);
		leftside.addChild(Poot3Left_r1);
		setRotationAngle(Poot3Left_r1, 0.0F, 0.0F, 0.9599F);
		Poot3Left_r1.texOffs(0, 3).addBox(-2.3192F, -0.5736F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);

		Poot2Left_r1 = new ModelRenderer(this);
		Poot2Left_r1.setPos(1.0F, 0.0F, -27.0F);
		leftside.addChild(Poot2Left_r1);
		setRotationAngle(Poot2Left_r1, 0.0F, 0.0F, 0.9599F);
		Poot2Left_r1.texOffs(0, 3).addBox(-1.7456F, -1.3927F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);
		Poot2Left_r1.texOffs(3, 3).addBox(-1.7456F, -1.3927F, 16.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		Poot2Left_r1.texOffs(0, 3).addBox(-1.7456F, -1.3927F, -10.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);

		Poot5Left_r1 = new ModelRenderer(this);
		Poot5Left_r1.setPos(0.0F, 0.0F, 0.0F);
		leftside.addChild(Poot5Left_r1);
		setRotationAngle(Poot5Left_r1, 0.0F, 0.0F, 0.9599F);
		Poot5Left_r1.texOffs(3, 3).addBox(-1.172F, -2.2119F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);

		rightside = new ModelRenderer(this);
		rightside.setPos(-7.6213F, 20.0503F, 39.0F);
		

		Poot2Right_r1 = new ModelRenderer(this);
		Poot2Right_r1.setPos(0.0F, 0.0F, -27.0F);
		rightside.addChild(Poot2Right_r1);
		setRotationAngle(Poot2Right_r1, 0.0F, 0.0F, -0.9599F);
		Poot2Right_r1.texOffs(0, 3).addBox(-5.4353F, -1.9663F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		Poot2Right_r1.texOffs(0, 3).addBox(-5.4353F, -1.9663F, 25.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		Poot2Right_r1.texOffs(0, 3).addBox(-5.4353F, -1.9663F, -10.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);

		Poot4Right_r1 = new ModelRenderer(this);
		Poot4Right_r1.setPos(1.0F, -1.0F, -9.0F);
		rightside.addChild(Poot4Right_r1);
		setRotationAngle(Poot4Right_r1, 0.0F, 0.0F, -0.9599F);
		Poot4Right_r1.texOffs(0, 3).addBox(-6.828F, -2.2119F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);

		Poot3Right_r1 = new ModelRenderer(this);
		Poot3Right_r1.setPos(1.0F, 0.0F, -18.0F);
		rightside.addChild(Poot3Right_r1);
		setRotationAngle(Poot3Right_r1, 0.0F, 0.0F, -0.9599F);
		Poot3Right_r1.texOffs(0, 3).addBox(-6.0088F, -2.7855F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(0, 9).addBox(-3.0F, -3.0F, 5.0F, 6.0F, 3.0F, 9.0F, 0.0F, false);
		bb_main.texOffs(0, 8).addBox(-4.5F, -4.0F, 7.0F, 9.0F, 5.0F, 8.0F, 0.0F, false);
		bb_main.texOffs(0, 8).addBox(-4.5F, -4.0F, 16.0F, 9.0F, 5.0F, 8.0F, 0.0F, false);
		bb_main.texOffs(0, 9).addBox(-3.0F, -3.0F, 14.0F, 6.0F, 3.0F, 9.0F, 0.0F, false);
		bb_main.texOffs(0, 8).addBox(-4.5F, -4.0F, 25.0F, 9.0F, 5.0F, 8.0F, 0.0F, false);
		bb_main.texOffs(0, 9).addBox(-3.0F, -3.0F, 23.0F, 6.0F, 3.0F, 9.0F, 0.0F, false);
		bb_main.texOffs(0, 8).addBox(-4.5F, -4.0F, -2.0F, 9.0F, 5.0F, 8.0F, 0.0F, false);
		bb_main.texOffs(0, 11).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 3.0F, 11.0F, 0.0F, false);
		bb_main.texOffs(0, 8).addBox(-4.5F, -4.0F, 34.0F, 9.0F, 5.0F, 8.0F, 0.0F, false);
		bb_main.texOffs(0, 9).addBox(-3.0F, -3.0F, 32.0F, 6.0F, 3.0F, 9.0F, 0.0F, false);
		bb_main.texOffs(0, 6).addBox(-4.0F, -18.0F, -14.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-1.0F, -13.5F, 0.0F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3442F, -0.0594F, -0.3594F);
		cube_r1.texOffs(0, 2).addBox(-6.2F, -4.5F, -5.0F, 4.0F, 11.0F, 2.0F, 0.0F, true);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(1.0F, -13.5F, 0.0F);
		bb_main.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.3442F, 0.0594F, 0.3594F);
		cube_r2.texOffs(0, 2).addBox(2.2F, -4.5F, -5.0F, 4.0F, 11.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(4.0F, -7.1247F, -9.9651F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.7025F, -0.2615F, 0.2964F);
		cube_r3.texOffs(0, 4).addBox(-2.0F, -3.5F, 0.0F, 2.0F, 7.0F, 4.0F, 0.0F, true);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-4.0F, -7.1247F, -9.9651F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.7025F, 0.2615F, -0.2964F);
		cube_r4.texOffs(0, 4).addBox(0.0F, -3.5F, 0.0F, 2.0F, 7.0F, 4.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, -3.5F, -8.0F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.7418F, 0.0F, 0.0F);
		cube_r5.texOffs(0, 4).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 4.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(2.5F, -20.5F, -13.0F);
		bb_main.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.8727F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 2).addBox(-0.5F, -4.5F, 2.0F, 1.0F, 7.0F, 2.0F, 0.0F, true);
		cube_r6.texOffs(0, 2).addBox(-5.5F, -4.5F, 2.0F, 1.0F, 7.0F, 2.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(5.0F, -16.5F, -16.0F);
		bb_main.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.5672F, 0.0F, 0.4363F);
		cube_r7.texOffs(0, 2).addBox(-1.0F, 0.5F, 3.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(-4.0F, -13.5F, -16.0F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.5672F, 0.0F, -0.4363F);
		cube_r8.texOffs(0, 2).addBox(-0.6385F, -2.1495F, 1.3121F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, -16.0F, -8.0F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.829F, 0.0F, 0.0F);
		cube_r9.texOffs(0, 2).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(6.5F, -9.0F, -20.0F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.9163F, 0.0F, 0.0F);
		cube_r10.texOffs(0, 11).addBox(-1.5F, 0.0F, -8.0F, 3.0F, 2.0F, 11.0F, 0.0F, true);
		cube_r10.texOffs(0, 11).addBox(-14.5F, 0.0F, -8.0F, 3.0F, 2.0F, 11.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(-10.5F, -6.4079F, -20.7286F);
		bb_main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.9903F, 0.176F, 0.4663F);
		cube_r11.texOffs(0, 9).addBox(-2.5F, 3.0F, -3.5F, 3.0F, 2.0F, 9.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(10.5F, -6.4079F, -20.7286F);
		bb_main.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.9903F, -0.176F, -0.4663F);
		cube_r12.texOffs(0, 9).addBox(-0.5F, 3.0F, -3.5F, 3.0F, 2.0F, 9.0F, 0.0F, true);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(-7.5F, -7.5F, -7.5F);
		bb_main.addChild(cube_r13);
		setRotationAngle(cube_r13, -2.2253F, 0.0F, 0.0F);
		cube_r13.texOffs(0, 3).addBox(0.0F, 0.2F, 4.5F, 2.0F, 9.0F, 3.0F, 0.0F, false);
		cube_r13.texOffs(0, 3).addBox(13.0F, 0.2F, 4.5F, 2.0F, 9.0F, 3.0F, 0.0F, true);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(-7.5F, -7.5F, -7.5F);
		bb_main.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.5236F, 0.0F, 0.0F);
		cube_r14.texOffs(0, 3).addBox(-0.5F, -3.5F, -3.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);
		cube_r14.texOffs(0, 3).addBox(12.5F, -3.5F, -3.5F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(10.5F, -6.8845F, -9.5567F);
		bb_main.addChild(cube_r15);
		setRotationAngle(cube_r15, -1.8422F, 0.0673F, -0.6106F);
		cube_r15.texOffs(3, 3).addBox(-1.0F, -3.0109F, 0.378F, 2.0F, 9.0F, 3.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(10.5F, -6.8845F, -9.5567F);
		bb_main.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.1405F, 0.0673F, -0.6106F);
		cube_r16.texOffs(3, 3).addBox(-1.5F, -7.1676F, 0.2215F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(-10.5F, -6.8845F, -9.5567F);
		bb_main.addChild(cube_r17);
		setRotationAngle(cube_r17, -1.8422F, -0.0673F, 0.6106F);
		cube_r17.texOffs(3, 3).addBox(-1.0F, -3.0109F, 0.378F, 2.0F, 9.0F, 3.0F, 0.0F, true);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(-10.5F, -6.8845F, -9.5567F);
		bb_main.addChild(cube_r18);
		setRotationAngle(cube_r18, -0.1405F, -0.0673F, 0.6106F);
		cube_r18.texOffs(3, 3).addBox(-1.5F, -7.1676F, 0.2215F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, -9.25F, -7.0F);
		bb_main.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.3927F, 0.0F, 0.0F);
		cube_r19.texOffs(0, 6).addBox(-5.4F, -6.75F, -3.0F, 10.0F, 4.0F, 6.0F, 0.0F, false);
		cube_r19.texOffs(0, 6).addBox(-5.0F, 0.25F, -3.0F, 10.0F, 9.0F, 6.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, -9.25F, -7.0F);
		bb_main.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.3444F, -0.1925F, 0.49F);
		cube_r20.texOffs(6, 6).addBox(1.0401F, -8.5646F, -3.1F, 5.0F, 9.0F, 6.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(0.0F, -9.25F, -7.0F);
		bb_main.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.3444F, 0.1925F, -0.49F);
		cube_r21.texOffs(0, 6).addBox(-6.0401F, -8.5646F, -3.1F, 5.0F, 9.0F, 6.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(-6.5F, -1.0F, 39.0F);
		bb_main.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.7854F);
		cube_r22.texOffs(0, 2).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r22.texOffs(2, 2).addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);
		cube_r22.texOffs(0, 2).addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r22.texOffs(2, 2).addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);
		cube_r22.texOffs(0, 2).addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r22.texOffs(0, 2).addBox(-2.5F, -3.0F, -28.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r22.texOffs(0, 2).addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r22.texOffs(2, 2).addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(6.5F, -1.0F, 39.0F);
		bb_main.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 0.0F, -0.7854F);
		cube_r23.texOffs(2, 2).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r23.texOffs(0, 2).addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);
		cube_r23.texOffs(2, 2).addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r23.texOffs(0, 2).addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);
		cube_r23.texOffs(2, 2).addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r23.texOffs(0, 2).addBox(-2.5F, -3.0F, -28.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);
		cube_r23.texOffs(2, 2).addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r23.texOffs(0, 2).addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(-8.6213F, -5.9497F, 30.0F);
		bb_main.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, -0.9599F);
		cube_r24.texOffs(3, 3).addBox(-6.5F, 0.0F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(7.6213F, -4.9497F, 30.0F);
		bb_main.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 0.0F, 0.9599F);
		cube_r25.texOffs(0, 3).addBox(-1.7456F, -1.3927F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(8.6213F, -5.9497F, 3.0F);
		bb_main.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, 0.0F, 0.9599F);
		cube_r26.texOffs(3, 3).addBox(-1.5F, 0.0F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(-6.6213F, -4.9497F, 21.0F);
		bb_main.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 0.0F, -0.9599F);
		cube_r27.texOffs(3, 3).addBox(-6.828F, -2.2119F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(7.6213F, -3.9497F, 21.0F);
		bb_main.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, 0.9599F);
		cube_r28.texOffs(3, 3).addBox(-2.5647F, -1.9663F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(-6.6213F, -3.9497F, 3.0F);
		bb_main.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.0F, -0.9599F);
		cube_r29.texOffs(3, 3).addBox(-6.0088F, -2.7855F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}