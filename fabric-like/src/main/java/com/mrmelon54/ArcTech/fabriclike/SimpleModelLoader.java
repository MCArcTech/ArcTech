package com.mrmelon54.ArcTech.fabriclike;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SimpleModelLoader<T extends UnbakedModel> implements ModelResourceProvider {
    private final ResourceLocation id;
    private final Supplier<T> supplier;

    public SimpleModelLoader(ResourceLocation id, Supplier<T> supplier) {
        this.id = id;
        this.supplier = supplier;
    }

    @Override
    public @Nullable UnbakedModel loadModelResource(ResourceLocation resourceId, ModelProviderContext context) throws ModelProviderException {
        return resourceId.equals(id) ? supplier.get() : null;
    }
}
