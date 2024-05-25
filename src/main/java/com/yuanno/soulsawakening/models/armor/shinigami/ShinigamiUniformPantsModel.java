package com.yuanno.soulsawakening.models.armor.shinigami;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ShinigamiUniformPantsModel<T extends LivingEntity> extends BipedModel<T> {
	private final ModelRenderer shinigamiuniform;
	private final ModelRenderer shinigamiuniformrightleg;
	private final ModelRenderer shinigamiuniformleftleg;

	public ShinigamiUniformPantsModel() {
        super(1);
        texWidth = 128;
		texHeight = 128;

		shinigamiuniform = new ModelRenderer(this);
		shinigamiuniform.setPos(0.0F, 24.0F, 0.0F);

		shinigamiuniformrightleg = new ModelRenderer(this);
		shinigamiuniformrightleg.setPos(-1.9F, -12.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformrightleg);
		shinigamiuniformrightleg.texOffs(77, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);

		shinigamiuniformleftleg = new ModelRenderer(this);
		shinigamiuniformleftleg.setPos(1.9F, -12.0F, 0.0F);
		shinigamiuniform.addChild(shinigamiuniformleftleg);
		shinigamiuniformleftleg.texOffs(59, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.3F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		this.shinigamiuniformleftleg.copyFrom(this.leftLeg);
		this.shinigamiuniformleftleg.render(matrixStack, buffer, packedLight, packedOverlay);
		this.shinigamiuniformrightleg.copyFrom(this.rightLeg);
		this.shinigamiuniformrightleg.render(matrixStack, buffer, packedLight, packedOverlay);}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}