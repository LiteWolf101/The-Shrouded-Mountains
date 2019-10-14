package com.litewolf101.the_shrouded_mountains.world.biome.genlayer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class ModGenLayer extends GenLayer {
    protected GenLayer parent;

    public ModGenLayer(long seed) {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
        //check
        byte biomeSize = (byte)getModdedBiomeSize(worldType, (worldType == WorldType.DEFAULT ? 6 : 4));

        GenLayer genLayer = new GenLayerIsland(1L);
        genLayer = new GenLayerFuzzyZoom(2000L, genLayer);

        genLayer = new GenLayerBiomes(100L, genLayer);
        genLayer = GenLayerZoom.magnify(2000L, genLayer, biomeSize);

        genLayer = new GenLayerVoronoiZoom(10L, genLayer);
        genLayer.initWorldGenSeed(seed);
        return new GenLayer[] {
                null,
                genLayer,
                null
        };
    }

    public static byte getModdedBiomeSize(WorldType worldType, byte original) {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return (byte)event.getNewSize();
    }

    @Override
    public abstract int[] getInts(int x, int z, int sizeX, int sizeZ);
}