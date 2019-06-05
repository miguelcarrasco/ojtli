package com.tlacaelelsoftware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public abstract class IDAstarSearch<T> implements HeuristicSearch<T> {
    private HashMap<String, T> nodesMap = new HashMap<String, T>();
    private T start;
    private T goal;
    private String startIndex;
    private String goalIndex;
    private Stack<String> pathIndexes = new Stack<String>();
    private int nodesVisited = 0;

    public ResultPath<T> searchPath(T start, T goal) {
        this.start = start;
        this.goal = goal;
        this.startIndex = getNodeIndex(start);
        this.goalIndex = getNodeIndex(goal);

        pathIndexes.empty();
        nodesMap.clear();
        nodesVisited = 0;

        pathIndexes.push(startIndex);
        nodesMap.put(startIndex, start);
        nodesMap.put(goalIndex, goal);

        float bound = getHeuristicCostEstimate(start);

        while (true) {
            IDAstarSearchResult result = searchMinPath(0, bound);
            if (result.isGoalFound()) {
                return getResultPath(pathIndexes);
            }
            //TODO falta el caso en que no encuentre nada

            bound = result.getMinScore();
        }
    }

    private ResultPath<T> getResultPath(Stack<String> pathIndexes) {
        List<T> path = new ArrayList<T>();
        for (String currentNodeIndex : pathIndexes) {
            path.add(nodesMap.get(currentNodeIndex));
        }
        return new ResultPath<T>(nodesVisited, path);
    }

    private IDAstarSearchResult searchMinPath(float gScore, float bound) {
        String currentNodeIndex = pathIndexes.pop();
        nodesVisited++;
        float currentFScore = gScore + getHeuristicCostEstimate(nodesMap.get(currentNodeIndex));

        if (currentFScore > bound) {
            return new IDAstarSearchResult(false, currentFScore);
        }
        if (currentNodeIndex.equals(getNodeIndex(goal))) {
            return new IDAstarSearchResult(true, currentFScore);
        }

        float minScore = Float.MAX_VALUE;

        for (T currentNeighbor : getNeighbors(nodesMap.get(currentNodeIndex))) {
            String currentNeighborIndex = getNodeIndex(currentNeighbor);
            nodesMap.put(currentNeighborIndex, currentNeighbor);

            if (!pathIndexes.contains(currentNeighborIndex)) {
                pathIndexes.push(currentNeighborIndex);
                IDAstarSearchResult result = searchMinPath(
                        gScore + getCost(nodesMap.get(currentNodeIndex), currentNeighbor), bound);
                if (result.isGoalFound()) {
                    // en el javascript aquí se creaba otro objeto, a lo mejor pasar simplemente
                    // el result es una optimización.
                    return result;
                }

                if (result.getMinScore() < minScore) {
                    minScore = result.getMinScore();
                }

                pathIndexes.pop();
            }
        }

        // TODO cambiar este return a tirar una exception de que no se encontró (revisar flujo)
        return new IDAstarSearchResult(false, minScore);
    }
}

class IDAstarSearchResult {
    private boolean isGoalFound;
    private float minScore;

    public IDAstarSearchResult(boolean isGoalFound, float minScore) {
        this.isGoalFound = isGoalFound;
        this.minScore = minScore;
    }

    public boolean isGoalFound() {
        return isGoalFound;
    }

    public float getMinScore() {
        return minScore;
    }
}
