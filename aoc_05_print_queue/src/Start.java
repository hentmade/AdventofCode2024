public class Start {
    public Start(){
        //part 1:
        //new PrintQueue_1();

        //part 2:
        PrintQueue_2 pq = new PrintQueue_2();
        pq.readInput("input.txt");
        pq.detectUnorderedPagelists();
        pq.orderPagelists();
        pq.calculateResult();
        System.out.println("result unordered = " + pq.getResult());
    }

    public static void main(String[] args) {
        new Start();
    }
}
