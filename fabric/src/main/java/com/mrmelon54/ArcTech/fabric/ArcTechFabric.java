package com.mrmelon54.ArcTech.fabric;

import com.mrmelon54.ArcTech.fabriclike.ArcTechFabricLike;
import net.fabricmc.api.ModInitializer;

public class ArcTechFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ArcTechFabricLike.init();
    }
}
