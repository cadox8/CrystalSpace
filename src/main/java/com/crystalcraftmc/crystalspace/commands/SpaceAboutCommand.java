/*
 * Copyright (c) 2015, Justin W. Flory, CrystalCraftMC
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
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents "/space about".
 * 
 * @author iffa
 * @author jflory7
 */
public class SpaceAboutCommand extends SpaceCommand {
    /**
     * Constructor of SpaceAboutCommand.
     * 
     * @param plugin CrystalSpace instance
     * @param sender the command sender
     * @param args command arguments
     */
    public SpaceAboutCommand(Space plugin, CommandSender sender, String[] args) {
        super(plugin, sender, args);
    }

    /**
     * Executes <code>/space about</code> command.
     */
    @Override
    public void command() {
        if (getArgs().length < 2) {
            getSender().sendMessage(ChatColor.GOLD + "About:");
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " You're running version " +
                    ChatColor.GOLD + getPlugin().getDescription().getVersion());
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " There are currently " +
                    ChatColor.GOLD + WorldHandler.getSpaceWorlds().size() + ChatColor.GRAY + " space worlds loaded.");
        } else if (getArgs().length < 3 && getArgs().equals("developers")) {
            getSender().sendMessage(ChatColor.GOLD + "-" + ChatColor.GRAY + " Core Developers:");
            getSender().sendMessage(ChatColor.GOLD + "    jflory7, iffa");
        }
    }
}
