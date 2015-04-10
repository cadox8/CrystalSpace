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
package com.crystalcraftmc.crystalspace.listeners.misc;

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
/**
 * Listener for world load events etc.
 *
 * @author iffa
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
        
        // Adding to spaceWorlds in SpaceWorldHandler
        WorldHandler.addSpaceWorld(world.getName());
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
