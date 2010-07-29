package com.assembla.liblda.io;

import com.assembla.liblda.LDA;

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
