package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.npc.KidoTeacherEntity;
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
public class KidoTeacherRenderer extends MobRenderer<KidoTeacherEntity, HumanoidModel<KidoTeacherEntity>> {

    public KidoTeacherRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(KidoTeacherEntity KidoTeacherEntity) {
        return new ResourceLocation(Main.MODID, "textures/entities/npc/kido_teacher.png");
    }

    public static class Factory implements IRenderFactory<KidoTeacherEntity> {

        @Override
        public EntityRenderer<? super KidoTeacherEntity> createRenderFor(EntityRendererManager manager) {
            return new KidoTeacherRenderer(manager);
        }
    }
}
