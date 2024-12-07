import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BridgeRepair1 {
    private ArrayList<Equation> eqs;
    private ArrayList<Equation> trueEqs;
    private ArrayList<Character> possibleOperators;
    private long result;

    public BridgeRepair1(){
        eqs  = new ArrayList<>();
        trueEqs  = new ArrayList<>();
        possibleOperators = new ArrayList<>();
        possibleOperators.add('+');
        possibleOperators.add('*');
        result = 0;
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                String[] tmp = line.split(": ");
                long eqResult = Long.parseLong(tmp[0]);
                String[] tmp2 = tmp[1].split(" ");
                ArrayList<Integer> operators = new ArrayList<>();
                for (String s : tmp2) {
                    operators.add(Integer.parseInt(s));
                }
                eqs.add(new Equation(eqResult, operators));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void countSolvableEquations(){
        for(Equation eq : eqs){
            CombinationBuilder cb = new CombinationBuilder(possibleOperators, eq.getNumbers().size()-1);
            ArrayList<ArrayList<Character>> combinations = cb.getCombinations();
            for(ArrayList<Character> combi : combinations){
                if(calcEquation(eq.getNumbers(), combi) == eq.getResult()){
                    trueEqs.add(eq);
                    break;
                }

            }
        }
    }

    private long calcEquation(ArrayList<Integer> numbers, ArrayList<Character> operators){
        long result = numbers.getFirst();
        assert(numbers.size() == operators.size()+1);
        for(int i = 0; i < operators.size(); ++i){
            switch(operators.get(i)){
                case '+' -> result += numbers.get(i+1);
                case '*' -> result *= numbers.get(i+1);
            }
        }
        return result;
    }

    public void calculateResult(){
        for(Equation eq : trueEqs){
            result += eq.getResult();
        }
    }

    public long getResult() {
        return result;
    }
}
