package de.fhg.iais.kd.tm.liblda;

import java.io.Serializable;
import java.util.Random;

public class RandomEngine extends Random implements Serializable {

	private static final long serialVersionUID = -2808433160933178125L;

	private Random random;

	public RandomEngine(Random rand) {
		random = rand;
	}

	public synchronized int nextDiscrete(double[] a, double sum) {
		double b = 0;
		double r = random.nextDouble() * sum;
		for (int i = 0; i < a.length; i++) {
			b += a[i];
			if (b > r) {
				return i;
			}
		}
		return a.length - 1;
	}

	public synchronized double nextGamma(double alpha, double beta) {
		double gamma = 0;
		if (alpha <= 0 || beta <= 0) {
			throw new IllegalArgumentException("alpha and beta must be strictly positive.");
		}
		if (alpha < 1) {
			double b, p;
			boolean flag = false;
			b = 1 + alpha * Math.exp(-1);
			while (!flag) {
				p = b * random.nextDouble();
				if (p > 1) {
					gamma = -Math.log((b - p) / alpha);
					if (random.nextDouble() <= Math.pow(gamma, alpha - 1))
						flag = true;
				} else {
					gamma = Math.pow(p, 1 / alpha);
					if (random.nextDouble() <= Math.exp(-gamma))
						flag = true;
				}
			}
		} else if (alpha == 1) {
			gamma = -Math.log(random.nextDouble());
		} else {
			double y = -Math.log(random.nextDouble());
			while (random.nextDouble() > Math.pow(y * Math.exp(1 - y), alpha - 1))
				y = -Math.log(random.nextDouble());
			gamma = alpha * y;
		}
		return beta * gamma;
	}

	public double[] nextDistribution(double[] alpha) {
		double magnitude = 0;
		double[] partition = new double[alpha.length];

        for (double anAlpha : alpha) {
            magnitude += anAlpha;
        }

		for (int i = 0; i < alpha.length; i++) {
			partition[i] = alpha[i] / magnitude;
		}

		double[] distribution = new double[partition.length];

		// For each dimension, draw a sample from Gamma(mp_i, 1)
		double sum = 0;
		for (int i = 0; i < distribution.length; i++) {
			distribution[i] = nextGamma(partition[i] * magnitude, 1);
			if (distribution[i] <= 0) {
				distribution[i] = 0.0001;
			}
			sum += distribution[i];
		}

		// Normalize
		for (int i = 0; i < distribution.length; i++) {
			distribution[i] /= sum;
		}

		return distribution;
	}

	@Override
	public boolean nextBoolean() {
		return random.nextBoolean();
	}

	@Override
	public void nextBytes(byte[] bytes) {
		random.nextBytes(bytes);
	}

	@Override
	public double nextDouble() {
		return random.nextDouble();
	}

	@Override
	public float nextFloat() {
		return random.nextFloat();
	}

	@Override
	public synchronized double nextGaussian() {
		return random.nextGaussian();
	}

	@Override
	public int nextInt() {
		return random.nextInt();
	}

	@Override
	public int nextInt(int n) {
		return random.nextInt(n);
	}

	@Override
	public long nextLong() {
		return random.nextLong();
	}

	@Override
	public synchronized void setSeed(long seed) {
		if (random != null) {
			random.setSeed(seed);
		}
	}

}
