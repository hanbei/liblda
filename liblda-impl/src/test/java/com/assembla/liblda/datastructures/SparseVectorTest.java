package com.assembla.liblda.datastructures;

/** @author fschulz */
public class SparseVectorTest extends VectorTestFixture {

    @Override
    protected Vector initMatrix(int size) {
        return new SparseVector(size);
    }

}
