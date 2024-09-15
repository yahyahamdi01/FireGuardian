package uparis.diamanthamdi.backend.algo;

/**
 * Scorer for the nodes. Used to compute the cost of moving from one node to another.
 *
 * @param <T> The type of the nodes.
 */
public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}
