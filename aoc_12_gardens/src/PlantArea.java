import java.util.ArrayList;

public class PlantArea {
    private ArrayList<Plant> plants;

    public PlantArea(){
        this.plants = new ArrayList<>();
    }

    public void addPlant(Plant plant){
        plants.add(plant);
    }

    public int getArea(){
        return plants.size();
    }

    public int getPerimeter(){
        int fenceCount = 0;
        for(Plant plant : plants){
            fenceCount += plant.getFenceCount();
        }
        return fenceCount;
    }

    //TODO: remove
    public Plant getFirstPlant(){
        return plants.getFirst();
    }
}
