public class Prize {
    private long xPos;
    private long yPos;
    private MovingButton buttonA;
    private MovingButton buttonB;
    private boolean isReachable;
    private long[] bestSolution;

    public Prize(long xPos, long yPos, MovingButton buttonA, MovingButton buttonB){
        this.xPos = xPos;
        this.yPos = yPos;
        this.buttonA = buttonA;
        this.buttonB = buttonB;
        this.setReachable(false);
        this.bestSolution = null;
    }

    public long getxPos() {
        return xPos;
    }

    public long getyPos() {
        return yPos;
    }

    public MovingButton getButtonA() {
        return buttonA;
    }

    public MovingButton getButtonB() {
        return buttonB;
    }

    public long[] getBestSolution(){
        return bestSolution;
    }

    public void addBestSolution(long[] bestSolution){
        this.bestSolution = bestSolution;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }
}
