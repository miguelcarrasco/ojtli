package com.tlacaelelsoftware.algorithms;

import java.util.Stack;

/**
 * A generic IDA* algorithm implementation
 * @param <T> The object that represents the "vertices" of the graph
 */
public class IDAstarSearch<T> implements PathSearch<T> {
    private Stack<T> pathNodes = new Stack<T>();
    private int nodesVisited = 0;
    private HeuristicSearch<T> heuristicSearch;
    private T goal;

    /**
     * Constructor that uses {@link HeuristicSearch} implementation for graph traversal.
     * @param heuristicSearch the {@link HeuristicSearch} implementation.
     */
    public IDAstarSearch(HeuristicSearch<T> heuristicSearch) {
        this.heuristicSearch = heuristicSearch;
    }

    /**
     * Perform a search for an optimal path from start to goal nodes using IDA* algorithm
     * @param start the start node
     * @param goal the goal node
     * @return a {@link ResultPath} containing an optimal path from start to goal nodes.
     */
    public ResultPath<T> searchPath(T start, T goal) {
        pathNodes.empty();
        nodesVisited = 0;
        this.goal = goal;
        pathNodes.push(start);

        float bound = heuristicSearch.getHeuristicCostEstimate(start,this.goal);

        while (true) {
            IDAstarSearchResult result = searchMinPath(0, bound);
            if (result.isGoalFound()) {
                return new ResultPath<T>(nodesVisited, pathNodes);
            }
            //TODO Return empty result path when there is no way to reach the goal.

            bound = result.getMinScore();
        }
    }

    /**
     * Recursive IDA* search implementation
     * @param gScore heuristic total cost estimate
     * @param bound maximum cost bound
     * @return An {@link IDAstarSearchResult} with a result of this search.
     */
    private IDAstarSearchResult searchMinPath(float gScore, float bound) {
        T currentNode = pathNodes.peek();
        nodesVisited++;
        float currentFScore = gScore + heuristicSearch.getHeuristicCostEstimate(currentNode,this.goal);

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
                    return result;
                }
                if (result.getMinScore() < minScore) {
                    minScore = result.getMinScore();
                }
                pathNodes.pop();
            }
        }
        return new IDAstarSearchResult(false, minScore);
    }
}

/**
 * A class that represent a result of an IDA* search.
 */
class IDAstarSearchResult {
    private boolean isGoalFound;
    private float minScore;

    IDAstarSearchResult(boolean isGoalFound, float minScore) {
        this.isGoalFound = isGoalFound;
        this.minScore = minScore;
    }

    /**
     * @return a boolean that indicates if the goal was found or not.
     */
    boolean isGoalFound() {
        return isGoalFound;
    }

    /**
     * @return the minimum score founded on the corresponding search (even if the goal was not found).
     */
    float getMinScore() {
        return minScore;
    }
}
