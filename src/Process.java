public class Process {
    private int processId;
    private int burstTime;
    private int arriveTime;
    private boolean completed;


    public Process(int processId, int burstTime, int arriveTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arriveTime = arriveTime;
        this.completed = false;
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
}