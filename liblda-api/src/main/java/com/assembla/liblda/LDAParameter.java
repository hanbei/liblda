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
package com.assembla.liblda;

import java.io.Serializable;
import java.util.Arrays;

import com.assembla.liblda.datastructures.Matrix;
import org.apache.commons.math.special.Gamma;

public final class LDAParameter implements Serializable {

    private static final long serialVersionUID = 6569466204277762671L;

    private int numberOfTopics;

    private double[] alpha;
    private double alphaSum;

    private double beta;
    private double betaSum;

    private int alphaIterations;

    public LDAParameter(int numTopics) {
        numberOfTopics = numTopics;
        alphaIterations = 200;
    }

    public int getNumberOfTopics() {
        return numberOfTopics;
    }

    public void setNumberOfTopics(int numTopics) {
        numberOfTopics = numTopics;
        alpha = new double[numberOfTopics];
    }

    public double[] getAlpha() {
        return alpha;
    }

    public double getAlphaForTopic(int topicIndex) {
        return alpha[topicIndex];
    }

    public void setAlpha(double[] alpha) {
        this.alpha = alpha;
    }

    public void setAlphaSum(double aSum) {
        Arrays.fill(alpha, aSum / numberOfTopics);
    }

    public double getAlphaSum() {
        return alphaSum;
    }

    public void reinitAlphaSum() {
        alphaSum = 0.0;
        for (double a : alpha) {
            alphaSum += a;
        }
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getBetaSum() {
        return betaSum;
    }


    public void reinitBetaSum(int numberOfTypes) {
        betaSum = beta * numberOfTypes;
    }

    public void learnAlphaParameter(Matrix topicDocumentCount, int[] documentTopicSum) {
        int numberOfDocs = documentTopicSum.length;

        for (int iteration = 0; iteration < alphaIterations; iteration++) {
            for (int k = 0; k < alpha.length; k++) {
                double oldAlpha = alpha[k];
                double sumOverDocumentsDigammaAlpha = 0.0;
                double sumOverDocumentsDigammaAlphaSum = 0.0;

                for (int i = 0; i < numberOfDocs; i++) {
                    double count = topicDocumentCount.get(i, k);
                    double countSum = documentTopicSum[i];
                    sumOverDocumentsDigammaAlpha += Gamma.digamma(count + oldAlpha);
                    sumOverDocumentsDigammaAlphaSum += Gamma.digamma(countSum + alphaSum);
                }

                alpha[k] = oldAlpha * (sumOverDocumentsDigammaAlpha - numberOfDocs * Gamma.digamma(oldAlpha))
                        / (sumOverDocumentsDigammaAlphaSum - numberOfDocs * Gamma.digamma(alphaSum));
                reinitAlphaSum();
            }

        }
    }
}