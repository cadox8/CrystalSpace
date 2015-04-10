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

import org.bukkit.World;

/**
 * A runnable class for forcing night.
 * 
 * @author iffa
 */
public class NightForceRunnable implements Runnable {
    // Variables
    private World world;

    /**
     * Constructor for NightForceRunnable.
     * 
     * @param world World
     */
    public NightForceRunnable(World world) {
        this.world = world;
    }

    /**
     * Forces night when repeated every 8399 ticks.
     */
    @Override
    public void run() {
        world.setTime(13801);
    }
}
