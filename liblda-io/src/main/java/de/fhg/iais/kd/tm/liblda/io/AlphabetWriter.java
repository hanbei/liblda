package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.Alphabet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 27, 2010 Time: 2:24:13 PM To change this template use File |
 * Settings | File Templates.
 */
public class AlphabetWriter<T> implements IWriter<Alphabet<T>> {

    private ItemWriterFactory<T> itemWriterFactory;

    public AlphabetWriter(ItemWriterFactory<T> itemWriterFactory) {
        this.itemWriterFactory = itemWriterFactory;
    }

    @Override
    public void write(Alphabet<T> alphabet, OutputStream out) throws IOException {

    }

    @Override
    public void write(Alphabet<T> alphabet, Writer out) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        for (T feature : alphabet.getFeatures()) {
            IWriter<T> itemWriter = itemWriterFactory.createWriterInstance();
            itemWriter.write(feature, bufferedWriter);
            int index = alphabet.lookupIndex(feature);
            bufferedWriter.newLine();
            bufferedWriter.write("" + index);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

}
