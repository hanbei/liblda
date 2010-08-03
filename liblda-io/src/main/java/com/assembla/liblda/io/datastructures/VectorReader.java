package com.assembla.liblda.io.datastructures;

import com.assembla.liblda.datastructures.Vector;
import com.assembla.liblda.io.IOHelper;
import com.assembla.liblda.io.IReader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

/** @author fschulz */
public class VectorReader implements IReader<Vector> {
    @Override
    public Vector read(InputStream in) throws IOException {
        return read(new BufferedReader(new InputStreamReader(in)));
    }

    @Override
    public Vector read(Reader reader) throws IOException {
        String line = IOHelper.readLine(reader);

        StringTokenizer tokenizer = new StringTokenizer(line);
        String vectorType = tokenizer.nextToken();
        int size = Integer.parseInt(tokenizer.nextToken());
        Vector vector = createMatrix(vectorType, size);

        line = IOHelper.readLine(reader);
        StringTokenizer lineTokenizer = new StringTokenizer(line);
        int index = 0;
        while (lineTokenizer.hasMoreTokens()) {
            double value = Double.parseDouble(lineTokenizer.nextToken());
            vector.set(index, value);
            index++;
        }
        checkVectorSizeViolation(index, size);
        return vector;
    }

    private Vector createMatrix(String vectorType, int size) throws IOException {
        try {
            Class<?> matrixClass = Class.forName(vectorType);
            Constructor<?> vectorConstructor = matrixClass.getConstructor(new Class[]{int.class});
            Vector vector = (Vector) vectorConstructor.newInstance(size);
            return vector;
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

    private void checkVectorSizeViolation(int expected, int actual) throws IOException {
        if (actual > expected) {
            throw new IOException("Too many elements. The expected number of elements was " + expected + " but there are " + actual);
        }
        if (actual < expected) {
            throw new IOException("Too few elements. The expected number of elements was " + expected + " but there are only " + actual);
        }
    }
}
