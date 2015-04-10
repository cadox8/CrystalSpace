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
package com.crystalcraftmc.crystalspace.api;

import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Useful methods to send messages to players and console.
 * 
 * External use only
 * 
 * @author iffa
 */
public class SpaceMessageHandler {
    // Variables
    protected static final Logger log = Bukkit.getLogger();
    private static String printPrefix = "[Unknown]";
    
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
     * Constructor of SpaceMessageHandler.
     */
    protected SpaceMessageHandler() {
    }
}
