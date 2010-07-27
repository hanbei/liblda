package de.fhg.iais.kd.tm.liblda.io.itemwriter;

import de.fhg.iais.kd.tm.liblda.io.IReader;
import de.fhg.iais.kd.tm.liblda.io.IWriter;
import de.fhg.iais.kd.tm.liblda.io.ItemWriterFactory;


/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 27, 2010 Time: 2:39:05 PM To change this template use File |
 * Settings | File Templates.
 */
public class StringWriterFactory<String> extends ItemWriterFactory {
    @Override
    public IWriter<String> createWriterInstance() {
        return (IWriter<String>) new StringItemWriter();
    }

    @Override
    public IReader<String> createReaderInstance() {
        return (IReader<String>) new StringItemReader(); 
    }
}
