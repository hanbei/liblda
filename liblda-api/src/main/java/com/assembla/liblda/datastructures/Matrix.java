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

import java.io.Serializable;

public interface Matrix extends Serializable {

	public abstract double get(int rowIndex, int colIndex);

	public abstract void set(int rowIndex, int colIndex, double value);

	public abstract int getNumberOfColumns();

	public abstract int getNumberOfRows();

	public abstract void increment(int rowIndex, int colIndex);

	public abstract void decrement(int rowIndex, int colIndex);

}