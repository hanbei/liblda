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

import com.assembla.liblda.datastructures.Vector;
import com.assembla.liblda.io.IWriter;

import java.io.*;

/** @author fschulz */
public class VectorWriter implements IWriter<Vector> {

    @Override
    public void write(Vector vector, OutputStream out) throws IOException {
        write(vector, new OutputStreamWriter(out));
    }

    @Override
    public void write(Vector vector, Writer out) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        bufferedWriter.write(vector.getClass().getName());
        bufferedWriter.write(" " + vector.size());
        bufferedWriter.newLine();

        for (int index = 0; index < vector.size(); index++) {
            double value = vector.get(index);
            bufferedWriter.write(" " + value);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
}
