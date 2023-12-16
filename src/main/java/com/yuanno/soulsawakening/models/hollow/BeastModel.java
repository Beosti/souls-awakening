package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entity.hollow.BeastEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BeastModel<T extends BeastEntity> extends EntityModel<T> {
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

	public BeastModel() {
		texWidth = 64;
		texHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(bb_main, 0.0F, 3.1416F, 0.0F);
		bb_main.texOffs(0, 6).addBox(-1.5F, -6.0F, -4.0F, 3.0F, 2.0F, 6.0F, 0.0F, false);
		bb_main.texOffs(0, 1).addBox(-2.0F, -6.4F, 3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(0, 2).addBox(-3.8F, -1.7F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(0, 2).addBox(1.6F, -1.7F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		bb_main.texOffs(0, 2).addBox(-2.6F, -1.7F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(0, 2).addBox(2.8F, -1.7F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		bb_main.texOffs(0, 1).addBox(2.5F, -3.0F, -4.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(0, 1).addBox(-3.5F, -3.0F, -4.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		bb_main.texOffs(0, 2).addBox(-1.0F, -6.3F, 4.2F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(0, 4).addBox(-0.5F, -6.6F, -4.5F, 1.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-3.3247F, -6.5615F, 4.9869F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.4007F, -0.515F, -0.07F);
		cube_r1.texOffs(0, 0).addBox(-0.15F, -1.0F, -0.25F, 0.0F, 2.0F, 0.0F, 0.0F, true);
		cube_r1.texOffs(0, 0).addBox(-0.65F, 0.0F, 0.55F, 0.0F, 2.0F, 0.0F, 0.0F, true);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(3.3247F, -6.5615F, 4.9869F);
		bb_main.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.4007F, 0.515F, 0.07F);
		cube_r2.texOffs(0, 0).addBox(0.15F, 0.0F, 0.55F, 0.0F, 2.0F, 0.0F, 0.0F, false);
		cube_r2.texOffs(0, 0).addBox(-0.35F, -1.0F, -0.25F, 0.0F, 2.0F, 0.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-1.9617F, -6.0F, 1.887F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.18F, -0.3007F, 0.5509F);
		cube_r3.texOffs(5, 5).addBox(0.1F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, 0.0F, true);
		cube_r3.texOffs(0, 5).addBox(0.1F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-4.25F, -7.0F, -0.75F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.499F, -0.6312F, -0.2902F);
		cube_r4.texOffs(0, 0).addBox(-0.25F, 1.6F, -0.25F, 0.0F, 2.0F, 0.0F, 0.0F, true);
		cube_r4.texOffs(0, 0).addBox(-0.25F, 1.0F, -1.25F, 0.0F, 2.0F, 0.0F, 0.0F, true);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(4.25F, -7.0F, -0.75F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.499F, 0.6312F, 0.2902F);
		cube_r5.texOffs(0, 0).addBox(-0.25F, 1.0F, -1.25F, 0.0F, 2.0F, 0.0F, 0.0F, false);
		cube_r5.texOffs(0, 0).addBox(-0.25F, 1.6F, -0.25F, 0.0F, 2.0F, 0.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, -4.925F, 7.1F);
		bb_main.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.4363F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 4).addBox(-1.1F, -0.275F, -1.7F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		cube_r6.texOffs(0, 3).addBox(-1.0F, -0.975F, -1.9F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, -4.925F, 7.1F);
		bb_main.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.5945F, -0.0579F, 0.0391F);
		cube_r7.texOffs(4, 4).addBox(0.1698F, -1.2191F, -2.4541F, 0.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.0F, -4.925F, 7.1F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.5945F, 0.0579F, -0.0391F);
		cube_r8.texOffs(0, 4).addBox(-1.0698F, -1.2191F, -2.4541F, 0.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(-3.0F, -0.403F, -3.9797F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.0087F, 0.0F, 0.0F);
		cube_r9.texOffs(0, 1).addBox(-0.7F, -1.15F, -0.55F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		cube_r9.texOffs(0, 1).addBox(5.2F, 0.05F, -0.65F, 1.0F, 0.0F, 1.0F, 0.0F, false);
		cube_r9.texOffs(0, 1).addBox(5.3F, -1.15F, -0.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r9.texOffs(0, 1).addBox(-0.8F, 0.05F, -0.65F, 1.0F, 0.0F, 1.0F, 0.0F, true);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(-3.0F, -0.9059F, -3.5358F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.2531F, 0.0F, 0.0F);
		cube_r10.texOffs(0, 0).addBox(-0.7F, -0.65F, 0.05F, 1.0F, 1.0F, 0.0F, 0.0F, true);
		cube_r10.texOffs(0, 0).addBox(5.3F, -0.65F, 0.05F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(2.5F, -3.8F, -3.0F);
		bb_main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.9637F, -0.1133F, -0.2403F);
		cube_r11.texOffs(0, 2).addBox(-1.1F, -1.0F, -1.8F, 2.0F, 2.0F, 3.0F, 0.0F, true);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(-2.5F, -3.8F, -3.0F);
		bb_main.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.9637F, 0.1133F, 0.2403F);
		cube_r12.texOffs(0, 2).addBox(-1.1F, -1.0F, -1.8F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(2.1035F, -5.0667F, -3.0389F);
		bb_main.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.4519F, -0.1395F, -0.2223F);
		cube_r13.texOffs(0, 2).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(-2.1035F, -5.0667F, -3.0389F);
		bb_main.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.4519F, 0.1395F, 0.2223F);
		cube_r14.texOffs(0, 2).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(3.9F, -1.0F, 3.9F);
		bb_main.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.6504F, -0.0795F, 0.1041F);
		cube_r15.texOffs(0, 1).addBox(-1.7F, -2.0F, 1.3F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(-3.9F, -1.0F, 3.9F);
		bb_main.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.6504F, 0.0795F, -0.1041F);
		cube_r16.texOffs(0, 1).addBox(0.2F, -2.0F, 1.3F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(-1.5F, -5.0F, 5.0F);
		bb_main.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.7418F, 0.0F);
		cube_r17.texOffs(4, 2).addBox(0.1F, -1.2F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(1.5F, -5.0F, 5.0F);
		bb_main.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, -0.7418F, 0.0F);
		cube_r18.texOffs(4, 2).addBox(-1.1F, -1.2F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(1.9617F, -6.0F, 1.887F);
		bb_main.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.18F, 0.3007F, -0.5509F);
		cube_r19.texOffs(5, 5).addBox(-1.1F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(3.0F, -5.0F, 4.0F);
		bb_main.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.3491F, 1.1781F, 0.0F);
		cube_r20.texOffs(0, 2).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(-3.0F, -5.0F, 4.0F);
		bb_main.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.3491F, -1.1781F, 0.0F);
		cube_r21.texOffs(0, 2).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(1.3264F, -5.0F, 1.4076F);
		bb_main.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.48F, 0.0F);
		cube_r22.texOffs(6, 5).addBox(-1.1F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, true);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(-1.3264F, -5.0F, 1.4076F);
		bb_main.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, -0.48F, 0.0F);
		cube_r23.texOffs(6, 5).addBox(-0.9F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		matrixStack.scale(3, 3, 3);

		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.setPos(0, 8.3F, 0);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}