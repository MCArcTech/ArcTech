package com.mrmelon54.ArcTech.forge;

import com.mrmelon54.ArcTech.ArcTech;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LoadModelExpectPlatformImpl {
    private static final Map<ResourceLocation, UnbakedModel> builtInModels = new HashMap<>();

    public static <T extends UnbakedModel> void loadModel(ResourceLocation id, Supplier<T> supplier) {
        builtInModels.put(id, supplier.get());
    }

    public static UnbakedModel getBuildInModel(ResourceLocation variantId) {
        if (!ArcTech.MOD_ID.equals(variantId.getNamespace())) return null;

        // Vanilla loads item models as <id>#inventory, which we replicate here
        if (variantId instanceof ModelResourceLocation modelId) {
            if ("inventory".equals(modelId.getVariant())) {
                var itemModelId = new ResourceLocation(modelId.getNamespace(), "item/" + modelId.getPath());
                return builtInModels.get(itemModelId);
            }

            return null;
        }
        return builtInModels.get(variantId);
    }
}
