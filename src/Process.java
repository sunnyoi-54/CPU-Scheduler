public class Process {
    private int burstTime;
    private int arriveTime;
    private int priority;
    private int timeSlice;

    public Process(int burstTime, int arriveTime, int priority, int timeSlice) {
        this.burstTime = burstTime;
        this.arriveTime = arriveTime;
        this.priority = priority;
        this.timeSlice = timeSlice;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeSlice() {
        return timeSlice;
    }
}