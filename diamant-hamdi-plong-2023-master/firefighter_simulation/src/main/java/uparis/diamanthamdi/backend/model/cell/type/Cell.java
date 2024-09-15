package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.algo.GraphNode;
import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.fire.Fire;
import uparis.diamanthamdi.backend.utils.MathUtils;

/**
 * Represents a cell in the grid.
 */
public abstract class Cell implements GraphNode {
    /** The id of a cell. */
    private final String id;
    /** The x-coordinate of the cell. */
    private final int x;
    /** The y-coordinate of the cell. */
    private final int y;

    /** The type of the cell. */
    private Celltype type;
    /** The fire on the cell. */
    private Fire fire;

    /**
     * Creates a new cell with the given coordinates.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
    protected Cell(final int x, final int y) {
        this.x = x;
        this.y = y;

        this.id = String.format("%d_%d", x, y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Checks if the cell is adjacent to another cell.
     *
     * @param other The other cell.
     * @return True if the cell is adjacent to the other cell, false otherwise.
     */
    public boolean isAdjacentTo(final Cell other) {
        return MathUtils.distance1D(this.x, other.x) <= 1 
            && MathUtils.distance1D(this.y, other.y) <= 1;
    }

    /**
     * Gets the time needed to extinguish the fire on the cell.
     *
     * @param vehicleCapacity The capacity of the vehicle.
     * @return The time needed to extinguish the fire on the cell.
     */
    public double getExtinguishTime(int vehicleCapacity) {
        if (this.fire == null) {
            return 0;
        }
    
        int intensity = this.fire.getIntensity();
        int roundsNeeded = (int) Math.ceil((double) intensity / vehicleCapacity);
    
  
        double penalty = (vehicleCapacity < 3) ? 2.0 : 1.0; 
        return roundsNeeded * this.fire.getExtinguishTime() * penalty;
    }
    

    public void setFire(int intensity) {
        this.fire = new Fire(this.x, this.y, intensity);
    }

    public void extinguish() {
        this.fire = null;
    }

    public boolean isOnFire() {
        return this.fire != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Cell)) {
            return false;
        }

        Cell cell = (Cell) obj;
        return this.x == cell.x && this.y == cell.y;
    }

    /**
     * Calculates the distance to another cell.
     *
     * @param other The other cell.
     * @return The distance to the other cell.
     */
    public double distanceTo(final Cell other) {
        return MathUtils.distance(this.x, this.y, other.x, other.y);
    }

    @Override
    public int hashCode() {
        return this.x * 31 + this.y;
    }

    public Celltype getCellType() {
        return this.type;
    }

    public Fire getFire() {
        return this.fire;
    }

    protected void setType(final Celltype type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return this.id;
    }
    public Celltype getTerrainType() {
        return type;
    }

    public void setTerrainType(Celltype terrainType) {
        this.type = terrainType;
    }

}
