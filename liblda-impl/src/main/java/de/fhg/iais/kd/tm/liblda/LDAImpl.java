package de.fhg.iais.kd.tm.liblda;

import de.fhg.iais.kd.tm.liblda.datastructures.DocumentData;
import de.fhg.iais.kd.tm.liblda.datastructures.Pair;
import de.fhg.iais.kd.tm.liblda.inference.InferenceEngine;
import de.fhg.iais.kd.tm.liblda.inference.gibbs.FastGibbsSampler;

import java.util.*;

/**
 * @author Florian Schulz
 * @param <T>
 */
public class LDAImpl<T> implements LDA<T> {

    private static final long serialVersionUID = -3996891572164914458L;

    private static final int UNKNOWN_TOKEN = -1;

    private Alphabet<T> alphabet;

    transient private List<DocumentData> documentData;

    private InferenceEngine inferenceEngine;

    /**
     * Create new LDAImpl Object that allows for inferencing topics from a collection of features.
     *
     * @param numTopics The number of topics.
     */
    public LDAImpl(int numTopics) {
        inferenceEngine = new FastGibbsSampler();
        setNumberOfTopics(numTopics);
        alphabet = new Alphabet<T>();
        setAlpha(50);
        setBeta(0.001);
        documentData = new ArrayList<DocumentData>();
    }

    @Override
    public double[] getAlpha() {
        return inferenceEngine.getLDAParameter().getAlpha();
    }

    @Override
    public void setAlpha(double[] alpha) {
        inferenceEngine.getLDAParameter().setAlpha(alpha);
    }

    @Override
    public void setAlpha(double alphaSum) {
        inferenceEngine.getLDAParameter().setAlphaSum(alphaSum);
    }

    @Override
    public double getBeta() {
        return inferenceEngine.getLDAParameter().getBeta();
    }

    @Override
    public void setBeta(double beta) {
        inferenceEngine.getLDAParameter().setBeta(beta);
    }

    @Override
    public int getNumberOfTopics() {
        return inferenceEngine.getLDAParameter().getNumberOfTopics();
    }

    @Override
    public void setNumberOfTopics(int numberOfTopics) {
        inferenceEngine.getLDAParameter().setNumberOfTopics(numberOfTopics);
    }

    @Override
    public void setTrainingData(List<? extends List<? extends T>> data) {
        documentData.clear();
        for (List<? extends T> date : data) {
            DocumentData docData = createTrainingDocumentData(date);
            documentData.add(docData);
        }
        inferenceEngine.setNumberOfFeatures(alphabet.size());
    }

    @Override
    public void addTrainingData(List<? extends T> data) {
        DocumentData documentDate = createTrainingDocumentData(data);
        documentData.add(documentDate);
        inferenceEngine.setNumberOfFeatures(alphabet.size());
    }

    private DocumentData createTrainingDocumentData(List<? extends T> data) {
        DocumentData documentDate = new DocumentData(data.size());
        documentDate.setId(documentData.size());
        for (int wordIndex = 0; wordIndex < data.size(); wordIndex++) {
            T word = data.get(wordIndex);
            int tokenIndex = alphabet.add(word);
            documentDate.setTokenAtWordIndex(wordIndex, tokenIndex);
        }
        return documentDate;
    }

    @Override
    public InferenceEngine getInferenceEngine() {
        return inferenceEngine;
    }

    @Override
    public void setInferenceEngine(InferenceEngine newInferenceEngine) {
        newInferenceEngine.setLDAParameter(inferenceEngine.getLDAParameter());
        newInferenceEngine.setNumberOfFeatures(inferenceEngine.getNumberOfFeatures());
        newInferenceEngine.setRandomEngine(inferenceEngine.getRandomEngine());
        this.inferenceEngine = newInferenceEngine;
    }

    @Override
    public void train() {
        inferenceEngine.train(documentData);
    }

    @Override
    public List<Topic> inference(List<? extends T> data) {
        DocumentData documentDate = new DocumentData(data.size());
        documentDate.setId(1);
        for (int wordIndex = 0; wordIndex < data.size(); wordIndex++) {
            T token = data.get(wordIndex);
            if (alphabet.containsFeature(token)) {
                int tokenIndex = alphabet.lookupIndex(token);
                documentDate.setTokenAtWordIndex(wordIndex, tokenIndex);
            } else {
                documentDate.setTokenAtWordIndex(wordIndex, UNKNOWN_TOKEN);
            }
        }

        double[] docTopicScores = inferenceEngine.inference(documentDate);
        return convertMapToTopicList(docTopicScores);
    }

    private List<Topic> convertMapToTopicList(double[] docTopicScores) {
        List<Topic> docTopicScoresList = new ArrayList<Topic>();
        for (int topicId = 0; topicId < docTopicScores.length; topicId++) {
            Topic topic = new Topic();
            topic.setId(topicId);
            topic.setProbability(docTopicScores[topicId]);
            docTopicScoresList.add(topic);
        }
        return docTopicScoresList;
    }

    @Override
    public Map<Integer, List<T>> getTopFeatures() {
        int numberOfTopics = inferenceEngine.getLDAParameter().getNumberOfTopics();
        Map<Integer, List<T>> topFeatures = new HashMap<Integer, List<T>>(numberOfTopics);

        for (int topicId = 0; topicId < numberOfTopics; topicId++) {
            List<T> topFeaturesForTopic = getTopFeaturesForTopic(topicId);
            topFeatures.put(topicId, topFeaturesForTopic);
        }
        return topFeatures;
    }

    @Override
    public List<T> getTopFeaturesForTopic(int topic) {
        List<T> topFeatures = new ArrayList<T>();
        List<Pair<Integer, Double>> topWordsPerTopic = new ArrayList<Pair<Integer, Double>>();
        double[] featureDistribution = inferenceEngine.getFeatureDistribution(topic);
        for (int type = 0; type < featureDistribution.length; type++) {
            if (featureDistribution[type] > 0) {
                topWordsPerTopic.add(new Pair<Integer, Double>(type, featureDistribution[type]));
            }
        }

        Collections.sort(topWordsPerTopic, new Comparator<Pair<Integer, Double>>() {
            @Override
            public int compare(Pair<Integer, Double> o1, Pair<Integer, Double> o2) {
                return -1 * o1.compareTo(o2);
            }
        });

        for (Pair<Integer, Double> pair : topWordsPerTopic) {
            T word = alphabet.lookupFeature(pair.getKey());
            topFeatures.add(word);
        }
        return topFeatures;
    }

    @Override
    public Alphabet<T> getAlphabet() {
        return alphabet;
    }

    @Override
    public int getNumberOfFeatures() {
        return inferenceEngine.getNumberOfFeatures();
    }
}
