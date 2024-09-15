package uparis.diamanthamdi.backend.algo;

import uparis.diamanthamdi.backend.model.VehicleType;

import java.util.*;

/**
 * Class to find the best route between two nodes in a graph.
 *
 * The route is found using the A* algorithm.
 *
 * @param <T> The type of the nodes in the graph.
 */
public class RouteFinder<T extends GraphNode> {
    /** The graph to find the route in. */
    private final Graph<T> graph;
    /** The scorers for the cells. */
    private final Map<VehicleType, Scorer<T>> cellScorers;
    /** The scorers for the terrain. */
    private final Map<VehicleType, Scorer<T>> terrainScorers;

    /**
     * Creates a new route finder with the given graph and scorers.
     *
     * @param graph The graph to find the route in.
     * @param cellScorers The scorers for the cells.
     * @param terrainScorers The scorers for the terrain.
     */
    public RouteFinder(Graph<T> graph, Map<VehicleType, Scorer<T>> cellScorers, Map<VehicleType, Scorer<T>> terrainScorers) {
        this.graph = graph;
        this.cellScorers = cellScorers;
        this.terrainScorers = terrainScorers;
    }

    /**
     * Finds the best route between the two given nodes.
     *
     * @param from The node to start from.
     * @param to The node to go to.
     * @param vehicleType The vehicle type to use.
     * @return The best route between the two nodes.
     */
    public List<T> findRoute(T from, T to, VehicleType vehicleType) {
        Queue<RouteNode<T>> openSet = new PriorityQueue<>(RouteNode::compareTo);
        Map<T, RouteNode<T>> allNodes = new HashMap<>();

        setInitialNode(from, to, vehicleType, openSet, allNodes);

        while (!openSet.isEmpty()) {
            RouteNode<T> next = openSet.poll();
            if (next.getCurrent().equals(to)) {
                return buildRoute(next, allNodes);
            }

            heuristicCalculation(to, vehicleType, openSet, next, allNodes);
        }

        return Collections.emptyList();
    }

    /**
     * Calculates the heuristic for the given node.
     *
     * @param to The node to go to.
     * @param vehicleType The vehicle type to use.
     * @param openSet The open set.
     * @param next The node to calculate the heuristic for.
     * @param allNodes All nodes.
     */
    private void heuristicCalculation(T to, VehicleType vehicleType, Queue<RouteNode<T>> openSet, RouteNode<T> next, Map<T, RouteNode<T>> allNodes) {
        graph.getConnections(next.getCurrent()).forEach(connection -> {
            RouteNode<T> nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
            allNodes.put(connection, nextNode);

            Scorer<T> cellScorer = cellScorers.get(vehicleType);
            Scorer<T> terrainScorer = terrainScorers.get(vehicleType);

            double newScore = next.getRouteScore() + cellScorer.computeCost(next.getCurrent(), connection);
            if (newScore < nextNode.getRouteScore()) {
                nextNode.setPrevious(next.getCurrent());
                nextNode.setRouteScore(newScore);
                nextNode.setEstimatedScore(newScore + terrainScorer.computeCost(connection, to));
                openSet.add(nextNode);
            }
        });
    }

    /**
     * Builds the route from the given node.
     *
     * @param next The node to start from.
     * @param allNodes All nodes.
     * @return The route from the given node.
     */
    private List<T> buildRoute(RouteNode<T> next, Map<T, RouteNode<T>> allNodes) {
        List<T> route = new ArrayList<>();
        RouteNode<T> current = next;
        do {
            route.add(0, current.getCurrent());
            current = allNodes.get(current.getPrevious());
        } while (current != null);

        return route;
    }

    /**
     * Sets the initial node.
     *
     * @param from The node to start from.
     * @param to The node to go to.
     * @param vehicleType The vehicle type to use.
     * @param openSet The open set.
     * @param allNodes All nodes.
     */
    private void setInitialNode(T from, T to, VehicleType vehicleType, Queue<RouteNode<T>> openSet, Map<T, RouteNode<T>> allNodes) {
        Scorer<T> terrainScorer = terrainScorers.get(vehicleType);
        RouteNode<T> start = new RouteNode<>(
                from,
                null,
                0,
                terrainScorer.computeCost(from, to));
        openSet.add(start);
        allNodes.put(from, start);
    }
}
