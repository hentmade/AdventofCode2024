import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModelPart1 {
    private long result;
    private ArrayList<Plant> plants;
    private ArrayList<PlantArea> plantareas;
    private Vector mapSize;
    private ArrayList<Vector> directions;


    public ModelPart1(){
        result = 0;
        plants = new ArrayList<>();
        plantareas = new ArrayList<>();

        directions = new ArrayList<>();
        directions.add(new Vector(+1,0));
        directions.add(new Vector(0,+1));
        directions.add(new Vector(-1,0));
        directions.add(new Vector(0,-1));
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int mapWidth = 0;
            int lineCount;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                mapWidth = line.length();
                for(int x = 0; x < line.length(); ++x){
                    char c = line.charAt(x);
                    plants.add(new Plant(c, new Vector(x, lineCount)));
                }
            }
            mapSize = new Vector(mapWidth, lineCount);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void examinePlants() {
        for(Plant plant : plants){
            if(plant.getPosition().equals(new Vector(6,6))){
                int i = 0;
            }
            if(!plant.hasPlantarea()){
                examineNeighbors(plant, null);
            }
        }
    }

    private void examineNeighbors(Plant plant, PlantArea plantarea) {
        if(plantarea == null){
            plantarea = new PlantArea();
            plantareas.add(plantarea);
            plantarea.addPlant(plant);
            plant.setPlantareaAssigned(true);
        }

        for(Vector dir : directions) {
            Vector neighborPos = plant.getPosition().add(dir);
            if(isInsideMap(neighborPos)) {
                Plant neighbor = plants.stream().filter(obj -> obj.getPosition().equals(neighborPos)).findFirst().orElse(null);
                if(plant.hasSameType(neighbor)){
                    if(!neighbor.hasPlantarea()){
                        neighbor.setPlantareaAssigned(true);
                        plantarea.addPlant(neighbor);
                        examineNeighbors(neighbor, plantarea);
                    }
                }else { //neighbor is other type
                    if(dir.equals(directions.get(0)) && !plant.hasFenceEast()) plant.setHasFenceEast(true);
                    if(dir.equals(directions.get(1)) && !plant.hasFenceSouth()) plant.setHasFenceSouth(true);
                    if(dir.equals(directions.get(2)) && !plant.hasFenceWest()) plant.setHasFenceWest(true);
                    if(dir.equals(directions.get(3)) && !plant.hasFenceNorth()) plant.setHasFenceNorth(true);
                }
            }else{ //neighbor is not inside Map
                if(dir.equals(directions.get(0)) && !plant.hasFenceEast()) plant.setHasFenceEast(true);
                if(dir.equals(directions.get(1)) && !plant.hasFenceSouth()) plant.setHasFenceSouth(true);
                if(dir.equals(directions.get(2)) && !plant.hasFenceWest()) plant.setHasFenceWest(true);
                if(dir.equals(directions.get(3)) && !plant.hasFenceNorth()) plant.setHasFenceNorth(true);
            }
        }
    }

    public void calculateResult(){
        for(PlantArea plantarea : plantareas){
            result += ((long) plantarea.getArea() * plantarea.getPerimeter());
        }
    }

    private boolean isInsideMap(Vector pos){
        return (pos.getX() >= 0 && pos.getX() < mapSize.getX() && pos.getY() >= 0 && pos.getY() < mapSize.getY());
    }

    public long getResult(){
        return result;
    }
}
