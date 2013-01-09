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
package de.hanbei.liblda.inference.gibbs;

import de.hanbei.liblda.datastructures.DenseMatrix;
import de.hanbei.liblda.datastructures.DenseVector;

public class FastGibbsSampler extends AbstractGibbsSampler {

	private static final long serialVersionUID = -5199403154196566743L;

	public FastGibbsSampler() {
		super();
	}

	@Override
	protected void initDataStructures(int numberOfTopics) {
		topicTypeCount = new DenseMatrix(numberOfTopics, numberOfFeatures);
		topicTypeSum = new DenseVector(numberOfTopics);

		topicDocumentCount = new DenseMatrix(numberOfDocuments, numberOfTopics);
		documentTopicSum = new DenseVector(numberOfDocuments);
	}

}
