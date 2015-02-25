// Package Declaration
package com.crystalcraftmc.crystalspace.api;

// Java Imports

import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

// CrystalSpace Imports
// Bukkit Imports

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
