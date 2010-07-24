package de.fhg.iais.kd.tm.liblda.inference.gibbs;

import de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix;

public class FastGibbsSampler extends AbstractGibbsSampler {

	private static final long serialVersionUID = -5199403154196566743L;

	public FastGibbsSampler() {
		super();
	}

	@Override
	protected void initDataStructures(int numberOfTopics) {
		topicTypeCount = new DenseMatrix(numberOfTopics, numberOfFeatures);
		topicTypeSum = new int[numberOfTopics];

		topicDocumentCount = new DenseMatrix(numberOfDocuments, numberOfTopics);
		documentTopicSum = new int[numberOfDocuments];
	}

}
