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
        setResult(stones.size());
    }

    private void blink(){
        for(int i = 0; i < stones.size(); ++i){
            long value = stones.get(i).getValue();
            if(value == 0){
                stones.get(i).setValue(1);
            }
            else if(hasEvenDigits(value)){
                Stone[] dividedStones = divideStone(stones.get(i));
                stones.set(i, dividedStones[0]);
                stones.add(i+1, dividedStones[1]);
                i++;
            }
            else{
                stones.get(i).setValue(value * FACTOR);
            }
        }
    }

    private Stone[] divideStone(Stone stone){
        long value = stone.getValue();
        Stone[] dividedStones = new Stone[2];
        long denominator = (long)Math.pow(10, getDigitCount(value)/2);
        dividedStones[0] = new Stone(value / denominator);
        dividedStones[1] = new Stone(value - (dividedStones[0].getValue() * denominator));
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
