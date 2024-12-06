public class Start {
    public Start(){
        GuardsMap gm = new GuardsMap();
        gm.readInput("input.txt");
        gm.determineStartPositionAndDirection();
        gm.walkAndCountPassingPoints();
        System.out.println("passedPoints = " + gm.getCountOfPassedPoints());
    }

    public static void main(String[] args) {
        new Start();
    }
}
