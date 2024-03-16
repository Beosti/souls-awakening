package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.npc.TraderEntity;
import com.yuanno.soulsawakening.entities.npc.ZanjutsuTeacherEntity;
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
public class ZanjutsuRenderer extends MobRenderer<ZanjutsuTeacherEntity, HumanoidModel<ZanjutsuTeacherEntity>> {

    public ZanjutsuRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ZanjutsuTeacherEntity zanjutsuTeacherEntity) {
        return new ResourceLocation(Main.MODID, "textures/entities/npc/zanjutsu.png");
    }

    public static class Factory implements IRenderFactory<ZanjutsuTeacherEntity> {

        @Override
        public EntityRenderer<? super ZanjutsuTeacherEntity> createRenderFor(EntityRendererManager manager) {
            return new ZanjutsuRenderer(manager);
        }
    }
}
