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
package com.crystalcraftmc.crystalspace.handlers;

import com.crystalcraftmc.crystalspace.api.SpaceWorldHandler;
import org.bukkit.World;

/**
 * Class that handles space worlds.
 * Internal use only
 *
 * @author Jack
 */
public class WorldHandler extends SpaceWorldHandler {
    /**
     * Removes a space world from the list of space worlds.
     * 
     * @param world World to remove
     */
    public static void removeSpaceWorld(World world) {
        if (spaceWorldNames.contains(world.getName())) {
            spaceWorldNames.remove(world.getName());
        }
    }
    private WorldHandler() {
    }
}
