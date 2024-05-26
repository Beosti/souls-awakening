package com.yuanno.soulsawakening.mixins.client;

import com.yuanno.soulsawakening.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.Splashes;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(Splashes.class)
public class SplashesMixin {

    private static final ResourceLocation SA_SPLASHES = new ResourceLocation(Main.MODID, "texts/splashes.txt");
    private static final Random RANDOM = new Random();

    @Inject(method = "getSplash", at = @At("HEAD"), cancellable = true)
    private void getSplash(CallbackInfoReturnable<String> cir) {

        List<String> splashes = new ArrayList<>();
        try (IResource iresource = Minecraft.getInstance().getResourceManager().getResource(SA_SPLASHES);
             BufferedReader reader = new BufferedReader(new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8));) {
            reader.lines().map(String::trim).filter((splash) -> {
                return splash.hashCode() != 125780783;
            }).forEach(splashes::add);
        }
        catch (IOException ex) {
        }
         cir.setReturnValue(splashes.get(RANDOM.nextInt(splashes.size())));
    }
}
