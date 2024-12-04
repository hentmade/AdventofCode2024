import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class XMAS_Search_Part2 {
    private ArrayList<ArrayList<Character>> fields;
    private int matches;

    public XMAS_Search_Part2(){
        fields = new ArrayList<>();
        matches = 0;
        readInput("input.txt");
        countMatches();
        System.out.println("matches = " + matches);
    }

    private void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                ArrayList<Character> tmp = new ArrayList<>();
                for(int i = 0; i < line.length(); ++i){
                    tmp.add(line.charAt(i));
                }
                fields.add(tmp);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void countMatches(){
        for(int i = 0; i < fields.size(); ++i){
            for(int j = 0; j < fields.getFirst().size(); ++j){
                if(fields.get(i).get(j).equals('A')){
                    ArrayList<Direction> directions = searchCharacterAround('M', i, j);
                    if(directions.size() == 2){
                        int validMatches = 0;
                        for(Direction dir : directions){
                            Direction oppositeDir = null;
                            switch(dir){
                                case Direction.NE -> oppositeDir = Direction.SW;
                                case Direction.SE -> oppositeDir = Direction.NW;
                                case Direction.SW -> oppositeDir = Direction.NE;
                                case Direction.NW -> oppositeDir = Direction.SE;
                            }
                            if(searchCharacterByDirection('S', i, j, oppositeDir)){
                                validMatches++;
                            }
                        }
                        if(validMatches == 2){
                            matches++;
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Direction> searchCharacterAround(Character c, int row, int column){
        ArrayList<Direction> directions = new ArrayList<>();

        if(row != 0 && column != fields.getFirst().size()-1 && c.equals(fields.get(row-1).get(column+1))){
            directions.add(Direction.NE);
        }
        if(row != fields.size()-1 && column != fields.getFirst().size()-1 && c.equals(fields.get(row+1).get(column+1))){
            directions.add(Direction.SE);
        }
        if(row != fields.size()-1 && column != 0 && c.equals(fields.get(row+1).get(column-1))){
            directions.add(Direction.SW);
        }
        if(row != 0 && column != 0 && c.equals(fields.get(row-1).get(column-1))){
            directions.add(Direction.NW);
        }

        return directions;
    }

    private boolean searchCharacterByDirection(Character c, int row, int column, Direction dir){
        boolean charFound = false;

        if(dir == Direction.NE && row != 0 && column != fields.getFirst().size()-1 && c.equals(fields.get(row-1).get(column+1))){
            charFound = true;
        }
        else if(dir == Direction.SE && row != fields.size()-1 && column != fields.getFirst().size()-1 && c.equals(fields.get(row+1).get(column+1))){
            charFound = true;
        }
        else if(dir == Direction.SW && row != fields.size()-1 && column != 0 && c.equals(fields.get(row+1).get(column-1))){
            charFound = true;
        }
        else if(dir == Direction.NW && row != 0 && column != 0 && c.equals(fields.get(row-1).get(column-1))){
            charFound = true;
        }

        return charFound;
    }

}
