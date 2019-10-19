package com.litewolf101.the_shrouded_mountains.registry;

import com.litewolf101.the_shrouded_mountains.TheShroudedMountains;
import com.litewolf101.the_shrouded_mountains.world.dimension.ShroudedMountainsDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = TheShroudedMountains.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionRegistry {
    //public static DimensionType the_shrouded_mountains;
    public static final ModDimension THE_SHROUDED_MOUNTAINS = new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return ShroudedMountainsDimension::new;
        }
    };

    private static final ResourceLocation SHROUDED_ID = new ResourceLocation(TheShroudedMountains.MODID, "the_shrouded_mountains");

    @SubscribeEvent
    public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {
        THE_SHROUDED_MOUNTAINS.setRegistryName(TheShroudedMountains.MODID, "the_shrouded_mountains");
        event.getRegistry().register(THE_SHROUDED_MOUNTAINS);
    }

    public static DimensionType the_shrouded_mountains () {
        return DimensionType.byName(SHROUDED_ID);
    }

    @Mod.EventBusSubscriber(modid = TheShroudedMountains.MODID)
    private static class ForgeEvents {
        @SubscribeEvent
        public static void registerDimensions(RegisterDimensionsEvent event) {
            if (DimensionType.byName(SHROUDED_ID) == null) {
                DimensionManager.registerDimension(SHROUDED_ID, THE_SHROUDED_MOUNTAINS, null, true);
            }
        }
    }
}
