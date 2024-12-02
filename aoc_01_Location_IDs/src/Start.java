import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Start {
    public static void main(String[] args) {
        ArrayList<Integer> list_1 = new ArrayList<>();
        ArrayList<Integer> list_2 = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] tmp = line.split("   ");
                list_1.add(Integer.parseInt(tmp[0]));
                list_2.add(Integer.parseInt(tmp[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(list_1);
        Collections.sort(list_2);

        int sum1 = 0;
        for(int i = 0; i < list_1.size(); ++i){
            sum1 += Math.abs(list_1.get(i) - list_2.get(i));
        }

        System.out.println("sum1 = " + sum1);

        //part 2:
        int sum2 = 0;
        int count;
        for(Integer id1 : list_1){
            count = 0;
            for(Integer id2 : list_2){
                if(id1.intValue() == id2.intValue()){
                    count++;
                }
            }
            sum2 += (id1.intValue() * count);
        }

        System.out.println("sum2 = " + sum2);
    }
}
