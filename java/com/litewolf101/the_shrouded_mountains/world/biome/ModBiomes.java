package com.litewolf101.the_shrouded_mountains.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static com.litewolf101.the_shrouded_mountains.TheShroudedMountains.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ModBiomes {
    public static ArrayList<Biome> BIOME_LIST = new ArrayList<Biome>();

    public static final BiomeTundra TUNDRA = new BiomeTundra();
    public static final BiomeSteppe STEPPE = new BiomeSteppe();
    public static final BiomeGlaciers GLACIERS = new BiomeGlaciers();


    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        registerBiome(registry, TUNDRA, "tundra", BiomeManager.BiomeType.ICY, 20, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPOOKY);
        registerBiome(registry, STEPPE, "steppe", BiomeManager.BiomeType.COOL, 20, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SNOWY);
        registerBiome(registry, GLACIERS, "glaciers", BiomeManager.BiomeType.ICY, 20, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.COLD);
    }

    private static <T extends Biome> void registerBiome(final IForgeRegistry<Biome> registry, final T biome, final String biomeName, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
        registry.register(biome.setRegistryName(MODID, biomeName));
        BiomeDictionary.addTypes(biome, types);
        //BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        for(int x = 0; x < weight; x++) {
            BIOME_LIST.add(biome); //temp hack for now
        }
    }
}
