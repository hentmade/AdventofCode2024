import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Antinodes1 {
    private ArrayList<ArrayList<Field>> map;
    private ArrayList<Vector> antinodes;
    private int result;

    public Antinodes1(){
        map = new ArrayList<>();
        antinodes = new ArrayList<>();
        result = 0;
    }


    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineCount = 0;
            while((line = reader.readLine()) != null){
                ArrayList<Field> tmp = new ArrayList<>();
                for(int i = 0; i < line.length(); ++i){
                    tmp.add(new Field(line.charAt(i), new Vector(i, lineCount)));
                }
                map.add(tmp);
                lineCount++;
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void countAntinodes(){
        for(ArrayList<Field> row : map) {
            for (Field field : row) {
                if(field.hasAntenna()){
                    ArrayList<Vector> otherAntennas = detectOtherAntennas(field);
                    for(Vector pos : otherAntennas){
                        int xDistance = 2* (pos.getX() - field.getPosition().getX());
                        int yDistance = 2* (pos.getY() - field.getPosition().getY());
                        int xAntinode = field.getPosition().getX() + xDistance;
                        int yAntinode = field.getPosition().getY() + yDistance;
                        if(xAntinode >= 0 && xAntinode < row.size() && yAntinode >= 0 && yAntinode < map.size()){
                            Vector antinode = new Vector(xAntinode, yAntinode);
                            if(!antinodes.contains(antinode)){
                                antinodes.add(antinode);
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Vector> detectOtherAntennas(Field field){
        ArrayList<Vector> otherAntennas = new ArrayList<>();
        for(ArrayList<Field> row : map){
            for(Field f : row){
                if(f.hasAntenna() && (f.getFrequency() == field.getFrequency()) && f.getPosition() != field.getPosition()){
                    otherAntennas.add(f.getPosition());
                }
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
