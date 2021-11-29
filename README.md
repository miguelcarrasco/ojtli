# Ojtli

Ojtli is a minimal and generic Java graph traversal and path finding algorithms library. It does not depend on another
library which makes it very lightweight.

"Ojtli" is a [nahuatl](https://en.wikipedia.org/w/index.php?title=Nahuatl&oldid=1056837457) world that means "path".

Current version implements the following path search algorithms:

- **[Iterative deepening A* (IDA*)](https://en.wikipedia.org/w/index.php?title=Iterative_deepening_A*&oldid=1032176059)**:
  The algorithm described in the ["Depth-First Iterative-Deepening: An Optimal Admissible Tree Search*"]() paper by
  Richard E. Korf for the _[Artifitial Ingelligence](https://www.journals.elsevier.com/artificial-intelligence)_
  Journal. doi:[10.1016/0004-3702(85)90084-0](https://doi.org/10.1016%2F0004-3702%2885%2990084-0)

## Usage

1. You need to create a class that implements the HeuristicSearch interface:

```java
/**
 * Heurisitic Search interface used by several algorithms like
 * A* and IDA*.
 *
 * @param <T>
 */
public interface HeuristicSearch<T> {
    /**
     * Calculate the heuristic cost from "node" to "goal".
     *
     * @param node a node.
     * @param goal the goal node.
     * @return heuristic cost estimate.
     */
    float getHeuristicCostEstimate(T node, T goal);

    /**
     * Get the real cost of a node and a successor (neighbor).
     *
     * @param startNode a start node.
     * @param endNode   the end node.
     * @return real cost.
     */
    float getCost(T startNode, T endNode);

    /**
     * Get the neighbors (successors) elements of the specified node.
     *
     * @param node the node from witch neighbors will be generated.
     * @return A list that contains the neighbors of the specified node.
     */
    List<T> getNeighbors(T node);
}
```

Note that this interface use a [generic](https://docs.oracle.com/javase/tutorial/extra/generics/index.html)
parameter. The generic parameter allows you to use a class that represent a node in your graph.

For instance, suppose that you created a **Node** class in your code to represent a node on a graph, then a
**GraphSearch** class can be created to implement the **HeuristicSearch** interface.
**GraphSearch** must implement the interface methods which must do the following:

* **getHeuristicCostEstimate**(**Node** node, **Node** goal): this method must return a _float_ that represents the
  estimated cost to get from the specified node (as the first param) to the goal node (as the second param) usually
  implemented using an admissible heuristic. An admissible heuristic is a computed estimate that must be less or equal
  than the real cost of going from the specified node to the goal node in the graph. It's important to ensure that the
  heuristic is an admissible one, if the heuristic is not admissible, that is, if the estimation value is greater than
  the real optimal cost, the path search algorithm does not guarantee that it will find an optimal path.
* **getCost**(**Node** startNode, **Node** endNode): this method must return a _float_ that represent the real cost from
  the startNode and the endNode, where startNode and endNode are neighbors nodes in the graph.
* **getNeighbors**(**Node** node): this method must return a list of the neighbors nodes for the specified node.

2. Once you created an implementation of the **HeuristicSearch** interface for the specified node class, you can
   calculate the optimal path creating an instance of a **PathSearch** object implementing a specific algorithm. For the
   previous example if we want to use the IDA* algorithm to find an optimal path using our custom **GraphSearch** class
   that implements the **HeuristicSearch** interface, we can do it via:

```java
// GraphSearch implements the HeuristicSearch interface
GraphSearch graphSearch = new GraphSearch();
        
// Create a PathSearch object specifing an implementation of the IDA* algorithm 
// for a GraphSearch object
PathSearch<Node> pathSearch = new IDAstarSearch<Node>(graphSearch);

// Find an optimal path from the specified node to the goal node as a ResultPath object
ResultPath<Node> resultPath = pathSearch.searchPath(node, goal);

// Get the optimal path from the node to the goal as a List
List<Node> optimalPath = resultPath.getPath();

// Gets the number of visited nodes in the corresponding path search.
int nodesVisited = resultPath.getNodesVisited()
```

In order to check if the goal has been reached, the path search algorithms use the _equals_ method for the specified
object that represent a node (**Node** instances in the previous example).
