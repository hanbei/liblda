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
package com.assembla.liblda.io.itemwriter;

import com.assembla.liblda.io.IWriter;

import java.io.*;

/**
 * @author Florian Schulz
 */
public class StringItemWriter implements IWriter<String> {
    @Override
    public void write(String s, OutputStream out) throws IOException {
        write(s, new OutputStreamWriter(out));
    }

    @Override
    public void write(String s, Writer out) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        bufferedWriter.write(s);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
