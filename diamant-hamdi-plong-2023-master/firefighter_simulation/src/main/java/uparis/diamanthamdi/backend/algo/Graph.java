package uparis.diamanthamdi.backend.algo;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a graph.
 * 
 * A graph is a collection of nodes and connections between them.
 * 
 * @param <T> The type of the nodes in the graph.
 */
public class Graph<T extends GraphNode> {
    /** The nodes in the graph. */
    private final Set<T> nodes;
    /** The connections between the nodes. */
    private final Map<String, Set<String>> connections;

    /**
     * Creates a new graph with the given nodes and connections.
     *
     * @param nodes The nodes in the graph.
     * @param connections The connections between the nodes.
     */
    public Graph(Set<T> nodes, Map<String, Set<String>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    /**
     * Gets the node with the given id.
     *
     * @param id The id of the node.
     * @return The node with the given id.
     * @throws IllegalArgumentException If the node is not found.
     */
    public T getNode(String id) {
        return nodes.stream()
            .filter(node -> node.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Node not found"));
    }

    /**
     * Gets the connections of the given node.
     *
     * @param node The node.
     * @return The connections of the node.
     */
    public Set<T> getConnections(T node) {
        return connections.get(node.getId()).stream()
            .map(this::getNode)
            .collect(Collectors.toSet());
    }

    
    public Set<T> getNodes() {
        return nodes;
    }

    public Map<String, Set<String>> getConnections() {
        return connections;
    }
}
