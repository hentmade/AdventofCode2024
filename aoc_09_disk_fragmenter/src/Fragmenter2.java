import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Fragmenter2 {
    private ArrayList<Integer> originDisk;
    private ArrayList<Diskspace> formattedDisk;
    private long result;

    public Fragmenter2(){
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
                    formattedDisk.add(new Diskspace(originDisk.get(i),Integer.toString(i/2)));
                }
            }else{
                for(int j = 0; j < originDisk.get(i); ++j){
                    formattedDisk.add(new Diskspace(originDisk.get(i), "."));
                }
            }
        }
    }

    public void moveFileBlocks(){
        for(int i = formattedDisk.size()-1; i >= 0; --i) {
            if (!formattedDisk.get(i).isFree()) {
                int filesize = formattedDisk.get(i).getSize();
                for (int j = 0; j < formattedDisk.size(); ++j) {
                    if (j >= i) {
                        break;
                    }
                    if (formattedDisk.get(j).isFree() && formattedDisk.get(j).getSize() >= filesize) {
                        moveFiles(formattedDisk, i + 1 - filesize, j, filesize);
                        break;
                    }
                }
            }

        }
    }

    private void moveFiles(ArrayList<Diskspace> disk, int fromIndex, int toIndex, int size){
        for(int i = 0; i < size; ++i){
            disk.set(toIndex+i, disk.get(fromIndex+i));
        }
        for(int i = toIndex+size; i < toIndex+size+disk.get(toIndex+size).getSize(); ++i){
            if(disk.get(i).isFree()){
                disk.get(i).setSize(disk.get(i).getSize()-size);
            }
        }
        for(int i = 0; i < size; ++i){
            disk.set(fromIndex+i, new Diskspace(size, "."));
        }
    }

    public void calculateChecksum(){
        for(int i = 0; i < formattedDisk.size(); ++i){
            if(!formattedDisk.get(i).isFree()){
                result += ((long) i * Integer.parseInt(formattedDisk.get(i).getValue()));
            }
        }
    }

    public long getResult() {
        return result;
    }
}
