import java.util.Objects;

public class Diskspace {
    private int size;
    private String value;
    private boolean isFree;

    public Diskspace(int size, String value){
        this.size = size;
        this.value = value;
        isFree = Objects.equals(value, ".");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getValue() {
        return value;
    }

    public boolean isFree() {
        return isFree;
    }
}
