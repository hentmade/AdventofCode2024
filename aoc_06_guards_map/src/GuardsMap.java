import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GuardsMap {
    private ArrayList<ArrayList<Character>> map;
    private Vector startPosition;
    private Vector[] directions;
    private Vector startDirection;
    private ArrayList<Vector> passedPoints;
    public GuardsMap(){
        map = new ArrayList<>();
        startPosition = new Vector(0,0);
        directions = new Vector[4];
        directions[0] = new Vector(0,-1);   //up
        directions[1] = new Vector(+1,0);   //left
        directions[2] = new Vector(0,+1);   //down
        directions[3] = new Vector(-1,0);   //right
        startDirection = new Vector(0,0);
        passedPoints = new ArrayList<>();
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                ArrayList<Character> tmp = new ArrayList<>();
                for(int i = 0; i < line.length(); ++i){
                    tmp.add(line.charAt(i));
                }
                map.add(tmp);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void determineStartPositionAndDirection(){
        boolean startFound = false;
        for(ArrayList<Character> line : map){
            if(startFound){
                break;
            }
            for(Character clmn : line){
                if(clmn == '^' || clmn == '>' || clmn == 'v' || clmn == '<' ){
                    startPosition = new Vector(line.indexOf(clmn), map.indexOf(line));
                    passedPoints.add(startPosition);
                    startFound = true;
                    break;
                }
            }
        }
        switch(map.get(startPosition.getY()).get(startPosition.getX())){
            case '^' -> startDirection = directions[0];
            case '>' -> startDirection = directions[1];
            case 'v' -> startDirection = directions[2];
            case '<' -> startDirection = directions[3];
        }
    }

    public void walkAndCountPassingPoints(){
        Vector actPosition = startPosition;
        Vector actDirection = startDirection;

        while(true){
            Vector newPosition = getNextField(actPosition, actDirection);
            if(newPosition.getX() < 0 || newPosition.getX() >= map.getFirst().size() || newPosition.getY() < 0 || newPosition.getY() >= map.size()){
                break;
            }
            while(getCharOfField(newPosition) == '#'){
                actDirection = changeDirection(actDirection);
                newPosition = getNextField(actPosition, actDirection);
            }
            actPosition = newPosition;
            if(!passedPoints.contains(actPosition)){
                passedPoints.add(actPosition);
            }
        }
    }

    public int getCountOfPassedPoints(){
        return passedPoints.size();
    }

    private Character getCharOfField(Vector field){
        return map.get(field.getY()).get(field.getX());
    }

    private Vector getNextField(Vector position, Vector direction){
        int newX = position.getX() + direction.getX();
        int newY = position.getY() + direction.getY();
        return new Vector(newX, newY);
    }

    private Vector changeDirection(Vector actDirection){
        Vector newDirection = startDirection;
        if(actDirection.equals(directions[0])){
            newDirection = directions[1];
        }else if(actDirection.equals(directions[1])){
            newDirection = directions[2];
        }else if(actDirection.equals(directions[2])){
            newDirection = directions[3];
        }else if(actDirection.equals(directions[3])){
            newDirection = directions[0];
        }
        return newDirection;
    }
}
