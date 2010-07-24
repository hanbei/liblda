package de.fhg.iais.kd.tm.liblda.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: hanbei
 * Date: 24.07.2010
 * Time: 17:48:28
 * To change this template use File | Settings | File Templates.
 */
public interface IWriter<T> {

    public void write(T t, OutputStream out) throws IOException;
    
    public void write(T t, java.io.Writer out) throws IOException;
}
