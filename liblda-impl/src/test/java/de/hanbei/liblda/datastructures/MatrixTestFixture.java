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