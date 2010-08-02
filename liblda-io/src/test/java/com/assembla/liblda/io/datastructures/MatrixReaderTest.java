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
package com.assembla.liblda.io.datastructures;

import com.assembla.liblda.datastructures.DenseMatrix;
import com.assembla.liblda.datastructures.Matrix;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

/**
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

        correctInputString = "com.assembla.liblda.datastructures.DenseMatrix 3 3" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;
        tooFewColumnsInputString = "com.assembla.liblda.datastructures.DenseMatrix 3 3" + LINE_SEPARATOR + " 0.0 0.1" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;
        tooManyColumnsInputString = "com.assembla.liblda.datastructures.DenseMatrix 3 4" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;
        tooFewRowsInputString = "com.assembla.liblda.datastructures.DenseMatrix 3 3" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR;
        tooManyRowsInputString = "com.assembla.liblda.datastructures.DenseMatrix 4 3" + LINE_SEPARATOR + " 0.0 0.1 0.2" + LINE_SEPARATOR + " 1.0 1.1 1.2" + LINE_SEPARATOR + " 2.0 2.1 2.2" + LINE_SEPARATOR;

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
