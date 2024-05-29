public class Process {
    private int processId;
    private int burstTime;
    private int arriveTime;
    private boolean completed;
    private int remainingTime;
    private int responseTime;

    public Process(int processId, int burstTime, int arriveTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arriveTime = arriveTime;
        this.completed = false;
        this.remainingTime = burstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getProcessId() {
        return processId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}