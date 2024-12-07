import java.util.ArrayList;

public class CombinationBuilder {
    private ArrayList<ArrayList<Character>> combinations;
    private ArrayList<Character> inputOptions;

    public CombinationBuilder(ArrayList<Character> inputOptions, int amount) {
        this.inputOptions = inputOptions;
        ArrayList<ArrayList<Character>> combis = new ArrayList<>();
        determineCombinations(new ArrayList<>(), amount, combis);
        this.combinations = combis;
    }

    private void determineCombinations(ArrayList<Character> combi, int remaining, ArrayList<ArrayList<Character>> combis){
        if(remaining == 0){
            combis.add(new ArrayList<>(combi));
            return;
        }
        for(Character c : inputOptions){
            combi.add(c);
            determineCombinations(combi, remaining - 1, combis);
            combi.removeLast();
        }
    }

    public ArrayList<ArrayList<Character>> getCombinations() {
        return combinations;
    }
}
