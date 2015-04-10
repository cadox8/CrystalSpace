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
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents "/space help".
 * 
 * @author iffa
 */
public class SpaceHelpCommand extends SpaceCommand {
    /**
     * Constructor of SpaceHelpCommand.
     * 
     * @param plugin CrystalSpace instance
     * @param sender Command sender
     * @param args Command arguments
     */
    public SpaceHelpCommand(Space plugin, CommandSender sender, String[] args) {
        super(plugin, sender, args);
    }

    /**
     * Does the command.
     */
    @Override
    public void command() {
        getSender().sendMessage(ChatColor.GOLD + "[CrystalSpace] Usage:");
        getSender().sendMessage(ChatColor.GRAY + " /space enter [world] - Go to space (default world or given one)");
        getSender().sendMessage(ChatColor.GRAY + " /space back - Leave space or go back where you were in space");
        getSender().sendMessage(ChatColor.GRAY + " /space list - Brings up a list of all space worlds");
        getSender().sendMessage(ChatColor.GRAY + " /space help - Brings up this help message");
        getSender().sendMessage(ChatColor.GRAY + " /space about [credits] - About CrystalSpace");
        getSender().sendMessage(ChatColor.GRAY + "If you have questions, please visit " + ChatColor.GOLD + "bit.ly/banspace" + ChatColor.GRAY + "!");
        //getSender().sendMessage(ChatColor.GRAY + "...or if you prefer IRC, #iffa or #bananacode (Espernet)");
    }
}
