package uparis.diamanthamdi.backend.algo;

import java.util.List;

import uparis.diamanthamdi.backend.model.VehicleType;
import uparis.diamanthamdi.backend.model.cell.type.Cell;

/**
 * Represents a firefly in the firefly algorithm.
 * 
 * A firefly is a fire sequence that is evaluated based on the fitness function.
 */
public class Firefly {
    /** The sequence of cells to extinguish the fire. */
    private List<Cell> fireSequence;
    /** The fitness of the firefly. 
     * The fitness is computed based on multiple factors such as the travel distance, the extinguish time, the vehicle type, etc. 
     */
    private double fitness;
    /** The type of vehicle used to extinguish the fire. */
    private VehicleType vehicleType;

    /**
     * Creates a new firefly with the given fire sequence and vehicle type.
     *
     * @param fireSequence The sequence of cells to extinguish the fire.
     * @param vehicleType The type of vehicle used to extinguish the fire.
     */
    public Firefly(List<Cell> fireSequence, VehicleType vehicleType) {
        this.fireSequence = fireSequence;
        this.vehicleType = vehicleType; 
    }

    /**
     * Evaluates the fitness of the firefly.
     */
    public void evaluateFitness() {
        // If the vehicle type is not set, throw an exception
        if (vehicleType == null) {
            throw new IllegalStateException("Vehicle type is not set for this firefly.");
        }
        double totalTravelDistance = calculateTravelDistance();
        double totalExtinguishTime = calculateExtinguishTime();
        double speedFactor = vehicleType.getSpeed();
        double tankFactor = vehicleType.getTankCapacity();
        double efficiencyFactor = vehicleType.getExtinguishEfficiency();
        double terrainAdaptabilityFactor = vehicleType.getTerrainAdaptability();

       //penalite pour les vehicules avec une capacite de reservoir inferieure a 3
        double extinguishPenalty = (tankFactor < 3) ? 2.0 : 1.0;

        fitness = 1 / ((totalTravelDistance / speedFactor) + (totalExtinguishTime * extinguishPenalty / efficiencyFactor) + terrainAdaptabilityFactor + 0.01);
    }

    /**
     * Calculates the total travel distance of the firefly.
     *
     * @return The total travel distance of the firefly.
     */
    private double calculateTravelDistance() {
        double totalTravelDistance = 0;
        for (int i = 0; i < fireSequence.size() - 1; i++) {
            Cell current = fireSequence.get(i);
            Cell next = fireSequence.get(i + 1);
            totalTravelDistance += current.distanceTo(next);
        }
        return totalTravelDistance;
    }

    /**
     * Calculates the total extinguish time of the firefly.
     *
     * @return The total extinguish time of the firefly.
     */
    private double calculateExtinguishTime() {
        double totalExtinguishTime = 0;
        for (Cell cell : fireSequence) {
            totalExtinguishTime += cell.getExtinguishTime(vehicleType.getTankCapacity());
        }
        return totalExtinguishTime;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setSequence(List<Cell> fireSequence) {
        this.fireSequence = fireSequence;
    }

    public List<Cell> getFireSequence() {
        return fireSequence;
    }

    public double getFitness() {
        return fitness;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
