public class Start {
    private static long startTime;

    public Start(){
        Model model = new ModelPart1();

        model.readInput("input.txt");
        //TODO

        System.out.println("score-sum = " + model.getResult());
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Start();
    }
}
