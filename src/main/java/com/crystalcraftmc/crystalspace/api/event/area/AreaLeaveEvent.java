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
package com.crystalcraftmc.crystalspace.api.event.area;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Event data for when a player leaves a breathable area.
 * 
 * @author iffa
 */
public class AreaLeaveEvent extends AreaEvent {
    // Variables
    private static final HandlerList handlers = new HandlerList();
    private static final long serialVersionUID = 7604929590186681633L;

    /**
     * Constructor for AreaLeaveEvent.
     * 
     * @param player Player
     */
    public AreaLeaveEvent(Player player) {
        super("AreaLeaveEvent", player);
    }
        
    /**
     * {@inheritDoc}
     * @return Handler list
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
