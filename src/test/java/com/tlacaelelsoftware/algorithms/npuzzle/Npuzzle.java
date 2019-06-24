package com.tlacaelelsoftware.algorithms.npuzzle;

import com.tlacaelelsoftware.algorithms.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Npuzzle {
    private final int dimension;
    private final int board[];


    public Npuzzle(int dimension, int[] board) {
        this.dimension = dimension;
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public int[] getBoard() {
        return board;
    }

    public List<Npuzzle> getNeighbors() {
        int zeroIndex = Utils.indexOfIntArray(board, 0);
        int zeroX = zeroIndex % dimension;
        int zeroY = (int) Math.floor((double) zeroIndex / (double) dimension);

        List<Npuzzle> neighbors = new ArrayList<Npuzzle>();

        if (zeroY - 1 >= 0) {
            neighbors.add(new Npuzzle(dimension, getNewNeighbor(zeroIndex, zeroX, zeroY - 1)));
        }

        if (zeroX - 1 >= 0) {
            neighbors.add(new Npuzzle(dimension, getNewNeighbor(zeroIndex, zeroX - 1, zeroY)));
        }

        if (zeroX + 1 < dimension) {
            neighbors.add(new Npuzzle(dimension, getNewNeighbor(zeroIndex, zeroX + 1, zeroY)));
        }

        if (zeroY + 1 < dimension) {
            neighbors.add(new Npuzzle(dimension, getNewNeighbor(zeroIndex, zeroX, zeroY + 1)));
        }

        return neighbors;
    }

    private int[] getNewNeighbor(int zeroIndex, int elementX, int elementY) {
        int[] boardCopy = board.clone();
        int elementIndex = elementY * dimension + elementX;
        Utils.swap(boardCopy, zeroIndex, elementIndex);
        return boardCopy;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Npuzzle other = (Npuzzle) obj;
        return Arrays.equals(board, other.board);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                stringBuilder.append(board[y * dimension + x]).append(", ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
