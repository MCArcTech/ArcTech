package com.mrmelon54.ArcTech.fabriclike;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class LoadModelExpectPlatformImpl {
    public static <T extends UnbakedModel> void loadModel(ResourceLocation id, Supplier<T> supplier) {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(resourceManager -> new SimpleModelLoader<>(id, supplier));
    }
}
