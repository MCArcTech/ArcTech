package com.mrmelon54.ArcTech.init;

import com.mrmelon54.ArcTech.ArcTech;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTab {
    public static final CreativeModeTab ARCTECH_CREATIVE_TAB = CreativeTabRegistry.create(Component.translatable("creative-tab.arctech.title"), () -> new ItemStack(Blocks.CRAFTING_STATION));

    public static void init() {
        ArcTech.MANAGER.get().get(Registries.CREATIVE_MODE_TAB).register(new ResourceLocation("creative-tab.arctech"), () -> CreativeTab.ARCTECH_CREATIVE_TAB);
    }
}
