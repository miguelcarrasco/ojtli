package com.tlacaelelsoftware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class IDAstarSearch<T> implements PathSearch<T>{
    private HashMap<String, T> nodesMap = new HashMap<String, T>();
    private String goalIndex;
    private Stack<String> pathIndexes = new Stack<String>();
    private int nodesVisited = 0;
    private HeuristicSearch<T> heuristicSearch;

    public IDAstarSearch(HeuristicSearch<T> heuristicSearch) {
        this.heuristicSearch = heuristicSearch;
    }

    public ResultPath<T> searchPath(T start, T goal) {
        String startIndex = heuristicSearch.getNodeIndex(start);
        this.goalIndex = heuristicSearch.getNodeIndex(goal);

        emptyFields();

        pathIndexes.push(startIndex);
        nodesMap.put(startIndex, start);
        nodesMap.put(goalIndex, goal);

        float bound = heuristicSearch.getHeuristicCostEstimate(start);

        while (true) {
            IDAstarSearchResult result = searchMinPath(0, bound);
            if (result.isGoalFound()) {
                return getResultPath(pathIndexes);
            }
            //TODO falta el caso en que no encuentre nada

            bound = result.getMinScore();
        }
    }

    private void emptyFields() {
        pathIndexes.empty();
        nodesMap.clear();
        nodesVisited = 0;
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
        float currentFScore = gScore + heuristicSearch.getHeuristicCostEstimate(nodesMap.get(currentNodeIndex));

        if (currentFScore > bound) {
            return new IDAstarSearchResult(false, currentFScore);
        }
        if (currentNodeIndex.equals(goalIndex)) {
            return new IDAstarSearchResult(true, currentFScore);
        }

        float minScore = Float.MAX_VALUE;

        for (T currentNeighbor : heuristicSearch.getNeighbors(nodesMap.get(currentNodeIndex))) {
            String currentNeighborIndex = heuristicSearch.getNodeIndex(currentNeighbor);
            nodesMap.put(currentNeighborIndex, currentNeighbor);

            if (!pathIndexes.contains(currentNeighborIndex)) {
                pathIndexes.push(currentNeighborIndex);
                IDAstarSearchResult result = searchMinPath(
                        gScore + heuristicSearch.getCost(
                                nodesMap.get(currentNodeIndex), currentNeighbor), bound);
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
