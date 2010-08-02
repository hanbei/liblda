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

import com.assembla.liblda.io.IOHelper;
import com.assembla.liblda.io.IReader;

import java.io.*;

/**
 * @author Florian Schulz
 */
public class StringItemReader implements IReader<String> {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String read(InputStream in) throws IOException {
        return read(new BufferedReader(new InputStreamReader(in)));
    }

    @Override
    public String read(Reader reader) throws IOException {
        return IOHelper.readLine(reader);
    }
}
