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

import com.assembla.liblda.datastructures.Matrix;
import com.assembla.liblda.io.IWriter;

import java.io.*;

/**
 * @author Florian Schulz
 */
public class MatrixWriter implements IWriter<Matrix> {

    public void write(Matrix matrix, OutputStream out) throws IOException {
        write(matrix, new OutputStreamWriter(out));
    }

    public void write(Matrix matrix, java.io.Writer writer) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(matrix.getClass().getName());
        bufferedWriter.write(" " + matrix.getNumberOfRows());
        bufferedWriter.write(" " + matrix.getNumberOfColumns());
        bufferedWriter.newLine();

        for (int row = 0; row < matrix.getNumberOfRows(); row++) {
            for (int column = 0; column < matrix.getNumberOfColumns(); column++) {
                double value = matrix.get(row, column);
                bufferedWriter.write(" " + value);
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
}
