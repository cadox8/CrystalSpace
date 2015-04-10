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

import java.util.Collections;
import java.util.Map;

/**
 * The <code>TAG_Compound</code> tag.
 * @author Graham Edgecombe
 *
 */
public final class CompoundTag extends Tag {
	
	/**
	 * The value.
	 */
	private final Map<String, Tag> value;
	
	/**
	 * Creates the tag.
	 * @param name The name.
	 * @param value The value.
	 */
	public CompoundTag(String name, Map<String, Tag> value) {
		super(name);
		this.value = Collections.unmodifiableMap(value);
	}

	@Override
	public Map<String, Tag> getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if(name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		StringBuilder bldr = new StringBuilder();
		bldr.append("TAG_Compound" + append + ": " + value.size() + " entries\r\n{\r\n");
		for(Map.Entry<String, Tag> entry : value.entrySet()) {
			bldr.append("   " + entry.getValue().toString().replaceAll("\r\n", "\r\n   ") + "\r\n");
		}
		bldr.append("}");
		return bldr.toString();
	}

}
