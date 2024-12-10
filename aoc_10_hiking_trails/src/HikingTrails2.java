import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HikingTrails2 {
    private ArrayList<MapPoint> map;
    private Vector mapSize;
    private int result;
    private ArrayList<Vector> directions;

    public HikingTrails2(){
        map = new ArrayList<>();
        mapSize = null;
        result = 0;

        directions = new ArrayList<>();
        directions.add(new Vector(0,-1));
        directions.add(new Vector(+1,0));
        directions.add(new Vector(0,+1));
        directions.add(new Vector(-1,0));
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int mapWidth = 0;
            String line;
            int lineCount;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                mapWidth = line.length();
                String[] tmp = line.split("");
                for(int i = 0; i < line.length(); ++i){
                    int x = Integer.parseInt(tmp[i]);
                    map.add(new MapPoint(x, new Vector(i, lineCount)));
                }
            }
            reader.close();
            mapSize = new Vector(mapWidth, lineCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void detectTrailScores(){
        for(MapPoint mp : map){
            if(mp.isTrailhead()){
                detectPathScore(mp);
            }
        }
    }

    private void detectPathScore(MapPoint mp){
        if(mp.isEndpoint()){
            result++;
            return;
        }
        for(Vector dir : directions){
            Vector neighbourPos = mp.getPosition().add(dir);
            if(isInsideMap(neighbourPos)) {
                MapPoint neighbour = getPointByPosition(neighbourPos);
                if (neighbour.getValue() == mp.getValue() + 1) {
                    detectPathScore(neighbour);
                }
            }
        }
    }

    private boolean isInsideMap(Vector pos){
        return (pos.getX() >= 0 && pos.getX() < mapSize.getX() && pos.getY() >= 0 && pos.getY() < mapSize.getY());
    }

    private MapPoint getPointByPosition(Vector pos){
        for(MapPoint mp : map){
            if(pos.equals(mp.getPosition())){
                return mp;
            }
        }
        return null;
    }

    public int getResult(){
        return result;
    }
}
