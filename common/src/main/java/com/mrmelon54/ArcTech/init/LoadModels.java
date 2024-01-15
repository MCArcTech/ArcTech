package com.mrmelon54.ArcTech.init;

import com.mrmelon54.ArcTech.ArcTech;
import com.mrmelon54.ArcTech.LoadModelExpectPlatform;
import com.mrmelon54.ArcTech.wire.WireModel;
import net.minecraft.resources.ResourceLocation;

public class LoadModels {
    public static void init() {
        LoadModelExpectPlatform.loadModel(new ResourceLocation(ArcTech.MOD_ID, "block/wire_electric"), WireModel.loadAll("electric"));
    }
}
