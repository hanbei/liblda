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

import gnu.trove.TIntDoubleHashMap;

/** @author Florian Schulz */
public class SparseVector implements Vector {

    private TIntDoubleHashMap vector;
    private int size;

    public SparseVector(int size) {
        this.size = size;
        vector = new TIntDoubleHashMap(size);
    }

    @Override
    public void set(int index, double value) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        vector.put(index, value);
    }

    @Override
    public double get(int index) {
        return vector.get(index);
    }

    @Override
    public void increment(int index) {
        vector.adjustOrPutValue(index, 1, 1);
    }

    @Override
    public void decrement(int index) {
        vector.adjustOrPutValue(index, -1, -1);
    }

    @Override
    public int size() {
        return size;
    }
}
