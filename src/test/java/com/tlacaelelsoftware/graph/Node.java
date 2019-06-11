package com.tlacaelelsoftware.graph;

public class Node {
    private String name;
    private float goalHeuristicCost;

    public Node(String name, float goalHeuristicCost) {
        this.name = name;
        this.goalHeuristicCost = goalHeuristicCost;
    }

    public String getName() {
        return name;
    }

    public float getGoalHeuristicCost() {
        return goalHeuristicCost;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
