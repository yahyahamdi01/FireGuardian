package uparis.diamanthamdi.backend.model.fire;

/**
 * Represents a fire in the grid.
 * 
 * The fire has an intensity that can be increased or decreased.
 */
public class Fire {
    /** The minimum intensity of the fire. */
    private static final int MIN_INTENSITY = 0;
    /** The maximum intensity of the fire. */
    private static final int MAX_INTENSITY = 9;
    /** The time needed to extinguish the fire with intensity 1. */
    private static final double EXTINGUISH_TIME = 0.5;

    /** The x-coordinate of the fire. */
    private int x;
    /** The y-coordinate of the fire. */
    private int y;
    /** The intensity of the fire. */
    private int intensity;

    /**
     * Creates a new fire with the given coordinates and intensity.
     *
     * @param x The x-coordinate of the fire.
     * @param y The y-coordinate of the fire.
     * @param intensity The intensity of the fire.
     */
    public Fire(int x, int y, int intensity) {
        if (intensity < MIN_INTENSITY || intensity > MAX_INTENSITY) {
            throw new IllegalArgumentException("Fire intensity must be between " + MIN_INTENSITY + " and " + MAX_INTENSITY);
        }

        this.x = x;
        this.y = y;
        this.intensity = intensity;
    }

    public double getExtinguishTime() {
        return this.intensity * EXTINGUISH_TIME;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getIntensity() {
        return this.intensity;
    }

    /**
     * Increases the intensity of the fire.
     */
    public void incrementIntensity() {
        if (this.intensity < MAX_INTENSITY) {
            this.intensity++;
        }
    }

    /**
     * Decreases the intensity of the fire.
     */
    public void decrementIntensity() {
        if (this.intensity > MIN_INTENSITY) {
            this.intensity--;
        }
    }

    public boolean isExtinguished() {
        return this.intensity == MIN_INTENSITY;
    }

    @Override
    public String toString() {
        return String.format("Fire at (%d, %d) with intensity %d", 
            this.x, this.y, this.intensity);
    }
}
