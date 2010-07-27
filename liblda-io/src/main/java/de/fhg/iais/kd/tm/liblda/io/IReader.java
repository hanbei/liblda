package de.fhg.iais.kd.tm.liblda.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 */
public interface IReader<T> {

    public T read(InputStream in) throws IOException;

    public T  read(Reader reader) throws IOException;
}
