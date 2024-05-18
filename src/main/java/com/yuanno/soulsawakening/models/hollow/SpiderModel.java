package com.yuanno.soulsawakening.models.hollow;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.soulsawakening.entities.hollow.SpiderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SpiderModel<T extends SpiderEntity> extends EntityModel<T> {
	private final ModelRenderer Minecraft_Placeholder;
	private final ModelRenderer MinecraftLeftLeg;
	private final ModelRenderer MinecraftRightLeg;
	private final ModelRenderer MinecraftLeftArm;
	private final ModelRenderer MinecraftRightArm;
	private final ModelRenderer MinecraftBody;
	private final ModelRenderer MinecraftHead;
	private final ModelRenderer headsection1;
	private final ModelRenderer headsection2;
	private final ModelRenderer mask;
	private final ModelRenderer uppermask;
	private final ModelRenderer lowermask;
	private final ModelRenderer bodysection1;
	private final ModelRenderer bodysection2;
	private final ModelRenderer bodysection3;
	private final ModelRenderer leftfrontleg;
	private final ModelRenderer lowerleftfrontleg;
	private final ModelRenderer bottomleftfrontleg;
	private final ModelRenderer rightfrontleg;
	private final ModelRenderer lowerrightfrontleg;
	private final ModelRenderer bottomrightfrontleg;
	private final ModelRenderer leftmiddleleg;
	private final ModelRenderer lowerleftmiddleleg;
	private final ModelRenderer bottomleftmiddleleg;
	private final ModelRenderer rightmiddleleg;
	private final ModelRenderer lowerrightmiddleleg;
	private final ModelRenderer bottomrightmiddleleg;
	private final ModelRenderer leftendleg;
	private final ModelRenderer lowerleftendleg;
	private final ModelRenderer bottomleftendleg;
	private final ModelRenderer rightendleg;
	private final ModelRenderer lowerrightendleg;
	private final ModelRenderer bottomrightendleg;
	private final ModelRenderer bodysection4;

	public SpiderModel() {
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

		headsection1 = new ModelRenderer(this);
		headsection1.setPos(0.0F, -3.9711F, -15.8753F);
		headsection1.texOffs(170, 187).addBox(-4.5F, -3.25F, -2.0F, 9.0F, 7.0F, 6.0F, 0.0F, false);

		headsection2 = new ModelRenderer(this);
		headsection2.setPos(0.0F, 1.375F, -1.75F);
		headsection1.addChild(headsection2);
		setRotationAngle(headsection2, 0.1745F, 0.0F, 0.0F);
		headsection2.texOffs(173, 175).addBox(-3.5F, -4.1302F, -3.2386F, 7.0F, 6.0F, 5.0F, 0.2F, false);

		mask = new ModelRenderer(this);
		mask.setPos(0.0F, 0.1198F, -2.9886F);
		headsection2.addChild(mask);
		mask.texOffs(175, 164).addBox(-4.0F, -5.1521F, -0.0326F, 8.0F, 8.0F, 2.0F, 0.0F, false);

		uppermask = new ModelRenderer(this);
		uppermask.setPos(0.0F, 3.5F, -0.5F);
		mask.addChild(uppermask);
		uppermask.texOffs(144, 173).addBox(-3.5F, -9.3521F, -3.0326F, 7.0F, 8.0F, 5.0F, 0.0F, false);
		uppermask.texOffs(178, 157).addBox(-3.0F, -0.2521F, -3.0326F, 2.0F, 1.0F, 5.0F, 0.2F, false);
		uppermask.texOffs(149, 157).addBox(1.0F, -0.2521F, -3.0326F, 2.0F, 1.0F, 5.0F, 0.2F, false);
		uppermask.texOffs(143, 187).addBox(-4.0F, -8.6521F, -3.0326F, 8.0F, 8.0F, 5.0F, 0.2F, false);

		lowermask = new ModelRenderer(this);
		lowermask.setPos(0.0F, 3.4729F, -0.2826F);
		mask.addChild(lowermask);
		lowermask.texOffs(145, 164).addBox(-3.0F, -0.725F, -3.25F, 6.0F, 3.0F, 5.0F, 0.0F, false);

		bodysection1 = new ModelRenderer(this);
		bodysection1.setPos(0.0F, -3.7211F, -16.3753F);
		setRotationAngle(bodysection1, -0.0436F, 0.0F, 0.0F);
		bodysection1.texOffs(20, 164).addBox(-7.0F, -4.5F, 0.0F, 14.0F, 9.0F, 7.0F, 0.0F, false);

		bodysection2 = new ModelRenderer(this);
		bodysection2.setPos(0.0F, 0.5F, 1.25F);
		bodysection1.addChild(bodysection2);
		setRotationAngle(bodysection2, 0.0436F, 0.0F, 0.0F);
		bodysection2.texOffs(87, 128).addBox(-10.0F, -6.0F, 0.25F, 20.0F, 12.0F, 6.0F, 0.0F, false);

		bodysection3 = new ModelRenderer(this);
		bodysection3.setPos(0.0F, 0.5F, 3.0F);
		bodysection2.addChild(bodysection3);
		bodysection3.texOffs(83, 108).addBox(-13.0F, -7.5F, 0.25F, 26.0F, 15.0F, 4.0F, 0.0F, false);
		bodysection3.texOffs(0, 97).addBox(-13.0F, -7.5F, 10.25F, 26.0F, 15.0F, 15.0F, 0.0F, false);
		bodysection3.texOffs(42, 128).addBox(-13.0F, -7.5F, 4.25F, 10.0F, 15.0F, 6.0F, 0.0F, false);
		bodysection3.texOffs(8, 128).addBox(3.0F, -7.5F, 4.25F, 10.0F, 15.0F, 6.0F, 0.0F, false);

		leftfrontleg = new ModelRenderer(this);
		leftfrontleg.setPos(10.75F, 0.75F, 1.75F);
		bodysection3.addChild(leftfrontleg);
		setRotationAngle(leftfrontleg, -0.1222F, 0.6981F, -0.1745F);
		leftfrontleg.texOffs(0, 0).addBox(-1.25F, -3.5F, -3.5F, 16.0F, 7.0F, 7.0F, 0.0F, false);

		lowerleftfrontleg = new ModelRenderer(this);
		lowerleftfrontleg.setPos(14.5F, 0.0F, 0.0F);
		leftfrontleg.addChild(lowerleftfrontleg);
		setRotationAngle(lowerleftfrontleg, 0.0F, 0.0F, 1.5708F);
		lowerleftfrontleg.texOffs(2, 15).addBox(-1.75F, -3.0F, -3.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

		bottomleftfrontleg = new ModelRenderer(this);
		bottomleftfrontleg.setPos(13.25F, 0.0F, 0.0F);
		lowerleftfrontleg.addChild(bottomleftfrontleg);
		setRotationAngle(bottomleftfrontleg, 0.0F, 0.0F, 0.1745F);
		bottomleftfrontleg.texOffs(2, 28).addBox(-1.0F, -2.5F, -2.5F, 16.0F, 5.0F, 5.0F, 0.0F, false);

		rightfrontleg = new ModelRenderer(this);
		rightfrontleg.setPos(-10.75F, 0.75F, 1.75F);
		bodysection3.addChild(rightfrontleg);
		setRotationAngle(rightfrontleg, -0.1222F, -0.6981F, 0.1745F);
		rightfrontleg.texOffs(0, 48).addBox(-14.75F, -3.5F, -3.5F, 16.0F, 7.0F, 7.0F, 0.0F, false);

		lowerrightfrontleg = new ModelRenderer(this);
		lowerrightfrontleg.setPos(-14.5F, 0.0F, 0.0F);
		rightfrontleg.addChild(lowerrightfrontleg);
		setRotationAngle(lowerrightfrontleg, 0.0F, 0.0F, -1.5708F);
		lowerrightfrontleg.texOffs(2, 63).addBox(-13.25F, -3.0F, -3.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

		bottomrightfrontleg = new ModelRenderer(this);
		bottomrightfrontleg.setPos(-13.25F, 0.0F, 0.0F);
		lowerrightfrontleg.addChild(bottomrightfrontleg);
		setRotationAngle(bottomrightfrontleg, 0.0F, 0.0F, -0.1745F);
		bottomrightfrontleg.texOffs(2, 76).addBox(-15.0F, -2.5F, -2.5F, 16.0F, 5.0F, 5.0F, 0.0F, false);

		leftmiddleleg = new ModelRenderer(this);
		leftmiddleleg.setPos(10.75F, 0.75F, 12.5F);
		bodysection3.addChild(leftmiddleleg);
		setRotationAngle(leftmiddleleg, 0.0F, 0.0F, -0.1745F);
		leftmiddleleg.texOffs(50, 0).addBox(-1.25F, -3.5F, -3.5F, 16.0F, 7.0F, 7.0F, 0.0F, false);

		lowerleftmiddleleg = new ModelRenderer(this);
		lowerleftmiddleleg.setPos(14.5F, 0.0F, 0.0F);
		leftmiddleleg.addChild(lowerleftmiddleleg);
		setRotationAngle(lowerleftmiddleleg, 0.0F, 0.0F, 1.5708F);
		lowerleftmiddleleg.texOffs(52, 15).addBox(-1.75F, -3.0F, -3.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

		bottomleftmiddleleg = new ModelRenderer(this);
		bottomleftmiddleleg.setPos(13.25F, 0.0F, 0.0F);
		lowerleftmiddleleg.addChild(bottomleftmiddleleg);
		setRotationAngle(bottomleftmiddleleg, 0.0F, 0.0F, 0.1745F);
		bottomleftmiddleleg.texOffs(52, 28).addBox(-1.0F, -2.5F, -2.5F, 16.0F, 5.0F, 5.0F, 0.0F, false);

		rightmiddleleg = new ModelRenderer(this);
		rightmiddleleg.setPos(-10.75F, 0.75F, 12.5F);
		bodysection3.addChild(rightmiddleleg);
		setRotationAngle(rightmiddleleg, 0.0F, 0.0F, 0.1745F);
		rightmiddleleg.texOffs(50, 48).addBox(-14.75F, -3.5F, -3.5F, 16.0F, 7.0F, 7.0F, 0.0F, false);

		lowerrightmiddleleg = new ModelRenderer(this);
		lowerrightmiddleleg.setPos(-14.5F, 0.0F, 0.0F);
		rightmiddleleg.addChild(lowerrightmiddleleg);
		setRotationAngle(lowerrightmiddleleg, 0.0F, 0.0F, -1.5708F);
		lowerrightmiddleleg.texOffs(52, 63).addBox(-13.25F, -3.0F, -3.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

		bottomrightmiddleleg = new ModelRenderer(this);
		bottomrightmiddleleg.setPos(-13.25F, 0.0F, 0.0F);
		lowerrightmiddleleg.addChild(bottomrightmiddleleg);
		setRotationAngle(bottomrightmiddleleg, 0.0F, 0.0F, -0.1745F);
		bottomrightmiddleleg.texOffs(52, 76).addBox(-15.0F, -2.5F, -2.5F, 16.0F, 5.0F, 5.0F, 0.0F, false);

		leftendleg = new ModelRenderer(this);
		leftendleg.setPos(10.75F, 0.75F, 23.25F);
		bodysection3.addChild(leftendleg);
		setRotationAngle(leftendleg, 0.1222F, -0.6981F, -0.1745F);
		leftendleg.texOffs(100, 0).addBox(-1.25F, -3.5F, -3.5F, 16.0F, 7.0F, 7.0F, 0.0F, false);

		lowerleftendleg = new ModelRenderer(this);
		lowerleftendleg.setPos(14.5F, 0.0F, 0.0F);
		leftendleg.addChild(lowerleftendleg);
		setRotationAngle(lowerleftendleg, 0.0F, 0.0F, 1.5708F);
		lowerleftendleg.texOffs(102, 15).addBox(-1.75F, -3.0F, -3.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

		bottomleftendleg = new ModelRenderer(this);
		bottomleftendleg.setPos(13.25F, 0.0F, 0.0F);
		lowerleftendleg.addChild(bottomleftendleg);
		setRotationAngle(bottomleftendleg, 0.0F, 0.0F, 0.1745F);
		bottomleftendleg.texOffs(102, 28).addBox(-1.0F, -2.5F, -2.5F, 16.0F, 5.0F, 5.0F, 0.0F, false);

		rightendleg = new ModelRenderer(this);
		rightendleg.setPos(-10.75F, 0.75F, 23.25F);
		bodysection3.addChild(rightendleg);
		setRotationAngle(rightendleg, 0.1222F, 0.6981F, 0.1745F);
		rightendleg.texOffs(100, 48).addBox(-14.75F, -3.5F, -3.5F, 16.0F, 7.0F, 7.0F, 0.0F, false);

		lowerrightendleg = new ModelRenderer(this);
		lowerrightendleg.setPos(-14.5F, 0.0F, 0.0F);
		rightendleg.addChild(lowerrightendleg);
		setRotationAngle(lowerrightendleg, 0.0F, 0.0F, -1.5708F);
		lowerrightendleg.texOffs(102, 63).addBox(-13.25F, -3.0F, -3.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

		bottomrightendleg = new ModelRenderer(this);
		bottomrightendleg.setPos(-13.25F, 0.0F, 0.0F);
		lowerrightendleg.addChild(bottomrightendleg);
		setRotationAngle(bottomrightendleg, 0.0F, 0.0F, -0.1745F);
		bottomrightendleg.texOffs(102, 76).addBox(-15.0F, -2.5F, -2.5F, 16.0F, 5.0F, 5.0F, 0.0F, false);

		bodysection4 = new ModelRenderer(this);
		bodysection4.setPos(0.0F, 0.0F, 25.5F);
		bodysection3.addChild(bodysection4);
		setRotationAngle(bodysection4, -0.0436F, 0.0F, 0.0F);
		bodysection4.texOffs(87, 147).addBox(-11.0F, -6.5F, -1.75F, 22.0F, 13.0F, 4.0F, 0.0F, false);
		bodysection4.texOffs(21, 150).addBox(-9.0F, -5.5F, 1.25F, 18.0F, 11.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		/*

		 */

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		//Minecraft_Placeholder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		headsection1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bodysection1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}