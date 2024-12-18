import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model2 {
    private long result;
    private ArrayList<Prize> prizes;
    private final long PRIZE_INCREASE = 10000000000000L;
    private final int A_TOKENFACTOR = 3;
    private final int B_TOKENFACTOR = 1;

    public Model2(){
        result = 0;
        prizes = new ArrayList<>();
    }

    public void readInput(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineCount;
            int xPosPrize = 0;
            int yPosPrize = 0;
            boolean prizeSet = false;
            MovingButton buttonA = null;
            MovingButton buttonB = null;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                Pattern pattern = Pattern.compile("Button (\\w+): X\\+(\\d+),\\s*Y\\+(\\d+)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String buttonLabel = matcher.group(1);
                    int xValue = Integer.parseInt(matcher.group(2));
                    int yValue = Integer.parseInt(matcher.group(3));
                    if (Objects.equals(buttonLabel, "A")) {
                        buttonA = new MovingButton(xValue, yValue);
                    } else {
                        buttonB = new MovingButton(xValue, yValue);
                    }
                }
                pattern = Pattern.compile("Prize: X=(\\d+),\\s*Y=(\\d+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    xPosPrize = Integer.parseInt(matcher.group(1));
                    yPosPrize = Integer.parseInt(matcher.group(2));
                    prizeSet = true;
                }
                if(buttonA != null && buttonB != null && prizeSet){
                    prizeSet = false;
                    prizes.add(new Prize(xPosPrize+PRIZE_INCREASE, yPosPrize+PRIZE_INCREASE, buttonA, buttonB));
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkSolutions(){
        for(Prize prize : prizes){
            long[] bestSolution = determineSolutions(prize);
            if(bestSolution != null){
                prize.setReachable(true);
                prize.addBestSolution(bestSolution);
            }
        }
    }

    public void calculateTokens(){
        for(Prize prize : prizes){
            if(prize.isReachable()){
                result += (prize.getBestSolution()[0] * A_TOKENFACTOR + prize.getBestSolution()[1] * B_TOKENFACTOR);
            }
        }
    }

    private long[] determineSolutions(Prize prize){
        for (int a = 1; ; a++) {
            int xA = prize.getButtonA().getxMov();
            int xB = prize.getButtonB().getxMov();
            long remaining = prize.getxPos() - xA * a;
            if (remaining < xB) {
                break;
            }
            if (remaining % xB == 0) {
                long b = remaining / xB;
                int yA = prize.getButtonA().getyMov();
                int yB = prize.getButtonB().getyMov();
                if(a*yA + b*yB == prize.getyPos()){
                    return new long[]{a, b};
                }
            }
        }
        return null;
    }

    public long getResult(){
        return result;
    }
}
