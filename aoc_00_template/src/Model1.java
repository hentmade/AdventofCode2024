import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model1 {
    private long result;

    public Model1(){
        result = 0;
    }

    public void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineCount;
            for(lineCount = 0; (line = reader.readLine()) != null; lineCount++){
                //TODO
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getResult(){
        return result;
    }
}
