public class Start {
    private static long startTime;

    public Start(){
        Model1 model = new Model1();

        model.readInput("input.txt");
        //TODO

        System.out.println("result = " + model.getResult());
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Start();
    }
}
