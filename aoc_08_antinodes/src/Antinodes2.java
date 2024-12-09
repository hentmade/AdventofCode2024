import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Antinodes2 {
    private ArrayList<Field> map;
    private Vector mapSize;
    private ArrayList<Vector> antinodes;
    private int result;

    public Antinodes2(){
        map = new ArrayList<>();
        mapSize = null;
        antinodes = new ArrayList<>();
        result = 0;
    }


    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int mapWidth = 0;
            String line;
            int lineCount;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                mapWidth = line.length();
                for(int i = 0; i < line.length(); ++i){
                    char c = line.charAt(i);
                    if(c != '.'){
                        map.add(new Field(c, new Vector(i, lineCount)));
                    }
                }
            }
            reader.close();
            mapSize = new Vector(mapWidth, lineCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void countAntinodes(){
        for (Field field : map) {
            ArrayList<Vector> otherAntennas = detectOtherAntennas(field);
            for(Vector pos : otherAntennas) {
                int xDistance = (pos.getX() - field.getPosition().getX());
                int yDistance = (pos.getY() - field.getPosition().getY());
                Vector distance = new Vector(xDistance, yDistance);
                Vector antinode = field.getPosition().add(distance);
                while (isInsideMap(antinode)) {
                    if (!antinodes.contains(antinode)) {
                        antinodes.add(antinode);
                    }
                    antinode = antinode.add(distance);
                }
            }
        }
    }

    private boolean isInsideMap(Vector pos){
        return (pos.getX() >= 0 && pos.getX() < mapSize.getX() && pos.getY() >= 0 && pos.getY() < mapSize.getY());
    }

    private ArrayList<Vector> detectOtherAntennas(Field field){
        ArrayList<Vector> otherAntennas = new ArrayList<>();
        for(Field f : map){
            if((f.getFrequency() == field.getFrequency()) && f.getPosition() != field.getPosition()){
                otherAntennas.add(f.getPosition());
            }
        }
        return otherAntennas;
    }

    public void calculateResult(){
        result = antinodes.size();
    }

    public long getResult() {
        return result;
    }
}
