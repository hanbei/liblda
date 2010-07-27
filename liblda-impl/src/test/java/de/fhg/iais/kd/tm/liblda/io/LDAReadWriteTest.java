package de.fhg.iais.kd.tm.liblda.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import de.fhg.iais.kd.tm.liblda.LDA;
import de.fhg.iais.kd.tm.liblda.LDAImpl;
import org.junit.Before;
import org.junit.Test;

import de.fhg.iais.kd.tm.liblda.DataReader;
import de.fhg.iais.kd.tm.liblda.inference.gibbs.FastGibbsSampler;

public class LDAReadWriteTest extends DataReader {

    private LDAImpl lda;

    @Before
    public void setUp() {
        lda = new LDAImpl<String>(10);
        lda.setInferenceEngine(new FastGibbsSampler());
        lda.setTrainingData(corpus);
        lda.getInferenceEngine().getRandomEngine().setSeed(0);

        long startTime = System.currentTimeMillis();
        lda.train();
        logRunTime(startTime);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testReadWrite() {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        LDAWriter writer = new LDAWriter();
        writer.writeLDAModel(lda, bytesOut);

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytesOut.toByteArray());
        LDAReader reader = new LDAReader();
        LDA ldaModel = reader.readLDAModel(bytesIn);

        assertEquals(lda.getNumberOfTopics(), ldaModel.getNumberOfTopics());
        assertArrayEquals(lda.getAlpha(), ldaModel.getAlpha(), 0.000000000000001);
        assertEquals(lda.getBeta(), ldaModel.getBeta(), 0.000000000000001);
        assertEquals(lda.getBeta(), ldaModel.getBeta(), 0.000000000000001);
    }
}
