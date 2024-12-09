public class Field {
    private char frequency;
    private Vector position;

    public Field(char c, Vector pos){
        this.frequency = c;
        this.position = pos;
    }

    public char getFrequency() {
        return frequency;
    }

    public Vector getPosition() {
        return position;
    }
}
