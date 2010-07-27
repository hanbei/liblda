package de.fhg.iais.kd.tm.liblda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.fhg.iais.kd.tm.liblda.inference.InferenceEngine;

/**
 * 0.6125968452677445, 0.7021263369523334, 0.8104565321643618, 0.6704615910882477, 0.8464015500087296,
 * 0.8451512496921193, 0.7007855117536164, 0.8922581539918372, 0.6915600088831061, 0.6182354730470828
 */
public class DocumentGenerator<T> {

    private LDA<T> ldaModel;
    private int minimumDocumentLength;
    private int maximumDocumentLength;

    public DocumentGenerator(LDA lda) {
        ldaModel = lda;
        maximumDocumentLength = 300;
        minimumDocumentLength = 50;
    }

    public List<T> generateDocument() {
        List<T> generatedDocument = new ArrayList<T>();

        InferenceEngine inferenceEngine = ldaModel.getInferenceEngine();
        LDAParameter parameter = inferenceEngine.getLDAParameter();
        RandomEngine randomEngine = inferenceEngine.getRandomEngine();

        double[][] topicTermDist = getTopicTermDistributions();

        int docLength = drawDocumentLength();
        double[] documentTopicDistribution = randomEngine.nextDistribution(parameter.getAlpha());

        for (int word = 0; word < docLength; word++) {
            int topic = randomEngine.nextDiscrete(documentTopicDistribution, 1.0);
            int featureIndex = randomEngine.nextDiscrete(topicTermDist[topic], 1.0);
            generatedDocument.add(ldaModel.getAlphabet().lookupFeature(featureIndex));
        }

        return generatedDocument;
    }

    public List<T> generateDocument(int[] topics, double[] probabilities, int docLength) {
        RandomEngine randomEngine = ldaModel.getInferenceEngine().getRandomEngine();
        List<T> generatedDocument = new ArrayList<T>();

        double[][] topicTermDist = getTopicTermDistributions();

        Integer[] wordIndices = fillTopicDistributionVector(topics, probabilities, docLength);

        Collections.shuffle(Arrays.asList(wordIndices), randomEngine);

        for (int n = 0; n < docLength; n++) {
            int topic = wordIndices[n];
            int term = randomEngine.nextDiscrete(topicTermDist[topic], 1.0);
            generatedDocument.add(ldaModel.getAlphabet().lookupFeature(term));
        }
        return generatedDocument;
    }

    private Integer[] fillTopicDistributionVector(int[] topics, double[] probabilities, int docLength) {
        int currentIndex = 0;
        int fractionSum = 0;
        Integer[] wordIndices = new Integer[docLength];

        for (int i = 0; i < topics.length; i++) {
            int fraction = (int) Math.round(docLength * probabilities[i]);
            fractionSum += fraction;
            int j = currentIndex;
            for (; (j < currentIndex + fraction) && (j < wordIndices.length); j++) {
                wordIndices[j] = topics[i];
            }
            currentIndex = j;
        }
        if (fractionSum != docLength) {
            int difference = docLength - fractionSum;
            if (difference > 0) {
                int lastTopic = wordIndices[docLength - difference - 1];
                for (int i = docLength - 1; i >= docLength - difference; i--) {
                    wordIndices[i] = lastTopic;
                }
            }
        }
        return wordIndices;
    }

    public List<T> generateDocument(int[] topics, double[] probabilities) {
        return generateDocument(topics, probabilities, drawDocumentLength());
    }

    private double[][] getTopicTermDistributions() {
        InferenceEngine inferenceEngine = ldaModel.getInferenceEngine();
        int numTopics = inferenceEngine.getLDAParameter().getNumberOfTopics();
        double[][] topicTermDist = new double[numTopics][];
        for (int k = 0; k < numTopics; k++) {
            double[] probabilities = inferenceEngine.getFeatureDistribution(k);
            topicTermDist[k] = probabilities;
        }
        return topicTermDist;
    }

    private int drawDocumentLength() {
        int n = maximumDocumentLength - minimumDocumentLength;
        if (n > 0) {
            return ldaModel.getInferenceEngine().getRandomEngine().nextInt(n) + minimumDocumentLength;
        }
        return minimumDocumentLength;
    }

    public int getMinimumDocumentLength() {
        return minimumDocumentLength;
    }

    public void setMinimumDocumentLength(int minimumDocumentLength) {
        this.minimumDocumentLength = minimumDocumentLength;
    }

    public int getMaximumDocumentLength() {
        return maximumDocumentLength;
    }

    public void setMaximumDocumentLength(int maximumDocumentLength) {
        this.maximumDocumentLength = maximumDocumentLength;
    }
}
