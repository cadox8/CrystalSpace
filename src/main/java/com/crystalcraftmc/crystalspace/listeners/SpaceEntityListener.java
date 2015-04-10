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

import com.crystalcraftmc.crystalspace.api.SpacePlayerHandler;
import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.logging.Level;

/**
 * EntityListener.
 * 
 * @author iffa
 */
public class SpaceEntityListener implements Listener {

    /**
     * Called when an entity (attempts) to take damage.
     * 
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == DamageCause.DROWNING) {
            Player player = (Player) event.getEntity();
            if (ConfigHandler.getStopDrowning()) {
                for (World world : ConfigHandler.getStopDrowningWorlds()) {
                    if (world == player.getWorld() && player.getInventory().getHelmet() != null && player.getInventory().getHelmet() == SpacePlayerHandler.toItemStack(ConfigHandler.getHelmet())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    /**
     * Called when an entity dies.
     * 
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(SpaceSuffocationListener.stopSuffocating(p)){
                MessageHandler.debugPrint(Level.INFO, "Cancelled suffocating task for player '" + p.getName() + "' because (s)he died.");
            }
        }
    }
}
