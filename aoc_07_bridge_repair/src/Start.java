public class Start {
    public Start(){
        //part 1:
//        BridgeRepair1 br = new BridgeRepair1();

        //part 2:
        BridgeRepair2 br = new BridgeRepair2();

        br.readInput("input.txt");
        br.countSolvableEquations();
        br.calculateResult();
        System.out.println("solvable equations = " + br.getResult());
    }

    public static void main(String[] args) {
        new Start();
    }
}
