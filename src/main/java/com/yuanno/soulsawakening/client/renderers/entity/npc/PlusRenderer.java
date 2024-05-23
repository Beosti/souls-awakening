package com.yuanno.soulsawakening.client.renderers.entity.npc;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.PlusEntity;
import com.yuanno.soulsawakening.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unchecked")
public class PlusRenderer extends MobRenderer<PlusEntity, HumanoidModel<PlusEntity>> {



    public PlusRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);

    }

    @Override
    public ResourceLocation getTextureLocation(PlusEntity plusEntity) {
        Random random = new Random();
        int randomIndex = random.nextInt(plusEntity.options.length);
        String texture = plusEntity.options[randomIndex];
        String finalTexture = plusEntity.constantSkin;
        ResourceLocation FINAL_TEXTURE = new ResourceLocation(Main.MODID, "textures/entities/npc/" + finalTexture +  ".png");

        return FINAL_TEXTURE;
    }

    public static class Factory implements IRenderFactory<PlusEntity> {

        @Override
        public EntityRenderer<? super PlusEntity> createRenderFor(EntityRendererManager manager) {
            return new PlusRenderer(manager);
        }
    }
}
