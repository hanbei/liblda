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
package de.hanbei.liblda;

import gnu.trove.TObjectIntHashMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An alphabet holds a mapping of features (e.g. words) to a number representing that class of a feature.
 *
 * @author Florian Schulz
 * @param <V> The type of feature to map. Must implement <code>hashCode()</code> and Serializable .
 */
public class Alphabet<V> implements Serializable {

    private static final long serialVersionUID = 6411827850590331667L;

    private TObjectIntHashMap<V> mapping;
    private List<V> entries;

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
     * Lookup a feature. Returns the mapped number of the feature if it is contained in the alphabet. Otherwise it
     * returns -1.
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
     * Tests whether the alphabet contains the feature.
     *
     * @param feature The value to test for existence in the alphabet.
     * @return True if the feature is contained in the alphabet, false otherwise.
     */
    public boolean containsFeature(V feature) {
        return mapping.containsKey(feature);
    }

    /**
     * Tests whether the alphabet contains the feature mapped to <code>number</code>.
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

    public void set(V feature, int index) {
        if (index == entries.size()) {
            entries.add(feature);
        } else {
            entries.set(index, feature);
        }
        mapping.put(feature, index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Alphabet)) {
            return false;
        }

        Alphabet alphabet = (Alphabet) o;

        if (!entries.equals(alphabet.entries)) {
            return false;
        }
        if (!mapping.equals(alphabet.mapping)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = mapping.hashCode();
        result = 31 * result + entries.hashCode();
        return result;
    }
}
