import java.util.Map;

public class MapPoint {
    private int value;
    private Vector position;
    private boolean isTrailhead;
    private boolean isEndpoint;
    private MapPoint reachedByTrailhead;

    public MapPoint(int value, Vector position){
        this.value = value;
        this.position = position;
        if(value == 0) isTrailhead = true;
        if(value == 9) isEndpoint = true;
        this.reachedByTrailhead = null;
    }

    public int getValue() {
        return value;
    }

    public Vector getPosition() {
        return position;
    }

    public boolean isTrailhead() {
        return isTrailhead;
    }

    public boolean isEndpoint() {
        return isEndpoint;
    }

    public void setReachedByTrailhead(MapPoint mp){
        this.reachedByTrailhead = mp;
    }

    public MapPoint getReachedByTrailhead() {
        return reachedByTrailhead;
    }
}
