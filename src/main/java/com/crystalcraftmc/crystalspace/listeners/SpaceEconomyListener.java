// Package Declaration
package com.crystalcraftmc.crystalspace.listeners;

// CrystalSpace Imports

import com.crystalcraftmc.crystalspace.api.event.area.SpaceEnterEvent;
import com.crystalcraftmc.crystalspace.api.event.area.SpaceLeaveEvent;
import com.crystalcraftmc.crystalspace.economy.Economy;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

// Bukkit Imports

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
