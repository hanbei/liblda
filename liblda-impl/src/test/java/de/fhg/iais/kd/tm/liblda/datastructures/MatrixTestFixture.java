package de.fhg.iais.kd.tm.liblda.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public abstract class MatrixTestFixture {

	private static final double EPSILON = 0.00000000000000000000000000000000001;
	protected static final int ROWS = 100;
	protected static final int COLS = 500;

	protected Matrix matrix;

	@Before
	public void setUp() {
		initMatrix();
	}

	protected abstract void initMatrix();

	@Test
	public void testAllZeroAfterConstruction() {
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLS; column++) {
				assertEquals(0.0, matrix.get(row, column), EPSILON);
			}
		}
	}

	@Test
	public void testSetGet() {
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLS; column++) {
				matrix.set(row, column, row * column + row);
			}
		}
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLS; column++) {
				assertEquals(row * column + row, matrix.get(row, column), EPSILON);
			}
		}
	}

	@Test
	public void testIncrement() {
		matrix.increment(0, 0);
		matrix.increment(0, 0);
		matrix.increment(0, 0);
		matrix.increment(0, 0);

		assertEquals(4, matrix.get(0, 0), EPSILON);
	}

	@Test
	public void testDecrement() {
		matrix.decrement(0, 0);
		matrix.decrement(0, 0);
		matrix.decrement(0, 0);
		matrix.decrement(0, 0);

		assertEquals(-4, matrix.get(0, 0), EPSILON);
	}

}