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
import com.crystalcraftmc.crystalspace.api.event.misc.SpaceCommandEvent;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Handles sending the command ahead to the SpaceCommand-objects.
 * 
 * @author iffa
 */
public class SpaceCommandHandler implements CommandExecutor {
    // Variables
    private Space plugin;

    /**
     * Constructor for SpaceCommandHandler.
     * 
     * @param plugin CrystalSpace instance
     */
    public SpaceCommandHandler(Space plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when a player uses the command 'space'.
     * 
     * @param sender CommandSender
     * @param command Command
     * @param label Command label
     * @param args Command arguments
     * 
     * @return True if no usage information should be sent, i.e command was successfull
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        /* Notify listeners start */
        SpaceCommandEvent e = new SpaceCommandEvent("SpaceCommandEvent", sender, args);
        if (e.isCancelled()) {
            MessageHandler.debugPrint(Level.INFO, "External plugin cancelled SpaceCommandEvent using the API.");
            return true;
        }
        /* Notify listeners end */
        if (!(sender instanceof Player)) {
            MessageHandler.debugPrint(Level.INFO, "An unknown person tried to use the command. (sorry if it's you, console!)");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 1 && args[0].equalsIgnoreCase("enter")) {
            // SpaceEnterCommand, 1 argument
            SpaceEnterCommand enterCommand = new SpaceEnterCommand(plugin, sender, args);
            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("enter")) {
            // SpaceEnterCommand, 2 arguments
            SpaceEnterCommand enterCommand = new SpaceEnterCommand(plugin, sender, args);
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("back")) {
            // SpaceExitCommand, 1 argument
            SpaceExitCommand exitCommand = new SpaceExitCommand(plugin, sender, args);
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            // SpaceListCommand, 1 argument
            SpaceListCommand listCommand = new SpaceListCommand(plugin, sender, args);
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            // SpaceHelpCommand, 1 argument
            SpaceHelpCommand helpCommand = new SpaceHelpCommand(plugin, sender, args);
            return true;
        } else if ((args.length == 1 && args[0].equalsIgnoreCase("about")) || (args.length == 2 && args[0].equalsIgnoreCase("about") && args[1].equalsIgnoreCase("credits"))) {
            // SpaceAboutCommand, 1 argument
            SpaceAboutCommand aboutCommand = new SpaceAboutCommand(plugin, sender, args);
            return true;
        } else {
            // SpaceHelpCommand, 1 argument
            SpaceHelpCommand helpCommand = new SpaceHelpCommand(plugin, sender, args);
            return true;
        }
    }
}