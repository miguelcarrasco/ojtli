package com.tlacaelelsoftware;

import java.util.List;

public interface HeuristicSearch<T> {
    float getHeuristicCostEstimate(T node);

    float getCost(T startNode, T endNode);

    List<T> getNeighbors(T node);
}
