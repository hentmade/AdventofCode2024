import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class XMAS_Search {
    private ArrayList<ArrayList<Character>> fields;
    private int matches;

    public XMAS_Search(){
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
                if(fields.get(i).get(j).equals('X')){
                    ArrayList<Direction> directions = searchCharacterAround('M', i, j);
                    if(!directions.isEmpty()){
                        for(Direction dir : directions){
                            int row = i;
                            int clmn = j;
                            switch(dir){
                                case Direction.N -> {row = i-1; clmn = j;}
                                case Direction.NE -> {row = i-1; clmn = j+1;}
                                case Direction.E -> {row = i; clmn = j+1;}
                                case Direction.SE -> {row = i+1; clmn = j+1;}
                                case Direction.S -> {row = i+1; clmn = j;}
                                case Direction.SW -> {row = i+1; clmn = j-1;}
                                case Direction.W -> {row = i; clmn = j-1;}
                                case Direction.NW -> {row = i-1; clmn = j-1;}
                            }
                            if(searchCharacterByDirection('A', row, clmn, dir)){
                                switch(dir){
                                    case Direction.N -> {row = row-1; clmn = clmn;}
                                    case Direction.NE -> {row = row-1; clmn = clmn+1;}
                                    case Direction.E -> {row = row; clmn = clmn+1;}
                                    case Direction.SE -> {row = row+1; clmn = clmn+1;}
                                    case Direction.S -> {row = row+1; clmn = clmn;}
                                    case Direction.SW -> {row = row+1; clmn = clmn-1;}
                                    case Direction.W -> {row = row; clmn = clmn-1;}
                                    case Direction.NW -> {row = row-1; clmn = clmn-1;}
                                }
                                if(searchCharacterByDirection('S', row, clmn, dir)){
                                    matches++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Direction> searchCharacterAround(Character c, int row, int column){
        ArrayList<Direction> directions = new ArrayList<>();

        if(row != 0 && c.equals(fields.get(row-1).get(column))){
            directions.add(Direction.N);
        }
        if(row != 0 && column != fields.getFirst().size()-1 && c.equals(fields.get(row-1).get(column+1))){
            directions.add(Direction.NE);
        }
        if(column != fields.getFirst().size()-1 && c.equals(fields.get(row).get(column+1))){
            directions.add(Direction.E);
        }
        if(row != fields.size()-1 && column != fields.getFirst().size()-1 && c.equals(fields.get(row+1).get(column+1))){
            directions.add(Direction.SE);
        }
        if(row != fields.size()-1 && c.equals(fields.get(row+1).get(column))){
            directions.add(Direction.S);
        }
        if(row != fields.size()-1 && column != 0 && c.equals(fields.get(row+1).get(column-1))){
            directions.add(Direction.SW);
        }
        if(column != 0 && c.equals(fields.get(row).get(column-1))){
            directions.add(Direction.W);
        }
        if(row != 0 && column != 0 && c.equals(fields.get(row-1).get(column-1))){
            directions.add(Direction.NW);
        }

        return directions;
    }

    private boolean searchCharacterByDirection(Character c, int row, int column, Direction dir){
        boolean charFound = false;

        if(dir == Direction.N && row != 0 && c.equals(fields.get(row-1).get(column))){
            charFound = true;
        }
        else if(dir == Direction.NE && row != 0 && column != fields.getFirst().size()-1 && c.equals(fields.get(row-1).get(column+1))){
            charFound = true;
        }
        else if(dir == Direction.E && column != fields.getFirst().size()-1 && c.equals(fields.get(row).get(column+1))){
            charFound = true;
        }
        else if(dir == Direction.SE && row != fields.size()-1 && column != fields.getFirst().size()-1 && c.equals(fields.get(row+1).get(column+1))){
            charFound = true;
        }
        else if(dir == Direction.S && row != fields.size()-1 && c.equals(fields.get(row+1).get(column))){
            charFound = true;
        }
        else if(dir == Direction.SW && row != fields.size()-1 && column != 0 && c.equals(fields.get(row+1).get(column-1))){
            charFound = true;
        }
        else if(dir == Direction.W && column != 0 && c.equals(fields.get(row).get(column-1))){
            charFound = true;
        }
        else if(dir == Direction.NW && row != 0 && column != 0 && c.equals(fields.get(row-1).get(column-1))){
            charFound = true;
        }

        return charFound;
    }
}
