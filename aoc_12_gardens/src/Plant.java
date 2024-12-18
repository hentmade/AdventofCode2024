public class Plant {
    private Character type;
    private Vector position;
    private boolean plantareaAssigned;
    private boolean[] fences;
    private boolean hasFenceNorth;
    private boolean hasFenceEast;
    private boolean hasFenceSouth;
    private boolean hasFenceWest;

    public Plant(Character type, Vector position){
        this.type = type;
        this.position = position;
        setPlantareaAssigned(false);
        fences = new boolean[4];
        fences[0] = false; //north
        fences[1] = false; //east
        fences[2] = false; //south
        fences[3] = false; //west
        setHasFenceNorth(false);
        setHasFenceEast(false);
        setHasFenceSouth(false);
        setHasFenceWest(false);
    }

    public Character getType() {
        return type;
    }

    public Vector getPosition() {
        return position;
    }

    public boolean hasPlantarea() {
        return plantareaAssigned;
    }

    public boolean hasSameType(Plant plant){
        return (this.type == plant.getType());
    }

    public int getFenceCount() {
        int fenceCount = 0;
        for(boolean b : fences){
            if(b) fenceCount++;
        }
        return fenceCount;
    }

    public boolean hasFenceNorth() {
        return hasFenceNorth;
    }

    public boolean hasFenceEast() {
        return hasFenceEast;
    }

    public boolean hasFenceSouth() {
        return hasFenceSouth;
    }

    public boolean hasFenceWest() {
        return hasFenceWest;
    }

    public void setHasFenceNorth(boolean hasFenceNorth) {
        this.hasFenceNorth = hasFenceNorth;
        fences[0] = hasFenceNorth;
    }

    public void setHasFenceEast(boolean hasFenceEast) {
        this.hasFenceEast = hasFenceEast;
        fences[1] = hasFenceEast;
    }

    public void setHasFenceSouth(boolean hasFenceSouth) {
        this.hasFenceSouth = hasFenceSouth;
        fences[2] = hasFenceSouth;
    }

    public void setHasFenceWest(boolean hasFenceWest) {
        this.hasFenceWest = hasFenceWest;
        fences[3] = hasFenceWest;
    }

    public void setPlantareaAssigned(boolean plantareaAssigned) {
        this.plantareaAssigned = plantareaAssigned;
    }
}
