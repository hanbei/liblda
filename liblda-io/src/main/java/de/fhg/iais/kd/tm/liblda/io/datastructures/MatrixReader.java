package de.fhg.iais.kd.tm.liblda.io.datastructures;

import de.fhg.iais.kd.tm.liblda.datastructures.Matrix;
import de.fhg.iais.kd.tm.liblda.io.IReader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

/**
 */
public class MatrixReader implements IReader<Matrix> {

    @Override
    public Matrix read(InputStream in) throws IOException {
        return read(new InputStreamReader(in));
    }

    @Override
    public Matrix read(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();

        StringTokenizer tokenizer = new StringTokenizer(line);
        String matrixType = tokenizer.nextToken();
        int rows = Integer.parseInt(tokenizer.nextToken());
        int columns = Integer.parseInt(tokenizer.nextToken());
        Matrix matrix = createMatrix(matrixType, rows, columns);

        int rowCounter = 0;
        while ((line = bufferedReader.readLine()) != null) {
            int columnCounter = 0;
            StringTokenizer lineTokenizer = new StringTokenizer(line);
            while (lineTokenizer.hasMoreTokens()) {
                double value = Double.parseDouble(lineTokenizer.nextToken());
                matrix.set(rowCounter, columnCounter, value);
                columnCounter++;
                checkMatrixSizeViolation(columns, columnCounter, "columns", true);
            }
            checkMatrixSizeViolation(columns, columnCounter, "columns", false);
            rowCounter++;
            checkMatrixSizeViolation(rows, rowCounter, "rows", true);
        }
        checkMatrixSizeViolation(rows, rowCounter, "rows", false);
        return matrix;
    }

    private void checkMatrixSizeViolation(int expected, int actual, String messagePart, boolean testBigger) throws IOException {
        if (testBigger) {
            if (actual > expected) {
                throw new IOException("Too much " + messagePart + ". The expected number of " + messagePart + " was " + expected + " but there are more");
            }
        } else {
            if (actual < expected) {
                throw new IOException("Too few " + messagePart + ". The expected number of " + messagePart + " was " + expected + " but there are only " + actual);
            }
        }

    }

    private Matrix createMatrix(String matrixType, int rows, int columns) throws IOException {
        try {
            Class<?> matrixClass = Class.forName(matrixType);
            Constructor<?> matrixConstructor = matrixClass.getConstructor(new Class[]{int.class, int.class});
            Integer[] matrixArguments = new Integer[2];
            matrixArguments[0] = rows;
            matrixArguments[1] = columns;
            Matrix matrix = (Matrix) matrixConstructor.newInstance(matrixArguments);
            return matrix;
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        } catch (InvocationTargetException e) {
            throw new IOException(e);
        } catch (InstantiationException e) {
            throw new IOException(e);
        } catch (IllegalAccessException e) {
            throw new IOException(e);
        } catch (NoSuchMethodException e) {
            throw new IOException(e);
        }
    }
}
