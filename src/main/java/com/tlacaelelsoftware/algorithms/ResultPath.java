package com.tlacaelelsoftware.algorithms;

import java.util.List;

/**
 * A class that contains the resulting path of a path search.
 *
 * @param <T> the type of objects that represent a node in the searched graph.
 */
public class ResultPath<T> {
    private int nodesVisited;
    private List<T> path;

    /**
     * @param nodesVisited number of visited nodes.
     * @param path         a list containing the path founded.
     */
    public ResultPath(int nodesVisited, List<T> path) {
        this.nodesVisited = nodesVisited;
        this.path = path;
    }

    /**
     * @return gets the number of visited nodes in the corresponding path search.
     */
    public int getNodesVisited() {
        return nodesVisited;
    }

    /**
     * @return a list that represent the path founded in the corresponding path search.
     */
    public List<T> getPath() {
        return path;
    }
}
