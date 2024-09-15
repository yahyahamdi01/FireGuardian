package uparis.diamanthamdi.backend.algo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import uparis.diamanthamdi.backend.model.VehicleType;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.backend.model.TerrainCosts;

public class FireflyAlgorithm {
    /** The absorption coefficient. */
    private static final double ABSORTIONCOEF = 0.1;
    /** The maximum number of stable iterations. */
    private static final double MAX_STABLE_ITERATIONS = 10;

    /** The maximum number of iterations. The algorithm will stop after this number of iterations. */
    private final int maxIterations;
    /** The population of fireflies. */
    private List<Firefly> population;
    /** The cells that are on fire. */
    private List<Cell> fires;

    /**
     * Creates a new firefly algorithm with the given maximum number of iterations.
     *
     * @param maxIterations The maximum number of iterations.
     */
    public FireflyAlgorithm(int maxIterations) {
        this.maxIterations = maxIterations;
        this.population = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Runs the firefly algorithm on the given grid.
     * 
     * The algorithm will stop if the number of stable iterations is greater than the maximum number of stable iterations
     * For each iteration, the algorithm will evaluate the fitness of the fireflies, update the fireflies, and get the best firefly
     * If the best firefly has a better fitness than the current best fitness, the best fitness and the best firefly are updated
     * If the number of stable iterations is greater than the maximum number of stable iterations, the population is reinitialized
     * The algorithm returns the best firefly.
     *
     * @param grid The grid where the fire is.
     * @return The best firefly.
     */
    public Firefly run(Grid grid) {
        double bestFitness = Double.NEGATIVE_INFINITY;
        Firefly bestFirefly = null;
        int stableIterations = 0;

        fires = grid.getCellOnFire();

        initializePopulation(grid);
        
        for (int i = 0; i < maxIterations; i++) {
            evaluateFitness();

            updateFireflies();

            Firefly currentBest = getBestFirefly();
            if (currentBest.getFitness() > bestFitness) {
                bestFitness = currentBest.getFitness();
                bestFirefly = currentBest;
                stableIterations = 0;
            } else {
                stableIterations++;
            }

            if (stableIterations >= MAX_STABLE_ITERATIONS) {
                initializePopulation(grid);
                stableIterations = 0;
            }
        }

        return bestFirefly;
    }

    /**
     * Initializes the population of fireflies.
     * 
     * A population is a random set of fire sequences with a random vehicle type.
     *
     * @param grid The grid where the fire is.
     */
    private void initializePopulation(Grid grid) {
        List<Firefly> newPopulation = fires.parallelStream()
            .map(fire -> {
                List<Cell> fireSequence = new ArrayList<>(fires);
                VehicleType vehicleType = chooseVehicleType(fireSequence);
                return new Firefly(fireSequence, vehicleType);
            })
            .collect(Collectors.toList());
        population.addAll(newPopulation);
    }

    /**
     * Chooses the vehicle type that has the lowest cost to extinguish the fire.
     * 
     * The cost is computed based on multiple factors such as the travel distance, the extinguish time, the vehicle type, etc.
     * The cost is computed for each vehicle type and the vehicle type with the lowest cost is chosen.
     *
     * @param fireSequence The sequence of cells to extinguish the fire.
     * @return The vehicle type that has the lowest cost to extinguish the fire.
     */
    private VehicleType chooseVehicleType(List<Cell> fireSequence) {
        double minCost = Double.POSITIVE_INFINITY;
        VehicleType chosenType = null;
    
        for (VehicleType vehicleType : VehicleType.values()) {
            double totalTravelDistance = 0;
            double totalExtinguishTime = 0;
            double speedFactor = vehicleType.getSpeed();
            double tankFactor = vehicleType.getTankCapacity();
            double efficiencyFactor = vehicleType.getExtinguishEfficiency();
            double terrainAdaptabilityFactor = vehicleType.getTerrainAdaptability();
            double terrainCost = 0;
            double extinguishPenalty = (tankFactor < 3) ? 2.0 : 1.0;

            for (Cell cell : fireSequence) {
                terrainCost += TerrainCosts.getCost(vehicleType, cell);
                totalExtinguishTime += cell.getExtinguishTime(vehicleType.getTankCapacity());
            }

            double totalCost = terrainCost + (totalTravelDistance / speedFactor) + (totalExtinguishTime * extinguishPenalty / efficiencyFactor) + terrainAdaptabilityFactor;
            if (totalCost < minCost) {
                minCost = totalCost;
                chosenType = vehicleType;
            }
        }

        return chosenType;
    }

    /**
     * Evaluates the fitness of the fireflies.
     */
    private void evaluateFitness() {
        population.parallelStream().forEach(Firefly::evaluateFitness);
    }
    
    /**
     * Updates the fireflies.
     * 
     * For each firefly, the algorithm will calculate the attractiveness of the other fireflies
     * If the other firefly has a better fitness, the firefly will move towards the other firefly
     * The new firefly sequence is stored in a map
     * The new firefly sequences are updated in the population
     */
    private void updateFireflies() {
        ConcurrentMap<Firefly, Firefly> newFireflySequences = new ConcurrentHashMap<>();
        population.parallelStream().forEach(firefly -> {
            population.parallelStream().forEach(other -> {
                if (firefly == other) return;
                double attractiveness = calculateAttractiveness(firefly, other);
                if (other.getFitness() > firefly.getFitness()) {
                    List<Cell> newSequence = moveTowardsFirefly(firefly, attractiveness);
                    newFireflySequences.put(firefly, new Firefly(newSequence, firefly.getVehicleType()));
                }
            });
        });

        newFireflySequences.forEach((oldFirefly, newFirefly) -> {
            oldFirefly.setSequence(newFirefly.getFireSequence());
            oldFirefly.setFitness(newFirefly.getFitness());
        });
    }
    
    /**
     * Moves the firefly towards another firefly.
     * 
     * The firefly will swap two cells in its sequence with a probability based on the attractiveness
     * The new sequence is returned.
     *
     * @param firefly The firefly.
     * @param attractiveness The attractiveness of the other firefly.
     * @return The new sequence of the firefly.
     */
    private List<Cell> moveTowardsFirefly(Firefly firefly, double attractiveness) {
        List<Cell> newSequence = new ArrayList<>(firefly.getFireSequence());
        if (attractiveness > ThreadLocalRandom.current().nextDouble()) {
            int i1 = ThreadLocalRandom.current().nextInt(newSequence.size());
            int i2 = ThreadLocalRandom.current().nextInt(newSequence.size());
            Collections.swap(newSequence, i1, i2);
        }
        return newSequence;
    }

    /**
     * Calculates the attractiveness of a firefly towards another firefly.  
     */
    private double calculateAttractiveness(Firefly firefly, Firefly other) {
        double distance = calculateDistance(firefly, other);
        return Math.exp(-ABSORTIONCOEF * distance);
    }

    /**
     * Calculates the distance between two fireflies.
     * 
     * @return The distance between the two fireflies.
     */
    private double calculateDistance(Firefly f1, Firefly f2) {
        return Math.abs(f1.getFitness() - f2.getFitness());
    }

    /**
     * Gets the best firefly from the population.
     * 
     * @return The best firefly from the population.
     */
    private Firefly getBestFirefly() {
        return Collections.max(population, (f1, f2) -> Double.compare(f1.getFitness(), f2.getFitness()));
    }
}
