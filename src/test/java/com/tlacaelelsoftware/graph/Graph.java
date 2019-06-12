package com.tlacaelelsoftware.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<Node>> neighbors = new HashMap<Node, List<Node>>();
    private Map<String, Float> costMap = new HashMap<String, Float>();

    public void addEdges(List<Edge> edges) {
        setNeighbors(edges);
        setCostMap(edges);
    }

    private void setCostMap(List<Edge> edges) {
        for (Edge edge : edges) {
            costMap.put(getCostMapHashKey(edge.getStart(), edge.getEnd()), edge.getCost());
        }
    }

    private static String getCostMapHashKey(Node start, Node end) {
        return start.getName() + end.getName();
    }

    private void setNeighbors(List<Edge> edges) {
        for (Edge edge : edges) {
            List<Node> currentNeighborsList = neighbors.get(edge.getStart());
            if (currentNeighborsList == null) {
                currentNeighborsList = new ArrayList<Node>();
                neighbors.put(edge.getStart(), currentNeighborsList);
            }
            currentNeighborsList.add(edge.getEnd());
        }
    }

    float getCost(Node startNode, Node endNode) {
        return costMap.get(getCostMapHashKey(startNode, endNode));
    }

    List<Node> getNeighbors(Node node) {
        List<Node> nodeNeighbors = neighbors.get(node);
        if(nodeNeighbors==null){
            return new ArrayList<Node>();
        }
        return nodeNeighbors;
    }
}
