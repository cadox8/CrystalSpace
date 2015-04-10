/*
 * Copyright 2015 CrystalCraftMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Package Declaration
package com.crystalcraftmc.crystalspace.commands;

import com.crystalcraftmc.crystalspace.Space;
import com.crystalcraftmc.crystalspace.economy.Economy;
import com.crystalcraftmc.crystalspace.handlers.LangHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import com.crystalcraftmc.crystalspace.handlers.PlayerHandler;
import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Represents "/space enter [spaceworld]".
 * 
 * @author iffa
 */
public class SpaceEnterCommand extends SpaceCommand {
    // Variables
    public static Map<Player, Location> exitDest = new HashMap<Player, Location>();

    /**
     * Constructor of SpaceEnterCommand.
     * 
     * @param plugin CrystalSpace instance
     * @param sender Command sender
     * @param args Command arguments
     */
    public SpaceEnterCommand(Space plugin, CommandSender sender, String[] args) {
        super(plugin, sender, args);
    }

    /**
     * Does the command.
     */
    @Override
    public void command() {
        Player player = (Player) this.getSender();
        if (getArgs().length == 1) {
            if (PlayerHandler.hasPermission("CrystalSpace.teleport.enter", player)) {
                if (WorldHandler.getSpaceWorlds().isEmpty()) {
                    player.sendMessage(ChatColor.RED + LangHandler.getNoSpaceLoaded());
                    return;
                }
                if (WorldHandler.getSpaceWorlds().get(0) == player.getWorld()) {
                    player.sendMessage(ChatColor.RED + LangHandler.getAlreadyInThatWorldMessage());
                    MessageHandler.debugPrint(Level.INFO, player.getName() + "tried to use /space enter, but he was already in that space world.");
                    return;
                }
                if (!Economy.enterCommand(player)) {
                    MessageHandler.sendNotEnoughMoneyMessage(player);
                    return;
                }
                exitDest.put(player, player.getLocation());
                Location location;
                if (SpaceExitCommand.enterDest.containsKey(player)) {
                    location = SpaceExitCommand.enterDest.get(player);
                } else {
                    if (WorldHandler.getSpaceWorlds().get(0) == null) {
                        MessageHandler.debugPrint(Level.SEVERE, "Entry #0 in getSpaceWorlds() is null!");
                    }
                    location = WorldHandler.getSpaceWorlds().get(0).getSpawnLocation();
                }
                MessageHandler.debugPrint(Level.INFO, "Teleported player '" + player.getName() + "' to space.");
                player.teleport(location, TeleportCause.COMMAND);
                return;
            }
            MessageHandler.sendNoPermissionMessage(player);
            return;
        } else if (getArgs().length >= 2) {
            if (PlayerHandler.hasPermission("CrystalSpace.teleport.enter", player)) {
                if (!Economy.enterCommand(player)) {
                    MessageHandler.sendNotEnoughMoneyMessage(player);
                    return;
                }
                if (Bukkit.getServer().getWorld(getArgs()[1]) == null) {
                    player.sendMessage(ChatColor.RED + LangHandler.getWorldNotFoundMessage());
                    return;
                }
                if (!WorldHandler.isSpaceWorld(Bukkit.getServer().getWorld(this.getArgs()[1]))) {
                    player.sendMessage(ChatColor.RED + LangHandler.getWorldNotSpaceMessage());
                    return;
                }
                if (Bukkit.getServer().getWorld(getArgs()[1]) == player.getWorld()) {
                    player.sendMessage(ChatColor.RED + LangHandler.getAlreadyInThatWorldMessage());
                    return;
                }
                exitDest.put(player, player.getLocation());
                Location location;
                if (SpaceExitCommand.enterDest.containsKey(player)) {
                    location = SpaceExitCommand.enterDest.get(player);
                } else {
                    location = Bukkit.getServer().getWorld(getArgs()[1]).getSpawnLocation();
                }
                MessageHandler.debugPrint(Level.INFO, "Teleported player '" + player.getName() + "' to space.");
                player.teleport(location, TeleportCause.COMMAND);
                return;
            }
        }
        MessageHandler.sendNoPermissionMessage(player);
    }
}