package io.github.miguelcarrasco.ojtli;

/**
 * A simple interface for path searching in a graph
 *
 * @param <T> is the type of the object that represent a node in the graph.
 */
public interface PathSearch<T> {
    /**
     * Search a path on a graph from start to goal nodes.
     *
     * @param start the start node.
     * @param goal  the goal node.
     * @return the desired path.
     */
    ResultPath<T> searchPath(T start, T goal);
}
