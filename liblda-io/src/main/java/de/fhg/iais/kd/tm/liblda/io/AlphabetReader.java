package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.Alphabet;

import java.io.*;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 29, 2010 Time: 10:29:02 AM To change this template use File |
 * Settings | File Templates.
 */
public class AlphabetReader<T> implements IReader<Alphabet<T>> {

    private IReader<T> itemReader;

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
            T feature = itemReader.read(bufferedReader);
            line = bufferedReader.readLine();
            if (line != null) {
                int index = Integer.parseInt(line.trim());
                alphabet.set(feature, index);
            }
        }
        return alphabet;
    }
}
