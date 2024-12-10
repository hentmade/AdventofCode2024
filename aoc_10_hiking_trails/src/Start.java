public class Start {
    private static long startTime;

    public Start(){
        //part 1:
        //HikingTrails1 ht = new HikingTrails1();

        //part 2:
        HikingTrails2 ht = new HikingTrails2();

        ht.readInput("input.txt");
        ht.detectTrailScores();
        System.out.println("score-sum = " + ht.getResult());
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Start();
    }
}
