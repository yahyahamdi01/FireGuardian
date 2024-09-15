package uparis.diamanthamdi.backend.model;




import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.grid.Grid;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Contains the costs of traversing different terrains for different vehicles.
 */
public class TerrainCosts {
    /**
     * The costs of traversing different terrains for different vehicles.
     */
    private static final Map<VehicleType, Map<Celltype, Double>> vehicleTerrainCosts = new EnumMap<>(VehicleType.class);

    private TerrainCosts() {}

    /**
     * Initializes the costs of traversing different terrains for different vehicles.
     */
    static {
        initTruckCosts();
        initFourByFourCosts();
        initPlaneCosts();
        initBoatCosts();
    }

    /**
     * Initializes the costs of traversing different terrains for trucks.
     */
    private static void initTruckCosts() {
        Map<Celltype, Double> truckCosts = new EnumMap<>(Celltype.class);
        truckCosts.put(Celltype.URBAN, 1.0);
        truckCosts.put(Celltype.GRASSLAND, 3.0);
        truckCosts.put(Celltype.WATER, Double.POSITIVE_INFINITY);
        truckCosts.put(Celltype.MANGROVE, 5.0);
        truckCosts.put(Celltype.TREE, 4.0);
        truckCosts.put(Celltype.CROPLAND, 3.5);
        truckCosts.put(Celltype.BAREVEGETATION, 2.0); 
        truckCosts.put(Celltype.SHRUBLAND, 2.5); 
        truckCosts.put(Celltype.SNOW, 4.5); 
        truckCosts.put(Celltype.WETLAND, 4.0); 
        truckCosts.put(Celltype.MOSS, 4.0); 
        vehicleTerrainCosts.put(VehicleType.TRUCK, truckCosts);
    }

    /**
     * Initializes the costs of traversing different terrains for four by fours.
     */
    private static void initFourByFourCosts() {
        Map<Celltype, Double> costs = new EnumMap<>(Celltype.class);
        costs.put(Celltype.URBAN, 1.0);
        costs.put(Celltype.GRASSLAND, 1.2);
        costs.put(Celltype.WATER, Double.POSITIVE_INFINITY);
        costs.put(Celltype.MANGROVE, 2.0);
        costs.put(Celltype.TREE, 1.5);
        costs.put(Celltype.CROPLAND, 1.0);
        costs.put(Celltype.BAREVEGETATION, 1.2); 
        costs.put(Celltype.SHRUBLAND, 1.3); 
        costs.put(Celltype.SNOW, 2.0); 
        costs.put(Celltype.WETLAND, 1.5); 
        costs.put(Celltype.MOSS, 1.5);
        vehicleTerrainCosts.put(VehicleType.FOUR_BY_FOUR, costs);
    }

    /**
     * Initializes the costs of traversing different terrains for planes.
     */
    private static void initPlaneCosts() {
        Map<Celltype, Double> costs = new EnumMap<>(Celltype.class);
        costs.put(Celltype.URBAN, 1.0);
        costs.put(Celltype.GRASSLAND, 1.0);
        costs.put(Celltype.WATER, 1.0);
        costs.put(Celltype.MANGROVE, 1.0);
        costs.put(Celltype.TREE, 1.0);
        costs.put(Celltype.CROPLAND, 1.0);
        costs.put(Celltype.BAREVEGETATION, 1.0); 
        costs.put(Celltype.SHRUBLAND, 1.0); 
        costs.put(Celltype.SNOW, 1.0);
        costs.put(Celltype.WETLAND, 1.0); 
        costs.put(Celltype.MOSS, 1.0); 
        vehicleTerrainCosts.put(VehicleType.PLANE, costs);
    }

    /**
     * Initializes the costs of traversing different terrains for boats.
     */
    private static void initBoatCosts() {
        Map<Celltype, Double> costs = new EnumMap<>(Celltype.class);
        costs.put(Celltype.URBAN, Double.POSITIVE_INFINITY);
        costs.put(Celltype.GRASSLAND, Double.POSITIVE_INFINITY);
        costs.put(Celltype.WATER, 1.0);
        costs.put(Celltype.MANGROVE, Double.POSITIVE_INFINITY);
        costs.put(Celltype.TREE, Double.POSITIVE_INFINITY);
        costs.put(Celltype.CROPLAND, Double.POSITIVE_INFINITY);
        costs.put(Celltype.BAREVEGETATION, Double.POSITIVE_INFINITY); 
        costs.put(Celltype.SHRUBLAND, Double.POSITIVE_INFINITY); 
        costs.put(Celltype.SNOW, Double.POSITIVE_INFINITY); 
        costs.put(Celltype.WETLAND, Double.POSITIVE_INFINITY); 
        costs.put(Celltype.MOSS, Double.POSITIVE_INFINITY); 
        vehicleTerrainCosts.put(VehicleType.BOAT, costs);
    }

    /**
     * Calculates the total costs of traversing a sequence of cells for each vehicle type.
     *
     * @param grid The grid.
     * @param fireSequence The sequence of cells.
     * 
     * @return The total costs of traversing a sequence of cells for each vehicle type.
     */
    public static Map<VehicleType, Double> calculateTotalCosts(Grid grid, List<Cell> fireSequence) {
        Map<VehicleType, Double> totalCosts = new EnumMap<>(VehicleType.class);
        for (VehicleType vehicleType : VehicleType.values()) {
            double totalCost = 0;
            for (Cell cell : fireSequence) {
                totalCost += getCost(vehicleType, cell);
            }
            totalCosts.put(vehicleType, totalCost);
        }
        return totalCosts;
    }

    /**
     * Returns the cost of traversing a cell for a vehicle type.
     *
     * @param vehicleType The vehicle type.
     * @param cell The cell.
     * 
     * @return The cost of traversing a cell for a vehicle type.
     */
    public static double getCost(VehicleType vehicleType, Cell cell) {
        Celltype cellType = cell.getCellType();
        if (cellType == null) {
            System.err.println("Cell type not specified for cell: " + cell.getId());
            return Double.POSITIVE_INFINITY;
        }

        Map<Celltype, Double> costs = vehicleTerrainCosts.get(vehicleType);
        if (costs == null || !costs.containsKey(cellType)) {
            
            System.err.println("Invalid or missing terrain type for vehicle: " + vehicleType + " on cell: " + cell.getId());
            return Double.POSITIVE_INFINITY;
        }
        return costs.get(cellType);
    }
}
