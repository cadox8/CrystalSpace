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

package com.crystalcraftmc.crystalspace.handlers;

import com.crystalcraftmc.crystalspace.api.SpaceMessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Useful methods to send messages to players and console.
 * 
 * Internal use only
 * 
 * @author Jack
 */
public class MessageHandler extends SpaceMessageHandler {
    private static String printPrefix = "[CrystalSpace]";
    
    /**
     * Prints a message to the console.
     * 
     * @param level Logging level (INFO, WARNING etc)
     * @param message Message to print
     */
    public static void print(Level level, String message) {
        log.log(level, printPrefix + " " + message);
    }

    /**
     * Prints a debug message to the console if debugging is enabled.
     * 
     * @param level Logging level (INFO, WARNING etc)
     * @param message Message to print
     */
    public static void debugPrint(Level level, String message) {
        
        if (ConfigHandler.getDebugging()) {
            log.log(level, printPrefix + " " + message);
        }
    }
    
    /**
     * Sends a "You don't have permission!"-message to a player.
     * 
     * @param player Player
     */
    public static void sendNoPermissionMessage(Player player) {
        player.sendMessage(ChatColor.RED + LangHandler.getNoPermissionMessage());
    }

    /**
     * sends a "You don't have enough money!"-message to a player.
     * 
     * @param player Player
     */
    public static void sendNotEnoughMoneyMessage(Player player) {
        player.sendMessage(ChatColor.RED + LangHandler.getNotEnoughMoneyMessage());
    }
     
    private MessageHandler() {
    }
    
    
}
