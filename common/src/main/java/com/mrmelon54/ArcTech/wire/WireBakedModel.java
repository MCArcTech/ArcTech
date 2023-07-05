package com.mrmelon54.ArcTech.wire;

import com.mrmelon54.ArcTech.ArcTech;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class WireBakedModel implements BakedModel {
    private final Map<ResourceLocation, BakedModel> partModels;
    private final TextureAtlasSprite particleTexture;
    private static final Material TEXTURE_WIRE_COPPER = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(ArcTech.MOD_ID, "part/wire_copper/"));

    private static final ResourceLocation PART_CENTRAL = new ResourceLocation("arctech:part/wire_copper/central");
    private static final ResourceLocation[] PART_SIDE = new ResourceLocation[]{
            new ResourceLocation("arctech:part/wire_copper/up"),
            new ResourceLocation("arctech:part/wire_copper/down"),
            new ResourceLocation("arctech:part/wire_copper/north"),
            new ResourceLocation("arctech:part/wire_copper/south"),
            new ResourceLocation("arctech:part/wire_copper/east"),
            new ResourceLocation("arctech:part/wire_copper/west"),
    };
    private static final BooleanProperty[] PART_PROPERTY = new BooleanProperty[]{
            BlockStateProperties.UP,
            BlockStateProperties.DOWN,
            BlockStateProperties.NORTH,
            BlockStateProperties.SOUTH,
            BlockStateProperties.EAST,
            BlockStateProperties.WEST,
    };

    WireBakedModel(Map<ResourceLocation, BakedModel> partModels, TextureAtlasSprite particleTexture) {
        this.partModels = partModels;
        this.particleTexture = particleTexture;
    }


    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, RandomSource randomSource) {
        if (blockState == null) return List.of();
        List<Boolean> sides = Arrays.stream(PART_PROPERTY).map(new Function<BooleanProperty, Boolean>() {
            @Override
            public Boolean apply(BooleanProperty booleanProperty) {
                return blockState.getValue(booleanProperty);
            }
        }).toList();

        // start with central quad
        List<BakedQuad> bakedQuads = new ArrayList<>(partModels.get(PART_CENTRAL).getQuads(blockState, direction, randomSource));

        // add side quads
        for (int i = 0; i < sides.size(); i++) {
            if (sides.get(i))
                bakedQuads.addAll(partModels.get(PART_SIDE[i]).getQuads(blockState, direction, randomSource));
        }
        return bakedQuads;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return particleTexture;
    }

    @Override
    public ItemTransforms getTransforms() {
        return null;
    }

    @Override
    public ItemOverrides getOverrides() {
        return null;
    }
}
