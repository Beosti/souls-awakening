package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entities.hollow.ThornsEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ThornsModel<T extends ThornsEntity> extends EntityModel<T> {
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
	private final ModelRenderer uppermasksection1;
	private final ModelRenderer lowermasksection1;
	private final ModelRenderer lowermasksection2;
	private final ModelRenderer lowermasksection3;
	private final ModelRenderer lowermask4_r1;
	private final ModelRenderer upperleftleg;
	private final ModelRenderer lowerleftleg;
	private final ModelRenderer leftfootsection1;
	private final ModelRenderer leftfootsection2;
	private final ModelRenderer upperrightleg;
	private final ModelRenderer lowerrightleg;
	private final ModelRenderer rightfootsection1;
	private final ModelRenderer rightfootsection2;

	public ThornsModel() {
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
		bodysection1.setPos(0.0F, -6.75F, 0.0F);
		bodysection1.texOffs(8, 151).addBox(-9.0F, -4.75F, -5.5F, 18.0F, 8.0F, 11.0F, 0.0F, false);
		bodysection1.texOffs(75, 164).addBox(0.0F, -4.75F, 4.5F, 0.0F, 11.0F, 7.0F, 0.0F, false);

		bodysection2 = new ModelRenderer(this);
		bodysection2.setPos(0.0F, -4.25F, 0.0F);
		bodysection1.addChild(bodysection2);
		setRotationAngle(bodysection2, 0.1309F, 0.0F, 0.0F);
		bodysection2.texOffs(9, 118).addBox(-8.0F, -2.1F, -6.0F, 16.0F, 3.0F, 12.0F, 0.2F, false);
		bodysection2.texOffs(67, 135).addBox(-8.0F, -7.5F, -6.0F, 5.0F, 5.0F, 12.0F, 0.2F, false);
		bodysection2.texOffs(67, 153).addBox(3.0F, -7.5F, -6.0F, 5.0F, 5.0F, 12.0F, 0.2F, false);
		bodysection2.texOffs(9, 134).addBox(-8.0F, -11.9F, -6.0F, 16.0F, 4.0F, 12.0F, 0.2F, false);

		bodysection3 = new ModelRenderer(this);
		bodysection3.setPos(0.0F, -6.5F, 0.0F);
		bodysection2.addChild(bodysection3);
		setRotationAngle(bodysection3, 0.0873F, 0.0F, 0.0F);
		bodysection3.texOffs(5, 95).addBox(-9.0F, -12.0F, -7.0F, 18.0F, 8.0F, 14.0F, 0.0F, false);
		bodysection3.texOffs(90, 165).addBox(0.0F, -13.0F, 6.0F, 0.0F, 9.0F, 8.0F, 0.0F, false);

		bodysection4 = new ModelRenderer(this);
		bodysection4.setPos(0.0F, -9.5F, 0.0F);
		bodysection3.addChild(bodysection4);
		setRotationAngle(bodysection4, 0.0873F, 0.0F, 0.0F);
		bodysection4.texOffs(0, 171).addBox(-10.5F, -11.75F, -8.0F, 21.0F, 13.0F, 16.0F, 0.0F, false);
		bodysection4.texOffs(75, 167).addBox(0.0F, -16.75F, -1.0F, 0.0F, 17.0F, 16.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setPos(10.0F, -5.75F, 0.0F);
		bodysection4.addChild(leftarm);
		setRotationAngle(leftarm, 0.0F, -0.2618F, -0.1309F);
		leftarm.texOffs(68, 17).addBox(-2.5F, -4.0F, -5.0F, 9.0F, 8.0F, 10.0F, 0.6F, false);

		upperleftarm = new ModelRenderer(this);
		upperleftarm.setPos(4.0F, -0.5F, 0.0F);
		leftarm.addChild(upperleftarm);
		setRotationAngle(upperleftarm, 0.0F, 0.0F, 0.0873F);
		upperleftarm.texOffs(33, 6).addBox(-4.0F, -2.0F, -4.5F, 8.0F, 20.0F, 9.0F, 0.3F, false);

		lowerleftarm = new ModelRenderer(this);
		lowerleftarm.setPos(0.5F, 18.25F, 0.0F);
		upperleftarm.addChild(lowerleftarm);
		setRotationAngle(lowerleftarm, -0.6545F, 0.0F, 0.0F);
		lowerleftarm.texOffs(0, 0).addBox(-4.0F, -1.5F, -4.5F, 7.0F, 26.0F, 9.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setPos(-10.0F, -5.75F, 0.0F);
		bodysection4.addChild(rightarm);
		setRotationAngle(rightarm, 0.0F, 0.2618F, 0.1309F);
		rightarm.texOffs(68, 53).addBox(-6.5F, -4.0F, -5.0F, 9.0F, 8.0F, 10.0F, 0.6F, false);

		upperrightarm = new ModelRenderer(this);
		upperrightarm.setPos(-4.0F, -0.5F, 0.0F);
		rightarm.addChild(upperrightarm);
		setRotationAngle(upperrightarm, 0.0F, 0.0F, -0.0873F);
		upperrightarm.texOffs(33, 42).addBox(-4.0F, -2.0F, -4.5F, 8.0F, 20.0F, 9.0F, 0.3F, false);

		lowerrightarm = new ModelRenderer(this);
		lowerrightarm.setPos(-0.5F, 18.25F, 0.0F);
		upperrightarm.addChild(lowerrightarm);
		setRotationAngle(lowerrightarm, -0.6545F, 0.0F, 0.0F);
		lowerrightarm.texOffs(0, 36).addBox(-3.0F, -1.5F, -4.5F, 7.0F, 26.0F, 9.0F, 0.0F, false);

		headsection1 = new ModelRenderer(this);
		headsection1.setPos(0.0F, -6.3854F, -4.5399F);
		bodysection4.addChild(headsection1);
		setRotationAngle(headsection1, -0.1745F, 0.0F, 0.0F);
		headsection1.texOffs(126, 20).addBox(-5.5F, -6.5F, -3.5F, 11.0F, 11.0F, 7.0F, 0.0F, false);

		headsection2 = new ModelRenderer(this);
		headsection2.setPos(0.0F, -0.25F, -2.25F);
		headsection1.addChild(headsection2);
		setRotationAngle(headsection2, 0.1745F, 0.0F, 0.0F);
		headsection2.texOffs(125, 0).addBox(-6.0F, -6.4456F, -5.7985F, 12.0F, 12.0F, 7.0F, 0.0F, false);

		mask = new ModelRenderer(this);
		mask.setPos(0.0F, -0.5706F, -4.7985F);
		headsection2.addChild(mask);
		setRotationAngle(mask, 0.1309F, 0.0F, 0.0F);
		mask.texOffs(168, 12).addBox(-6.0F, -3.55F, 0.0F, 12.0F, 10.0F, 2.0F, 0.15F, false);

		uppermasksection1 = new ModelRenderer(this);
		uppermasksection1.setPos(0.0F, 2.35F, -1.75F);
		mask.addChild(uppermasksection1);
		uppermasksection1.texOffs(164, 25).addBox(-6.0F, -8.25F, -4.25F, 12.0F, 8.0F, 6.0F, 0.2F, false);
		uppermasksection1.texOffs(178, 58).addBox(-5.0F, -7.25F, -5.25F, 10.0F, 7.0F, 1.0F, 0.2F, false);

		lowermasksection1 = new ModelRenderer(this);
		lowermasksection1.setPos(0.0F, 2.6F, -1.25F);
		mask.addChild(lowermasksection1);
		lowermasksection1.texOffs(164, 1).addBox(-6.0F, -0.1F, -4.75F, 12.0F, 4.0F, 6.0F, 0.2F, false);
		lowermasksection1.texOffs(178, 45).addBox(-5.0F, -0.1F, -5.75F, 10.0F, 3.0F, 1.0F, 0.2F, false);

		lowermasksection2 = new ModelRenderer(this);
		lowermasksection2.setPos(0.0F, 4.25F, -3.15F);
		lowermasksection1.addChild(lowermasksection2);
		setRotationAngle(lowermasksection2, -0.2182F, 0.0F, 0.0F);
		lowermasksection2.texOffs(179, 50).addBox(-3.0F, -1.95F, -1.75F, 6.0F, 3.0F, 4.0F, 0.2F, false);

		lowermasksection3 = new ModelRenderer(this);
		lowermasksection3.setPos(0.0F, 0.0F, 0.0F);
		lowermasksection2.addChild(lowermasksection3);


		lowermask4_r1 = new ModelRenderer(this);
		lowermask4_r1.setPos(0.0F, 1.3F, -0.25F);
		lowermasksection3.addChild(lowermask4_r1);
		setRotationAngle(lowermask4_r1, -0.0873F, 0.0F, 0.0F);
		lowermask4_r1.texOffs(185, 40).addBox(-1.0F, -0.85F, -1.4F, 2.0F, 2.0F, 2.0F, 0.2F, false);

		upperleftleg = new ModelRenderer(this);
		upperleftleg.setPos(5.5F, -6.5F, 0.0F);
		setRotationAngle(upperleftleg, -0.5585F, -0.0873F, 0.0F);
		upperleftleg.texOffs(164, 105).addBox(-4.0F, -1.25F, -5.0F, 8.0F, 17.0F, 10.0F, 0.2F, false);

		lowerleftleg = new ModelRenderer(this);
		lowerleftleg.setPos(0.0F, 13.0F, -0.25F);
		upperleftleg.addChild(lowerleftleg);
		setRotationAngle(lowerleftleg, -0.48F, 0.0F, 0.0F);
		lowerleftleg.texOffs(144, 75).addBox(-3.5F, -3.6507F, -0.9648F, 7.0F, 8.0F, 21.0F, 0.2F, false);

		leftfootsection1 = new ModelRenderer(this);
		leftfootsection1.setPos(0.5F, 0.3493F, 17.7852F);
		lowerleftleg.addChild(leftfootsection1);
		setRotationAngle(leftfootsection1, -0.5236F, 0.0F, 0.0F);
		leftfootsection1.texOffs(139, 114).addBox(-4.5F, -6.0F, 0.0F, 8.0F, 14.0F, 4.0F, 0.2F, false);
		leftfootsection1.texOffs(136, 78).addBox(-5.5F, 3.0F, 0.075F, 10.0F, 13.0F, 4.0F, 0.0F, false);

		leftfootsection2 = new ModelRenderer(this);
		leftfootsection2.setPos(-0.5F, -6.0F, 2.25F);
		leftfootsection1.addChild(leftfootsection2);
		setRotationAngle(leftfootsection2, -0.3491F, 0.0F, 0.0F);
		leftfootsection2.texOffs(145, 106).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		upperrightleg = new ModelRenderer(this);
		upperrightleg.setPos(-5.5F, -6.5F, 0.0F);
		setRotationAngle(upperrightleg, -0.5585F, 0.0873F, 0.0F);
		upperrightleg.texOffs(164, 173).addBox(-4.0F, -1.25F, -5.0F, 8.0F, 17.0F, 10.0F, 0.2F, false);

		lowerrightleg = new ModelRenderer(this);
		lowerrightleg.setPos(0.0F, 13.0F, -0.25F);
		upperrightleg.addChild(lowerrightleg);
		setRotationAngle(lowerrightleg, -0.48F, 0.0F, 0.0F);
		lowerrightleg.texOffs(144, 143).addBox(-3.5F, -3.6507F, -0.9648F, 7.0F, 8.0F, 21.0F, 0.2F, false);

		rightfootsection1 = new ModelRenderer(this);
		rightfootsection1.setPos(-0.5F, 0.3493F, 17.7852F);
		lowerrightleg.addChild(rightfootsection1);
		setRotationAngle(rightfootsection1, -0.5236F, 0.0F, 0.0F);
		rightfootsection1.texOffs(140, 145).addBox(-3.5F, -6.0F, 0.0F, 8.0F, 14.0F, 4.0F, 0.2F, false);
		rightfootsection1.texOffs(135, 183).addBox(-4.5F, 3.0F, 0.075F, 10.0F, 13.0F, 4.0F, 0.0F, false);

		rightfootsection2 = new ModelRenderer(this);
		rightfootsection2.setPos(0.5F, -6.0F, 2.25F);
		rightfootsection1.addChild(rightfootsection2);
		setRotationAngle(rightfootsection2, -0.3491F, 0.0F, 0.0F);
		rightfootsection2.texOffs(143, 175).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		/*

		 */

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Minecraft_Placeholder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bodysection1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		upperleftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		upperrightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}