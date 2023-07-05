package com.mrmelon54.ArcTech.forge.mixin;

import com.mrmelon54.ArcTech.forge.LoadModelExpectPlatformImpl;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {
    @Shadow
    protected abstract void cacheAndQueueDependencies(ResourceLocation resourceLocation, UnbakedModel unbakedModel);

    @Inject(method = "loadModel", at = @At("HEAD"), cancellable = true)
    private void loadModelHook(ResourceLocation id, CallbackInfo ci) {
        var model = LoadModelExpectPlatformImpl.getBuildInModel(id);
        if (model != null) {
            cacheAndQueueDependencies(id, model);
            ci.cancel();
        }
    }
}
