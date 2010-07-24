/**
 *
 */
package de.fhg.iais.kd.tm.liblda.datastructures;

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