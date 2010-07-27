package de.fhg.iais.kd.tm.liblda.io.datastructures;

import de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix;
import de.fhg.iais.kd.tm.liblda.datastructures.Matrix;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: hanbei
 * Date: 25.07.2010
 * Time: 15:43:10
 * To change this template use File | Settings | File Templates.
 */
public class MatrixReaderTest {

    private Matrix expectedMatrix;
    private MatrixReader matrixReader;
    private String correctInputString;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private String tooFewColumnsInputString;
    private String tooManyColumnsInputString;
    private String tooFewRowsInputString;
    private String tooManyRowsInputString;

    @Before
    public void setup() {
        expectedMatrix = new DenseMatrix(3, 3);
        expectedMatrix.set(0, 0, 0.0);
        expectedMatrix.set(0, 1, 0.1);
        expectedMatrix.set(0, 2, 0.2);

        expectedMatrix.set(1, 0, 1.0);
        expectedMatrix.set(1, 1, 1.1);
        expectedMatrix.set(1, 2, 1.2);

        expectedMatrix.set(2, 0, 2.0);
        expectedMatrix.set(2, 1, 2.1);
        expectedMatrix.set(2, 2, 2.2);

        correctInputString = "de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix 3 3" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;
        tooFewColumnsInputString = "de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix 3 3" + LINE_SEPARATOR + " 0.0 0.1" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;
        tooManyColumnsInputString = "de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix 3 4" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;
        tooFewRowsInputString = "de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix 3 3" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR;
        tooManyRowsInputString = "de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix 4 3" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;

        matrixReader = new MatrixReader();
    }

    @Test
    public void testReadWithReader() throws Exception {
        StringReader reader = new StringReader(correctInputString);
        Matrix matrix = matrixReader.read(reader);
        assertEquals(expectedMatrix, matrix);
    }

    @Test(expected = IOException.class)
    public void testTooFewColumns() throws Exception {
        StringReader reader = new StringReader(tooFewColumnsInputString);
        matrixReader.read(reader);
    }

    @Test(expected = IOException.class)
    public void testTooManyColumns() throws Exception {
        StringReader reader = new StringReader(tooManyColumnsInputString);
        matrixReader.read(reader);
    }

    @Test(expected = IOException.class)
    public void testTooFewRows() throws Exception {
        StringReader reader = new StringReader(tooFewRowsInputString);
        matrixReader.read(reader);
    }

    @Test(expected = IOException.class)
    public void testTooManyRows() throws Exception {
        StringReader reader = new StringReader(tooManyRowsInputString);
        matrixReader.read(reader);
    }

    @Test
    public void testReadWithInputStream() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(correctInputString.getBytes());
        Matrix matrix = matrixReader.read(byteArrayInputStream);
        assertEquals(expectedMatrix, matrix);
    }
}
