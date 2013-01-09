/*
 *
 *  This file is part of liblda.
 *
 *  liblda is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  liblda is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with liblda.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.hanbei.liblda.datastructures;

/**
 * Holds a integer (id) and a value.
 * 
 * @author Florian Schulz
 * @param <VALUE>
 *            type parameter
 * @param <KEY>
 *            type parameter
 */
public class Pair<KEY extends Comparable<KEY>, VALUE extends Comparable<VALUE>>
		implements Comparable<Pair<KEY, VALUE>> {
	KEY key;
	VALUE value;

	/**
	 * Create new pair (0,0.0).
	 */
	public Pair() {
		key = null;
		value = null;
	}

	/**
	 * Create new pair (id,p).
	 * 
	 * @param id
	 *            Id of the pair.
	 * @param p
	 *            Value of the pair.
	 */
	public Pair(KEY id, VALUE p) {
		this.key = id;
		this.value = p;
	}

	@Override
	public final int compareTo(Pair<KEY, VALUE> o2) {
		return value.compareTo(o2.value);
	}

	/**
	 * Get the id.
	 * 
	 * @return The id.
	 */
	public KEY getKey() {
		return key;
	}

	/**
	 * Get the value.
	 * 
	 * @return The value.
	 */
	public VALUE getValue() {
		return value;
	}

	/**
	 * Reinitialize an IDSorter.
	 * 
	 * @param key
	 *            The id of the pair
	 * @param p
	 *            The value of the pair
	 */
	public void set(KEY key, VALUE p) {
		this.key = key;
		this.value = p;
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + ")";
	}

	/**
	 * Set the value.
	 * 
	 * @param d
	 *            The value.
	 */
	public void setValue(VALUE d) {
		value = d;
	}

	/**
	 * Set the id.
	 * 
	 * @param k
	 *            The id.
	 */
	public void setKey(KEY k) {
		this.key = k;
	}
}
