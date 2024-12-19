import java.util.AbstractMap;
import java.util.HashMap;

public class ModelPart1 extends Model{
    private final int FACTOR = 2024;

    public ModelPart1(){
        super();
    }

    @Override
    public void calculateResult(int count) {
        for(int i = 0; i < count; ++i){
            blink();
        }

        long result = 0;
        for(Long stone : stones.values()){
            result += stone;
        }
        setResult(result);
    }

    private void blink(){
        HashMap<Long, Long> stonesToExamine= new HashMap<>(stones);

        for(Long stone : stonesToExamine.keySet()){
            Long occurence = stonesToExamine.get(stone);
            if(stone == 0){
                // 0 --> 1
                AbstractMap.SimpleEntry<Long, Long> tmp = addStoneToMap(1L, occurence);
                if(tmp != null) stones.put(tmp.getKey(), tmp.getValue());
                stones.replace(stone, stones.get(stone) - occurence);
            }
            else if(hasEvenDigits(stone)){
                // divide
                Long[] dividedStone = divideStone(stone);
                AbstractMap.SimpleEntry<Long, Long> tmp = addStoneToMap(dividedStone[0], occurence);
                if(tmp != null) stones.put(tmp.getKey(), tmp.getValue());
                tmp = addStoneToMap(dividedStone[1], occurence);
                if(tmp != null) stones.put(tmp.getKey(), tmp.getValue());
                stones.replace(stone, stones.get(stone) - occurence);
            }
            else{
                // multiply with factor
                Long newStone = stone * FACTOR;
                AbstractMap.SimpleEntry<Long, Long> tmp = addStoneToMap(newStone, occurence);
                if(tmp != null) stones.put(tmp.getKey(), tmp.getValue());
                stones.replace(stone, stones.get(stone) - occurence);
            }
        }
    }

    private AbstractMap.SimpleEntry<Long, Long> addStoneToMap(Long stone, Long occurence){
        if(stones.containsKey(stone)){
            stones.replace(stone, (stones.get(stone) + occurence));
            return null;
        }else{
            return new AbstractMap.SimpleEntry<>(stone, occurence);
        }
    }

    private Long[] divideStone(Long stone){
        Long[] dividedStones = new Long[2];
        long denominator = (long)Math.pow(10, getDigitCount(stone)/2);
        dividedStones[0] = (stone / denominator);
        dividedStones[1] = (stone - (dividedStones[0] * denominator));
        return dividedStones;
    }

    private boolean hasEvenDigits(long i){
        return (getDigitCount(i) % 2) == 0;
    }

    private int getDigitCount(long i){
        int countDigits = 1;
        while((i /= 10) > 0){
            countDigits++;
        }
        return countDigits;
    }
}
