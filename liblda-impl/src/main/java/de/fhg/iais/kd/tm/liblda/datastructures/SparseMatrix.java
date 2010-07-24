package de.fhg.iais.kd.tm.liblda.datastructures;

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
}
