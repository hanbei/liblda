package de.fhg.iais.kd.tm.liblda.datastructures;

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

}
