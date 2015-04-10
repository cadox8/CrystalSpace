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
import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents "/space about".
 * 
 * @author iffa
 */
public class SpaceAboutCommand extends SpaceCommand {
    /**
     * Constructor of SpaceAboutCommand.
     * 
     * @param plugin CrystalSpace instance
     * @param sender Command sender
     * @param args Command arguments
     */
    public SpaceAboutCommand(Space plugin, CommandSender sender, String[] args) {
        super(plugin, sender, args);
    }

    /**
     * Does the command.
     */
    @Override
    public void command() {
        if (getArgs().length < 2) {
            getSender().sendMessage(ChatColor.GOLD + Space.getPrefix() + " About:");
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " You're running version " + ChatColor.GOLD + getPlugin().getDescription().getVersion());
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " There are currently "+ ChatColor.GOLD + WorldHandler.getSpaceWorlds().size() + ChatColor.GRAY + " space worlds loaded");
        } else {
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " Main developers:");
            getSender().sendMessage(ChatColor.GOLD + "    iffa, kitskub");
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " Other contributors (in no particular order):");
            getSender().sendMessage(ChatColor.GOLD + "    Canis85, BR, SwearWord, HACKhalo2");
        }
    }
    
}
