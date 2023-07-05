package com.mrmelon54.ArcTech;

import com.mrmelon54.ArcTech.block.CraftingStationBlock;
import com.mrmelon54.ArcTech.block.WireCopperBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class Blocks {
    public static final Block CRAFTING_STATION = new CraftingStationBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.5f).ignitedByLava());
    public static final Block WIRE_COPPER = new WireCopperBlock(BlockBehaviour.Properties.of().sound(SoundType.COPPER).strength(0.5f).dynamicShape());
    public static final Block LITHIUM_ORE =  new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7));
    public static final Block DEEPSLATE_LITHIUM_ORE = new DropExperienceBlock(BlockBehaviour.Properties.copy(LITHIUM_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7));
}
