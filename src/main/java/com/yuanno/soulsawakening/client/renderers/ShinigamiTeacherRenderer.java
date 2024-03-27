package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.npc.ShinigamiTeacherEntity;
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
public class ShinigamiTeacherRenderer extends MobRenderer<ShinigamiTeacherEntity, HumanoidModel<ShinigamiTeacherEntity>> {

    public ShinigamiTeacherRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ShinigamiTeacherEntity shinigamiTeacherEntity) {
        return new ResourceLocation(Main.MODID, "textures/entities/npc/shinigami_teacher.png");
    }

    public static class Factory implements IRenderFactory<ShinigamiTeacherEntity> {

        @Override
        public EntityRenderer<? super ShinigamiTeacherEntity> createRenderFor(EntityRendererManager manager) {
            return new ShinigamiTeacherRenderer(manager);
        }
    }
}
