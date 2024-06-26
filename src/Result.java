public class Result {
    private int processID;
    private int waitingTime;
    private int turnaroundTime;
    private int responseTime;

    public Result(int processID, int waitingTime, int turnaroundTime, int responseTime) {
        this.processID = processID;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
        this.responseTime = responseTime;
    }

    public int getProcessID() {
        return processID;
    }
    public int getWaitingTime() {
        return waitingTime;
    }
    public int getTurnaroundTime() {
        return turnaroundTime;
    }
    public int getResponseTime() {
        return responseTime;
    }

}
