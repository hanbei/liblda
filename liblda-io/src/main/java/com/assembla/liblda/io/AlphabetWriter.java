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
 */
public class AlphabetWriter<T> implements IWriter<Alphabet<T>> {

    private IWriter<T> itemWriter;

    public AlphabetWriter(IWriter<T> itemWriter) {
        this.itemWriter = itemWriter;
    }

    @Override
    public void write(Alphabet<T> alphabet, OutputStream out) throws IOException {
        write(alphabet, new OutputStreamWriter(out));
    }

    @Override
    public void write(Alphabet<T> alphabet, Writer out) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        for (T feature : alphabet.getFeatures()) {
            itemWriter.write(feature, bufferedWriter);
            int index = alphabet.lookupIndex(feature);
            bufferedWriter.write("" + index);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

}
