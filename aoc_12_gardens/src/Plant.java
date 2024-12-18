public class Plant {
    private Character type;
    private Vector position;
    private boolean plantareaAssigned;
    private boolean[] fences;

    public Plant(Character type, Vector position){
        this.type = type;
        this.position = position;
        setPlantareaAssigned(false);
        fences = new boolean[4];
        fences[0] = false; //north
        fences[1] = false; //east
        fences[2] = false; //south
        fences[3] = false; //west
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
        return fences[0];
    }

    public boolean hasFenceEast() {
        return fences[1];
    }

    public boolean hasFenceSouth() {
        return fences[2];
    }

    public boolean hasFenceWest() {
        return fences[3];
    }

    public void setHasFenceNorth(boolean hasFenceNorth) {
        fences[0] = hasFenceNorth;
    }

    public void setHasFenceEast(boolean hasFenceEast) {
        fences[1] = hasFenceEast;
    }

    public void setHasFenceSouth(boolean hasFenceSouth) {
        fences[2] = hasFenceSouth;
    }

    public void setHasFenceWest(boolean hasFenceWest) {
        fences[3] = hasFenceWest;
    }

    public void setPlantareaAssigned(boolean plantareaAssigned) {
        this.plantareaAssigned = plantareaAssigned;
    }
}
