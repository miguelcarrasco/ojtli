package com.tlacaelelsoftware;

import java.util.Stack;

public class IDAstarSearch<T> implements PathSearch<T> {
    private Stack<T> pathNodes = new Stack<T>();
    private int nodesVisited = 0;
    private HeuristicSearch<T> heuristicSearch;
    private T goal;

    public IDAstarSearch(HeuristicSearch<T> heuristicSearch) {
        this.heuristicSearch = heuristicSearch;
    }

    public ResultPath<T> searchPath(T start, T goal) {
        pathNodes.empty();
        nodesVisited = 0;
        this.goal = goal;
        pathNodes.push(start);

        float bound = heuristicSearch.getHeuristicCostEstimate(start);

        while (true) {
            IDAstarSearchResult result = searchMinPath(0, bound);
            if (result.isGoalFound()) {

                return new ResultPath<T>(nodesVisited, pathNodes);
            }
            //TODO falta el caso en que no encuentre nada

            bound = result.getMinScore();
        }
    }

    private IDAstarSearchResult searchMinPath(float gScore, float bound) {
//        T currentNode = pathNodes.pop();
        T currentNode = pathNodes.peek();
        nodesVisited++;
        float currentFScore = gScore + heuristicSearch.getHeuristicCostEstimate(currentNode);

        if (currentFScore > bound) {
            return new IDAstarSearchResult(false, currentFScore);
        }
        if (currentNode.equals(goal)) {
            return new IDAstarSearchResult(true, currentFScore);
        }

        float minScore = Float.MAX_VALUE;

        for (T currentNeighbor : heuristicSearch.getNeighbors(currentNode)) {

            if (!pathNodes.contains(currentNeighbor)) {
                pathNodes.push(currentNeighbor);
                IDAstarSearchResult result = searchMinPath(
                        gScore + heuristicSearch.getCost(currentNode, currentNeighbor), bound);
                if (result.isGoalFound()) {
                    // en el javascript aquí se creaba otro objeto, a lo mejor pasar simplemente
                    // el result es una optimización.
                    return result;
                }

                if (result.getMinScore() < minScore) {
                    minScore = result.getMinScore();
                }

                pathNodes.pop();
            }
        }

        // TODO cambiar este return a tirar una exception de que no se encontró (revisar flujo)
        return new IDAstarSearchResult(false, minScore);
    }
}

class IDAstarSearchResult {
    private boolean isGoalFound;
    private float minScore;

    IDAstarSearchResult(boolean isGoalFound, float minScore) {
        this.isGoalFound = isGoalFound;
        this.minScore = minScore;
    }

    boolean isGoalFound() {
        return isGoalFound;
    }

    float getMinScore() {
        return minScore;
    }
}
