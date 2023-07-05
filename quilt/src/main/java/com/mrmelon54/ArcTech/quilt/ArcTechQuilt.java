package com.mrmelon54.ArcTech.quilt;

import com.mrmelon54.ArcTech.fabriclike.ArcTechFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ArcTechQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ArcTechFabricLike.init();
    }
}
