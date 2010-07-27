package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.Alphabet;

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
