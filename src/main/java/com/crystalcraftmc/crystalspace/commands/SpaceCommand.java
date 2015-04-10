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
import org.bukkit.command.CommandSender;

/**
 * Represents "/space args".
 * 
 * @author iffa
 */
public abstract class SpaceCommand {
    // Variables
    private Space plugin;
    private CommandSender sender;
    private String[] args;

    /**
     * Constructor of SpaceCommand.
     * 
     * @param plugin CrystalSpace instance
     * @param sender Command sender
     * @param args Command arguments
     */
    public SpaceCommand(Space plugin, CommandSender sender, String[] args) {
        this.plugin = plugin;
        this.sender = sender;
        this.args = args;
        command(); // It's bad but fuck that
    }

    /**
     * Does the command.
     */
    public abstract void command();

    /**
     * Gets the plugin.
     * 
     * @return the plugin
     */
    public Space getPlugin() {
        return plugin;
    }

    /**
     * Gets the sender.
     * 
     * @return the sender
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * Gets the arguments.
     * 
     * @return the args
     */
    public String[] getArgs() {
        return args;
    }
}
