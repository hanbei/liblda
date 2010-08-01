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
package com.assembla.liblda.io;

import com.assembla.liblda.Alphabet;

import java.io.*;

/**
 * Reads an alphabet from a InputStream or a Reader. The items present in the alphabet are read with a
 * separate reader. This reader must not use a BufferedReader to read the item as these do not correctly set the
 * stream at the right position. Please write a unit test for AlphabetReader with your own item reader class. <br/>
 *
 * The AlphabetReader assumes the following format.
 * <pre>
 * Item
 * Index
 * Item2
 * Index2
 * ...
 * </pre>
 * Each item is written to the output stream separated by a newline from the index.
 * @see IOHelper 
 *
 * @author Florian Schulz
 */
public class AlphabetReader<T> implements IReader<Alphabet<T>> {

    private IReader<T> itemReader;

    /**
     * Create a new AlphabetReader that reads items with the supplied IReader.
     *
     * @param itemReader
     */
    public AlphabetReader(IReader<T> itemReader) {
        this.itemReader = itemReader;
    }

    @Override
    public Alphabet<T> read(InputStream in) throws IOException {
        return read(new InputStreamReader(in));
    }

    @Override
    public Alphabet<T> read(Reader reader) throws IOException {
        Alphabet<T> alphabet = new Alphabet<T>();

        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        while (line != null) {
            T feature = itemReader.read(reader);
            line = IOHelper.readLine(reader);
            if (line != null) {
                int index = Integer.parseInt(line.trim());
                alphabet.set(feature, index);
            }
        }
        return alphabet;
    }


}
