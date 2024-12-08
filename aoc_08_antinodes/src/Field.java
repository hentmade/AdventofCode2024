public class Field {
    private char frequency;
    private Vector position;
    private boolean hasAntenna;

    public Field(char c, Vector pos){
        this.frequency = c;
        this.position = pos;
        if(c != '.'){
            hasAntenna = true;
        }else{
            hasAntenna = false;
        }
    }

    public char getFrequency() {
        return frequency;
    }

    public Vector getPosition() {
        return position;
    }

    public boolean hasAntenna() {
        return hasAntenna;
    }
}
