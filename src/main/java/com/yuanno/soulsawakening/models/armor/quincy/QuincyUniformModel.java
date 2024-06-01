package com.yuanno.soulsawakening.models.armor.quincy;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class QuincyUniformModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer quincyuniform;
	private final ModelRenderer quincyuniformhead;
	private final ModelRenderer quincyuniformbody;
	private final ModelRenderer quincyuniformbodyfrontrobe;
	private final ModelRenderer quincyuniformbodybackrobe;
	private final ModelRenderer quincyuniformleftarm;
	private final ModelRenderer quincyuniformrightarm;
	private final ModelRenderer quincyuniformleftleg;
	private final ModelRenderer quincyuniformrightleg;
	private final ModelRenderer quincyuniformleftboot;
	private final ModelRenderer quincyuniformrightboot;
	private final ModelRenderer quincyuniformbelt;

	public QuincyUniformModel() {
		super(1);
		texWidth = 128;
		texHeight = 128;
		
		quincyuniform = new ModelRenderer(this);
		quincyuniform.setPos(0.0F, 24.0F, 0.0F);
		

		quincyuniformhead = new ModelRenderer(this);
		quincyuniformhead.setPos(0.0F, -24.0F, 0.0F);
		quincyuniform.addChild(quincyuniformhead);
		quincyuniformhead.texOffs(96, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.55F, false);

		quincyuniformbody = new ModelRenderer(this);
		quincyuniformbody.setPos(0.0F, -24.0F, 0.0F);
		quincyuniform.addChild(quincyuniformbody);
		quincyuniformbody.texOffs(100, 94).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.36F, false);

		quincyuniformbodyfrontrobe = new ModelRenderer(this);
		quincyuniformbodyfrontrobe.setPos(0.0F, 12.245F, -2.275F);
		quincyuniformbody.addChild(quincyuniformbodyfrontrobe);
		setRotationAngle(quincyuniformbodyfrontrobe, -0.0873F, 0.0F, 0.0F);
		quincyuniformbodyfrontrobe.texOffs(110, 0).addBox(-4.0F, -0.475F, 0.275F, 8.0F, 6.0F, 1.0F, 0.36F, false);

		quincyuniformbodybackrobe = new ModelRenderer(this);
		quincyuniformbodybackrobe.setPos(0.0F, 12.245F, 2.275F);
		quincyuniformbody.addChild(quincyuniformbodybackrobe);
		setRotationAngle(quincyuniformbodybackrobe, 0.0873F, 0.0F, 0.0F);
		quincyuniformbodybackrobe.texOffs(110, 8).addBox(-4.0F, -0.475F, -1.275F, 8.0F, 6.0F, 1.0F, 0.36F, false);

		quincyuniformleftarm = new ModelRenderer(this);
		quincyuniformleftarm.setPos(5.0F, -22.0F, 0.0F);
		quincyuniform.addChild(quincyuniformleftarm);
		quincyuniformleftarm.texOffs(59, 94).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		quincyuniformrightarm = new ModelRenderer(this);
		quincyuniformrightarm.setPos(-5.0F, -22.0F, 0.0F);
		quincyuniform.addChild(quincyuniformrightarm);
		quincyuniformrightarm.texOffs(77, 94).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		quincyuniformleftleg = new ModelRenderer(this);
		quincyuniformleftleg.setPos(1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformleftleg);
		quincyuniformleftleg.texOffs(59, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.35F, false);

		quincyuniformrightleg = new ModelRenderer(this);
		quincyuniformrightleg.setPos(-1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformrightleg);
		quincyuniformrightleg.texOffs(77, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.35F, false);

		quincyuniformleftboot = new ModelRenderer(this);
		quincyuniformleftboot.setPos(1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformleftboot);
		quincyuniformleftboot.texOffs(59, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);

		quincyuniformrightboot = new ModelRenderer(this);
		quincyuniformrightboot.setPos(-1.9F, -12.0F, 0.0F);
		quincyuniform.addChild(quincyuniformrightboot);
		quincyuniformrightboot.texOffs(77, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);

		quincyuniformbelt = new ModelRenderer(this);
		quincyuniformbelt.setPos(0.0F, -13.75F, 0.0F);
		quincyuniform.addChild(quincyuniformbelt);
		setRotationAngle(quincyuniformbelt, 0.0F, 0.0F, 0.0436F);
		quincyuniformbelt.texOffs(61, 67).addBox(-4.5F, -1.0F, -2.5F, 9.0F, 2.0F, 5.0F, 0.0F, false);
		quincyuniformbelt.texOffs(72, 62).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 1.0F, 0.2F, false);
	}

	/*
	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		MinecraftPlayerModel.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		quincyuniform.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	 */
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}