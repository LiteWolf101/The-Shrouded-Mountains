package com.litewolf101.the_shrouded_mountains.world.biome;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class BiomeTundra extends Biome {
    private static BiomeProperties properties = new Biome.BiomeProperties("Tundra").setBaseHeight(0.45F).setHeightVariation(0.3F).setTemperature(0F).setRainfall(0.5F).setSnowEnabled();

    public BiomeTundra() {
        super(properties);
        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        spawnableCaveCreatureList.clear();

        spawnableWaterCreatureList.clear();

        spawnableMonsterList.clear();

        spawnableCreatureList.clear();
    }

    @Override
    public float getSpawningChance() {
        return 0.05F;
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.generateTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    protected void generateTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int seaLevel = worldIn.getSeaLevel();
        IBlockState topBlock = this.topBlock;
        IBlockState fillerBlock = this.fillerBlock;
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkX = x & 15;
        int chunkZ = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int currentY = 255; currentY >= 0; --currentY) {
            if(currentY <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, Blocks.BEDROCK.getDefaultState());
            }
            else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(chunkZ, currentY, chunkX);

                if(iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                }
                else if(iblockstate2.getBlock() == Blocks.STONE) {
                    if(j == -1) {
                        if(k <= 0) {
                            topBlock = AIR;
                            fillerBlock = STONE;
                        }
                        else if(currentY >= seaLevel - 4 && currentY <= seaLevel + 1) {
                            topBlock = Blocks.SNOW.getDefaultState();
                            fillerBlock = Blocks.DIRT.getDefaultState();
                        }

                        if(currentY < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
                            if(this.getTemperature(blockpos$mutableblockpos.setPos(x, currentY, z)) < 0.15F) {
                                topBlock = ICE;
                            }
                            else {
                                topBlock = WATER;
                            }
                        }

                        j = k;

                        if(currentY >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, topBlock);
                        }
                        else if(currentY < seaLevel - 7 - k) {
                            topBlock = AIR;
                            fillerBlock = STONE;
                            chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, GRAVEL);
                        }
                        else {
                            chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, fillerBlock);
                        }
                    }
                    else if(j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(chunkZ, currentY, chunkX, fillerBlock);

                        if(j == 0 && fillerBlock.getBlock() == Blocks.SAND && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, currentY - 63);
                            fillerBlock = fillerBlock.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                        }
                    }
                }
            }
        }
    }
}
