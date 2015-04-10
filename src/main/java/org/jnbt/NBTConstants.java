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

import java.nio.charset.Charset;

/**
 * A class which holds constant values.
 * @author Graham Edgecombe
 *
 */
public final class NBTConstants {
	
	/**
	 * The character set used by NBT (UTF-8).
	 */
	public static final Charset CHARSET = Charset.forName("UTF-8");
	
	/**
	 * Tag type constants.
	 */
	public static final int TYPE_END = 0,
		TYPE_BYTE = 1,
		TYPE_SHORT = 2,
		TYPE_INT = 3,
		TYPE_LONG = 4,
		TYPE_FLOAT = 5,
		TYPE_DOUBLE = 6,
		TYPE_BYTE_ARRAY = 7,
		TYPE_STRING = 8,
		TYPE_LIST = 9,
		TYPE_COMPOUND = 10;
	
	/**
	 * Default private constructor.
	 */
	private NBTConstants() {
		
	}

}
