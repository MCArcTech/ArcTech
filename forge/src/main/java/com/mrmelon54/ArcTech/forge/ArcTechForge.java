package com.mrmelon54.ArcTech.forge;

import com.mrmelon54.ArcTech.ArcTech;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ArcTech.MOD_ID)
public class ArcTechForge {
    public ArcTechForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ArcTech.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ArcTech.init();
    }
}
