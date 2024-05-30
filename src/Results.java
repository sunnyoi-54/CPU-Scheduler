public class Results {
    private double averageWaitingTime;
    private double averageResponseTime;
    private double averageTurnaroundTime;

    public Results(double averageWaitingTime, double averageResponseTime, double averageTurnaroundTime) {
        this.averageWaitingTime = averageWaitingTime;
        this.averageResponseTime = averageResponseTime;
        this.averageTurnaroundTime = averageTurnaroundTime;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageResponseTime() {
        return averageResponseTime;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }
}
