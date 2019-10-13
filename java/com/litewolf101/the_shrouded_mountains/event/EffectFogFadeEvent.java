package com.litewolf101.the_shrouded_mountains.event;

import com.litewolf101.the_shrouded_mountains.world.dimension.ModDimension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.litewolf101.the_shrouded_mountains.TheShroudedMountains.MODID;

//@Mod.EventBusSubscriber(modid = MODID, value = Side.CLIENT)
public class EffectFogFadeEvent {
    //Just some silly code I wanted to play around with. Ignore it.
    //The concept was that it would just simply fade in the fog whenever it rained in the custom dimension.

    /*@SubscribeEvent
    public static void changeFogRender(EntityViewRenderEvent.FogDensity event){
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer) {
            if (entity.getEntityWorld().provider.getDimensionType() == ModDimension.the_shrouded_mountains) {
                if (entity.getEntityWorld().isRaining()) {
                    event.setDensity(0.07f);
                } else {
                    event.setDensity(0.01f);
                }
            }
            event.setDensity(0.003f);
        }
        event.setCanceled(true);
    }*/
}
