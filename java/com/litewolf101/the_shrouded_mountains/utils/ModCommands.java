package com.litewolf101.the_shrouded_mountains.utils;

import com.google.common.collect.ImmutableList;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.litewolf101.the_shrouded_mountains.TheShroudedMountains.MOD_NAME;
import static com.litewolf101.the_shrouded_mountains.TheShroudedMountains.VERSION;

public class ModCommands extends CommandBase {
    private final String name = "theshroudedmountains";
    private final List<String> commands = new ArrayList<String>();
    private static final List<String> aliases = ImmutableList.of("theshroudedmountains", "shroudedm", "tsm");

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public String getName() {
        return name;
    }

    public ModCommands() {
        commands.add("data");
        commands.add("dim_teleport");
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "data: Displays The mod name, current version of mod and current version of minecraft.\n" + "dim_teleport: Teleports the player to the given coordinates in the mod's dimension. \n";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender sender, String[] strings) throws CommandException {
        if(!(sender instanceof EntityPlayerMP)) {
            throw new WrongUsageException("message.command.onlyInGame");
        }
        EntityPlayerMP player = (EntityPlayerMP)sender;
        WorldServer world = player.getServerWorld();
        if(strings.length < 1) {
            throw new WrongUsageException(getUsage(sender));
        }
        if(strings[0].equals("data")) {
            sender.sendMessage(new TextComponentString(TextFormatting.BLUE + "\u2605" + "MOD: " + TextFormatting.RESET + MOD_NAME + "\n" + TextFormatting.BLUE + "\u2605" + "VERSION: " + TextFormatting.RESET + VERSION + "\n" + TextFormatting.BLUE + "\u2605" + "MINECRAFT VERSION: " + TextFormatting.RESET + "1.12.2"));
        }
        if(strings[0].equals("dim_teleport")) {
            if(sender instanceof EntityPlayer) {
                BlockPos blockpos = parseBlockPos(sender, strings, 1, false);
                ModTeleporter.teleportToDimension((EntityPlayer)sender, /*ModConfig.dimension_ID*/ 100, blockpos.getX(), blockpos.getY(), blockpos.getZ());
            }
        }

    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args[0].equals("dim_teleport")) {
            if (args.length > 1 && args.length <= 4) {
                return getTabCompletionCoordinate(args, 0, targetPos);
            }
        }
        return args.length == 1 ? commands : Collections.emptyList();
    }
}
