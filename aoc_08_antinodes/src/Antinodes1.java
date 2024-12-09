import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Antinodes1 {
    private ArrayList<Field> map;
    private Vector mapSize;
    private ArrayList<Vector> antinodes;
    private int result;

    public Antinodes1(){
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
            for(Vector pos : otherAntennas){
                int xDistance = 2* (pos.getX() - field.getPosition().getX());
                int yDistance = 2* (pos.getY() - field.getPosition().getY());
                int xAntinode = field.getPosition().getX() + xDistance;
                int yAntinode = field.getPosition().getY() + yDistance;
                if(xAntinode >= 0 && xAntinode < mapSize.getX() && yAntinode >= 0 && yAntinode < mapSize.getY()){
                    Vector antinode = new Vector(xAntinode, yAntinode);
                    if(!antinodes.contains(antinode)){
                        antinodes.add(antinode);
                    }
                }
            }
        }
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
