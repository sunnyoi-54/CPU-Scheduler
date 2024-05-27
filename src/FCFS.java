import java.util.*;

public class FCFS {
    private List<Process> jobList; // 작업 큐
    private List<Result> resultList; // 결과 리스트

    public FCFS() {
        this.jobList = new ArrayList<>();
        this.resultList = new ArrayList<>();
    }

    public void run(List<Process> jobList, List<Result> resultList){
        this.jobList = jobList;
        this.resultList = resultList;

        // 도착 시간 기준으로 정렬
        jobList.sort(Comparator.comparingInt(Process::getArriveTime));

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process p : jobList) {
            int arriveTime = p.getArriveTime();
            int burstTime = p.getBurstTime();

            // 현재 시간이 프로세스의 도착 시간보다 작은 경우, 도착할 때까지 기다림
            currentTime = Math.max(currentTime, arriveTime);

            int waitingTime = currentTime - arriveTime; // 대기 시간 계산
            totalWaitingTime += waitingTime;
            currentTime += burstTime; // 현재 시간 증가
            int turnaroundTime = waitingTime + burstTime;
            totalTurnaroundTime += turnaroundTime;
            int responseTime = waitingTime;

            resultList.add(new Result(p.getProcessId(), burstTime, waitingTime, turnaroundTime, responseTime));
        }

        double averageWaitingTime = (double)totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double)totalTurnaroundTime / jobList.size();

        printResults(averageWaitingTime, currentTime, averageTurnaroundTime, resultList);
    }

    private void printResults(double averageWaitingTime, int cpuExecutionTime, double averageTurnaroundTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("FCFS 결과");
        System.out.println("평균 대기 시간 : " + averageWaitingTime);
        System.out.println("평균 Turnaround Time : " + averageTurnaroundTime);
        System.out.println("CPU 실행 시간 : " + cpuExecutionTime);

        resultList.sort(Comparator.comparingInt(Result::getProcessID));

        resultList.forEach(result -> {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Process" + result.getProcessID() + "의 waiting time : " + result.getWaitingTime());
            System.out.println("Process" + result.getProcessID() + "의 turnaround Time : " + result.getTurnaroundTime());
            System.out.println("Process" + result.getProcessID() + "의 response Time : " + result.getResponseTime());
        });

        System.out.println("=================================================================");
    }
}
