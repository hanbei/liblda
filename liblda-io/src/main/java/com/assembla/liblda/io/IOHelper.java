/**
 *
 *  This file is part of ${PROJECT}.
 *
 *  ${PROJECT} is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  ${PROJECT} is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with ${PROJECT}.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  @author Florian Schulz
 */
package com.assembla.liblda.io;

import java.io.IOException;
import java.io.Reader;/*
 *
 *  @author Florian Schulz
 */

public class IOHelper {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private IOHelper() {
        // not allowed
    }

    public static String readLine(Reader reader) throws IOException {
        StringBuffer line = new StringBuffer();
        int character = -2;
        while ((character = reader.read()) != -1) {
            if (!isLineBreak(character)) {
                line.append((char) character);
            } else {
                if (reader.markSupported()) {
                    reader.mark(10);
                }
                if (isLineBreak(reader.read())) {
                    break;
                } else {
                    reader.reset();
                    if (!reader.markSupported()) {
                        reader.skip(line.length());
                    }
                }
            }
        }
        if(line.length() == 0) {
            return null;
        }
        return line.toString();
    }

    public static boolean isLineBreak(int character) {
        char character2 = (char) character;
        int index = LINE_SEPARATOR.indexOf(character2);
        return index != -1;
    }
}
