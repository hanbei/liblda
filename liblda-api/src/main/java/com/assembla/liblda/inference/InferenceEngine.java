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
package com.assembla.liblda.inference;

import java.io.Serializable;
import java.util.List;

import com.assembla.liblda.LDAParameter;
import com.assembla.liblda.RandomEngine;
import com.assembla.liblda.datastructures.DocumentData;
import com.assembla.liblda.datastructures.Matrix;

/**
 * An InferenceEngine trains topics for a training set and inferences topics for new documents.
 *
 * @author Florian Schulz
 */
public interface InferenceEngine extends Serializable {

    /**
     * Set the {@link com.assembla.liblda.RandomEngine} to sample topics. The standard random engine should is an
     * engine that uses {@link java.util.Random}.
     *
     * @param random The {@link com.assembla.liblda.RandomEngine} to sample topics
     */
    public void setRandomEngine(RandomEngine random);

    /**
     * Get the random engine.
     *
     * @return The RandomEngine used to sample for this InferenceEngine. 
     */
    public RandomEngine getRandomEngine();

    /**
     * Train the topic model. The training data is supplied as a List of {@link com.assembla.liblda.datastructures.DocumentData}.
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

    /**
     * Get the number of iterations to perform the training steps.
     *
     * @return The number of iterations the gibbs sampling should run. 
     */
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

    Matrix getTopicTypeCount();

    void setTopicTypeCount(Matrix topicTypeCount);

    Matrix getTopicDocumentCount();

    void setTopicDocumentCount(Matrix topicDocumentCount);
}
