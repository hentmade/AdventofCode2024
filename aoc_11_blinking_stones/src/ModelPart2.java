import java.util.HashMap;
import java.util.Map;

public class ModelPart2 extends Model{
    private final int FACTOR = 2024;
    private Map<Long, Integer> numbers = new HashMap<>();

    public ModelPart2(){
        super();
        for(Stone st : stones){
            numbers.put(st.getValue(), 1);
        }
    }

    @Override
    public void calculateResult(int count) {
        for(int i = 0; i < count; ++i){
            numbers = blink(numbers);
        }
        setResult(stones.size());
    }

    private Map<Long, Integer> blink(Map<Long, Integer> nb){
        Map<Long, Integer> newNumbers = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : numbers.entrySet()) {
            long number = entry.getKey();
            Integer count = entry.getValue();

            if (number == 0) {
                // Rule 1: 0 becomes 1
                newNumbers.merge((long)1, count, Integer::sum);
            } else if (hasEvenDigits(number)) {
                // Rule 2: Split numbers with an even number of digits
                long[] split = divideNumber(number);
                newNumbers.merge(split[0], count, Integer::sum);
                newNumbers.merge(split[1], count, Integer::sum);
            } else {
                // Rule 3: Multiply numbers with an odd number of digits by the factor
                long multiplied = number * FACTOR;
                newNumbers.merge(multiplied, count, Integer::sum);
            }
        }

        return newNumbers;
    }

    private long[] divideNumber(long value){
        long[] dividedValues = new long[2];
        long denominator = (long)Math.pow(10, getDigitCount(value)/2);
        dividedValues[0] = value / denominator;
        dividedValues[1] = (value - (dividedValues[0] * denominator));
        return dividedValues;
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
