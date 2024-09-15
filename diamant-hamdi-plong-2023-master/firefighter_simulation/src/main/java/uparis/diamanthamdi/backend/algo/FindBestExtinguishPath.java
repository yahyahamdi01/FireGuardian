package uparis.diamanthamdi.backend.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

import uparis.diamanthamdi.backend.model.VehicleType;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.fire.generator.FireGenerator;
import uparis.diamanthamdi.backend.model.grid.Grid;

/**
 * Class to find the best path to extinguish the fire using the firefly algorithm and the A* algorithm.
 */
public class FindBestExtinguishPath {
    private static final Logger logger = Logger.getLogger(FindBestExtinguishPath.class.getName());

    /** The maximum number of iteration */
    private static final int NUMBER_OF_ITERATION = 100;

    /** Grid where the fire is */
    private Grid grid;
    /** The cell where the firestation is */
    private Cell firestation;

    /**
     * Creates a new find best extinguish path with the given grid.
     *
     * @param grid The grid where the fire is.
     */
    public FindBestExtinguishPath(Grid grid) {
        this.grid = grid;
    }

    /**
     * Runs the algorithm to find the best path to extinguish the fire.
     *
     * @return The best path to extinguish the fire.
     */
    public List<Cell> run() {
        generateFire();

        Firefly bestFirefly = runFireflyAlgorithm();

        return findBestPath(bestFirefly);
    }

    /**
     * Finds the best path to extinguish the fire using the given firefly.
     *
     * @param firefly The best firefly.
     * @return The best path to extinguish the fire.
     */
    private List<Cell> findBestPath(Firefly firefly) {
        firestation = findNearestFirestation(firefly);

        Graph<Cell> graph = generateGraph();

        List<Cell> route = findRoute(graph, firefly);

        if (!route.isEmpty()) {
            logger.info("Route found");
            route.forEach(cell -> logger.info("Cell at (" + cell.getX() + ", " + cell.getY() + ")"));
        } else {
            logger.info("No route found");
        }

        return route;
    }

    /**
     * Create the route to extinguish the fire from the firestation to the fires and back to the firestation.
     * 
     * @param graph The graph of the grid.
     * @param firefly The best firefly.
     * 
     * @return The route to extinguish the fire.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<Cell> findRoute(Graph<Cell> graph, Firefly firefly) {
        List<Cell> route = new ArrayList<>();

        List<Cell> fireSequence = firefly.getFireSequence();

        Map<VehicleType, CellScorer> cellScorers = generateCellScorer();
        Map<VehicleType, TerrainScorer> terrainScorers = generateTerrainScorer();
        RouteFinder<Cell> finder = new RouteFinder(graph, cellScorers, terrainScorers);

        // Add the sub routes to the route
        if (!addSubRoute(route, () -> findRouteBetweenFirestationAndFirstFire(finder, fireSequence))
        || !addSubRoute(route, () -> findRouteBetweenFires(finder, fireSequence))
        || !addSubRoute(route, () -> findRouteBetweenLastFireAndFirestation(finder, fireSequence))) {
            return Collections.emptyList();
        }

        return route;
    }

    /**
     * Adds a sub route to the route.
     * 
     * @param route The route to which the sub route is added.
     * @param findSubRoute The function to find the sub route.
     * 
     * @return True if the sub route was added, false otherwise.
     */
    private boolean addSubRoute(List<Cell> route, Supplier<List<Cell>> findSubRoute) {
        List<Cell> subRoute = findSubRoute.get();
        if (!isRouteValid(subRoute)) {
            return false;
        }
        route.addAll(subRoute);
        return true;
    }

    /**
     * Finds the route between the firestation and the first fire.
     * 
     * @param finder The route finder.
     * @param fireSequence The sequence of fires.
     * 
     * @return The route between the firestation and the first fire.
     */
    private List<Cell> findRouteBetweenFirestationAndFirstFire(RouteFinder<Cell> finder, List<Cell> fireSequence) {
        return findRouteFromTo(finder, firestation, fireSequence.get(0));
    }

