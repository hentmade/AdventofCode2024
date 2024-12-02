import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Start {
    public Start(){
        ArrayList<String> reports = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while((line = reader.readLine()) != null){
                reports.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int countSafeReports = 0;
        for(String report : reports){
            String[] tmpString = report.split(" ");
            ArrayList<Integer> levels = new ArrayList<>();
            for(int i = 0; i < tmpString.length; ++i){
                levels.add(Integer.parseInt(tmpString[i]));
            }

            boolean isSafe = levelChecker(levels);
            if(isSafe){
                countSafeReports++;
            }
            else{
                ArrayList<Integer> levelsCopy = new ArrayList<>(levels);
                for(int i = 0; i < levels.size(); ++i){
                    levelsCopy.remove(i);
                    if(levelChecker(levelsCopy)){
                        countSafeReports++;
                        break;
                    }
                    levelsCopy = (ArrayList<Integer>)levels.clone();
                }
            }
        }
        System.out.println("safe reports = " + countSafeReports);
    }

    public static void main(String[] args) {
        new Start();
    }

    private static boolean levelChecker(ArrayList<Integer> levels){
        boolean isSafe = false;
        boolean increase = false;
//        Integer prevLevel = levels.getFirst();
//        int distance;
//        for(Integer level : levels){
//            if(level != prevLevel){
//                distance = -(prevLevel - level);
//                if(Math.abs(distance) < 1 || Math.abs(distance) > 3){
//                    break;
//                }
//                if(prevLevel == levels.getFirst()){
//                    if(distance > 0){
//                        increase = true;
//                    }
//                }
//                if((increase == true && distance < 0) || (increase == false && distance > 0)){
//                    break;
//                }
//                if(level == levels.getLast()){
//                    isSafe = true;
//                }
//            }
//            prevLevel = level;
//        }
        for(int i = 0; i < levels.size()-1; ++i){
            int distance = levels.get(i+1) - levels.get(i);
            if(Math.abs(distance) < 1 || Math.abs(distance) > 3){
                break;
            }
            if(i == 0){
                if(distance > 0){
                    increase = true;
                }
            }
            if((increase == true && distance < 0) || (increase == false && distance > 0)){
                break;
            }
            if(i == levels.size()-2){
                isSafe = true;
            }
        }
        return isSafe;
    }
}
