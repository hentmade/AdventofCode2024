public class Vector {
    private int x;
    private int y;
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Vector add(Vector v){
        return new Vector(this.x + v.getX(), this.y + v.getY());
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) return true;

        // Check if obj is an instance of Vector
        if (obj == null || getClass() != obj.getClass()) return false;

        // Compare x and y values
        Vector other = (Vector) obj;
        return x == other.x && y == other.y;
    }
}
