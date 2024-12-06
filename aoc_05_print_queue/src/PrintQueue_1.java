import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrintQueue_1 {
    private ArrayList<ArrayList<Integer>> rules;
    private ArrayList<ArrayList<Integer>> updates;
    private int result;
    public PrintQueue_1(){
        rules = new ArrayList<>();
        updates = new ArrayList<>();
        result = 0;
        readInput("input.txt");
        calculateResult();
        System.out.println("result = " + result);
    }

    private void readInput(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            boolean readingUpdates = false;
            while((line = reader.readLine()) != null){
                if(line.equals("")){
                    readingUpdates = true;
                    continue;
                }
                if(readingUpdates){
                    String[] tmp = line.split(",");
                    ArrayList<Integer> tmpInt = new ArrayList<>();
                    for (String s : tmp) {
                        tmpInt.add(Integer.parseInt(s));
                    }
                    updates.add(tmpInt);
                }
                else{
                    String[] tmp = line.split("\\|");
                    ArrayList<Integer> tmpInt = new ArrayList<>();
                    tmpInt.add(Integer.parseInt(tmp[0]));
                    tmpInt.add(Integer.parseInt(tmp[1]));
                    rules.add(tmpInt);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void calculateResult(){
        for(ArrayList<Integer> pageList : updates){
            boolean updateValid = true;
            for(Integer page : pageList){
                ArrayList<Integer> validFollowingPages = new ArrayList<>();
                ArrayList<Integer> validPreviousPages = new ArrayList<>();
                for(ArrayList<Integer> rule : rules){
                    if(rule.getFirst() == page){
                        validFollowingPages.add(rule.getLast());
                    }
                    if(rule.getLast() == page){
                        validPreviousPages.add(rule.getFirst());
                    }
                }

                boolean pageValid = true;
                for(Integer followingPage : pageList){
                    if(pageList.indexOf(page) < pageList.indexOf(followingPage)){
                        boolean valid = false;
                        for(Integer validFollowingPage : validFollowingPages){
                            if(followingPage.equals(validFollowingPage)){
                                valid = true;
                                break;
                            }
                        }
                        if(!valid){
                            pageValid = false;
                            break;
                        }
                    }
                }
                for(Integer prevPage : pageList){
                    if(pageList.indexOf(page) > pageList.indexOf(prevPage)){
                        boolean valid = false;
                        for(Integer validPrevPage : validPreviousPages){
                            if(prevPage.equals(validPrevPage)){
                                valid = true;
                                break;
                            }
                        }
                        if(!valid){
                            pageValid = false;
                            break;
                        }
                    }
                }
                if(!pageValid){
                    updateValid = false;
                    break;
                }
            }
            if(updateValid){
                int middleIndex = pageList.size()/2;
                result += pageList.get(middleIndex);
            }
        }
    }
}
