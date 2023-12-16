package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entity.hollow.ClawEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClawModel<T extends ClawEntity> extends EntityModel<T> {
	private final ModelRenderer Rleg;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer Lleg;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer bb_main;
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
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer cube_r41;
	private final ModelRenderer cube_r42;
	private final ModelRenderer cube_r43;

	public ClawModel() {
		texWidth = 128;
		texHeight = 128;

		Rleg = new ModelRenderer(this);
		Rleg.setPos(-3.0F, 24.0F, 0.0F);
		Rleg.texOffs(105, 106).addBox(-5.6F, -15.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Rleg.texOffs(7, 88).addBox(-7.1F, -14.6F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
		Rleg.texOffs(32, 98).addBox(-5.5F, -15.0F, -0.5F, 2.0F, 14.0F, 6.0F, 0.0F, false);
		Rleg.texOffs(98, 39).addBox(-7.1F, -3.0F, 0.0F, 5.0F, 3.0F, 5.0F, 0.0F, false);
		Rleg.texOffs(76, 97).addBox(-4.0F, -2.0F, -7.0F, 2.0F, 2.0F, 8.0F, 0.0F, true);
		Rleg.texOffs(95, 97).addBox(-7.0F, -2.0F, -7.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -0.75F, 1.5F);
		Rleg.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.6545F, 0.0F);
		cube_r1.texOffs(105, 20).addBox(-2.0F, -0.75F, -2.5F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-2.9582F, -3.4587F, 2.5F);
		Rleg.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.1309F);
		cube_r2.texOffs(76, 102).addBox(-1.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-6.0418F, -3.4587F, 2.5F);
		Rleg.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.1309F);
		cube_r3.texOffs(81, 102).addBox(-0.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);

		Lleg = new ModelRenderer(this);
		Lleg.setPos(3.0F, 23.25F, 1.5F);
		Lleg.texOffs(104, 4).addBox(3.4F, -14.45F, -2.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Lleg.texOffs(61, 71).addBox(1.9F, -13.85F, -1.5F, 5.0F, 7.0F, 5.0F, 0.0F, true);
		Lleg.texOffs(6, 102).addBox(3.5F, -14.25F, -2.0F, 2.0F, 14.0F, 6.0F, 0.0F, false);
		Lleg.texOffs(99, 66).addBox(1.9F, -2.25F, -1.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);
		Lleg.texOffs(80, 98).addBox(5.0F, -1.25F, -8.5F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		Lleg.texOffs(98, 98).addBox(2.0F, -1.25F, -8.5F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, 0.0F, 0.0F);
		Lleg.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.6545F, 0.0F);
		cube_r4.texOffs(105, 89).addBox(0.0F, -0.75F, -2.5F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(2.9582F, -2.7087F, 1.0F);
		Lleg.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.1309F);
		cube_r5.texOffs(93, 75).addBox(-0.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(6.0418F, -2.7087F, 1.0F);
		Lleg.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.1309F);
		cube_r6.texOffs(59, 10).addBox(-1.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(81, 93).addBox(-2.3F, -27.0F, 7.0F, 4.0F, 5.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(52, 84).addBox(-2.2F, -26.9F, 11.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);
		bb_main.texOffs(6, 85).addBox(-2.1F, -26.8F, 16.0F, 4.0F, 4.0F, 6.0F, 0.0F, true);
		bb_main.texOffs(87, 44).addBox(-2.0F, -26.7F, 21.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);
		bb_main.texOffs(67, 91).addBox(-1.9F, -26.6F, 26.0F, 3.0F, 4.0F, 6.0F, 0.0F, true);
		bb_main.texOffs(84, 54).addBox(-1.8F, -26.5F, 31.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
		bb_main.texOffs(87, 103).addBox(-1.0F, -27.2F, 7.0F, 2.0F, 2.0F, 5.0F, 0.0F, true);
		bb_main.texOffs(103, 103).addBox(-1.0F, -27.1F, 12.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(103, 104).addBox(-1.0F, -27.0F, 17.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(104, 104).addBox(-1.0F, -26.9F, 22.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(104, 104).addBox(-1.0F, -26.8F, 27.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(104, 104).addBox(-1.0F, -26.7F, 32.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		bb_main.texOffs(18, 95).addBox(-2.0F, -43.0F, -3.0F, 4.0F, 4.0F, 5.0F, 0.0F, true);
		bb_main.texOffs(86, 44).addBox(-2.5F, -43.2F, -4.0F, 5.0F, 2.0F, 4.0F, 0.0F, true);
		bb_main.texOffs(42, 88).addBox(-2.6F, -47.0F, -3.4F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.48F, 0.0F, 0.0F);
		cube_r7.texOffs(20, 102).addBox(-1.0F, -1.075F, -8.7173F, 2.0F, 2.0F, 6.0F, 0.0F, true);
		cube_r7.texOffs(83, 26).addBox(-1.0F, -1.275F, -2.7173F, 2.0F, 2.0F, 12.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.4849F, -0.211F, 0.1298F);
		cube_r8.texOffs(95, 68).addBox(-2.3938F, -0.5087F, 2.444F, 1.0F, 4.0F, 5.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.4849F, 0.211F, -0.1298F);
		cube_r9.texOffs(66, 33).addBox(1.3938F, -0.5087F, 2.444F, 1.0F, 4.0F, 5.0F, 0.0F, true);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.4899F, 0.1932F, 0.102F);
		cube_r10.texOffs(87, 91).addBox(-0.0027F, -0.875F, -4.3891F, 2.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.4835F, -0.116F, -0.0607F);
		cube_r11.texOffs(87, 101).addBox(-2.2173F, -1.075F, 2.4269F, 2.0F, 2.0F, 7.0F, 0.0F, true);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.4835F, 0.116F, 0.0607F);
		cube_r12.texOffs(100, 101).addBox(0.2173F, -1.075F, 2.4269F, 2.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(0.0F, -45.1033F, -5.8201F);
		bb_main.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.4899F, -0.1932F, -0.102F);
		cube_r13.texOffs(100, 100).addBox(-1.9973F, -0.875F, -4.3891F, 2.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(-13.5F, -34.0F, 0.0F);
		bb_main.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.2054F, 0.0741F, 0.3414F);
		cube_r14.texOffs(18, 54).addBox(2.2F, 2.0F, -3.3F, 5.0F, 3.0F, 6.0F, 0.0F, false);
		cube_r14.texOffs(25, 61).addBox(2.5F, -5.0F, -3.0F, 5.0F, 19.0F, 6.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(13.5F, -34.0F, 0.0F);
		bb_main.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.2054F, -0.0741F, -0.3414F);
		cube_r15.texOffs(88, 40).addBox(-7.8F, 2.0F, -3.3F, 5.0F, 3.0F, 6.0F, 0.0F, false);
		cube_r15.texOffs(6, 73).addBox(-7.5F, -5.0F, -3.0F, 5.0F, 19.0F, 6.0F, 0.0F, true);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(11.0447F, -17.6997F, -7.7259F);
		bb_main.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.7673F, 0.3663F, 0.1667F);
		cube_r16.texOffs(14, 103).addBox(0.5F, -2.5F, -1.0F, 2.0F, 7.0F, 5.0F, 0.0F, false);
		cube_r16.texOffs(17, 68).addBox(-3.5F, -8.5F, -2.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
		cube_r16.texOffs(44, 7).addBox(-3.5F, 2.5F, -2.0F, 7.0F, 6.0F, 7.0F, 0.0F, true);
		cube_r16.texOffs(27, 102).addBox(-2.5F, -2.5F, -1.0F, 2.0F, 7.0F, 5.0F, 0.0F, true);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(-11.0447F, -17.6997F, -7.7259F);
		bb_main.addChild(cube_r17);
		setRotationAngle(cube_r17, -0.7673F, -0.3663F, -0.1667F);
		cube_r17.texOffs(48, 64).addBox(0.5F, -2.5F, -1.0F, 2.0F, 7.0F, 5.0F, 0.0F, false);
		cube_r17.texOffs(4, 102).addBox(-2.5F, -2.5F, -1.0F, 2.0F, 7.0F, 5.0F, 0.0F, false);
		cube_r17.texOffs(63, 60).addBox(-3.5F, -8.5F, -2.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
		cube_r17.texOffs(70, 61).addBox(-3.5F, 2.5F, -2.0F, 7.0F, 6.0F, 7.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(9.0335F, -10.5009F, -15.1792F);
		bb_main.addChild(cube_r18);
		setRotationAngle(cube_r18, 1.0293F, 0.3639F, 0.1718F);
		cube_r18.texOffs(100, 24).addBox(-4.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		cube_r18.texOffs(18, 106).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		cube_r18.texOffs(100, 106).addBox(-2.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(7.7705F, -7.2411F, -17.1237F);
		bb_main.addChild(cube_r19);
		setRotationAngle(cube_r19, 1.9892F, 0.3639F, 0.1718F);
		cube_r19.texOffs(107, 109).addBox(-4.25F, 0.0F, -2.8F, 0.0F, 1.0F, 4.0F, 0.0F, false);
		cube_r19.texOffs(100, 109).addBox(-2.25F, 0.0F, -2.8F, 0.0F, 1.0F, 4.0F, 0.0F, false);
		cube_r19.texOffs(87, 107).addBox(-0.25F, 0.0F, -2.8F, 0.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(-5.811F, -8.2262F, -10.3623F);
		bb_main.addChild(cube_r20);
		setRotationAngle(cube_r20, -1.302F, 0.0662F, 2.7637F);
		cube_r20.texOffs(40, 106).addBox(-0.475F, -1.4711F, -1.4754F, 1.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(-5.811F, -8.2262F, -10.3623F);
		bb_main.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.342F, 0.0662F, 2.7637F);
		cube_r21.texOffs(105, 25).addBox(-0.275F, -1.8883F, -3.5472F, 0.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(-7.7705F, -7.2411F, -17.1237F);
		bb_main.addChild(cube_r22);
		setRotationAngle(cube_r22, 1.9892F, -0.3639F, -0.1718F);
		cube_r22.texOffs(93, 109).addBox(3.75F, 0.0F, -2.8F, 0.0F, 1.0F, 4.0F, 0.0F, false);
		cube_r22.texOffs(4, 64).addBox(1.75F, 0.0F, -2.8F, 0.0F, 1.0F, 4.0F, 0.0F, true);
		cube_r22.texOffs(59, 90).addBox(-0.25F, 0.0F, -2.8F, 0.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(-9.0335F, -10.5009F, -15.1792F);
		bb_main.addChild(cube_r23);
		setRotationAngle(cube_r23, 1.0293F, -0.3639F, -0.1718F);
		cube_r23.texOffs(100, 103).addBox(3.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		cube_r23.texOffs(80, 100).addBox(1.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		cube_r23.texOffs(98, 26).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(5.811F, -8.2262F, -10.3623F);
		bb_main.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.342F, -0.0662F, -2.7637F);
		cube_r24.texOffs(27, 61).addBox(-0.225F, -1.8883F, -3.5472F, 0.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(5.811F, -8.2262F, -10.3623F);
		bb_main.addChild(cube_r25);
		setRotationAngle(cube_r25, -1.302F, -0.0662F, -2.7637F);
		cube_r25.texOffs(75, 77).addBox(-0.525F, -1.4711F, -1.4754F, 1.0F, 2.0F, 7.0F, 0.0F, true);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(11.5F, -33.0F, 0.0F);
		bb_main.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.2333F, 0.1198F, -0.4659F);
		cube_r26.texOffs(89, 93).addBox(-8.2F, -2.4F, -0.4F, 5.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(-11.5F, -33.0F, 0.0F);
		bb_main.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.2333F, -0.1198F, 0.4659F);
		cube_r27.texOffs(4, 93).addBox(3.2F, -2.4F, -0.4F, 5.0F, 2.0F, 4.0F, 0.0F, true);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(4.9F, -35.0012F, -3.3951F);
		bb_main.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.2657F, -0.1685F, -0.0456F);
		cube_r28.texOffs(28, 66).addBox(-2.5F, -3.0F, -0.8F, 6.0F, 7.0F, 1.0F, 0.0F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(1.653F, -21.0017F, 1.0455F);
		bb_main.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.2739F, -0.2947F, -0.0814F);
		cube_r29.texOffs(61, 108).addBox(-1.0F, -7.6F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r29.texOffs(100, 108).addBox(-1.0F, -5.3F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		cube_r29.texOffs(57, 70).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r29.texOffs(32, 107).addBox(-1.0F, -9.9F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setPos(-1.653F, -21.0017F, 1.0455F);
		bb_main.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.2739F, 0.2947F, 0.0814F);
		cube_r30.texOffs(12, 108).addBox(-1.0F, -9.9F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r30.texOffs(107, 107).addBox(-1.0F, -7.6F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r30.texOffs(44, 107).addBox(-1.0F, -5.3F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r30.texOffs(10, 24).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setPos(7.0F, -35.0F, 1.0F);
		bb_main.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.2618F, 0.0F, 0.0F);
		cube_r31.texOffs(43, 27).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, 0.0F, true);
		cube_r31.texOffs(31, 89).addBox(-16.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, 0.0F, false);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setPos(-4.9F, -35.0012F, -3.3951F);
		bb_main.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.2657F, 0.1685F, 0.0456F);
		cube_r32.texOffs(24, 58).addBox(-3.5F, -3.0F, -0.8F, 6.0F, 7.0F, 1.0F, 0.0F, false);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setPos(0.0F, -32.0F, -12.0F);
		bb_main.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.2618F, 0.0F, 0.0F);
		cube_r33.texOffs(16, 49).addBox(-1.0F, 2.0F, 8.5F, 2.0F, 13.0F, 8.0F, 0.0F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setPos(0.0F, -41.0F, 0.0F);
		bb_main.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.2618F, 0.0F, 0.0F);
		cube_r34.texOffs(75, 75).addBox(-2.0F, -0.3F, -5.0F, 4.0F, 4.0F, 9.0F, 0.0F, false);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setPos(8.0F, -41.0F, -1.5F);
		bb_main.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.2502F, -0.0779F, 0.2956F);
		cube_r35.texOffs(31, 33).addBox(-6.0F, 2.0F, -3.5F, 8.0F, 4.0F, 9.0F, 0.0F, true);

		cube_r36 = new ModelRenderer(this);
		cube_r36.setPos(-8.0F, -41.0F, -1.5F);
		bb_main.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.2502F, 0.0779F, -0.2956F);
		cube_r36.texOffs(61, 15).addBox(-2.0F, 2.0F, -3.5F, 8.0F, 4.0F, 9.0F, 0.0F, false);

		cube_r37 = new ModelRenderer(this);
		cube_r37.setPos(0.0F, -28.3878F, 2.6F);
		bb_main.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.258F, -0.045F, 0.1687F);
		cube_r37.texOffs(0, 83).addBox(1.0059F, -10.75F, -3.9F, 4.0F, 15.0F, 8.0F, 0.0F, false);

		cube_r38 = new ModelRenderer(this);
		cube_r38.setPos(0.0F, -28.3878F, 2.6F);
		bb_main.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.258F, 0.045F, -0.1687F);
		cube_r38.texOffs(0, 82).addBox(-5.0059F, -10.75F, -3.9F, 4.0F, 15.0F, 8.0F, 0.0F, false);

		cube_r39 = new ModelRenderer(this);
		cube_r39.setPos(0.0F, -28.3878F, 2.6F);
		bb_main.addChild(cube_r39);
		setRotationAngle(cube_r39, 0.228F, 0.1298F, -0.5087F);
		cube_r39.texOffs(44, 78).addBox(-1.1987F, 0.8179F, -3.6F, 5.0F, 10.0F, 7.0F, 0.0F, false);

		cube_r40 = new ModelRenderer(this);
		cube_r40.setPos(0.0F, -28.3878F, 2.6F);
		bb_main.addChild(cube_r40);
		setRotationAngle(cube_r40, 0.228F, -0.1298F, 0.5087F);
		cube_r40.texOffs(12, 78).addBox(-3.8013F, 0.8179F, -3.6F, 5.0F, 10.0F, 7.0F, 0.0F, false);

		cube_r41 = new ModelRenderer(this);
		cube_r41.setPos(0.0F, -28.3878F, 2.6F);
		bb_main.addChild(cube_r41);
		setRotationAngle(cube_r41, 0.2618F, 0.0F, 0.0F);
		cube_r41.texOffs(31, 10).addBox(-4.0F, -5.6122F, -4.1F, 8.0F, 13.0F, 8.0F, 0.0F, false);

		cube_r42 = new ModelRenderer(this);
		cube_r42.setPos(7.5F, -18.8F, 2.75F);
		bb_main.addChild(cube_r42);
		setRotationAngle(cube_r42, -0.2182F, 0.0F, -0.2182F);
		cube_r42.texOffs(56, 82).addBox(-3.8F, -4.5F, -2.05F, 5.0F, 9.0F, 6.0F, 0.0F, false);

		cube_r43 = new ModelRenderer(this);
		cube_r43.setPos(-7.5F, -18.8F, 2.75F);
		bb_main.addChild(cube_r43);
		setRotationAngle(cube_r43, -0.2182F, 0.0F, 0.2182F);
		cube_r43.texOffs(18, 82).addBox(-1.8F, -4.5F, -2.05F, 5.0F, 9.0F, 6.0F, 0.0F, true);
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
		Lleg.render(matrixStack, buffer, packedLight, packedOverlay);
		Rleg.render(matrixStack, buffer, packedLight, packedOverlay);

	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}