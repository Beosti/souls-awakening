package com.yuanno.soulsawakening.client;

import com.yuanno.soulsawakening.client.renderers.*;
import com.yuanno.soulsawakening.client.renderers.entity.hostile.hollow.*;
import com.yuanno.soulsawakening.client.renderers.entity.hostile.shinigami.ChallengeShinigamiRenderer;
import com.yuanno.soulsawakening.client.renderers.entity.hostile.shinigami.ShinigamiRenderer;
import com.yuanno.soulsawakening.client.renderers.entity.npc.*;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientHandler {

    public static void onSetup()
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GOLEM.get(), new GolemRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPIDER.get(), new SpiderRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.THORNS.get(), new ThornsRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FLYING.get(), new FlyingRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.APE.get(), new ApeRenderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.PLUS.get(), new PlusRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SHINIGAMI.get(), new ShinigamiRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CHALLENGE_SHINIGAMI.get(), new ChallengeShinigamiRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.TRADER.get(), new TraderRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SHINIGAMI_TEACHER.get(), new ShinigamiTeacherRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.KIDO_TEACHER.get(), new KidoTeacherRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BAKUDO_TEACHER.get(), new BakudoTeacherRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SHIKAI.get(), new InnerShikaiRenderer.Factory());

        Map<String, PlayerRenderer> playerSkinMap = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
        ClientHandler.addPlayerLayers(playerSkinMap.get("default"));
        ClientHandler.addPlayerLayers(playerSkinMap.get("slim"));

    }

    @OnlyIn(Dist.CLIENT)
    public static void addPlayerLayers(PlayerRenderer renderer)
    {
        List<LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>> layers = ObfuscationReflectionHelper.getPrivateValue(LivingRenderer.class, renderer, "field_177097_h");
        if(layers != null)
        {
            /*
            layers.add(new GrimoirRenderer<>(renderer));
            layers.add(new BlackModeRenderer<>(renderer));
            layers.add(new EarthManipulationRenderer<>(renderer));
            layers.add(new LeoPalmaRenderer<>(renderer));
            layers.add(new SlashBladesRenderer<>(renderer));
            layers.add(new ThunderGodGearRenderer<>(renderer));
            layers.add(new EarthGlovesRenderer<>(renderer));
            layers.add(new ValkyrieArmorRenderer<>(renderer));
            layers.add(new ManaSkinRenderer<>(renderer));
            layers.add(new BearClawRenderer<>(renderer));
            layers.add(new RhinocerosArmorRenderer<>(renderer));
            layers.add(new WagnerDevilFamiliarRenderer<>(renderer));
            layers.add(new LilithDevilFamiliarRenderer<>(renderer));
            layers.add(new NahamanDevilFamiliarRenderer<>(renderer));
            layers.add(new ManaLayerRenderer<>(renderer));

             */
        }
    }
}
