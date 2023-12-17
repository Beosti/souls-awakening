package com.yuanno.soulsawakening.client.renderers;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.ShinigamiEntity;
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
public class ShinigamiRenderer extends MobRenderer<ShinigamiEntity, HumanoidModel<ShinigamiEntity>> {



    public ShinigamiRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ShinigamiEntity ShinigamiEntity) {
        Random random = new Random();
        int randomIndex = random.nextInt(ShinigamiEntity.options.length);
        String texture = ShinigamiEntity.options[randomIndex];
        String finalTexture = ShinigamiEntity.constantSkin;
        ResourceLocation FINAL_TEXTURE = new ResourceLocation(Main.MODID, "textures/entities/npc/" + finalTexture +  ".png");

        return FINAL_TEXTURE;
    }

    public static class Factory implements IRenderFactory<ShinigamiEntity> {

        @Override
        public EntityRenderer<? super ShinigamiEntity> createRenderFor(EntityRendererManager manager) {
            return new ShinigamiRenderer(manager);
        }
    }
}
