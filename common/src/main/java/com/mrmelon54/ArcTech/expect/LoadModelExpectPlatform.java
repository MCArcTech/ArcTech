package com.mrmelon54.ArcTech.expect;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class LoadModelExpectPlatform {
    @ExpectPlatform
    public static <T extends UnbakedModel> void loadModel(ResourceLocation id, Supplier<T> supplier) {
        throw new AssertionError();
    }
}
