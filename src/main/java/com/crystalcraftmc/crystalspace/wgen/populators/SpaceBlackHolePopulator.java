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
package com.crystalcraftmc.crystalspace.wgen.populators;

import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * Populates a world with black holes.
 *
 * @author kitskub
 * @author iffa
 * @author jflory7 
 */
public class SpaceBlackHolePopulator extends BlockPopulator {
    public static final int ID_TO_USE = 120; //for easier changing if needed

    public SpaceBlackHolePopulator() {
    }


    /**
     * Populates a chunk with black holes.
     *
     * @param world World
     * @param random Random
     * @param source Source chunk
     */
    @Override
    public void populate(World world, Random random, Chunk source) {
        String id = ConfigHandler.getID(world);
        
        if (withinSpawn(source)) {
            return;
        }
        
        if (random.nextInt(100) <= ConfigHandler.getBlackHoleChance(id)) {
            int chunkX = source.getX();
            int chunkZ = source.getZ();
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = random.nextInt(world.getMaxHeight());
            Block block = world.getBlockAt((chunkX * 16 + x), y, (chunkZ * 16 + z));
            block.setTypeId(ID_TO_USE);
        }
    }

    /**
     * Checks if the source chunk is in the spawn area.
     * 
     * @param source Source chunk
     * 
     * @return True if within spawn
     */
    private boolean withinSpawn(Chunk source) {
        int x = source.getX();
        int z = source.getZ();

        return x > -2 && x < 2 && z > -2 && z < 2;
    }
}
