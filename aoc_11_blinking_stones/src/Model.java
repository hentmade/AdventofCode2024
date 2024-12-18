import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Model {
    private long result;
    protected ArrayList<Stone> stones;

    public Model(){
        result = 0;
        stones = new ArrayList<>();
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineCount;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                String[] tmp = line.split(" ");
                for(String s : tmp){
                    stones.add(new Stone(Long.parseLong(s)));
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
