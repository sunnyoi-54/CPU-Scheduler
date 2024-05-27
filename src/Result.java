public class Result {
    private int processID;
    private int burstTime;
    private int waitingTime;
    private int turnaroundTime;
    private int responseTime;

    public Result(int processID, int burstTime, int waitingTime, int turnaroundTime, int responseTime) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
        this.responseTime = responseTime;
    }

    public int getProcessID() {
        return processID;
    }


    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}
