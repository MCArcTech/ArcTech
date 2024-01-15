package com.mrmelon54.ArcTech.wire;

import com.mrmelon54.ArcTech.ArcTech;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class WireModel implements UnbakedModel, BakedModel {
    private final Map<ResourceLocation, BakedModel> partModels = new HashMap<>();

    // materials
    private Material PARTICLE;
    private Material MODEL_TEXTURE;

    // parts
    private ResourceLocation PART_CENTRAL;
    private ResourceLocation[] PART_SIDE;
    private List<ResourceLocation> PART_ALL;

    // properties
    private static final BooleanProperty[] PART_PROPERTY = new BooleanProperty[]{
            BlockStateProperties.UP,
            BlockStateProperties.DOWN,
            BlockStateProperties.NORTH,
            BlockStateProperties.SOUTH,
            BlockStateProperties.EAST,
            BlockStateProperties.WEST,
    };

    private static TextureAtlasSprite particleTexture;

    public WireModel(String modelType) {
        PARTICLE = new Material(TextureAtlas.LOCATION_PARTICLES, new ResourceLocation(ArcTech.MOD_ID, "texture/wire/" + modelType + "/particle"));
        MODEL_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(ArcTech.MOD_ID, "texture/wire/" + modelType + "/wire"));

        PART_CENTRAL = new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/central");
        PART_SIDE = new ResourceLocation[]{
                new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/up"),
                new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/down"),
                new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/north"),
                new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/south"),
                new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/east"),
                new ResourceLocation(ArcTech.MOD_ID, "part/wire/" + modelType + "/west"),
        };
        PART_ALL = Stream.concat(Stream.of(PART_CENTRAL), Stream.of(PART_SIDE)).toList();
    }

    public static Supplier<BakedModel> loadAll(String modelType) {
        WireModel wire = new WireModel(modelType);
        return () -> wire;
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, RandomSource randomSource) {
        if (blockState == null) return List.of();
        List<Boolean> sides = Arrays.stream(PART_PROPERTY).map(blockState::getValue).toList();

        // start with central quad
        List<BakedQuad> bakedQuads = new ArrayList<>(partModels.get(PART_CENTRAL).getQuads(blockState, direction, randomSource));
        System.out.println("Loaded" + bakedQuads.size() + "baked quads");

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
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return particleTexture;
    }

    @Override
    public @NotNull ItemTransforms getTransforms() {
        return ItemTransforms.NO_TRANSFORMS;
    }

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }

    @Override
    public @NotNull Collection<ResourceLocation> getDependencies() {
        return PART_ALL;
    }

    @Override
    public void resolveParents(Function<ResourceLocation, UnbakedModel> function) {
        function.apply(new ResourceLocation(ArcTech.MOD_ID, "nothing"));
    }

    @Nullable
    @Override
    public BakedModel bake(ModelBaker modelBaker, Function<Material, TextureAtlasSprite> bakedTextureGetter, ModelState modelState, ResourceLocation resourceLocation) {
        System.out.println("Bake " + resourceLocation);
        modelBaker.getModel(PART_CENTRAL).bake(modelBaker, bakedTextureGetter, modelState, PART_CENTRAL);
        return null;
    }
}
