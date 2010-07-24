package de.fhg.iais.kd.tm.liblda.inference;

import java.io.Serializable;
import java.util.List;

import de.fhg.iais.kd.tm.liblda.LDAParameter;
import de.fhg.iais.kd.tm.liblda.RandomEngine;
import de.fhg.iais.kd.tm.liblda.datastructures.DocumentData;

/**
 * An InferenceEngine trains topics for a training set and inferences topics for new documents.
 *
 * @author Florian Schulz
 */
public interface InferenceEngine extends Serializable {

    /**
     * Set the {@link de.fhg.iais.kd.tm.liblda.RandomEngine} to sample topics. The standard random engine should is an
     * engine that uses {@link java.util.Random}.
     *
     * @param random The {@link de.fhg.iais.kd.tm.liblda.RandomEngine} to sample topics
     */
    public void setRandomEngine(RandomEngine random);

    /**
     * Get the random engine.
     *
     * @return
     */
    public RandomEngine getRandomEngine();

    /**
     * Train the topic model. The training data is supplied as a List of {@link de.fhg.iais.kd.tm.liblda.datastructures.DocumentData}.
     *
     * @param data The training data.
     */
    public void train(List<DocumentData> data);

    /**
     * Apply the topic model on data and inference the topics present in this data.
     *
     * @param data The test data.
     * @return The topic probabilities of all topics.
     */
    public double[] inference(DocumentData data);

    /**
     * Get the number of features (terms) that are present in the training. corpus.
     *
     * @return The number of features found in the training corpus.
     */
    public int getNumberOfFeatures();

    /**
     * Set the number of features (terms) that are present in the training.
     *
     * @param size The number of features (terms) that are present in the training
     */
    public void setNumberOfFeatures(int size);

    /** Get the number of iterations to perform the training steps. */
    public int getNumberOfIterations();

    /**
     * Set the number of iterations to perform the training steps.
     *
     * @param maximumIterations
     */
    public void setNumberOfIterations(int maximumIterations);

    /**
     * Get the parameter of the LDA, i.e. alpha, beta and number of topics.
     *
     * @return The LDAParameter alpha, beta and number of topics.
     */
    public LDAParameter getLDAParameter();

    /**
     * Set the parameter of the LDA, i.e. alpha, beta and number of topics.
     *
     * @param params The LDAParameter alpha, beta and number of topics.
     */
    public void setLDAParameter(LDAParameter params);

    /**
     * @param topicId
     * @return
     */
    public double[] getFeatureDistribution(int topicId);

}
