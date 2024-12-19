import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Model {
    private long result;
    protected HashMap<Long, Long> stones;   //stone, occurence

    public Model(){
        result = 0;
        stones = new HashMap<>();
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineCount;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                String[] tmp = line.split(" ");
                for(String s : tmp){
                    Long actValue = stones.putIfAbsent(Long.parseLong(s), 1L);
                    if(actValue != null){
                        stones.put(Long.parseLong(s), actValue+1);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getResult(){
        return result;
    }

    public void setResult(long result){
        this.result = result;
    }

    public abstract void calculateResult(int count);
}
