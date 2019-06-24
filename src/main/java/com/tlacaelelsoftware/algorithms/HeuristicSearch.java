package com.tlacaelelsoftware.algorithms;

import java.util.List;

public interface HeuristicSearch<T> {
    float getHeuristicCostEstimate(T node, T goal);

    float getCost(T startNode, T endNode);

    List<T> getNeighbors(T node);
}