package de.fhg.iais.kd.tm.liblda.inference.gibbs;

import de.fhg.iais.kd.tm.liblda.LDAParameter;
import de.fhg.iais.kd.tm.liblda.RandomEngine;
import de.fhg.iais.kd.tm.liblda.datastructures.DocumentData;
import de.fhg.iais.kd.tm.liblda.datastructures.Matrix;
import de.fhg.iais.kd.tm.liblda.inference.InferenceEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public abstract class AbstractGibbsSampler implements InferenceEngine {

    private static final long serialVersionUID = -228081286320312725L;
    private static final Logger logger = Logger.getLogger(AbstractGibbsSampler.class.getName());

    private LDAParameter parameter;

    protected int numberOfDocuments;
    protected int numberOfFeatures;

    private int maximumIterations;

    protected Matrix topicTypeCount;

    protected int[] topicTypeSum;
    protected Matrix topicDocumentCount;
    protected int[] documentTopicSum;

    transient private RandomEngine random;

    private int burnIn = 1800;

    protected AbstractGibbsSampler() {
        parameter = new LDAParameter(50);
        Random rand = new Random();
        setRandomEngine(new RandomEngine(rand));
        setNumberOfIterations(2000);
    }

    public double[] inference(DocumentData data) {
        int[] localTopicDocumentCount = new int[parameter.getNumberOfTopics()];
        int localDocumentTopicSum = initLocalModelForInference(data, localTopicDocumentCount);

        for (int i = 0; i < maximumIterations; i++) {
            for (int tokenIndex = 0; tokenIndex < data.getLength(); tokenIndex++) {
                int type = data.getTokenAtIndex(tokenIndex);
                int oldTopic = data.getTopicForTokenAtIndex(tokenIndex);
                localTopicDocumentCount[oldTopic] -= 1;
                localDocumentTopicSum -= 1;

                int newTopic = sampleNewTopicForInferenceStep(localTopicDocumentCount, localDocumentTopicSum, type);

                data.setTopicForTokenAtIndex(tokenIndex, newTopic);
                localTopicDocumentCount[newTopic] += 1;
                localDocumentTopicSum += 1;
            }
            if ((i + 1) % 100 == 0) {
                logger.info("Iteration: " + (i - 1));
            }
        }
        return collectTopicInferenceResults(data);
    }

    private double[] collectTopicInferenceResults(DocumentData data) {
        double[] inferenceResults = new double[parameter.getNumberOfTopics()];
        int sum = 0;
        for (int topicForToken : data.getTopicsForTokens()) {
            inferenceResults[topicForToken]++;
            sum++;
        }
        for (int topic = 0; topic < parameter.getNumberOfTopics(); topic++) {
            inferenceResults[topic] = (inferenceResults[topic] + parameter.getAlphaForTopic(topic))
                    / (sum + parameter.getAlphaSum());
        }
        return inferenceResults;
    }

    private int sampleNewTopicForInferenceStep(int[] localTopicDocumentCount, int localDocumentTopicSum, int type) {
        double[] topicWeight = new double[parameter.getNumberOfTopics()];
        double topicWeightSum = 0.0;
        Arrays.fill(topicWeight, 0.0);

        for (int topic = 0; topic < parameter.getNumberOfTopics(); topic++) {
            double wordTopicProbability = (topicTypeCount.get(topic, type) + parameter.getBeta())
                    / (topicTypeSum[topic] + parameter.getBetaSum());
            double topicProbability = (localTopicDocumentCount[topic] + parameter.getAlphaForTopic(topic))
                    / (localDocumentTopicSum + parameter.getAlphaSum());
            topicWeight[topic] = wordTopicProbability * topicProbability;
            topicWeightSum += topicWeight[topic];
        }
        int newTopic = random.nextDiscrete(topicWeight, topicWeightSum);
        return newTopic;
    }

    private int initLocalModelForInference(DocumentData data, int[] localTopicDocumentCount) {
        parameter.reinitBetaSum(numberOfFeatures);
        parameter.reinitAlphaSum();

        int localDocumentTopicSum = 0;
        for (int tokenIndex = 0; tokenIndex < data.getLength(); tokenIndex++) {
            int randomTopic = random.nextInt(parameter.getNumberOfTopics());
            data.setTopicForTokenAtIndex(tokenIndex, randomTopic);
            localTopicDocumentCount[randomTopic] += 1;
            localDocumentTopicSum += 1;
        }
        return localDocumentTopicSum;
    }

    @Override
    public void train(List<DocumentData> data) {
        initModel(data);

        long overallTime = System.nanoTime();
        for (int iteration = 0; iteration < maximumIterations; iteration++) {
            long iterationTime = System.nanoTime();
            if (samplingBurnedIn(iteration)) {
                if (isRightSampleStep(iteration)) {
                    long alphaTime = System.nanoTime();
                    parameter.learnAlphaParameter(topicDocumentCount, documentTopicSum);
                    System.err.println("Learning alpha took " + (System.nanoTime() - alphaTime) / 1000000000.0);
                }
            }
            for (int documentIndex = 0; documentIndex < numberOfDocuments; documentIndex++) {
                DocumentData documentData = data.get(documentIndex);
                for (int tokenIndex = 0; tokenIndex < documentData.getLength(); tokenIndex++) {
                    int oldTopic = documentData.getTopicForTokenAtIndex(tokenIndex);
                    decrementDocumentTopicCount(documentIndex, oldTopic);
                    decrementTopicTypeCount(documentData, tokenIndex, oldTopic);

                    int type = documentData.getTokenAtIndex(tokenIndex);
                    int newTopic = sampleNewTopicForTrainingStep(documentIndex, type);

                    documentData.setTopicForTokenAtIndex(tokenIndex, newTopic);
                    incrementDocumentTopicCount(documentIndex, newTopic);
                    incrementTopicTypeCount(documentData, tokenIndex, newTopic);
                }
            }
            System.err.println("Iteration time took " + (System.nanoTime() - iterationTime) / 1000000000.0);
        }
        System.err.println("Overall time took " + (System.nanoTime() - overallTime) / 1000000000.0);
    }

    private boolean isRightSampleStep(int iteration) {
        return iteration % 20 == 0;
    }

    private boolean samplingBurnedIn(int iteration) {
        return iteration > burnIn;
    }

    protected void initModel(List<DocumentData> data) {
        numberOfDocuments = data.size();

        initDataStructures(parameter.getNumberOfTopics());

        parameter.reinitBetaSum(numberOfFeatures);
        parameter.reinitAlphaSum();

        for (int documentIndex = 0; documentIndex < numberOfDocuments; documentIndex++) {
            DocumentData documentData = data.get(documentIndex);
            for (int tokenIndex = 0; tokenIndex < documentData.getLength(); tokenIndex++) {

                int randomTopic = random.nextInt(parameter.getNumberOfTopics());
                documentData.setTopicForTokenAtIndex(tokenIndex, randomTopic);
                incrementDocumentTopicCount(documentIndex, randomTopic);
                incrementTopicTypeCount(documentData, tokenIndex, randomTopic);
            }
        }
    }

    protected abstract void initDataStructures(int numTopics);

    private void incrementDocumentTopicCount(int documentIndex, int topic) {
        topicDocumentCount.increment(documentIndex, topic);
        documentTopicSum[documentIndex] += 1;
    }

    private void decrementDocumentTopicCount(int documentIndex, int topic) {
        topicDocumentCount.decrement(documentIndex, topic);
        documentTopicSum[documentIndex] -= 1;
    }

    private void incrementTopicTypeCount(DocumentData documentDate, int tokenIndex, int topic) {
        int type = documentDate.getTokenAtIndex(tokenIndex);
        topicTypeCount.increment(topic, type);
        topicTypeSum[topic] += 1;
    }

    private void decrementTopicTypeCount(DocumentData documentDate, int tokenIndex, int topic) {
        topicTypeCount.decrement(topic, documentDate.getTokenAtIndex(tokenIndex));
        topicTypeSum[topic] -= 1;
    }

    private int sampleNewTopicForTrainingStep(int documentIndex, int type) {
        double[] topicWeights = new double[parameter.getNumberOfTopics()];
        double topicWeightSum = 0.0;
        for (int topicIndex = 0; topicIndex < parameter.getNumberOfTopics(); topicIndex++) {
            double wordTopicProbability = (topicTypeCount.get(topicIndex, type) + parameter.getBeta())
                    / (topicTypeSum[topicIndex] + parameter.getBetaSum());
            double topicProbability = (topicDocumentCount.get(documentIndex, topicIndex) + parameter
                    .getAlphaForTopic(topicIndex))
                    / (documentTopicSum[documentIndex] + parameter.getAlphaSum());
            topicWeights[topicIndex] = wordTopicProbability * topicProbability;
            topicWeightSum += topicWeights[topicIndex];
        }

        int newTopic = random.nextDiscrete(topicWeights, topicWeightSum);
        return newTopic;
    }

    public void setLDAParameter(LDAParameter params) {
        parameter = params;
    }

    public LDAParameter getLDAParameter() {
        return parameter;
    }

    @Override
    public int getNumberOfFeatures() {
        return numberOfFeatures;
    }

    @Override
    public void setNumberOfFeatures(int numTypes) {
        numberOfFeatures = numTypes;
    }

    public int getNumberOfIterations() {
        return maximumIterations;
    }

    public void setNumberOfIterations(int maximumIterations) {
        this.maximumIterations = maximumIterations;
        burnIn = (int) (maximumIterations * 0.9);
    }

    public RandomEngine getRandomEngine() {
        return random;
    }

    public void setRandomEngine(RandomEngine rand) {
        random = rand;
    }

    public double[] getFeatureDistribution(int topicId) {
        double[] featureDistribution = new double[numberOfFeatures];
        double topicTypeSumValue = topicTypeSum[topicId];

        for (int type = 0; type < numberOfFeatures; type++) {
            double ttCount = topicTypeCount.get(topicId, type);
            double fraction = ttCount / topicTypeSumValue;
            featureDistribution[type] = fraction;
        }
        return featureDistribution;
    }

    @Override
    public Matrix getTopicTypeCount() {
        return topicTypeCount;
    }

    @Override
    public void setTopicTypeCount(Matrix topicTypeCount) {
        this.topicTypeCount = topicTypeCount;
    }

    @Override
    public Matrix getTopicDocumentCount() {
        return topicDocumentCount;
    }

    @Override
    public void setTopicDocumentCount(Matrix topicDocumentCount) {
        this.topicDocumentCount = topicDocumentCount;
    }
}
