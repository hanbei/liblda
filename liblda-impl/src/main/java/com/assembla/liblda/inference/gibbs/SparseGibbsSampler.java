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
package com.assembla.liblda.inference.gibbs;

import com.assembla.liblda.datastructures.SparseMatrix;
import com.assembla.liblda.datastructures.SparseVector;


public class SparseGibbsSampler extends AbstractGibbsSampler {

	private static final long serialVersionUID = 4142129726300058154L;

	public SparseGibbsSampler() {
		super();
	}

	@Override
	protected void initDataStructures(int numberOfTopics) {
		topicTypeCount = new SparseMatrix(numberOfTopics, numberOfFeatures);
		topicTypeSum = new SparseVector(numberOfTopics);

		topicDocumentCount = new SparseMatrix(numberOfDocuments, numberOfTopics);
		documentTopicSum = new SparseVector(numberOfDocuments);
	}

}
