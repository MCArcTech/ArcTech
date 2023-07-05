package com.mrmelon54.ArcTech;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;

import java.util.function.Supplier;

public class ArcTech {
    public static final String MOD_ID = "arctech";
    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(ArcTech.MOD_ID));

    public static void init() {
        CreativeTab.init();
        BlocksAndItems.init();
    }
}
