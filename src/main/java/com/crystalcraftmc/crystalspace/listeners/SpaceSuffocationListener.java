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
package com.crystalcraftmc.crystalspace.listeners;

import com.crystalcraftmc.crystalspace.Space;
import com.crystalcraftmc.crystalspace.api.event.area.AreaEnterEvent;
import com.crystalcraftmc.crystalspace.api.event.area.AreaLeaveEvent;
import com.crystalcraftmc.crystalspace.api.event.area.SpaceEnterEvent;
import com.crystalcraftmc.crystalspace.api.event.area.SpaceLeaveEvent;
import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import com.crystalcraftmc.crystalspace.handlers.PlayerHandler;
import com.crystalcraftmc.crystalspace.runnables.SuffacationRunnable;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Jack
 */
public class SpaceSuffocationListener implements Listener {
    // Variables
    public static Map<Player, Integer> taskid = new HashMap<Player, Integer>();
    private static Space plugin;

    /**
     * Constructor of SpaceSuffocationListener.
     * 
     * @param plugin Space instance
     */
    public SpaceSuffocationListener(Space plugin) {
        SpaceSuffocationListener.plugin = plugin;
    }

    /**
     * Called when someone enters an area.
     * 
     * @param event Event data 
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAreaEnter(AreaEnterEvent event) {
        stopSuffocating(event.getPlayer());
    }

    /**
     * Called when someone leaves an area.
     * 
     * @param event Event data 
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAreaLeave(AreaLeaveEvent event) {
        startSuffocating(event.getPlayer());
    }

    /**
     * Called when someone leaves space.
     * 
     * @param event Event data 
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onSpaceLeave(SpaceLeaveEvent event) {
        stopSuffocating(event.getPlayer());
    }

    /**
     * Called when someone enters space.
     * 
     * @param event Event data 
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onSpaceEnter(SpaceEnterEvent event) {
        if (!PlayerHandler.insideArea(event.getTo())) {
            startSuffocating(event.getPlayer(), event.getTo().getWorld());
        }
    }

    /**
     * Starts suffocation for a player.
     * This is only a convience method.
     * This should NOT be used on cross-world teleportation.
     * 
     * @param player Player to suffocate
     */
    public static void startSuffocating(Player player) {
        startSuffocating(player, player.getWorld());
    }
    
    /**
     * Starts suffocation for a player.
     * 
     * @param player Player to suffocate
     * @param world the world
     */
    public static void startSuffocating(Player player, World world) {
        if (player.hasPermission("CrystalSpace.ignoresuitchecks")) {
            return;
        }
        String id = ConfigHandler.getID(world);
        boolean suffocatingOn = (ConfigHandler.getRequireHelmet(id) || ConfigHandler.getRequireSuit(id));
        MessageHandler.debugPrint(Level.INFO, "Started SuffocationRunnable for " + player.getName());
        if (suffocatingOn) {
            SuffacationRunnable task = new SuffacationRunnable(player);
            int taskInt = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, 20L, 20L);
            taskid.put(player, taskInt);
        }
    }

    /**
     * Stops a player from suffocating.
     * 
     * @param player Player to stop suffocating
     * 
     * @return True if suffocating stopped
     */
    public static boolean stopSuffocating(Player player) {
        if (!taskid.containsKey(player)) {
            return false;
        }
        if (Bukkit.getScheduler().isQueued(taskid.get(player))) {
            Bukkit.getScheduler().cancelTask(taskid.get(player));
            taskid.remove(player);
            return true;
        }
        return false;
    }
}
