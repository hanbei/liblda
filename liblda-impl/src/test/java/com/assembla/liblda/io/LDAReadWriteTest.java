/*
 *
 *  This file is part of liblda.
 *
 *  liblda is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  liblda is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with liblda.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.assembla.liblda.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.assembla.liblda.DataReader;
import com.assembla.liblda.LDAImpl;
import com.assembla.liblda.inference.gibbs.FastGibbsSampler;
import com.assembla.liblda.LDA;
import org.junit.Before;
import org.junit.Test;

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
