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

package de.hanbei.liblda;

import de.hanbei.liblda.inference.InferenceEngine;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A interface that provides common methods for performing LDA on a set of data. The type parameter <T> denotes the
 * type of items the LDA should be performed on. The concrete class <T> must implement hashCode.
 *
 *  @author Florian Schulz
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

    Map<Integer, List<T>> getTopFeatures();

    List<T> getTopFeaturesForTopic(int topic);

    Alphabet<T> getAlphabet();

    int getNumberOfFeatures();
}
