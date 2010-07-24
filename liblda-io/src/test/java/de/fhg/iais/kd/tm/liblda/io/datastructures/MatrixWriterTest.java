package de.fhg.iais.kd.tm.liblda.io.datastructures;

import de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix;
import de.fhg.iais.kd.tm.liblda.datastructures.Matrix;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: hanbei
 * Date: 24.07.2010
 * Time: 17:53:45
 * To change this template use File | Settings | File Templates.
 */
public class MatrixWriterTest {

    private Matrix matrix;
    private MatrixWriter matrixWriter;
    private String expectedMatrixAsString;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void setup() {
        matrix = new DenseMatrix(3, 3);
        matrixWriter = new MatrixWriter();
        matrix.set(0, 0, 0.0);
        matrix.set(0, 1, 0.1);
        matrix.set(0, 2, 0.2);

        matrix.set(1, 0, 1.0);
        matrix.set(1, 1, 1.1);
        matrix.set(1, 2, 1.2);

        matrix.set(2, 0, 2.0);
        matrix.set(2, 1, 2.1);
        matrix.set(2, 2, 2.2);

        expectedMatrixAsString = " 0.0 0.1 0.2"+LINE_SEPARATOR+" 1.0 1.1 1.2"+LINE_SEPARATOR+" 2.0 2.1 2.2"+LINE_SEPARATOR;
    }

    @Test
    public void testWriteMatrixWithWriter() throws IOException {
        StringWriter writer = new StringWriter();
        matrixWriter.write(matrix, writer);
        writer.close();
        String actualString = writer.toString();
        assertEquals(expectedMatrixAsString, actualString);
    }

}
