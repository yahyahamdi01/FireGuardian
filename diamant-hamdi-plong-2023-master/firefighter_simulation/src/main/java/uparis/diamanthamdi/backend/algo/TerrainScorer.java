package uparis.diamanthamdi.backend.algo;

import uparis.diamanthamdi.backend.model.TerrainCosts;
import uparis.diamanthamdi.backend.model.VehicleType;
import uparis.diamanthamdi.backend.model.cell.type.Cell;

/** Scorer for a terrain */
public class TerrainScorer implements Scorer<Cell> {
    /** The vehicle type for which the cost is computed. */
    private final VehicleType vehicleType;

    /**
     * Creates a new terrain scorer for the given vehicle type.
     *
     * @param vehicleType The vehicle type for which the cost is computed.
     */
    public TerrainScorer(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Computes the cost of moving from one cell to another.
     *
     * @param from The cell to move from.
     * @param to The cell to move to.
     * @return The cost of moving from one cell to another.
     */
    @Override
    public double computeCost(Cell from, Cell to) {
        double terrainCost = TerrainCosts.getCost(vehicleType, to);
        double distance = from.distanceTo(to);
        return terrainCost * distance; 
    }
}
