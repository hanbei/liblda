package de.fhg.iais.kd.tm.liblda.io;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 27, 2010 Time: 2:32:23 PM To change this template use File |
 * Settings | File Templates.
 */
public abstract class ItemWriterFactory<T> {


    public abstract IWriter<T> createWriterInstance();

    public abstract IReader<T> createReaderInstance();
}
