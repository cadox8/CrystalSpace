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

import com.crystalcraftmc.crystalspace.api.schematic.Schematic;
import com.crystalcraftmc.crystalspace.api.schematic.SpaceSchematicHandler;
import com.crystalcraftmc.crystalspace.handlers.ConfigHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;
import java.util.logging.Level;

/**
 * Populates a world with schematics.
 * 
 * @author iffa
 */
public class SpaceSchematicPopulator extends BlockPopulator {

    /**
     * Populates a chunk with schematics.
     * 
     * @param world World
     * @param random Random
     * @param chunk Source chunk
     */
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (SpaceSchematicHandler.getSchematics().isEmpty()) {
            return;
        }
        int y = new Random().nextInt(world.getMaxHeight());
        String id = ConfigHandler.getID(world);
        Schematic randomSchematic = SpaceSchematicHandler.getSchematics().get(new Random().nextInt(SpaceSchematicHandler.getSchematics().size()));
        if (new Random().nextInt(200) <= ConfigHandler.getSchematicChance(id)) {
            MessageHandler.debugPrint(Level.INFO, "Starting schematic population process with schematic '" + randomSchematic.getName() + "'.");
            SpaceSchematicHandler.placeSchematic(randomSchematic, new Location(world, (chunk.getX() << 4) + new Random().nextInt(10), y, (chunk.getZ() << 4) + new Random().nextInt(10)));
        }
    }
}
