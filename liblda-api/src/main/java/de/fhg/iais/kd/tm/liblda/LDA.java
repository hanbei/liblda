package de.fhg.iais.kd.tm.liblda;

import de.fhg.iais.kd.tm.liblda.inference.InferenceEngine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 23, 2010 Time: 11:54:14 AM To change this template use File |
 * Settings | File Templates.
 */
public interface LDA<T> extends Serializable {
    double[] getAlpha();

    void setAlpha(double[] alpha);

    void setAlpha(double alphaSum);

    double getBeta();

    void setBeta(double beta);

    int getNumberOfTopics();

    void setNumberOfTopics(int numberOfTopics);

    void setTrainingData(List<? extends List<? extends T>> data);

    void addTrainingData(List<? extends T> data);

    InferenceEngine getInferenceEngine();

    void setInferenceEngine(InferenceEngine newInferenceEngine);

    void train();

    List<Topic> inference(List<? extends T> data);

    HashMap<Integer, List<T>> getTopFeatures();

    List<T> getTopFeaturesForTopic(int topic);

    Alphabet<T> getAlphabet();

    int getNumberOfFeatures();
}
