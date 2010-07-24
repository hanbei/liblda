package de.fhg.iais.kd.tm.liblda;

import gnu.trove.TObjectIntHashMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * An alphabet holds a mapping of features (e.g. words) to a number representing that class of a feature.
 *
 * @author Florian Schulz
 * @param <V> The type of feature to map. Must implement <code>hashCode()</code> and Serializable .
 */
public class Alphabet<V> implements Serializable {

    private static final long serialVersionUID = 6411827850590331667L;

    private TObjectIntHashMap<V> mapping;
    private ArrayList<V> entries;

    public Alphabet() {
        mapping = new TObjectIntHashMap<V>();
        entries = new ArrayList<V>();
    }

    /**
     * Add a feature to the alphabet. As a result the mapped number is returned.
     *
     * @param feature The feature to add.
     * @return The number mapping to the added feature.
     */
    public int add(V feature) {
        if (!mapping.containsKey(feature)) {
            int index = entries.size();
            mapping.put(feature, index);
            entries.add(feature);
            return index;
        }
        return mapping.get(feature);
    }

    /**
     * Lookup a feature. Returns the mapped number of the feature if it is contained in the alphabet. Otherwise it returns
     * -1.
     *
     * @param feature The feature to look up.
     * @return The mapped number of the feature if contained in the alphabet or -1 instead.
     */
    public int lookupIndex(V feature) {
        Integer index = mapping.get(feature);
        if (index != null) {
            return index;
        }
        return -1;
    }

    /**
     * Get the feature mapped to number. If there is no feature mapped to the number <code>null</code> is returned.
     *
     * @param number The number of the feature to look up.
     * @return The feature if there exists a mapping to the feature, <code>null</code> otherwise.
     */
    public V lookupFeature(int number) {
        return entries.get(number);
    }

    /**
     * Return the size of the alphabet.
     *
     * @return The size of the alphabet.
     */
    public int size() {
        return entries.size();
    }

    /**
     * Tests wether the alphabet contains the feature.
     *
     * @param feature The value to test for existence in the alphabet.
     * @return True if the feature is contained in the alphabet, false otherwise.
     */
    public boolean containsFeature(V feature) {
        return mapping.containsKey(feature);
    }

    /**
     * Tests wether the alphabet contains the feature mapped to <code>number</code>.
     *
     * @param number The value to test for existence in the alphabet.
     * @return True if the number is mapped to a feature, false otherwise.
     */
    public boolean containsIndex(int number) {
        if (number >= entries.size()) {
            return false;
        }
        return entries.get(number) != null;
	}

	public Collection<V> getFeatures() {
		return entries;
	}
}
