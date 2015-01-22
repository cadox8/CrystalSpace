// Package Declaration
package com.crystalcraftmc.crystalspace.listeners.spout;

// bSpace Imports

import com.crystalcraftmc.crystalspace.Space;
import com.crystalcraftmc.crystalspace.api.event.area.AreaEnterEvent;
import com.crystalcraftmc.crystalspace.api.event.area.AreaLeaveEvent;
import com.crystalcraftmc.crystalspace.api.event.area.SpaceEnterEvent;
import com.crystalcraftmc.crystalspace.api.event.area.SpaceLeaveEvent;
import com.crystalcraftmc.crystalspace.handlers.PlayerHandler;
import com.crystalcraftmc.crystalspace.handlers.SpoutHandler;
import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

// Bukkit Imports
// Spout Imports

/**
 * Area Listener to change gravity settings between space areas and airlock areas.
 * 
 * @author HACKhalo2
 * @author iffa
 * @author kitskub
 */
public class SpaceSpoutAreaListener implements Listener {
    private final Space plugin = (Space) Bukkit.getPluginManager().getPlugin("bSpace");
    /**
     * Called when a player enters an area.
     * 
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAreaEnter(AreaEnterEvent event) {
        SpoutHandler.resetGravity(event.getPlayer());
    }

    /**
     * Called when a player leaves an area.
     * 
     * @param event Event data
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAreaLeave(AreaLeaveEvent event) {
        SpoutHandler.setGravity(event.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onSpaceEnter(SpaceEnterEvent event) {
        SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
        if (event.isCancelled() || !player.isSpoutCraftEnabled() || event.getFrom().getWorld().equals(event.getTo().getWorld())
                || (WorldHandler.isSpaceWorld(event.getFrom().getWorld()) && WorldHandler.isSpaceWorld(event.getTo().getWorld()))) {
            //Return if the event is canceled, if player doesn't have spoutcraft, if teleporting interworld, or it teleporting between space worlds
            return;
        }
        SpoutHandler.setOrReset(plugin, player, event.getTo());
        if(!PlayerHandler.insideArea(event.getTo())){
            SpoutHandler.setGravity(player);
        }
        
        
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onSpaceLeave(SpaceLeaveEvent event) {
        SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
        if (event.isCancelled() || !player.isSpoutCraftEnabled() || event.getFrom().getWorld().equals(event.getTo().getWorld())
                || (WorldHandler.isSpaceWorld(event.getFrom().getWorld()) && WorldHandler.isSpaceWorld(event.getTo().getWorld()))) {
            //Return if the event is canceled, if player doesn't have spoutcraft, if teleporting interworld, or it teleporting between space worlds
            return;
        }
        SpoutHandler.setOrReset(plugin, player, event.getFrom());
        SpoutHandler.resetGravity(player);
    }
}
