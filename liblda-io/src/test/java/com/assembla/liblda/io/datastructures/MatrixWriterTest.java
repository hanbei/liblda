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
import com.assembla.liblda.io.TestConstants;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author hanbei
 */
public class MatrixWriterTest {

    private Matrix matrix;
    private MatrixWriter matrixWriter;
    private String expectedMatrixAsString;


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

        expectedMatrixAsString = "com.assembla.liblda.datastructures.DenseMatrix 3 3" + TestConstants.LINE_SEPARATOR + " 0.0 0.1 0.2" + TestConstants.LINE_SEPARATOR + " 1.0 1.1 1.2" + TestConstants.LINE_SEPARATOR + " 2.0 2.1 2.2" + TestConstants.LINE_SEPARATOR;
    }

    @Test
    public void testWriteMatrixWithWriter() throws IOException {
        StringWriter writer = new StringWriter();
        matrixWriter.write(matrix, writer);
        writer.close();
        String actualString = writer.toString();
        assertEquals(expectedMatrixAsString, actualString);
    }

    @Test
    public void testWriteMatrixWithInputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        matrixWriter.write(matrix, byteArrayOutputStream);
        byteArrayOutputStream.close();
        byte[] actualString = byteArrayOutputStream.toByteArray();
        assertArrayEquals(expectedMatrixAsString.getBytes(), actualString);
    }


}
