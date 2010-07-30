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
package com.assembla.liblda.datastructures;

/**
 * A interface to define a numerical vector. A vector has a fixed size specified during construction time.
 *
 * @author Florian Schulz
 */
public interface Vector {

    /**
     * Set a specific index to a value
     *
     * @param index The index to set.
     * @param value The value that should be set.
     */
    public void set(int index, double value);

    /**
     * Get a value at a specific index.
     *
     * @param index The index to get.
     * @return The value at index.
     */
    public double get(int index);

    /**
     * Increment a value at a specific index.
     *
     * @param index The index to increment.
     */
    public void increment(int index);

    /**
     * Decrement a value at a specific index.
     *
     * @param index The index to decrement.
     */
    public void decrement(int index);

    /**
     * Get the fixed size of the vector.
     *
     * @return The fixed size of the vector.
     */
    public int size();
}
