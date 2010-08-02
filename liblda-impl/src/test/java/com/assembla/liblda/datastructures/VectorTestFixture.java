package com.assembla.liblda.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** @author fschulz */
public abstract class VectorTestFixture {

    private static final double EPSILON = 0.00000000000000000000000000000000001;

    private Vector vector;
    private static final int SIZE = 100;

    @Before
    public void setUp() {
        vector = initMatrix(SIZE);
    }

    protected abstract Vector initMatrix(int size);

    @Test
    public void testAllZeroAfterConstruction() {
        for (int row = 0; row < SIZE; row++) {
            assertEquals(0.0, vector.get(row), EPSILON);
        }
    }

    @Test
    public void testSetGet() {
        for (int row = 0; row < SIZE; row++) {
            vector.set(row, row);
        }
        for (int row = 0; row < SIZE; row++) {
            assertEquals(row, vector.get(row), EPSILON);
        }
    }

    @Test
    public void testIncrement() {
        vector.increment(0);
        vector.increment(0);
        vector.increment(0);
        vector.increment(0);

        assertEquals(4, vector.get(0), EPSILON);
    }

    @Test
    public void testDecrement() {
        vector.decrement(0);
        vector.decrement(0);
        vector.decrement(0);
        vector.decrement(0);

        assertEquals(-4, vector.get(0), EPSILON);
    }
}
