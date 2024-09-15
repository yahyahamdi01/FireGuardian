package uparis.diamanthamdi.backend.algo;

import uparis.diamanthamdi.backend.model.VehicleType;
import uparis.diamanthamdi.backend.model.cell.type.Cell;

/**
 * Scorer for the cells. Used to compute the cost of moving from one cell to another.
 */
public class CellScorer implements Scorer<Cell> {
    /** The vehicle type for which the cost is computed. */
    private final VehicleType vehicleType;

    /**
     * Creates a new cell scorer for the given vehicle type.
     *
     * @param vehicleType The vehicle type for which the cost is computed.
     */
    public CellScorer(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public double computeCost(Cell from, Cell to) {
        return from.distanceTo(to);
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
