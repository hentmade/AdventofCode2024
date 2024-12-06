public class Start {
    public Start(){
        //part 1:
//        GuardsMap gm = new GuardsMap();
//        gm.readInput("input.txt");
//        gm.determineStartPositionAndDirection();
//        gm.walkAndCountPassingPoints();
//        System.out.println("passedPoints = " + gm.getCountOfPassedPoints());

        //part 2:
        GuardsMap2 gm = new GuardsMap2();
        gm.readInput("input.txt");
        gm.determineStartPositionAndDirection();
        gm.tryObstructionPositions();
        System.out.println("different obstruction positions = " + gm.getObstructionPositions());
    }

    public static void main(String[] args) {
        new Start();
    }
}
