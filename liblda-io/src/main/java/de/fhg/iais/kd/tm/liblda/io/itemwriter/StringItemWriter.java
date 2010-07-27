package de.fhg.iais.kd.tm.liblda.io.itemwriter;

import de.fhg.iais.kd.tm.liblda.io.IWriter;

import java.io.*;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 27, 2010 Time: 2:40:25 PM To change this template use File |
 * Settings | File Templates.
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
