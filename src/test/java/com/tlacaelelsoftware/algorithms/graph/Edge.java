package com.tlacaelelsoftware.algorithms.graph;

public class Edge {
    private final Node start;
    private final Node end;
    private final float cost;

    public Edge(Node start, Node end, float cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public float getCost() {
        return cost;
    }
}
