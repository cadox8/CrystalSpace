// Package Declaration
package com.crystalcraftmc.crystalspace.listeners.misc;

// Java Imports

import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import com.crystalcraftmc.crystalspace.wgen.planets.PlanetsChunkGenerator;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.logging.Level;

// bSpace Imports
// Bukkit Imports

/**
 * Listener for world load events etc.
 *
 * @author iffamies
 */
public class SpaceWorldListener implements Listener {

    /**
     * Called when a world is loaded.
     *
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();
        if (!(world.getGenerator() instanceof PlanetsChunkGenerator)) {
            return;
        }
        String id = ConfigHandler.getID(world);
        if (ConfigHandler.forceNight(id)) {
            WorldHandler.startForceNightTask(world);
            MessageHandler.debugPrint(Level.INFO, "Started night forcing task for world '" + world.getName() + "'.");
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldUnload(WorldUnloadEvent event) {
        World world = event.getWorld();
        if (!(world.getGenerator() instanceof PlanetsChunkGenerator)) {
            return;
        }
        String id = ConfigHandler.getID(world);
        if (ConfigHandler.forceNight(id)) {
            WorldHandler.stopForceNightTask(world);
            MessageHandler.debugPrint(Level.INFO, "Stopped night forcing task for world '" + world.getName() + "'. Reason: World unload");
        }
        WorldHandler.removeSpaceWorld(world);
    }
}
