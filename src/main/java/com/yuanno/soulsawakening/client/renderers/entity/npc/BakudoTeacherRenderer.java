package com.yuanno.soulsawakening.client.renderers.entity.npc;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.npc.BakudoTeacherEntity;
import com.yuanno.soulsawakening.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unchecked")
public class BakudoTeacherRenderer extends MobRenderer<BakudoTeacherEntity, HumanoidModel<BakudoTeacherEntity>> {

    public BakudoTeacherRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BakudoTeacherEntity BakudoTeacherEntity) {
        return new ResourceLocation(Main.MODID, "textures/entities/npc/bakudo_teacher.png");
    }

    public static class Factory implements IRenderFactory<BakudoTeacherEntity> {

        @Override
        public EntityRenderer<? super BakudoTeacherEntity> createRenderFor(EntityRendererManager manager) {
            return new BakudoTeacherRenderer(manager);
        }
    }
}
