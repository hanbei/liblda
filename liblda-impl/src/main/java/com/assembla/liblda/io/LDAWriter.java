package com.assembla.liblda.io;

import com.assembla.liblda.LDAImpl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;


public class LDAWriter {

	public void writeLDAModel(LDAImpl<?> lda, OutputStream out) {
		try {
			GZIPOutputStream zippedOut = new GZIPOutputStream(out);
			ObjectOutputStream objectOut = new ObjectOutputStream(zippedOut);
			objectOut.writeObject(lda);
			objectOut.close();
			zippedOut.finish();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
