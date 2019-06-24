package com.tlacaelelsoftware.algorithms;

import com.tlacaelelsoftware.algorithms.graph.Edge;
import com.tlacaelelsoftware.algorithms.graph.Graph;
import com.tlacaelelsoftware.algorithms.graph.GraphSearch;
import com.tlacaelelsoftware.algorithms.graph.Node;
import com.tlacaelelsoftware.algorithms.npuzzle.Npuzzle;
import com.tlacaelelsoftware.algorithms.npuzzle.NpuzzleSearch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IDAstarSearchTest {

    @Test
    void testHeuristicSearchOnGraphs() {

        Node nodeA = new Node("a", 8f);
        Node nodeB = new Node("b", 7.5f);
        Node nodeC = new Node("c", 6.2f);
        Node nodeD = new Node("d", 4.7f);
        Node nodeE = new Node("e", 5.4f);
        Node nodeF = new Node("f", 5.3f);
        Node nodeG = new Node("g", 6.5f);
        Node nodeH = new Node("h", 4.6f);
        Node nodeI = new Node("i", 5.5f);
        Node nodeJ = new Node("j", 3f);
        Node nodeK = new Node("k", 3.2f);
        Node nodeL = new Node("l", 1.5f);
        Node nodeM = new Node("m", 2.6f);
        Node nodeN = new Node("n", 0f);

        Graph graph = new Graph();
        graph.addEdges(Arrays.asList(
                new Edge(nodeA, nodeB, 1.5f),
                new Edge(nodeB, nodeC, 1f),
                new Edge(nodeB, nodeG, 3.4f),
                new Edge(nodeC, nodeF, 2.6f),
                new Edge(nodeC, nodeE, 1f),
                new Edge(nodeC, nodeD, 2.9f),
                new Edge(nodeD, nodeH, 1.6f),
                new Edge(nodeD, nodeG, 1.8f),
                new Edge(nodeE, nodeM, 3.5f),
                new Edge(nodeF, nodeJ, 4.5f),
                new Edge(nodeG, nodeD, 1.8f),
                new Edge(nodeG, nodeH, 2.5f),
                new Edge(nodeH, nodeI, 1f),
                new Edge(nodeH, nodeJ, 1.6f),
                new Edge(nodeI, nodeK, 3f),
                new Edge(nodeJ, nodeK, 2f),
                new Edge(nodeK, nodeL, 3.5f),
                new Edge(nodeL, nodeN, 1.5f)
        ));

        PathSearch<Node> pathSearch = new IDAstarSearch<Node>(new GraphSearch(graph));
        ResultPath<Node> resultPath = pathSearch.searchPath(nodeA, nodeN);

        assertEquals(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeH, nodeJ, nodeK, nodeL, nodeN), resultPath.getPath());
    }

    @Test
    void testNpuzzleSearch() {
        Npuzzle start = new Npuzzle(3, new int[]{
                2, 3, 5,
                1, 4, 0,
                7, 8, 6,
        });

        Npuzzle goal = new Npuzzle(3, new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0
        });

        PathSearch<Npuzzle> pathSearch = new IDAstarSearch<Npuzzle>(new NpuzzleSearch());
        ResultPath<Npuzzle> resultPath = pathSearch.searchPath(start, goal);
        assertEquals(8, resultPath.getPath().size());
        assertEquals(Arrays.asList(
                new Npuzzle(3, new int[]{
                        2, 3, 5,
                        1, 4, 0,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        2, 3, 0,
                        1, 4, 5,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        2, 0, 3,
                        1, 4, 5,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        0, 2, 3,
                        1, 4, 5,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        1, 2, 3,
                        0, 4, 5,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        1, 2, 3,
                        4, 0, 5,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        1, 2, 3,
                        4, 5, 0,
                        7, 8, 6,
                }),
                new Npuzzle(3, new int[]{
                        1, 2, 3,
                        4, 5, 6,
                        7, 8, 0,
                })
        ), resultPath.getPath());
    }
}