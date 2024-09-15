package uparis.diamanthamdi.backend.model.firestation;

import java.util.ArrayList;
import java.util.List;

import uparis.diamanthamdi.backend.model.firefighter.Firefighter;

/**
 * Represents a firestation.
 */
public class Firestation {
    /** The capacity of the firefighter. */
    private static final int FIREFIGHTER_CAPACITY = 5;
    /** The capacity of the water tank in liters. */
    private static final int WATER_CAPACITY = 1000;

    /** The x-coordinate of the firestation. */
    private final int x;
    /** The y-coordinate of the firestation. */
    private final int y;

    /** The firefighters of the firestation. */
    private ArrayList<Firefighter> firefighters;
    /** The water in the tank of the firestation. */
    private int water;

    /**
     * Creates a new firestation with the given coordinates.
     *
     * @param x The x-coordinate of the firestation.
     * @param y The y-coordinate of the firestation.
     */
    public Firestation(final int x, final int y) {
        this.x = x;
        this.y = y;

        this.firefighters = new ArrayList<>(FIREFIGHTER_CAPACITY);
        this.water = WATER_CAPACITY;

        generateFirefighters();
    }

    /**
     * Generates the firefighters of the firestation.
     */
    private void generateFirefighters() {
        for (int i = 0; i < FIREFIGHTER_CAPACITY; i++) {
            firefighters.add(new Firefighter(this.x, this.y));
        }
    }

    /**
     * Refills the water tank of the firestation.
     */
    public void refillWater() {
        this.water = WATER_CAPACITY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Firestation firestation = (Firestation) obj;

        return this.x == firestation.x && this.y == firestation.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public List<Firefighter> getFirefighters() {
        return this.firefighters;
    }

    public int getWater() {
        return this.water;
    }
}
