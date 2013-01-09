package de.hanbei.liblda.datastructures;

/** @author fschulz */
public class DenseVectorTest extends VectorTestFixture {

    @Override
    protected Vector initMatrix(int size) {
        return new DenseVector(size);
    }


}
