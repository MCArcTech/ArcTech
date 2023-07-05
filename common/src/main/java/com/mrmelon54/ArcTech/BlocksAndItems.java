package com.mrmelon54.ArcTech;

import dev.architectury.registry.registries.Registrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlocksAndItems {
    private static final Registrar<Block> blockReg = ArcTech.MANAGER.get().get(Registries.BLOCK);
    private static final Registrar<Item> itemReg = ArcTech.MANAGER.get().get(Registries.ITEM);

    public static void init() {
        initBlockItem("arctech:crafting_station", Blocks.CRAFTING_STATION, new Item.Properties().stacksTo(61).arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("arctech:wire_copper", Blocks.WIRE_COPPER, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("arctech:lithium_ore", Blocks.LITHIUM_ORE, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("arctech:deepslate_lithium_ore", Blocks.DEEPSLATE_LITHIUM_ORE, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
    }

    private static ResourceLocation id(String a) {
        return new ResourceLocation(a);
    }

    private static void initBlockItem(String loc, Block block, Item.Properties properties) {
        blockReg.register(id(loc), () -> block);
        itemReg.register(id(loc), () -> new BlockItem(block, properties));
    }
}
