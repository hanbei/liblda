package de.fhg.iais.kd.tm.liblda.inference.gibbs;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import de.fhg.iais.kd.tm.liblda.datastructures.DenseMatrix;
import de.fhg.iais.kd.tm.liblda.datastructures.SparseMatrix;

@SuppressWarnings("unused")
public class TestColtPerformance {

	private static final int NUMBEROFINSERTS = 100000;
	private static final int DIM1 = 3000;
	private static final int DIM2 = 2000;

	private static Random rand;
	private static int[] xInsert;
	private static int[] yInsert;
	private static double[] val;

	@BeforeClass
	public static void setUp() {
		xInsert = new int[NUMBEROFINSERTS];
		yInsert = new int[NUMBEROFINSERTS];
		val = new double[NUMBEROFINSERTS];
		rand = new Random();
		for (int i = 0; i < NUMBEROFINSERTS; i++) {
			xInsert[i] = rand.nextInt(DIM2);
			yInsert[i] = rand.nextInt(DIM1);
			val[i] = rand.nextDouble();
		}
	}

	@Test
	public void testColtDenseMatrixTimes() {
		System.out.println("Colt Dense Matrix");
		DoubleMatrix2D matrix2D = new DenseDoubleMatrix2D(DIM2, DIM1);
		long startTime = System.currentTimeMillis();
		for (int k = 0; k < NUMBEROFINSERTS; k++) {
			matrix2D.setQuick(xInsert[k], yInsert[k], val[k]);
		}
		System.out.println("set " + (System.currentTimeMillis() - startTime)
				+ " ms");
		startTime = System.currentTimeMillis();
		for (int k = NUMBEROFINSERTS - 1; k >= 0; k--) {
			double i = matrix2D.getQuick(xInsert[k], yInsert[k]);
		}
		System.out.println("get " + (System.currentTimeMillis() - startTime)
				+ " ms");
	}

	@Test
	public void testColtSparseMatrixTimes() {
		System.out.println("Colt Sparse Matrix");
		DoubleMatrix2D matrix2D = new SparseDoubleMatrix2D(DIM2, DIM1);
		long startTime = System.currentTimeMillis();
		for (int k = 0; k < NUMBEROFINSERTS; k++) {
			matrix2D.setQuick(xInsert[k], yInsert[k], val[k]);
		}
		System.out.println("set " + (System.currentTimeMillis() - startTime)
				+ " ms");
		startTime = System.currentTimeMillis();
		for (int k = NUMBEROFINSERTS - 1; k >= 0; k--) {
			double i = matrix2D.getQuick(xInsert[k], yInsert[k]);
		}
		System.out.println("get " + (System.currentTimeMillis() - startTime)
				+ " ms");
	}

	@Test
	public void testDoubleArrayTimes() {
		System.out.println("own dense matrix");
		DenseMatrix matrix2D = new DenseMatrix(DIM2, DIM1);
		long startTime = System.currentTimeMillis();
		for (int k = 0; k < NUMBEROFINSERTS; k++) {
			matrix2D.set(xInsert[k], yInsert[k], val[k]);
		}
		System.out.println("set " + (System.currentTimeMillis() - startTime)
				+ " ms");
		startTime = System.currentTimeMillis();
		for (int k = NUMBEROFINSERTS - 1; k >= 0; k--) {
			double i = matrix2D.get(xInsert[k], yInsert[k]);
		}
		System.out.println("get " + (System.currentTimeMillis() - startTime)
				+ " ms");
	}

	@Test
	public void testOwnSparseStuffTimes() {
		System.out.println("own sparse matrix");
		SparseMatrix matrix2D = new SparseMatrix(DIM1, DIM2);
		long startTime = System.currentTimeMillis();
		for (int k = 0; k < NUMBEROFINSERTS; k++) {
			matrix2D.set(xInsert[k], yInsert[k], val[k]);
		}
		System.out.println("set " + (System.currentTimeMillis() - startTime)
				+ " ms");
		startTime = System.currentTimeMillis();
		for (int k = NUMBEROFINSERTS - 1; k >= 0; k--) {
			double i = matrix2D.get(xInsert[k], yInsert[k]);
		}
		System.out.println("get " + (System.currentTimeMillis() - startTime)
				+ " ms");
	}

	public static void main(String[] args) {
		TestColtPerformance test = new TestColtPerformance();
		TestColtPerformance.setUp();
		test.testColtDenseMatrixTimes();
		test.testColtSparseMatrixTimes();
		test.testDoubleArrayTimes();
		test.testOwnSparseStuffTimes();
	}

}
