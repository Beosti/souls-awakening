package com.yuanno.soulsawakening.models.armor.shinigami;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ShinigamiUniformChestModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer shinigamiuniform;
	private final ModelRenderer shinigamiuniformhead;
	private final ModelRenderer shinigamiuniformbody;
	private final ModelRenderer shinigamiuniformrightarm;
	private final ModelRenderer shinigamiuniformleftarm;

	public ShinigamiUniformChestModel() {
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

		shinigamiuniformrightarm = new ModelRenderer(this);
		shinigamiuniformrightarm.setPos(-5.0F, -22.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformrightarm);
		shinigamiuniformrightarm.texOffs(77, 94).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.29F, false);

		shinigamiuniformleftarm = new ModelRenderer(this);
		shinigamiuniformleftarm.setPos(5.0F, -22.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformleftarm);
		shinigamiuniformleftarm.texOffs(59, 94).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.29F, false);
	}

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
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}