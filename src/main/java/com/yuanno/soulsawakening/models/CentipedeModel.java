package com.yuanno.soulsawakening.models;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class custom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart leftside;
	private final ModelPart rightside;
	private final ModelPart bb_main;

	public custom_model(ModelPart root) {
		this.leftside = root.getChild("leftside");
		this.rightside = root.getChild("rightside");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftside = partdefinition.addOrReplaceChild("leftside", CubeListBuilder.create(), PartPose.offset(6.6213F, 19.0503F, 39.0F));

		PartDefinition Poot3Left_r1 = leftside.addOrReplaceChild("Poot3Left_r1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-2.3192F, -0.5736F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, -18.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition Poot2Left_r1 = leftside.addOrReplaceChild("Poot2Left_r1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1.7456F, -1.3927F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(3, 3).addBox(-1.7456F, -1.3927F, 16.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).mirror().addBox(-1.7456F, -1.3927F, -10.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, -27.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition Poot5Left_r1 = leftside.addOrReplaceChild("Poot5Left_r1", CubeListBuilder.create().texOffs(3, 3).addBox(-1.172F, -2.2119F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition rightside = partdefinition.addOrReplaceChild("rightside", CubeListBuilder.create(), PartPose.offset(-7.6213F, 20.0503F, 39.0F));

		PartDefinition Poot2Right_r1 = rightside.addOrReplaceChild("Poot2Right_r1", CubeListBuilder.create().texOffs(0, 3).addBox(-5.4353F, -1.9663F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-5.4353F, -1.9663F, 25.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-5.4353F, -1.9663F, -10.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -27.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition Poot4Right_r1 = rightside.addOrReplaceChild("Poot4Right_r1", CubeListBuilder.create().texOffs(0, 3).addBox(-6.828F, -2.2119F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -9.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition Poot3Right_r1 = rightside.addOrReplaceChild("Poot3Right_r1", CubeListBuilder.create().texOffs(0, 3).addBox(-6.0088F, -2.7855F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -18.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -3.0F, 5.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-4.5F, -4.0F, 7.0F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-4.5F, -4.0F, 16.0F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(-3.0F, -3.0F, 14.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-4.5F, -4.0F, 25.0F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(-3.0F, -3.0F, 23.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-4.5F, -4.0F, -2.0F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-4.5F, -4.0F, 34.0F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(-3.0F, -3.0F, 32.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-4.0F, -18.0F, -14.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-6.2F, -4.5F, -5.0F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -13.5F, 0.0F, 0.3442F, -0.0594F, -0.3594F));

		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 2).addBox(2.2F, -4.5F, -5.0F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -13.5F, 0.0F, 0.3442F, 0.0594F, 0.3594F));

		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-2.0F, -3.5F, 0.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -7.1247F, -9.9651F, 0.7025F, -0.2615F, 0.2964F));

		PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -3.5F, 0.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -7.1247F, -9.9651F, 0.7025F, 0.2615F, -0.2964F));

		PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, -8.0F, 0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-0.5F, -4.5F, 2.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 2).addBox(-5.5F, -4.5F, 2.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -20.5F, -13.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-1.0F, 0.5F, 3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -16.5F, -16.0F, -0.5672F, 0.0F, 0.4363F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 2).addBox(-0.6385F, -2.1495F, 1.3121F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -13.5F, -16.0F, -0.5672F, 0.0F, -0.4363F));

		PartDefinition cube_r9 = bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 2).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, -8.0F, -0.829F, 0.0F, 0.0F));

		PartDefinition cube_r10 = bb_main.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-1.5F, 0.0F, -8.0F, 3.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 11).addBox(-14.5F, 0.0F, -8.0F, 3.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, -9.0F, -20.0F, 0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bb_main.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 9).addBox(-2.5F, 3.0F, -3.5F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -6.4079F, -20.7286F, 0.9903F, 0.176F, 0.4663F));

		PartDefinition cube_r12 = bb_main.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-0.5F, 3.0F, -3.5F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.5F, -6.4079F, -20.7286F, 0.9903F, -0.176F, -0.4663F));

		PartDefinition cube_r13 = bb_main.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, 0.2F, 4.5F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).mirror().addBox(13.0F, 0.2F, 4.5F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, -7.5F, -7.5F, -2.2253F, 0.0F, 0.0F));

		PartDefinition cube_r14 = bb_main.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 3).addBox(-0.5F, -3.5F, -3.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).mirror().addBox(12.5F, -3.5F, -3.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, -7.5F, -7.5F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r15 = bb_main.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(3, 3).addBox(-1.0F, -3.0109F, 0.378F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -6.8845F, -9.5567F, -1.8422F, 0.0673F, -0.6106F));

		PartDefinition cube_r16 = bb_main.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(3, 3).addBox(-1.5F, -7.1676F, 0.2215F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -6.8845F, -9.5567F, -0.1405F, 0.0673F, -0.6106F));

		PartDefinition cube_r17 = bb_main.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(3, 3).mirror().addBox(-1.0F, -3.0109F, 0.378F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.5F, -6.8845F, -9.5567F, -1.8422F, -0.0673F, 0.6106F));

		PartDefinition cube_r18 = bb_main.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(3, 3).mirror().addBox(-1.5F, -7.1676F, 0.2215F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.5F, -6.8845F, -9.5567F, -0.1405F, -0.0673F, 0.6106F));

		PartDefinition cube_r19 = bb_main.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 6).addBox(-5.4F, -6.75F, -3.0F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-5.0F, 0.25F, -3.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.25F, -7.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r20 = bb_main.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(6, 6).addBox(1.0401F, -8.5646F, -3.1F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.25F, -7.0F, 0.3444F, -0.1925F, 0.49F));

		PartDefinition cube_r21 = bb_main.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 6).addBox(-6.0401F, -8.5646F, -3.1F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.25F, -7.0F, 0.3444F, 0.1925F, -0.49F));

		PartDefinition cube_r22 = bb_main.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 2).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).mirror().addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 2).addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).mirror().addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 2).addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-2.5F, -3.0F, -28.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).mirror().addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.5F, -1.0F, 39.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r23 = bb_main.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(2, 2).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).mirror().addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 2).addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).mirror().addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 2).addBox(-2.5F, -3.0F, -10.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).mirror().addBox(-2.5F, -3.0F, -28.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 2).addBox(-2.5F, -3.0F, -19.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).mirror().addBox(-2.5F, -3.0F, -37.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.5F, -1.0F, 39.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r24 = bb_main.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(3, 3).mirror().addBox(-6.5F, 0.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.6213F, -5.9497F, 30.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r25 = bb_main.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1.7456F, -1.3927F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.6213F, -4.9497F, 30.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition cube_r26 = bb_main.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(3, 3).addBox(-1.5F, 0.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.6213F, -5.9497F, 3.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition cube_r27 = bb_main.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(3, 3).mirror().addBox(-6.828F, -2.2119F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.6213F, -4.9497F, 21.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r28 = bb_main.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(3, 3).addBox(-2.5647F, -1.9663F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.6213F, -3.9497F, 21.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition cube_r29 = bb_main.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(3, 3).mirror().addBox(-6.0088F, -2.7855F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.6213F, -3.9497F, 3.0F, 0.0F, 0.0F, -0.9599F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftside.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightside.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}