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
package de.hanbei.liblda.datastructures;

import java.io.Serializable;

public class DocumentData implements Serializable {

    private static final long serialVersionUID = 2059356896140763515L;

    private int id;
    private int[] tokens;
    private int[] topicForToken;

    /**
     * Construct new empty document data.
     *
     * @param size The number of tokens of the document.
     */
    public DocumentData(int size) {
        tokens = new int[size];
        topicForToken = new int[size];
    }

    public int[] getTokens() {
        return tokens;
    }

    public void setTokenAtWordIndex(int index, int tokenIndex) {
        tokens[index] = tokenIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return tokens.length;
    }

    public int getTokenAtIndex(int index) {
        return tokens[index];
    }

    public int getTopicForTokenAtIndex(int index) {
        return topicForToken[index];
    }

    public void setTopicForTokenAtIndex(int index, int topic) {
        topicForToken[index] = topic;
    }

    public int[] getTopicsForTokens() {
        return topicForToken;
	}
}