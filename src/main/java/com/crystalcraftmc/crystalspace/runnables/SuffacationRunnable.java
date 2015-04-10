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
package com.crystalcraftmc.crystalspace.runnables;

import com.crystalcraftmc.crystalspace.api.event.misc.SpaceSuffocationEvent;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import com.crystalcraftmc.crystalspace.handlers.PlayerHandler;
import com.crystalcraftmc.crystalspace.listeners.SpaceSuffocationListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * A runnable class for suffocating.
 * 
 * @author iffa
 */
public class SuffacationRunnable implements Runnable {
    // Variables
    private final Player player;
    private boolean suffocating = false;

    /**
     * Constructor for SuffacationRunnable.
     * 
     * @param player Player
     */
    public SuffacationRunnable(Player player) {
        this.player = player;
    }

    /**
     * Suffocates the player when repeated every second.
     */
    @Override
    public void run() {
        if (!player.isDead()) {
            if(PlayerHandler.checkNeedsSuffocation(player)){
                if(!suffocating){
                    /* Notify listeners start */
                    SpaceSuffocationEvent e = new SpaceSuffocationEvent(player);
                    Bukkit.getServer().getPluginManager().callEvent(e);
                    if (e.isCancelled()) {
                        return;
                    }
                    /* Notify listeners end */
                    suffocating=true;
                    player.sendMessage("You left an area and are now suffocating.");
                    MessageHandler.debugPrint(Level.INFO, "Player '" + player.getName() + "' is now suffocating in space.");
                }
            } else {
                if(suffocating) {
                    suffocating = false;
                }
            }
            
            if(suffocating){
                if (player.getHealth() < 2 && player.getHealth() > 0) {
                    player.setHealth(0);
                    SpaceSuffocationListener.stopSuffocating(player);
                    return;
                } else if (player.getHealth() <= 0) {
                    SpaceSuffocationListener.stopSuffocating(player);
                    return;
                }
                player.setHealth(player.getHealth() - 2);
            }
        } else {
            SpaceSuffocationListener.stopSuffocating(player);
        }
    }
}
