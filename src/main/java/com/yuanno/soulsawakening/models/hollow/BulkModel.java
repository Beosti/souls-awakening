package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entity.hollow.BulkEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BulkModel<T extends BulkEntity> extends EntityModel<T> {
	private final ModelRenderer Rleg;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer Lleg;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer Lleg2;
	private final ModelRenderer Rleg2;
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

	public BulkModel() {
		texWidth = 128;
		texHeight = 128;

		Rleg = new ModelRenderer(this);
		Rleg.setPos(-3.0F, 24.0F, 0.0F);
		Rleg.texOffs(39, 41).addBox(-3.6F, -13.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Rleg.texOffs(41, 59).addBox(-5.1F, -11.6F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		Rleg.texOffs(49, 49).addBox(-3.5F, -12.0F, -0.5F, 2.0F, 11.0F, 6.0F, 0.0F, false);
		Rleg.texOffs(10, 71).addBox(-5.1F, -3.0F, 0.0F, 5.0F, 3.0F, 5.0F, 0.0F, false);
		Rleg.texOffs(39, 83).addBox(-2.0F, -2.0F, -4.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		Rleg.texOffs(21, 80).addBox(-5.0F, -2.0F, -4.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(2.0F, -0.75F, 1.5F);
		Rleg.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.6545F, 0.0F);
		cube_r1.texOffs(13, 74).addBox(-2.0F, -0.75F, -0.5F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-0.9582F, -3.4587F, 2.5F);
		Rleg.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.1309F);
		cube_r2.texOffs(76, 102).addBox(-1.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-4.0418F, -3.4587F, 2.5F);
		Rleg.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.1309F);
		cube_r3.texOffs(81, 102).addBox(-0.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);

		Lleg = new ModelRenderer(this);
		Lleg.setPos(3.0F, 24.0F, 0.0F);
		Lleg.texOffs(39, 41).addBox(1.4F, -13.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		Lleg.texOffs(49, 49).addBox(1.5F, -12.0F, -0.5F, 2.0F, 11.0F, 6.0F, 0.0F, true);
		Lleg.texOffs(10, 71).addBox(-0.1F, -3.0F, 0.0F, 5.0F, 3.0F, 5.0F, 0.0F, true);
		Lleg.texOffs(21, 80).addBox(3.0F, -2.0F, -4.0F, 2.0F, 2.0F, 5.0F, 0.0F, true);
		Lleg.texOffs(39, 83).addBox(0.0F, -2.0F, -4.0F, 2.0F, 2.0F, 5.0F, 0.0F, true);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-2.0F, -0.75F, 1.5F);
		Lleg.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.6545F, 0.0F);
		cube_r4.texOffs(13, 74).addBox(0.0F, -0.75F, -0.5F, 2.0F, 1.0F, 4.0F, 0.0F, true);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.9582F, -3.4587F, 2.5F);
		Lleg.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.1309F);
		cube_r5.texOffs(76, 102).addBox(-0.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(4.0418F, -3.4587F, 2.5F);
		Lleg.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.1309F);
		cube_r6.texOffs(81, 102).addBox(-1.6F, -3.55F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);

		Lleg2 = new ModelRenderer(this);
		Lleg2.setPos(-3.0F, 24.0F, 0.0F);
		

		Rleg2 = new ModelRenderer(this);
		Rleg2.setPos(3.0F, 24.0F, 0.0F);
		Rleg2.texOffs(41, 59).addBox(-0.1F, -11.6F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, true);

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(62, 74).addBox(-2.5F, -37.2F, -8.0F, 5.0F, 2.0F, 4.0F, 0.0F, false);
		bb_main.texOffs(79, 69).addBox(-2.6F, -41.0F, -7.4F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(19.0115F, -14.3488F, -12.4783F);
		bb_main.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.5782F, 0.1836F, -0.1432F);
		cube_r7.texOffs(77, 77).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 7.0F, 0.0F, true);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(-15.5844F, -11.6842F, -11.4037F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.5742F, 0.147F, 0.3563F);
		cube_r8.texOffs(0, 7).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 7.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(19.8847F, -11.0902F, -10.3291F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.5831F, 0.2201F, -0.1188F);
		cube_r9.texOffs(77, 42).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 7.0F, 0.0F, true);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(-19.0115F, -14.3488F, -12.4783F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.5782F, -0.1836F, 0.1432F);
		cube_r10.texOffs(77, 77).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 7.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(14.0F, -25.3246F, -0.5632F);
		bb_main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.5672F, 0.0F, -0.2618F);
		cube_r11.texOffs(0, 0).addBox(-4.5F, 1.1335F, -13.9424F, 9.0F, 9.0F, 17.0F, 0.0F, true);
		cube_r11.texOffs(0, 17).addBox(-1.5F, 2.1335F, -11.9424F, 3.0F, 9.0F, 17.0F, 0.0F, true);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(-19.8847F, -11.0902F, -10.3291F);
		bb_main.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.5831F, -0.2201F, 0.1188F);
		cube_r12.texOffs(77, 42).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 7.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(15.5844F, -11.6842F, -11.4037F);
		bb_main.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.5742F, -0.147F, -0.3563F);
		cube_r13.texOffs(0, 7).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 7.0F, 0.0F, true);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(-14.0F, -25.3246F, -0.5632F);
		bb_main.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.5672F, 0.0F, 0.2618F);
		cube_r14.texOffs(0, 17).addBox(-1.5F, 2.1335F, -11.9424F, 3.0F, 9.0F, 17.0F, 0.0F, false);
		cube_r14.texOffs(0, 0).addBox(-4.5F, 1.1335F, -13.9424F, 9.0F, 9.0F, 17.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(14.0F, -25.3246F, -0.5632F);
		bb_main.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, -0.2618F);
		cube_r15.texOffs(59, 0).addBox(-4.0F, -11.6754F, -2.4368F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		cube_r15.texOffs(0, 7).addBox(-3.5F, -4.6754F, -1.9368F, 7.0F, 12.0F, 7.0F, 0.0F, true);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(-14.0F, -25.3246F, -0.5632F);
		bb_main.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, 0.2618F);
		cube_r16.texOffs(0, 7).addBox(-3.5F, -4.6754F, -1.9368F, 7.0F, 12.0F, 7.0F, 0.0F, false);
		cube_r16.texOffs(59, 0).addBox(-4.0F, -11.6754F, -2.4368F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(0.0F, -39.1263F, -0.8194F);
		bb_main.addChild(cube_r17);
		setRotationAngle(cube_r17, -0.48F, 0.0F, 0.0F);
		cube_r17.texOffs(15, 74).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 2.0F, 6.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(0.0F, -35.1444F, -4.7516F);
		bb_main.addChild(cube_r18);
		setRotationAngle(cube_r18, -0.4531F, -0.272F, -0.5042F);
		cube_r18.texOffs(24, 80).addBox(0.0F, -1.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, -35.1444F, -4.7516F);
		bb_main.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.4531F, 0.272F, 0.5042F);
		cube_r19.texOffs(24, 80).addBox(-2.0F, -1.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F, true);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, -21.0F, -7.0F);
		bb_main.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.2333F, -0.1198F, 0.4659F);
		cube_r20.texOffs(9, 9).addBox(-9.8F, -1.0F, 6.0F, 8.0F, 2.0F, 9.0F, 0.0F, true);
		cube_r20.texOffs(18, 9).addBox(-5.3F, 5.0F, 6.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);
		cube_r20.texOffs(78, 55).addBox(-7.6F, 2.0F, 6.0F, 7.0F, 2.0F, 9.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(9.0085F, -35.8701F, -0.114F);
		bb_main.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.1525F, 0.2136F, -0.9435F);
		cube_r21.texOffs(74, 26).addBox(-5.5F, -4.0F, -5.5F, 8.0F, 2.0F, 11.0F, 0.0F, true);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(0.0F, -21.0F, -7.0F);
		bb_main.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.2333F, 0.1198F, -0.4659F);
		cube_r22.texOffs(78, 55).addBox(0.3F, 2.0F, 6.0F, 7.0F, 2.0F, 9.0F, 0.0F, true);
		cube_r22.texOffs(18, 9).addBox(-1.1F, 5.0F, 6.0F, 6.0F, 2.0F, 9.0F, 0.0F, true);
		cube_r22.texOffs(9, 9).addBox(1.8F, -1.0F, 6.0F, 8.0F, 2.0F, 9.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(-9.0085F, -35.8701F, -0.114F);
		bb_main.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.1525F, -0.2136F, 0.9435F);
		cube_r23.texOffs(74, 26).addBox(-3.0F, -4.0F, -5.5F, 8.0F, 2.0F, 11.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(0.0F, -35.1444F, -4.7516F);
		bb_main.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.5236F, 0.0F, 0.0F);
		cube_r24.texOffs(48, 32).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(1.5982F, -37.718F, -2.834F);
		bb_main.addChild(cube_r25);
		setRotationAngle(cube_r25, -0.0581F, 0.5787F, 0.8455F);
		cube_r25.texOffs(78, 73).addBox(-1.6F, -1.05F, -3.5F, 3.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(-1.5982F, -37.718F, -2.834F);
		bb_main.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.0581F, -0.5787F, -0.8455F);
		cube_r26.texOffs(78, 73).addBox(-2.1F, -1.05F, -3.5F, 3.0F, 2.0F, 7.0F, 0.0F, true);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(0.0F, -25.3878F, 2.6F);
		bb_main.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.258F, 0.045F, -0.1687F);
		cube_r27.texOffs(0, 83).addBox(-7.0059F, -10.75F, -3.9F, 6.0F, 17.0F, 8.0F, 0.0F, true);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(11.5F, -30.0F, 0.0F);
		bb_main.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.2333F, 0.1198F, -0.4659F);
		cube_r28.texOffs(84, 22).addBox(-7.8F, -2.0F, -0.4F, 5.0F, 2.0F, 4.0F, 0.0F, true);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(-11.5F, -30.0F, 0.0F);
		bb_main.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.2333F, -0.1198F, 0.4659F);
		cube_r29.texOffs(84, 22).addBox(2.8F, -2.0F, -0.4F, 5.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setPos(4.9F, -32.0012F, -3.3951F);
		bb_main.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.2657F, -0.1685F, -0.0456F);
		cube_r30.texOffs(23, 7).addBox(-2.5F, -3.0F, -0.8F, 6.0F, 7.0F, 1.0F, 0.0F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setPos(7.0F, -32.0F, 1.0F);
		bb_main.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.2618F, 0.0F, 0.0F);
		cube_r31.texOffs(47, 74).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, 0.0F, true);
		cube_r31.texOffs(47, 74).addBox(-16.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, 0.0F, false);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setPos(-4.9F, -32.0012F, -3.3951F);
		bb_main.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.2657F, 0.1685F, 0.0456F);
		cube_r32.texOffs(23, 7).addBox(-3.5F, -3.0F, -0.8F, 6.0F, 7.0F, 1.0F, 0.0F, true);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setPos(0.0F, -29.0F, -12.0F);
		bb_main.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.2618F, 0.0F, 0.0F);
		cube_r33.texOffs(0, 76).addBox(-1.0F, 2.0F, 8.5F, 2.0F, 15.0F, 8.0F, 0.0F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setPos(0.0F, -38.0F, 0.0F);
		bb_main.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.2618F, 0.0F, 0.0F);
		cube_r34.texOffs(70, 0).addBox(-2.0F, -0.3F, -5.0F, 4.0F, 4.0F, 9.0F, 0.0F, false);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setPos(8.0F, -38.0F, -1.5F);
		bb_main.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.2502F, -0.0779F, 0.2956F);
		cube_r35.texOffs(67, 36).addBox(-6.0F, 2.0F, -3.5F, 8.0F, 4.0F, 9.0F, 0.0F, true);

		cube_r36 = new ModelRenderer(this);
		cube_r36.setPos(-8.0F, -38.0F, -1.5F);
		bb_main.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.2502F, 0.0779F, -0.2956F);
		cube_r36.texOffs(67, 36).addBox(-2.0F, 2.0F, -3.5F, 8.0F, 4.0F, 9.0F, 0.0F, false);

		cube_r37 = new ModelRenderer(this);
		cube_r37.setPos(0.0F, -25.3878F, 2.6F);
		bb_main.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.258F, -0.045F, 0.1687F);
		cube_r37.texOffs(0, 83).addBox(1.0059F, -10.75F, -3.9F, 6.0F, 17.0F, 8.0F, 0.0F, false);

		cube_r38 = new ModelRenderer(this);
		cube_r38.setPos(0.0F, -25.3878F, 2.6F);
		bb_main.addChild(cube_r38);
		setRotationAngle(cube_r38, 0.2427F, 0.0992F, -0.3806F);
		cube_r38.texOffs(24, 66).addBox(-2.1987F, 0.8179F, -3.6F, 6.0F, 10.0F, 7.0F, 0.0F, true);

		cube_r39 = new ModelRenderer(this);
		cube_r39.setPos(0.0F, -25.3878F, 2.6F);
		bb_main.addChild(cube_r39);
		setRotationAngle(cube_r39, 0.2427F, -0.0992F, 0.3806F);
		cube_r39.texOffs(24, 66).addBox(-3.8013F, 0.8179F, -3.6F, 6.0F, 10.0F, 7.0F, 0.0F, false);

		cube_r40 = new ModelRenderer(this);
		cube_r40.setPos(0.0F, -25.3878F, 2.6F);
		bb_main.addChild(cube_r40);
		setRotationAngle(cube_r40, 0.2618F, 0.0F, 0.0F);
		cube_r40.texOffs(18, 27).addBox(-4.0F, -5.6122F, -4.1F, 8.0F, 15.0F, 8.0F, 0.0F, false);

		cube_r41 = new ModelRenderer(this);
		cube_r41.setPos(5.5F, -15.8F, 2.75F);
		bb_main.addChild(cube_r41);
		setRotationAngle(cube_r41, -0.2182F, 0.0F, -0.2182F);
		cube_r41.texOffs(12, 6).addBox(-3.8F, -2.3F, -2.05F, 5.0F, 7.0F, 6.0F, 0.0F, false);

		cube_r42 = new ModelRenderer(this);
		cube_r42.setPos(-5.5F, -15.8F, 2.75F);
		bb_main.addChild(cube_r42);
		setRotationAngle(cube_r42, -0.2182F, 0.0F, 0.2182F);
		cube_r42.texOffs(12, 6).addBox(-1.8F, -2.3F, -2.05F, 5.0F, 7.0F, 6.0F, 0.0F, true);
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
		Lleg2.render(matrixStack, buffer, packedLight, packedOverlay);
		Rleg2.render(matrixStack, buffer, packedLight, packedOverlay);

	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}