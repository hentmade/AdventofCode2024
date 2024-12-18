public class Start {
    private static long startTime;

    public Start(){
        ModelPart1 model = new ModelPart1();

        model.readInput("input.txt");
        model.examinePlants();
        model.calculateResult();

        System.out.println("result = " + model.getResult());
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Start();
    }
}