    /**
     * Finds the route between the fires.
     * 
     * @param finder The route finder.
     * @param fireSequence The sequence of fires.
     * 
     * @return The route between the fires.
     */
    private List<Cell> findRouteBetweenFires(RouteFinder<Cell> finder, List<Cell> fireSequence) {
        List<Cell> route = new ArrayList<>();

        for (int i = 0; i < fireSequence.size() - 1; i++) {
            List<Cell> subRoute = findRouteFromTo(finder, fireSequence.get(i), fireSequence.get(i + 1));
            if (!isRouteValid(subRoute)) {
                return Collections.emptyList();
            }
            route.addAll(subRoute);
        }

        return route;
    }

    /**
     * Finds the route between the last fire and the firestation.
     * 
     * @param finder The route finder.
     * @param fireSequence The sequence of fires.
     * 
     * @return The route between the last fire and the firestation.
     */
    private List<Cell> findRouteBetweenLastFireAndFirestation(RouteFinder<Cell> finder, List<Cell> fireSequence) {
        return findRouteFromTo(finder, fireSequence.get(fireSequence.size() - 1), firestation);
    }

    /**
     * Finds the route between the start and the end.
     * 
     * @param finder The route finder.
     * @param start The start cell.
     * @param end The end cell.
     * 
     * @return The route between the start and the end.
     */
    private List<Cell> findRouteFromTo(RouteFinder<Cell> finder, Cell start, Cell end) {
        List<Cell> route = finder.findRoute(start, end, VehicleType.TRUCK);

        if (route == null) {
            logger.info("No route found");
            return Collections.emptyList();
        }

        return route;
    }

    /**
     * Checks if the route is valid. A route is valid if it is not empty.
     * 
     * @param route The route to check.
     * 
     * @return True if the route is valid, false otherwise.
     */
    private boolean isRouteValid(List<Cell> route) {
        return !route.isEmpty();
    }

    /**
     * Generates the graph of the grid.
     * 
     * @return The graph of the grid.
     */
    private Graph<Cell> generateGraph() {
        GraphGenerator generator = new GraphGenerator(grid);
        return generator.generate();
    }

    /**
     * Finds the nearest firestation from the firefly.
     * 
     * @param firefly The firefly.
     * 
     * @return The nearest firestation from the firefly.
     */
    private Cell findNearestFirestation(Firefly firefly) {
        List<Cell> fireSequence = firefly.getFireSequence();
        
        int size = fireSequence.size();
        Cell firstFire = fireSequence.get(size - 1);

        return grid.getNearestFirestation(firstFire);
    }

    /**
     * Runs the firefly algorithm to find the best firefly.
     * 
     * @return The best firefly.
     */
    private Firefly runFireflyAlgorithm() {
        FireflyAlgorithm fireflyAlgorithm = new FireflyAlgorithm(
            NUMBER_OF_ITERATION
        );
        return fireflyAlgorithm.run(grid);
    }

    /**
     * Generates the fire on the grid.
     */
    private void generateFire() {
        FireGenerator fireGenerator = new FireGenerator(grid);
        fireGenerator.generateFire();
    }

    /**
     * Generates the cell scorers for the different vehicle types.
     * 
     * @return The cell scorers for the different vehicle types.
     */
    private static Map<VehicleType, CellScorer> generateCellScorer() {
        Map<VehicleType, CellScorer> scorers = new EnumMap<>(VehicleType.class);
        for (VehicleType vehicleType : VehicleType.values()) {
            scorers.put(vehicleType, new CellScorer(vehicleType));
        }
        return scorers;
    }

    /**
     * Generates the terrain scorers for the different vehicle types.
     * 
     * @return The terrain scorers for the different vehicle types.
     */
    private static Map<VehicleType, TerrainScorer> generateTerrainScorer() {
        Map<VehicleType, TerrainScorer> scorers = new EnumMap<>(VehicleType.class);
        for (VehicleType vehicleType : VehicleType.values()) {
            scorers.put(vehicleType, new TerrainScorer(vehicleType));
        }
        return scorers;
    }
}
