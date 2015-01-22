// Package Declaration
package com.crystalcraftmc.crystalspace.runnables;

// Java Imports

import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import com.crystalcraftmc.crystalspace.handlers.WorldHandler;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

import java.util.logging.Level;

// bSpace Imports
// Bukkit Imports
// Spout Imports

/**
 * A runnable class as a workaround for Spout player modifiers.
 * 
 * @author HACKhalo2
 * @author iffa
 */
public class SpoutFixRunnable implements Runnable {
    // Variables
    private final Player player;

    /**
     * Constructor of SpaceSpoutRunnable.
     * 
     * @param player Player
     */
    public SpoutFixRunnable(Player player) {
        this.player = player;
    }

    /**
     * Sets a player's gravity settings. (hacky solution!!)
     */
    public void run() {
        if (WorldHandler.isInAnySpace(player)) {
            SpoutPlayer p = SpoutManager.getPlayer(player); // Abuse Afforess because of bugs
            p.setAirSpeedMultiplier(0.7);
            p.setGravityMultiplier(0.15);
            p.setWalkingMultiplier(0.7);
            MessageHandler.debugPrint(Level.INFO, "Changed player '" + p.getName() + "'s gravity settings (" + p.getAirSpeedMultiplier() + ", " + p.getGravityMultiplier() + ", " + p.getJumpingMultiplier() + ").");
        }

    }
}
