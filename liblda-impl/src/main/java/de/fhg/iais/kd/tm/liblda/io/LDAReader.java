package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.LDAImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;


public class LDAReader {

	public LDAImpl readLDAModel(InputStream in) {
		try {
			GZIPInputStream gzIn = new GZIPInputStream(in);
			ObjectInputStream objectIn = new ObjectInputStream(gzIn);
			LDAImpl<?> ldaModel = (LDAImpl) objectIn.readObject();
			return ldaModel;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
