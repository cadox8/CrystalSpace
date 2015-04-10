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

package org.jnbt;

/**
 * Represents a single NBT tag.
 * @author Graham Edgecombe
 *
 */
public abstract class Tag {
	
	/**
	 * The name of this tag.
	 */
	private final String name;
	
	/**
	 * Creates the tag with the specified name.
	 * @param name The name.
	 */
	public Tag(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of this tag.
	 * @return The name of this tag.
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Gets the value of this tag.
	 * @return The value of this tag.
	 */
	public abstract Object getValue();

}
