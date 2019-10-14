package com.litewolf101.the_shrouded_mountains.proxy;

import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("ERROR: Incorrect client code usage!");
    }
}
