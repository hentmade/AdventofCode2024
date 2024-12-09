public class Start {
    private static long startTime;
    public Start(){
        //part 1:
        //Antinodes1 an = new Antinodes1();

        //part 2:
        Antinodes2 an = new Antinodes2();

        an.readInput("input.txt");
        an.countAntinodes();
        an.calculateResult();
        System.out.println("antinodes = " + an.getResult());
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Start();
    }
}
