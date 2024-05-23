// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class GoteiUniform extends EntityModel<Entity> {
	private final ModelRenderer MinecraftPlayerModel;
	private final ModelRenderer MinecraftHead;
	private final ModelRenderer MinecraftBody;
	private final ModelRenderer MinecraftRightArm;
	private final ModelRenderer MinecraftLeftArm;
	private final ModelRenderer MinecraftRightLeg;
	private final ModelRenderer MinecraftLeftLeg;
	private final ModelRenderer goteiuniform;
	private final ModelRenderer goteiuniformhead;
	private final ModelRenderer goteiuniformbody;
	private final ModelRenderer belt;
	private final ModelRenderer leftbandsection1;
	private final ModelRenderer leftbandsection2;
	private final ModelRenderer leftbandsection3;
	private final ModelRenderer rightbandsection1;
	private final ModelRenderer rightbandsection2;
	private final ModelRenderer rightbandsection3;
	private final ModelRenderer coat;
	private final ModelRenderer goteiuniformrightarm;
	private final ModelRenderer goteiuniformleftarm;
	private final ModelRenderer goteiuniformrightleg;
	private final ModelRenderer goteiuniformleftleg;

	public GoteiUniform() {
		textureWidth = 128;
		textureHeight = 128;

		MinecraftPlayerModel = new ModelRenderer(this);
		MinecraftPlayerModel.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		MinecraftHead = new ModelRenderer(this);
		MinecraftHead.setRotationPoint(0.0F, -24.0F, 0.0F);
		MinecraftPlayerModel.addChild(MinecraftHead);
		MinecraftHead.setTextureOffset(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		MinecraftHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		MinecraftBody = new ModelRenderer(this);
		MinecraftBody.setRotationPoint(0.0F, -24.0F, 0.0F);
		MinecraftPlayerModel.addChild(MinecraftBody);
		MinecraftBody.setTextureOffset(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftBody.setTextureOffset(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftRightArm = new ModelRenderer(this);
		MinecraftRightArm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		MinecraftPlayerModel.addChild(MinecraftRightArm);
		MinecraftRightArm.setTextureOffset(56, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftRightArm.setTextureOffset(68, 75).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftLeftArm = new ModelRenderer(this);
		MinecraftLeftArm.setRotationPoint(5.0F, -22.0F, 0.0F);
		MinecraftPlayerModel.addChild(MinecraftLeftArm);
		MinecraftLeftArm.setTextureOffset(52, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftLeftArm.setTextureOffset(0, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftRightLeg = new ModelRenderer(this);
		MinecraftRightLeg.setRotationPoint(-1.9F, -12.0F, 0.0F);
		MinecraftPlayerModel.addChild(MinecraftRightLeg);
		MinecraftRightLeg.setTextureOffset(36, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftRightLeg.setTextureOffset(20, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		MinecraftLeftLeg = new ModelRenderer(this);
		MinecraftLeftLeg.setRotationPoint(1.9F, -12.0F, 0.0F);
		MinecraftPlayerModel.addChild(MinecraftLeftLeg);
		MinecraftLeftLeg.setTextureOffset(44, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		MinecraftLeftLeg.setTextureOffset(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		goteiuniform = new ModelRenderer(this);
		goteiuniform.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		goteiuniformhead = new ModelRenderer(this);
		goteiuniformhead.setRotationPoint(0.0F, -24.0F, 0.0F);
		goteiuniform.addChild(goteiuniformhead);
		goteiuniformhead.setTextureOffset(96, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.55F, false);

		goteiuniformbody = new ModelRenderer(this);
		goteiuniformbody.setRotationPoint(0.0F, -24.0F, 0.0F);
		goteiuniform.addChild(goteiuniformbody);
		goteiuniformbody.setTextureOffset(100, 94).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.3F, false);
		goteiuniformbody.setTextureOffset(5, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.32F, false);

		belt = new ModelRenderer(this);
		belt.setRotationPoint(0.0F, -0.25F, 0.0F);
		goteiuniformbody.addChild(belt);
		belt.setTextureOffset(100, 88).addBox(-4.0F, 11.0F, -2.0F, 8.0F, 1.0F, 4.0F, 0.34F, false);

		leftbandsection1 = new ModelRenderer(this);
		leftbandsection1.setRotationPoint(1.1F, 11.25F, -2.325F);
		belt.addChild(leftbandsection1);
		setRotationAngle(leftbandsection1, -0.1309F, 0.0F, -0.0873F);
		leftbandsection1.setTextureOffset(113, 79).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		leftbandsection2 = new ModelRenderer(this);
		leftbandsection2.setRotationPoint(0.0F, 2.0F, 0.0F);
		leftbandsection1.addChild(leftbandsection2);
		setRotationAngle(leftbandsection2, 0.0873F, 0.0F, 0.0F);
		leftbandsection2.setTextureOffset(113, 82).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		leftbandsection3 = new ModelRenderer(this);
		leftbandsection3.setRotationPoint(0.0F, 2.0F, 0.0F);
		leftbandsection2.addChild(leftbandsection3);
		setRotationAngle(leftbandsection3, 0.0873F, 0.0F, 0.0F);
		leftbandsection3.setTextureOffset(113, 85).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		rightbandsection1 = new ModelRenderer(this);
		rightbandsection1.setRotationPoint(-1.1F, 11.25F, -2.325F);
		belt.addChild(rightbandsection1);
		setRotationAngle(rightbandsection1, -0.1309F, 0.0F, 0.0873F);
		rightbandsection1.setTextureOffset(107, 79).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, true);

		rightbandsection2 = new ModelRenderer(this);
		rightbandsection2.setRotationPoint(0.0F, 2.0F, 0.0F);
		rightbandsection1.addChild(rightbandsection2);
		setRotationAngle(rightbandsection2, 0.0873F, 0.0F, 0.0F);
		rightbandsection2.setTextureOffset(107, 82).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, true);

		rightbandsection3 = new ModelRenderer(this);
		rightbandsection3.setRotationPoint(0.0F, 2.0F, 0.0F);
		rightbandsection2.addChild(rightbandsection3);
		setRotationAngle(rightbandsection3, 0.0873F, 0.0F, 0.0F);
		rightbandsection3.setTextureOffset(107, 85).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, true);

		coat = new ModelRenderer(this);
		coat.setRotationPoint(0.0F, 12.34F, -2.325F);
		goteiuniformbody.addChild(coat);
		coat.setTextureOffset(5, 32).addBox(-4.0F, 0.3F, 0.325F, 8.0F, 6.0F, 4.0F, 0.32F, false);

		goteiuniformrightarm = new ModelRenderer(this);
		goteiuniformrightarm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		goteiuniform.addChild(goteiuniformrightarm);
		goteiuniformrightarm.setTextureOffset(77, 94).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.29F, false);
		goteiuniformrightarm.setTextureOffset(18, 17).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.31F, false);

		goteiuniformleftarm = new ModelRenderer(this);
		goteiuniformleftarm.setRotationPoint(5.0F, -22.0F, 0.0F);
		goteiuniform.addChild(goteiuniformleftarm);
		goteiuniformleftarm.setTextureOffset(59, 94).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.29F, false);
		goteiuniformleftarm.setTextureOffset(0, 17).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.31F, false);

		goteiuniformrightleg = new ModelRenderer(this);
		goteiuniformrightleg.setRotationPoint(-1.9F, -12.0F, 0.0F);
		goteiuniform.addChild(goteiuniformrightleg);
		goteiuniformrightleg.setTextureOffset(77, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);
		goteiuniformrightleg.setTextureOffset(77, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);

		goteiuniformleftleg = new ModelRenderer(this);
		goteiuniformleftleg.setRotationPoint(1.9F, -12.0F, 0.0F);
		goteiuniform.addChild(goteiuniformleftleg);
		goteiuniformleftleg.setTextureOffset(59, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);
		goteiuniformleftleg.setTextureOffset(59, 77).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		MinecraftPlayerModel.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		goteiuniform.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}