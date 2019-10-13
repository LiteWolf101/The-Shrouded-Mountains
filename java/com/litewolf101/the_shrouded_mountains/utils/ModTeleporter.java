package com.litewolf101.the_shrouded_mountains.utils;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;

public class ModTeleporter extends Teleporter {
    private final WorldServer worldServer;
    private double x;
    private double y;
    private double z;
    public ModTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.worldServer = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
        int oldDimension = player.getEntityWorld().provider.getDimension();
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
        MinecraftServer server = player.getEntityWorld().getMinecraftServer();
        WorldServer worldServer = server.getWorld(dimension);
        player.addExperienceLevel(0);
        BlockPos.MutableBlockPos worldpos = new BlockPos.MutableBlockPos((int)x, (int)y, (int)z);

        if(worldServer == null || worldServer.getMinecraftServer() == null) {
            throw new IllegalArgumentException("Dimension: " + dimension + " doesn't exist!");
        }

        //TODO: Fix
        for(int checkY = 255; checkY > 6; checkY--) {
            if(worldServer.isAirBlock(new BlockPos(x, checkY, z)) && worldServer.getBlockState(new BlockPos(x, checkY -1, z)).getBlock() != Blocks.LAVA && worldServer.isAirBlock(new BlockPos(x, checkY + 1, z)) && !worldServer.isAirBlock(new BlockPos(x, checkY -1, z))) {
                worldpos.setY(checkY);
                break;
            }
        }


        worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new ModTeleporter(worldServer, worldpos.getX(), worldpos.getY(), worldpos.getZ()));
        player.setPositionAndUpdate(worldpos.getX() + 0.5, worldpos.getY(), worldpos.getZ() + 0.5);

        if(oldDimension == 1) {
            // For some reason teleporting out of the end does weird things. Compensate for that
            player.setPositionAndUpdate(x, y, z);
            worldServer.spawnEntity(player);
            worldServer.updateEntityWithOptionalForce(player, false);
        }
    }

    @Override
    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
        // The main purpose of this function is to *not* create a nether portal
        this.worldServer.getBlockState(new BlockPos((int)this.x, (int)this.y, (int)this.z));

        entity.setPosition(this.x, this.y, this.z);
        entity.motionX = 0.0f;
        entity.motionY = 0.0f;
        entity.motionZ = 0.0f;
    }
}
