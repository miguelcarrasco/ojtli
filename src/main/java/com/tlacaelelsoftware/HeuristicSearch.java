package com.tlacaelelsoftware;

import java.util.List;

public interface HeuristicSearch<T> {
    float getHeuristicCostEstimate(T node);

    float getCost(T startNode, T endNode);

    String getNodeIndex(T node);

    List<T> getNeighbors(T node);
}
