package de.hanbei.liblda.inference.gibbs;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import de.hanbei.liblda.LDA;
import de.hanbei.liblda.LDAImpl;
import de.hanbei.liblda.Topic;
import de.hanbei.liblda.io.LDAWriter;
import org.junit.Test;

public class TestLDASparseGibbsampler extends AbstractGibbsSamplerTestFixture {

    @Test
    public void testLDATraining() throws FileNotFoundException {
        LDA<String> lda = new LDAImpl<String>(10);
        lda.setInferenceEngine(new SparseGibbsSampler());
        lda.getInferenceEngine().getRandomEngine().setSeed(0);
        lda.setTrainingData(corpus);

        long startTime = System.currentTimeMillis();
        lda.train();
        logRunTime(startTime);

        Map<Integer, List<String>> topFeatures = lda.getTopFeatures();
        LDAWriter writer = new LDAWriter();
        FileOutputStream out = new FileOutputStream("model.lda");
        writer.writeLDAModel(lda, out);

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
        LDAImpl lda = new LDAImpl<String>(10);
        lda.setInferenceEngine(new SparseGibbsSampler());
        lda.setTrainingData(corpus);
        lda.getInferenceEngine().getRandomEngine().setSeed(0);

        long startTime = System.currentTimeMillis();
        lda.train();
        logRunTime(startTime);

        List<? extends String> inferenceData = lda.getTopFeaturesForTopic(6);

        List<Topic> topicScores = lda.inference(inferenceData);
        for (Topic t : topicScores) {
            System.out.println(t.getProbability());
        }

        assertEquals("topic 0 wrong probability", topicScores.get(0).getProbability(), 0.0012107012802541894,
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
