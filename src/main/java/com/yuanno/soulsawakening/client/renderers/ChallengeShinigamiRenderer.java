package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.ChallengeShinigamiEntity;
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
public class ChallengeShinigamiRenderer extends MobRenderer<ChallengeShinigamiEntity, HumanoidModel<ChallengeShinigamiEntity>> {



    public ChallengeShinigamiRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ChallengeShinigamiEntity ChallengeShinigamiEntity) {
        String finalTexture = ChallengeShinigamiEntity.constantSkin;
        ResourceLocation FINAL_TEXTURE = new ResourceLocation(Main.MODID, "textures/entities/npc/" + finalTexture +  ".png");

        return FINAL_TEXTURE;
    }

    public static class Factory implements IRenderFactory<ChallengeShinigamiEntity> {

        @Override
        public EntityRenderer<? super ChallengeShinigamiEntity> createRenderFor(EntityRendererManager manager) {
            return new ChallengeShinigamiRenderer(manager);
        }
    }
}
