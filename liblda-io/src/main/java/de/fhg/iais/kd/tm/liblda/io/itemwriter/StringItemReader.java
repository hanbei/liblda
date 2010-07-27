package de.fhg.iais.kd.tm.liblda.io.itemwriter;

import de.fhg.iais.kd.tm.liblda.io.IReader;

import java.io.*;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 27, 2010 Time: 2:43:20 PM To change this template use File |
 * Settings | File Templates.
 */
public class StringItemReader implements IReader<String> {
    @Override
    public String read(InputStream in) throws IOException {
         return read(new InputStreamReader(in));
    }

    @Override
    public String read(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String string = bufferedReader.readLine();
        return string;
    }
}
