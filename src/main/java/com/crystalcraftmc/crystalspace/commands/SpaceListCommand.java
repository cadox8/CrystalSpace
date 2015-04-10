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
import java.util.logging.Level;

/**
 * Represents "/space list".
 * 
 * @author iffa
 */
public class SpaceListCommand extends SpaceCommand {
    /**
     * Constructor of SpaceListCommand.
     * 
     * @param plugin CrystalSpace instance
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
        if (!PlayerHandler.hasPermission("CrystalSpace.teleport.list", (Player) this.getSender())) {
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
            if (world == null) {
                MessageHandler.debugPrint(Level.SEVERE, "world is null in SpaceListCommand! :(");
                continue;
            }
            spaceWorlds.add(world.getName());
        }
        getSender().sendMessage(ChatColor.GRAY + spaceWorlds.toString().replace("]", "").replace("[", ""));
    }
}
