package uparis.diamanthamdi.backend.model;

public enum VehicleType {
    TRUCK(1, 5, true, false, false, 1.0, 1.5),  
    FOUR_BY_FOUR(3, 3, true, true, false, 1.2, 1.2),  
    PLANE(5, 1, false, false, true, 0.8, 2.0),  
    BOAT(2, 4, false, false, false, 1.5, 1.8);  

    private final double speed; // units/second
    private final int tankCapacity; 
    private final boolean canTravelOnPavedRoad; 
    private final boolean canTravelOnUnpavedRoad;
    private final boolean canFly; 
    private final double extinguishEfficiency; 
    private final double terrainAdaptability; 

    VehicleType(double speed, int tankCapacity, boolean canTravelOnPavedRoad, boolean canTravelOnUnpavedRoad, boolean canFly, double extinguishEfficiency, double terrainAdaptability) {
        this.speed = speed;
        this.tankCapacity = tankCapacity;
        this.canTravelOnPavedRoad = canTravelOnPavedRoad;
        this.canTravelOnUnpavedRoad = canTravelOnUnpavedRoad;
        this.canFly = canFly;
        this.extinguishEfficiency = extinguishEfficiency;
        this.terrainAdaptability = terrainAdaptability;
    }

    public double getSpeed() {
        return speed;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public boolean canTravelOnPavedRoad() {
        return canTravelOnPavedRoad;
    }

    public boolean canTravelOnUnpavedRoad() {
        return canTravelOnUnpavedRoad;
    }

    public boolean canFly() {
        return canFly;
    }

    public double getExtinguishEfficiency() {
        return extinguishEfficiency;
    }

    public double getTerrainAdaptability() {
        return terrainAdaptability;
    }
}
