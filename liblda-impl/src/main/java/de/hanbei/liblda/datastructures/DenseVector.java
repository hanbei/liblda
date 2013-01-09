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
 * @author Florian Schulz
 */
public class DenseVector implements Vector {
    
    private double[] vector;

    public DenseVector(int size) {
        vector = new double[size];
    }
    
    @Override
    public void set(int index, double value) {
        vector[index] = value;
    }

    @Override
    public double get(int index) {
        return vector[index];
    }

    @Override
    public void increment(int index) {
        vector[index]++;
    }

    @Override
    public void decrement(int index) {
        vector[index]--;
    }

    @Override
    public int size() {
         return vector.length;
    }
}
