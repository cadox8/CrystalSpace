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

import com.crystalcraftmc.crystalspace.api.event.area.SpaceEnterEvent;
import com.crystalcraftmc.crystalspace.api.event.area.SpaceLeaveEvent;
import com.crystalcraftmc.crystalspace.economy.Economy;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Listener for economy stuff.
 * 
 * @author Jack
 * @author iffa
 */
public class SpaceEconomyListener implements Listener {

    /**
     * Called when someone enters space.
     * 
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpaceEnter(SpaceEnterEvent event) {
        if (event.getFrom() == event.getTo()) {
            return;
        }
        if (!Economy.enter(event.getPlayer())) {
            MessageHandler.sendNotEnoughMoneyMessage(event.getPlayer());
            event.setCancelled(true);
            return;
        }
    }

    /**
     * Called when someone leaves space.
     * 
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onSpaceLeave(SpaceLeaveEvent event) {
        if (!Economy.exit(event.getPlayer())) {
            event.setCancelled(true);
            return;
        }
    }
}
