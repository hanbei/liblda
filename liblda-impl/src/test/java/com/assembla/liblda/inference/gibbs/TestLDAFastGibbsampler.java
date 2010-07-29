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
package com.assembla.liblda.inference.gibbs;

import com.assembla.liblda.LDAImpl;
import com.assembla.liblda.Topic;
import com.assembla.liblda.inference.gibbs.FastGibbsSampler;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestLDAFastGibbsampler extends AbstractGibbsSamplerTestFixture {

    @Test
    public void testLDATraining() throws IOException {
        LDAImpl<String> lda = new LDAImpl(10);
        lda.setInferenceEngine(new FastGibbsSampler());
        lda.getInferenceEngine().getRandomEngine().setSeed(0);
        lda.setTrainingData(corpus);

        long startTime = System.currentTimeMillis();
        lda.train();
        logRunTime(startTime);

        Map<Integer, List<String>> topFeatures = lda.getTopFeatures();
        BufferedWriter writer = new BufferedWriter(new FileWriter("topics.txt"));
        for (Integer topicId : topFeatures.keySet()) {
            List<String> topWords = topFeatures.get(topicId);
            writer.write(topicId + ": ");
            for (String word : topWords) {
                writer.write("\"" + word + "\", ");
            }
            writer.write("\n");
        }
        writer.close();

        assertEquals("topic0", TOPIC0_TOPWORDS, topFeatures.get(0));
        assertEquals("topic1", TOPIC1_TOPWORDS, topFeatures.get(1));
        assertEquals("topic2", TOPIC2_TOPWORDS, topFeatures.get(2));
        assertEquals("topic3", TOPIC3_TOPWORDS, topFeatures.get(3));
        assertEquals("topic4", TOPIC4_TOPWORDS, topFeatures.get(4));
        assertEquals("topic5", TOPIC5_TOPWORDS, topFeatures.get(5));
        assertEquals("topic6", TOPIC6_TOPWORDS, topFeatures.get(6));
        assertEquals("topic7", TOPIC7_TOPWORDS, topFeatures.get(7));
        assertEquals("topic8", TOPIC8_TOPWORDS, topFeatures.get(8));
        assertEquals("topic9", TOPIC9_TOPWORDS, topFeatures.get(9));

    }

    @Test
    public void testLDAInference() {
        LDAImpl lda = new LDAImpl(10);
        lda.setTrainingData(corpus);
        lda.getInferenceEngine().getRandomEngine().setSeed(0);

        long startTime = System.currentTimeMillis();
        lda.train();
        logRunTime(startTime);

        List<String> inferenceData = lda.getTopFeaturesForTopic(6);
        List<Topic> topicScores = lda.inference(inferenceData);
        for (Topic t : topicScores) {
            System.out.println(t.getProbability());
        }

        assertEquals("topic 0 wrong probability", topicScores.get(0).getProbability(), 0.0012107012802561894,
                0.000000001);
        assertEquals("topic 1 wrong probability", topicScores.get(1).getProbability(), 0.001632411512371312,
                0.000000001);
        assertEquals("topic 2 wrong probability", topicScores.get(2).getProbability(), 0.0011770304098438697,
                0.000000001);
        assertEquals("topic 3 wrong probability", topicScores.get(3).getProbability(), 0.001147616025948401,
                0.000000001);
        assertEquals("topic 4 wrong probability", topicScores.get(4).getProbability(), 0.0015916170425831525,
                0.000000001);
        assertEquals("topic 5 wrong probability", topicScores.get(5).getProbability(), 0.001279952972566929,
                0.000000001);
        assertEquals("topic 6 wrong probability", topicScores.get(6).getProbability(), 0.9886534512373198, 0.000000001);
        assertEquals("topic 7 wrong probability", topicScores.get(7).getProbability(), 0.0010713470002680304,
                0.000000001);
        assertEquals("topic 8 wrong probability", topicScores.get(8).getProbability(), 0.001230717716727431,
                0.000000001);
        assertEquals("topic 9 wrong probability", topicScores.get(9).getProbability(), 0.0010051548021168025,
                0.000000001);
    }

}
