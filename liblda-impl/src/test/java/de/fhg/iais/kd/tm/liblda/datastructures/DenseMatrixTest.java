package de.fhg.iais.kd.tm.liblda.datastructures;

public class DenseMatrixTest extends MatrixTestFixture {

	@Override
	protected void initMatrix() {
		matrix = new DenseMatrix(ROWS, COLS);
	}
}
