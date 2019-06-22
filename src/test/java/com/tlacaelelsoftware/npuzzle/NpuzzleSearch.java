package com.tlacaelelsoftware.npuzzle;

import com.tlacaelelsoftware.HeuristicSearch;
import com.tlacaelelsoftware.Utils;

import java.util.List;

public class NpuzzleSearch implements HeuristicSearch<Npuzzle> {

    public float getHeuristicCostEstimate(Npuzzle node, Npuzzle goal) {
        return manhattanHeuristic(node, goal, node.getDimension());
    }

    private static float manhattanHeuristic(Npuzzle node, Npuzzle goal, int dimension) {
        int[] nodeBoard = node.getBoard();
        int[] goalBoard = goal.getBoard();

        int length = nodeBoard.length;
        float manhattanSum = 0;
        for (int i = 0; i < length; i++) {
            int currentValue = nodeBoard[i];
            if (currentValue != 0) {
                int finalPosition = Utils.indexOfIntArray(goalBoard, currentValue);
                manhattanSum += manhattanDistance(i % dimension, (int) Math.floor((float) i / dimension),
                        finalPosition % dimension, (int) Math.floor((float) finalPosition / dimension));
            }
        }
        return manhattanSum;
    }

    private static int manhattanDistance(int originX, int originY, int finalX, int finalY) {
        return Math.abs(originX - finalX) + Math.abs(originY - finalY);
    }

    public float getCost(Npuzzle startNode, Npuzzle endNode) {
        return 1f;
    }

    public List<Npuzzle> getNeighbors(Npuzzle node) {
        return node.getNeighbors();
    }
}
