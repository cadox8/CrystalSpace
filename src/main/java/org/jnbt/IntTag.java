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
 * The <code>TAG_Int</code> tag.
 * @author Graham Edgecombe
 *
 */
public final class IntTag extends Tag {

	/**
	 * The value.
	 */
	private final int value;
	
	/**
	 * Creates the tag.
	 * @param name The name.
	 * @param value The value.
	 */
	public IntTag(String name, int value) {
		super(name);
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if(name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Int" + append + ": " + value;
	}

}
