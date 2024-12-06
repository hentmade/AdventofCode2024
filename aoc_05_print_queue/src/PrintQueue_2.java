import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrintQueue_2 {
    private ArrayList<ArrayList<Integer>> rules;
    private ArrayList<ArrayList<Integer>> pagelists;
    private ArrayList<ArrayList<Integer>> unorderedPagelists;
    private ArrayList<ArrayList<Integer>> newOrderedPagelists;
    private int result;

    public PrintQueue_2(){
        rules = new ArrayList<>();
        pagelists = new ArrayList<>();
        unorderedPagelists = new ArrayList<>();
        newOrderedPagelists = new ArrayList<>();
        result = 0;
    }

    public void readInput(String filename){
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
                    pagelists.add(tmpInt);
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

    public void detectUnorderedPagelists(){
        for(ArrayList<Integer> pagelist : pagelists){
            if(!isOrdered(pagelist)){
                unorderedPagelists.add(pagelist);
            }
        }
    }

    public void orderPagelists(){
        for(ArrayList<Integer> pagelist : unorderedPagelists){
            orderPagelist(pagelist);
        }
    }

    private void orderPagelist(ArrayList<Integer> list){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i){
            ArrayList<Integer> validFollowingPages = new ArrayList<>();
            for(ArrayList<Integer> rule : rules) {
                if (rule.getFirst() == list.get(i)) {
                    validFollowingPages.add(rule.getLast());
                }
            }

            for(int j = i+1; j < list.size(); ++j){
                if(!validFollowingPages.contains(list.get(j))){
                    if(tmp.isEmpty()){
                        tmp.add(list.get(j));
                    }
                    else if(!tmp.contains(list.get(j))){
                        ArrayList<Integer> validFollowingPages2 = new ArrayList<>();
                        for(ArrayList<Integer> rule : rules) {
                            if (rule.getFirst() == list.get(j)) {
                                validFollowingPages2.add(rule.getLast());
                            }
                        }
                        boolean inserted = false;
                        for(int k = 0; k < tmp.size(); ++k){
                            if(validFollowingPages2.contains(tmp.get(k))){
                                inserted = true;
                                tmp.add(k, list.get(j));
                                break;
                            }
                        }
                        if(!inserted){
                            tmp.add(list.get(j));
                        }
                    }
                }
            }
            if(!tmp.contains(list.get(i))){
                tmp.add(list.get(i));
            }
        }
        newOrderedPagelists.add(tmp);
    }

    public void calculateResult(){
        for(ArrayList<Integer> pagelist : newOrderedPagelists){
            int middleIndex = pagelist.size()/2;
            result += pagelist.get(middleIndex);
        }
    }

    public int getResult(){
        return result;
    }

    private boolean isOrdered(ArrayList<Integer> pageList){
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
        return updateValid;
    }
}
