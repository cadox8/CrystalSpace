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

import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import com.crystalcraftmc.crystalspace.wgen.populators.SpaceBlackHolePopulator;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Player listener to trigger black hole sucking.
 *
 * @author iffa
 * @author jflory7
 */
public class BlackHolePlayerListener implements Listener {
    // Variables
    private static Map<Player, Integer> runnables = new HashMap<Player, Integer>();
    private static Map<Chunk, Boolean> scannedNonSpout = new HashMap<Chunk, Boolean>();
    private static final int SIZE = 20;
    private static long lastTime = System.currentTimeMillis();
    private static List<Block> nonSpoutBlocks = new ArrayList<Block>(); //To be used if not using Spout and black holes is on

    /**
     * Called when a player attempts to move.
     *
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled() || !WorldHandler.isInAnySpace(event.getPlayer()) || event.getPlayer().getHealth() == 0 || event.getPlayer().hasPermission("CrystalSpace.ignoreblackholes")) return;
        long currentTime = System.currentTimeMillis();
        if (!(lastTime + 200 <= currentTime)) return;
        lastTime = System.currentTimeMillis();
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event){// Check if breaking non-spout black hole
	if (event.isCancelled() || !WorldHandler.isInAnySpace(event.getPlayer()) || event.getPlayer().getHealth() == 0 || !event.getPlayer().hasPermission("CrystalSpace.ignoreblackholes")) {
            return;
        }
	String id = WorldHandler.getID(event.getPlayer().getWorld());
	if(Action.LEFT_CLICK_BLOCK != event.getAction() || event.getClickedBlock().getTypeId() != SpaceBlackHolePopulator.ID_TO_USE){
	    return;
	}
	event.getClickedBlock().setTypeId(0);
    }
    /**
     * Gets all running suck tasks.
     *
     * @return All tasks
     */
    public static Map<Player, Integer> getRunningTasks() {
        return runnables;
    }

    public static void stopRunnable(Player player) {
        Bukkit.getScheduler().cancelTask(runnables.get(player));
        runnables.remove(player);
    }
    
    private static void checkBlocksNonSpout(Location loc) {
        Chunk center = loc.getChunk();
        int chunks = (int) Math.ceil((SIZE - 1) / 16);
        chunks = chunks > Bukkit.getViewDistance() ? Bukkit.getViewDistance() : chunks;
        for (int chunkX = -chunks; chunkX <= chunks; chunkX++) {
            for (int chunkZ = -chunks; chunkZ <= chunks; chunkZ++) {
                Chunk chunk = loc.getWorld().getChunkAt(center.getX() + chunkX, center.getZ() + chunkZ);
                if (scannedNonSpout.containsKey(chunk) && scannedNonSpout.get(chunk)) {
                    continue;
                }
                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 128; y++) {
                        for (int z = 0; z < 16; z++) {
			    Block block = chunk.getBlock(x, y, z);
			    if(block.getTypeId() == SpaceBlackHolePopulator.ID_TO_USE){
				nonSpoutBlocks.add(block);
			    }

                        }
                    }
                }
                scannedNonSpout.put(chunk, true);
            }
        }
    }
}
