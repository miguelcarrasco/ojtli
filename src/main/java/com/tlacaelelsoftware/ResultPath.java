package com.tlacaelelsoftware;

import java.util.List;

public class ResultPath<T> {
    private int nodesVisited;
    private List<T> path;

    public ResultPath(int nodesVisited, List<T> path) {
        this.nodesVisited = nodesVisited;
        this.path = path;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public List<T> getPath() {
        return path;
    }
}
