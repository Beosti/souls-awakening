package com.yuanno.soulsawakening.models.armor.shinigami;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ShinigamiUniformModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer shinigamiuniform;
	private final ModelRenderer shinigamiuniformhead;
	private final ModelRenderer shinigamiuniformbody;
	private final ModelRenderer shinigamiuniformbelt;
	private final ModelRenderer leftbandsection1;
	private final ModelRenderer leftbandsection2;
	private final ModelRenderer leftbandsection3;
	private final ModelRenderer rightbandsection1;
	private final ModelRenderer rightbandsection2;
	private final ModelRenderer rightbandsection3;
	private final ModelRenderer shinigamiuniformrightarm;
	private final ModelRenderer shinigamiuniformleftarm;
	private final ModelRenderer shinigamiuniformrightleg;
	private final ModelRenderer shinigamiuniformleftleg;
	private final ModelRenderer shinigamiuniformleftboot;
	private final ModelRenderer shinigamiuniformrightboot;

	public ShinigamiUniformModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;

		shinigamiuniform = new ModelRenderer(this);
		shinigamiuniform.setPos(0.0F, 24.0F, 0.0F);


		shinigamiuniformhead = new ModelRenderer(this);
		shinigamiuniformhead.setPos(0.0F, -24.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformhead);
		shinigamiuniformhead.texOffs(96, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.55F, false);

		shinigamiuniformbody = new ModelRenderer(this);
		shinigamiuniformbody.setPos(0.0F, -24.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformbody);
		shinigamiuniformbody.texOffs(100, 94).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.3F, false);

		shinigamiuniformbelt = new ModelRenderer(this);
		shinigamiuniformbelt.setPos(0.0F, -24.25F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformbelt);
		shinigamiuniformbelt.texOffs(100, 88).addBox(-4.0F, 11.0F, -2.0F, 8.0F, 1.0F, 4.0F, 0.33F, false);

		leftbandsection1 = new ModelRenderer(this);
		leftbandsection1.setPos(1.1F, 11.25F, -2.325F);
		shinigamiuniformbelt.addChild(leftbandsection1);
		setRotationAngle(leftbandsection1, -0.1309F, 0.0F, -0.0873F);
		leftbandsection1.texOffs(113, 79).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		leftbandsection2 = new ModelRenderer(this);
		leftbandsection2.setPos(0.0F, 2.0F, 0.0F);
		leftbandsection1.addChild(leftbandsection2);
		setRotationAngle(leftbandsection2, 0.0873F, 0.0F, 0.0F);
		leftbandsection2.texOffs(113, 82).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		leftbandsection3 = new ModelRenderer(this);
		leftbandsection3.setPos(0.0F, 2.0F, 0.0F);
		leftbandsection2.addChild(leftbandsection3);
		setRotationAngle(leftbandsection3, 0.0873F, 0.0F, 0.0F);
		leftbandsection3.texOffs(113, 85).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		rightbandsection1 = new ModelRenderer(this);
		rightbandsection1.setPos(-1.1F, 11.25F, -2.325F);
		shinigamiuniformbelt.addChild(rightbandsection1);
		setRotationAngle(rightbandsection1, -0.1309F, 0.0F, 0.0873F);
		rightbandsection1.texOffs(107, 79).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, true);

		rightbandsection2 = new ModelRenderer(this);
		rightbandsection2.setPos(0.0F, 2.0F, 0.0F);
		rightbandsection1.addChild(rightbandsection2);
		setRotationAngle(rightbandsection2, 0.0873F, 0.0F, 0.0F);
		rightbandsection2.texOffs(107, 82).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, true);

		rightbandsection3 = new ModelRenderer(this);
		rightbandsection3.setPos(0.0F, 2.0F, 0.0F);
		rightbandsection2.addChild(rightbandsection3);
		setRotationAngle(rightbandsection3, 0.0873F, 0.0F, 0.0F);
		rightbandsection3.texOffs(107, 85).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, true);

		shinigamiuniformrightarm = new ModelRenderer(this);
		shinigamiuniformrightarm.setPos(-5.0F, -22.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformrightarm);
		shinigamiuniformrightarm.texOffs(77, 94).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.29F, false);

		shinigamiuniformleftarm = new ModelRenderer(this);
		shinigamiuniformleftarm.setPos(5.0F, -22.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformleftarm);
		shinigamiuniformleftarm.texOffs(59, 94).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.29F, false);

		shinigamiuniformrightleg = new ModelRenderer(this);
		shinigamiuniformrightleg.setPos(-1.9F, -12.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformrightleg);
		shinigamiuniformrightleg.texOffs(77, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		shinigamiuniformleftleg = new ModelRenderer(this);
		shinigamiuniformleftleg.setPos(1.9F, -12.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformleftleg);
		shinigamiuniformleftleg.texOffs(59, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		shinigamiuniformleftboot = new ModelRenderer(this);
		shinigamiuniformleftboot.setPos(1.9F, -12.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformleftboot);
		shinigamiuniformleftboot.texOffs(59, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);

		shinigamiuniformrightboot = new ModelRenderer(this);
		shinigamiuniformrightboot.setPos(-1.9F, -12.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformrightboot);
		shinigamiuniformrightboot.texOffs(77, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		this.shinigamiuniformrightarm.copyFrom(this.rightArm);
		this.shinigamiuniformrightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		this.shinigamiuniformleftarm.copyFrom(this.leftArm);
		this.shinigamiuniformleftarm.render(matrixStack, buffer, packedLight, packedOverlay);
		this.shinigamiuniformbody.copyFrom(this.body);
		this.shinigamiuniformbody.render(matrixStack, buffer, packedLight, packedOverlay);
		/*
		this.shinigamiuniformleftarm.copyFrom(this.leftArm);
		this.shinigamiuniformleftarm.render(matrixStack, buffer, packedLight, packedLight);
		this.shinigamiuniformrightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		this.shinigamiuniformleftleg.render(matrixStack, buffer, packedLight, packedOverlay);
		this.shinigamiuniformrightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		this.shinigamiuniformbody.render(matrixStack, buffer, packedLight, packedOverlay);

		 */
		//this.shinigamiuniform.render(matrixStack, buffer, packedLight, packedOverlay); -> shoes
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}