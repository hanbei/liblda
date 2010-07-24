package de.fhg.iais.kd.tm.liblda.inference.gibbs;

import de.fhg.iais.kd.tm.liblda.datastructures.SparseMatrix;

public class SparseGibbsSampler extends AbstractGibbsSampler {

	private static final long serialVersionUID = 4142129726300058154L;

	public SparseGibbsSampler() {
		super();
	}

	@Override
	protected void initDataStructures(int numberOfTopics) {
		topicTypeCount = new SparseMatrix(numberOfTopics, numberOfFeatures);
		topicTypeSum = new int[numberOfTopics];

		topicDocumentCount = new SparseMatrix(numberOfDocuments, numberOfTopics);
		documentTopicSum = new int[numberOfDocuments];
	}

}
