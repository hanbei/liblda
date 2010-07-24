package de.fhg.iais.kd.tm.liblda.datastructures;

public class SparseMatrixTest extends MatrixTestFixture {

	@Override
	protected void initMatrix() {
		matrix = new SparseMatrix(ROWS, COLS);
	}
}
