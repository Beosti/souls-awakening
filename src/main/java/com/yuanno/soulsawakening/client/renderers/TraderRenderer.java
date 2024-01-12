package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.TraderEntity;
import com.yuanno.soulsawakening.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unchecked")
public class TraderRenderer extends MobRenderer<TraderEntity, HumanoidModel<TraderEntity>> {



    public TraderRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TraderEntity TraderEntity) {

        ResourceLocation FINAL_TEXTURE = new ResourceLocation(Main.MODID, "textures/entities/npc/trader"  +  ".png");

        return FINAL_TEXTURE;
    }

    public static class Factory implements IRenderFactory<TraderEntity> {

        @Override
        public EntityRenderer<? super TraderEntity> createRenderFor(EntityRendererManager manager) {
            return new TraderRenderer(manager);
        }
    }
}
