package com.tlacaelelsoftware.algorithms;

import java.util.List;

/**
 * Heurisitic Search interface used by several algorithms like
 * A* and IDA*.
 * @param <T>
 */
public interface HeuristicSearch<T> {
    /**
     * Calculate the heuristic cost from "node" to "goal".
     * @param node a node.
     * @param goal the goal node.
     * @return heuristic cost estimate.
     */
    float getHeuristicCostEstimate(T node, T goal);

    /**
     * Get the real cost of a node and a successor (neighbor).
     * @param startNode a start node.
     * @param endNode the end node.
     * @return real cost.
     */
    float getCost(T startNode, T endNode);

    /**
     * Get the neighbors (successors) elements of the specified node.
     * @param node the node from witch neighbors will be generated.
     * @return A list that contains the neighbors of the specified node.
     */
    List<T> getNeighbors(T node);
}
