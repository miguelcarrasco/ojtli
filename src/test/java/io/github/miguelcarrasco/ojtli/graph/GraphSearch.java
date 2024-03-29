package io.github.miguelcarrasco.ojtli.graph;

import io.github.miguelcarrasco.ojtli.HeuristicSearch;

import java.util.List;

public class GraphSearch implements HeuristicSearch<Node> {

    private Graph graph;

    public GraphSearch(Graph graph) {
        this.graph = graph;
    }

    public float getHeuristicCostEstimate(Node node, Node goal) {
        return node.getGoalHeuristicCost();
    }

    public float getCost(Node startNode, Node endNode) {
        return graph.getCost(startNode, endNode);
    }

    public List<Node> getNeighbors(Node node) {
        return graph.getNeighbors(node);
    }
}
