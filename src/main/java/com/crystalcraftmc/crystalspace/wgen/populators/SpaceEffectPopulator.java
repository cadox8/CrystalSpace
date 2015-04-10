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

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * Populates a world with end portal blocks around bedrock.
 * 
 * @author iffa
 */
public class SpaceEffectPopulator extends BlockPopulator {

    /**
     * Populates a chunk with end portal blocks around bedrock.
     * Gives a cool effect and is totally harmless because the
     * portal frame is not present.
     *
     * @param world World
     * @param random Random
     * @param source Source chunk
     */
    @Override
    public void populate(World world, Random random, Chunk source) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                source.getBlock(x, 1, z).setType(Material.ENDER_PORTAL);
            }
        }
    }
    
}
