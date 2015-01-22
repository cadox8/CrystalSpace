// Package Declaration
package com.crystalcraftmc.crystalspace.commands;

// Java Imports

import com.crystalcraftmc.crystalspace.Space;
import com.crystalcraftmc.crystalspace.handlers.LangHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import com.crystalcraftmc.crystalspace.handlers.PlayerHandler;
import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

// bSpace Imports
// Bukkit Imports

/**
 * Represents "/space list".
 * 
 * @author iffa
 */
public class SpaceListCommand extends SpaceCommand {
    /**
     * Constructor of SpaceListCommand.
     * 
     * @param plugin bSpace instance
     * @param sender Command sender
     * @param args Command arguments
     */
    public SpaceListCommand(Space plugin, CommandSender sender, String[] args) {
        super(plugin, sender, args);
    }

    /**
     * Does the command.
     */
    @Override
    public void command() {
        if (!PlayerHandler.hasPermission("bSpace.teleport.list", (Player) this.getSender())) {
            MessageHandler.sendNoPermissionMessage((Player) getSender());
            return;
        }
        if (WorldHandler.getSpaceWorlds().isEmpty()) {
            getSender().sendMessage(ChatColor.RED + LangHandler.getNoSpaceLoaded());
            return;
        }
        getSender().sendMessage(ChatColor.GOLD + Space.getPrefix() + " " + LangHandler.getListOfSpaceMessage());
        List<String> spaceWorlds = new ArrayList<String>();
        for (World world : WorldHandler.getSpaceWorlds()) {
            spaceWorlds.add(world.getName());
        }
        getSender().sendMessage(ChatColor.GRAY + spaceWorlds.toString().replace("]", "").replace("[", ""));
    }
}
