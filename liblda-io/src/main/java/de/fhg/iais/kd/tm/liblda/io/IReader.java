package de.fhg.iais.kd.tm.liblda.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: hanbei
 * Date: 24.07.2010
 * Time: 17:51:06
 * To change this template use File | Settings | File Templates.
 */
public interface IReader<T> {

    public T read(InputStream in) throws IOException;

    public T  read(Reader reader) throws IOException;
}
