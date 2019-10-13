package com.litewolf101.the_shrouded_mountains.world.dimension;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import static com.litewolf101.the_shrouded_mountains.TheShroudedMountains.MODID;

public class ModDimension {
    public static DimensionType the_shrouded_mountains;
    
    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(/*ModConfig.dimension_ID*/ 100, ModDimension.the_shrouded_mountains);
    }

    private static void registerDimensionTypes() {
        the_shrouded_mountains = DimensionType.register(MODID, "_the_shrouded_mountains", /*ModConfig.dimension_ID*/ 100, ModWorldProvider.class, false);
    }
}
