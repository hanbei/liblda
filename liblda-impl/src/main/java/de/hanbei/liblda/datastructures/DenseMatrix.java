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

public class DenseMatrix implements Matrix {

    private static final long serialVersionUID = 5317902507648565277L;

    private double[][] matrix;

    public DenseMatrix(int numberOfRows, int numberOfColumns) {
        matrix = new double[numberOfRows][numberOfColumns];
    }

    public void set(int rowIndex, int columnIndex, double value) {
        matrix[rowIndex][columnIndex] = value;
    }

    public double get(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }

    @Override
    public void decrement(int rowIndex, int colIndex) {
        matrix[rowIndex][colIndex]--;
    }

    @Override
    public int getNumberOfColumns() {
        return matrix[0].length;
    }

    @Override
    public int getNumberOfRows() {
        return matrix.length;
    }

    @Override
    public void increment(int rowIndex, int colIndex) {
        matrix[rowIndex][colIndex]++;
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
