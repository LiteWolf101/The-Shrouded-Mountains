package com.litewolf101.the_shrouded_mountains.world.dimension;

import com.litewolf101.the_shrouded_mountains.TheShroudedMountains;
import com.litewolf101.the_shrouded_mountains.world.biome.ModBiomeProvider;
import com.litewolf101.the_shrouded_mountains.world.chunk.ModChunkGenerator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ModWorldProvider extends WorldProvider {
    @Override
    public DimensionType getDimensionType() {
        return ModDimension.the_shrouded_mountains;
    }

    @Override
    protected void init() {
        this.biomeProvider = new ModBiomeProvider(world);
        this.nether = false;
        this.hasSkyLight = true;

    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ModChunkGenerator(world, world.getSeed());
    }

    public boolean canCoordinateBeSpawn(int x, int z) {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    public boolean canRespawnHere() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public int getAverageGroundLevel() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return world.isRaining();
    }

    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.7529412F; //Blue
        float f2 = 0.84705883F; //Red
        float f3 = 1.0F; // Green
        f1 = f1 * (f * 0.94F + 0.06F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.09F);
        return new Vec3d((double)f1, (double)f2, (double)f3);
    }

    @Nullable
    @Override
    public String getSaveFolder() {
        return TheShroudedMountains.MOD_NAME;
    }

    @Override
    public int getHeight() {
        return 256;
    }

    @Override
    public int getActualHeight() {
        return 256;
    }

    @Override
    public double getHorizon() {
        return 64;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight() {
        return 90F;
    }
}
