package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.LDA;
import de.fhg.iais.kd.tm.liblda.LDAImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;


public class LDAReader {

	public LDA readLDAModel(InputStream in) {
		try {
			GZIPInputStream gzIn = new GZIPInputStream(in);
			ObjectInputStream objectIn = new ObjectInputStream(gzIn);
			LDA ldaModel = (LDA) objectIn.readObject();
			return ldaModel;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
