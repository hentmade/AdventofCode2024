public class Start {
    private static long startTime;
    public Start(){
        //part 1:
//        Fragmenter1 fr = new Fragmenter1();

        //part 2:
        Fragmenter2 fr = new Fragmenter2();

        fr.readInput("input.txt");
        fr.showFilesAsBlocks();
        fr.moveFileBlocks();
        fr.calculateChecksum();
        System.out.println("checksum = " + fr.getResult());
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Start();
    }
}
