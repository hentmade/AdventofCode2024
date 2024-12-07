import java.util.ArrayList;

public class Equation {
    private long result;
    private ArrayList<Integer> numbers;

    public Equation(long result, ArrayList<Integer> operators){
        this.result = result;
        this.numbers = operators;
    }


    public long getResult() {
        return result;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }
}
