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

import gnu.trove.TIntDoubleHashMap;

public class SparseMatrix implements Matrix {

	private static final long serialVersionUID = 5005355635430670675L;

	private TIntDoubleHashMap matrix;

	private int numberOfColumns;
	private int numberOfRows;

	public SparseMatrix(int rows, int cols) {
		numberOfRows = rows;
		numberOfColumns = cols;

		matrix = new TIntDoubleHashMap();
	}

	public double get(int rowIndex, int colIndex) {
		return matrix.get(index(rowIndex, colIndex));
	}

	private int index(int rowIndex, int colIndex) {
		return rowIndex * numberOfColumns + colIndex;
	}

	public void set(int rowIndex, int colIndex, double value) {
		int index = index(rowIndex, colIndex);
		if (value == 0.0) {
			matrix.remove(index);
		} else {
			matrix.put(index, value);
		}
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void increment(int rowIndex, int colIndex) {
		int index = index(rowIndex, colIndex);
		matrix.adjustOrPutValue(index, 1, 1);
	}

	public void decrement(int rowIndex, int colIndex) {
		int index = index(rowIndex, colIndex);
		matrix.adjustOrPutValue(index, -1, -1);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < numberOfRows; i++) {
			buffer.append("[");
			for (int j = 0; j < numberOfColumns; j++) {
				buffer.append(get(i, j));
				buffer.append(" ");
			}
			buffer.append("]\n");
		}
		return buffer.toString();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) {
            return false;
        } else {
            Matrix other = (Matrix) o;
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int column = 0; column < getNumberOfRows(); column++) {
                    if (other.get(row, column) != this.get(row, column)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
}
