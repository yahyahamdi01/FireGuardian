package uparis.diamanthamdi.backend.algo;

/**
 * Represents a node in a route.
 *
 * @param <T> The type of the node.
 */
public class RouteNode<T extends GraphNode> implements Comparable<RouteNode<T>> {
    /** The current node. */
    private final T current;
    /** The previous node. */
    private T previous;
    /** The score of the route to this node. */
    private double routeScore;
    /** The estimated score of the route to the end node. */
    private double estimatedScore;

    /**
     * Creates a new route node with the given current node.
     *
     * @param current The current node.
     */
    public RouteNode(T current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Creates a new route node with the given current node, previous node, route score and estimated score.
     *
     * @param current The current node.
     * @param previous The previous node.
     * @param routeScore The score of the route to this node.
     * @param estimatedScore The estimated score of the route to the end node.
     */
    public RouteNode(T current, T previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int hashCode() {
        return current.hashCode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        RouteNode<T> other = (RouteNode<T>) obj;
        return current.equals(other.current);
    }

    @Override
    public int compareTo(RouteNode<T> other) {
        if (this.estimatedScore > other.estimatedScore) {
            return 1;
        } else if (this.estimatedScore < other.estimatedScore) {
            return -1;
        } else {
            return 0;
        }
    }

    public T getCurrent() {
        return current;
    }

    public T getPrevious() {
        return previous;
    }

    public void setPrevious(T previous) {
        this.previous = previous;
    }

    public double getRouteScore() {
        return routeScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    public void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    public void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }
}
