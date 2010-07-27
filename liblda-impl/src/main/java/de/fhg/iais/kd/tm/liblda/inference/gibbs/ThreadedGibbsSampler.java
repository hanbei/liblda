package de.fhg.iais.kd.tm.liblda.inference.gibbs;

import java.util.List;

import de.fhg.iais.kd.tm.liblda.datastructures.DocumentData;
import de.fhg.iais.kd.tm.liblda.LDAParameter;
import de.fhg.iais.kd.tm.liblda.RandomEngine;
import de.fhg.iais.kd.tm.liblda.inference.InferenceEngine;

public class ThreadedGibbsSampler implements InferenceEngine {

	private static final long serialVersionUID = -894776317159386218L;
	private int numberOfThreads;
	private RandomEngine randomEngine;
	private LDAParameter parameter;
	private int maxIterations;
	private int numberOfFeatures;

	public ThreadedGibbsSampler() {
		numberOfThreads = Runtime.getRuntime().availableProcessors() + 1;
	}

	@Override
	public void train(List<DocumentData> data) {
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	@Override
	public double[] getFeatureDistribution(int topicId) {
		return null;
	}

	@Override
	public LDAParameter getLDAParameter() {
		return parameter;
	}

	@Override
	public int getNumberOfFeatures() {
		return numberOfFeatures;
	}

	@Override
	public int getNumberOfIterations() {
		return maxIterations;
	}

	@Override
	public RandomEngine getRandomEngine() {
		return randomEngine;
	}

	@Override
	public double[] inference(DocumentData data) {
		return null;
	}

	@Override
	public void setLDAParameter(LDAParameter params) {
		parameter = params;
	}

	@Override
	public void setNumberOfFeatures(int numFeatures) {
		numberOfFeatures = numFeatures;
	}

	@Override
	public void setNumberOfIterations(int maximumIterations) {
		maxIterations = maximumIterations;
	}

	@Override
	public void setRandomEngine(RandomEngine random) {
		randomEngine = random;
	}

}