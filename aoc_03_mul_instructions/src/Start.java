import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Start {
    private String input;
    private ArrayList<String> mulExpressions;
    private int result;
    public Start(){
        mulExpressions = new ArrayList<>();
        result = 0;
        readInput();
        separateMulExpressions();
        calculateResult();
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        new Start();
    }

    private void readInput(){
        Path filePath = Paths.get("./input.txt");
        try {
            this.input = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void separateMulExpressions(){
        //String regex = "mul\\(\d{1,3},\d{1,3}\\)";
        String regex = "(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don't\\(\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            mulExpressions.add(matcher.group());
        }
    }

    private void calculateResult(){
        String regex = "\\d{1,3}";
        Pattern pattern = Pattern.compile(regex);
        ArrayList<Integer> factors = new ArrayList<>();
        boolean validMul = true;

        for(String mul : mulExpressions){
            if(Objects.equals(mul, "don't()")){
                validMul = false;
            }else if(Objects.equals(mul, "do()")){
                validMul = true;
            }
            if(validMul && !Objects.equals(mul, "do()")){
                Matcher matcher = pattern.matcher(mul);
                while(matcher.find()){
                    factors.add(Integer.parseInt(matcher.group()));
                }
                result += (factors.getFirst() * factors.getLast());
                factors.clear();
            }
        }
    }
}
