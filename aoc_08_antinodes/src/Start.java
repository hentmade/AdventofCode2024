public class Start {
    public Start(){
        //part 1:
        //Antinodes1 an = new Antinodes1();

        //part 2:
        Antinodes2 an = new Antinodes2();

        an.readInput("input.txt");
        an.countAntinodes();
        an.calculateResult();
        System.out.println("antinodes = " + an.getResult());
    }

    public static void main(String[] args) {
        new Start();
    }
}
