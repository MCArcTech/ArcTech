package com.mrmelon54.ArcTech.init;

import com.mrmelon54.ArcTech.ArcTech;
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
        initBlockItem("crafting_station", Blocks.CRAFTING_STATION, new Item.Properties().stacksTo(61).arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("wire_copper", Blocks.WIRE_COPPER, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("wire_electric", Blocks.WIRE_ELECTRIC, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("lithium_ore", Blocks.LITHIUM_ORE, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("deepslate_lithium_ore", Blocks.DEEPSLATE_LITHIUM_ORE, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initBlockItem("lithium_block", Blocks.LITHIUM_BLOCK, new Item.Properties().arch$tab(CreativeTab.ARCTECH_CREATIVE_TAB));
        initItem("crescent_hammer", Items.CRESCENT_HAMMER);
        initItem("raw_lithium", Items.RAW_LITHIUM);
        initItem("lithium_ingot", Items.LITHIUM_INGOT);
    }

    private static ResourceLocation id(String a) {
        return new ResourceLocation(ArcTech.MOD_ID, a);
    }

    private static void initBlockItem(String loc, Block block, Item.Properties properties) {
        blockReg.register(id(loc), () -> block);
        initItem(loc, new BlockItem(block, properties));
    }

    private static void initItem(String loc, Item item) {
        itemReg.register(id(loc), () -> item);
    }
}
