package uparis.diamanthamdi.backend.model.firefighter;

/**
 * Represents a firefighter.
 */
public class Firefighter {
    /** The speed of the firefighter in m/s. */
    private static final double VEHICLE_SPEED = 1.0; // m/s
    /** The capacity of the tank in liters. */
    private static final int TANK_CAPACITY = 100; // liters

    /** The x-coordinate of the firefighter. */
    private int x;
    /** The y-coordinate of the firefighter. */
    private int y;

    /** The speed of the firefighter in m/s. */
    private final double speed;
    /** The capacity of the tank in liters. */
    private final int tankCapacity;

    /**
     * Creates a new firefighter with the given coordinates.
     *
     * @param x The x-coordinate of the firefighter.
     * @param y The y-coordinate of the firefighter.
     */
    public Firefighter(final int x, final int y) {
        this.x = x;
        this.y = y;

        this.speed = VEHICLE_SPEED;
        this.tankCapacity = TANK_CAPACITY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getTankCapacity() {
        return this.tankCapacity;
    }
}
