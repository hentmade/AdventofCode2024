import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Fragmenter1 {
    private ArrayList<Integer> originDisk;
    private ArrayList<String> formattedDisk;
    private long result;

    public Fragmenter1(){
        originDisk = new ArrayList<>();
        formattedDisk = new ArrayList<>();
        result = 0;
    }

    public void readInput(String filename){
        Path filePath = Paths.get(filename);
        try {
            String tmp = Files.readString(filePath);
            String[] tmp2 = tmp.split("");
            for(String s : tmp2){
                if(!s.equals("\n")){
                    originDisk.add(Integer.parseInt(s));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showFilesAsBlocks(){
        for(int i = 0; i < originDisk.size(); ++i){
            if((i % 2) == 0){
                for(int j = 0; j < originDisk.get(i); ++j){
                    formattedDisk.add(Integer.toString(i/2));
                }
            }else{
                for(int j = 0; j < originDisk.get(i); ++j){
                    formattedDisk.add(".");
                }
            }
        }
    }

    public void moveFileBlocks(){
        for(int i = 0; i < formattedDisk.size(); ++i){
            if(Objects.equals(formattedDisk.get(i), ".")){
                String toMove = formattedDisk.getLast();
                formattedDisk.set(i, toMove);
                formattedDisk.removeLast();
                while(Objects.equals(formattedDisk.getLast(), ".")){
                    formattedDisk.removeLast();
                }
            }
        }
    }

    public void calculateChecksum(){
        for(int i = 0; i < formattedDisk.size(); ++i){
            result += ((long) i * Integer.parseInt(formattedDisk.get(i)));
        }
    }

    public long getResult() {
        return result;
    }
}
