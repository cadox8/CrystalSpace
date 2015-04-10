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
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * SpaceAsteroidPopulator, populates a world with Notch's original stone (glowstone) patches.
 *
 * @author Markus 'Notch' Persson
 * @author iffa
 * @author Nightgunner5
 * @author kitskub
 */
public class SpaceAsteroidPopulator extends BlockPopulator {
    // Variables

    private static final BlockFace[] faces = {BlockFace.DOWN, BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.UP, BlockFace.WEST};

    /**
     * Populates a world with stone patches.
     * 
     * @param world World
     * @param random Random
     * @param source Source chunk
     */
    @Override
    public void populate(World world, Random random, Chunk source) {
        for (int i = 0; i < 2; i++) {
            Block block = getRandomBlock(source, random);
            String id = ConfigHandler.getID(world);
            if (random.nextInt(200) <= ConfigHandler.getStoneChance(id) && block.getTypeId() == 0) {
                block.setTypeId(1);
                for (int j = 0; j < 1500; j++) {
                    Block current = block.getRelative(random.nextInt(8) - random.nextInt(8),
                            random.nextInt(12),
                            random.nextInt(8) - random.nextInt(8));
                    if (current.getTypeId() != 0) {
                        continue;
                    }
                    int count = 0;
                    for (BlockFace face : faces) {
                        if (current.getRelative(face).getTypeId() == 1) {
                            count++;
                        }
                    }
                    if (count == 1) {
                        current.setTypeId(1);
                    }
                }
            }
            block = getRandomBlock(source, random);
            if (random.nextInt(200) <= ConfigHandler.getGlowstoneChance(id) && block.getTypeId() == 0) {
                block.setTypeId(89);

                for (int j = 0; j < 1500; j++) {
                    Block current = block.getRelative(random.nextInt(8) - random.nextInt(8),
                            random.nextInt(12),
                            random.nextInt(8) - random.nextInt(8));
                    if (current.getTypeId() != 0) {
                        continue;
                    }
                    int count = 0;
                    for (BlockFace face : faces) {
                        if (current.getRelative(face).getTypeId() == 89) {
                            count++;
                        }
                    }
                    if (count == 1) {
                        current.setTypeId(89);
                    }
                }
            }
        }
    }

    /**
     * Gets a random block from the source chunk.
     * 
     * @param source Source chunk
     * @param random Random
     * 
     * @return Random block;
     */
    private Block getRandomBlock(Chunk source, Random random) {
        int x = random.nextInt(16);
        int y = random.nextInt(source.getWorld().getMaxHeight());
        int z = random.nextInt(16);
        return source.getBlock(x, y, z);
    }
}
