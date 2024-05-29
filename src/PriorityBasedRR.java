import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PriorityBasedRR { //HRRN + RR
    public void run(List<Process> jobList, List<Result> resultList, int timeQuantum) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int completedProcesses = 0;
        int totalTurnaroundTime = 0;
        Queue<Process> processQueue = new LinkedList<>();

        while (completedProcesses < jobList.size()) {
            // 현재 시간까지 도착한 프로세스를 큐에 추가
            for (Process p : jobList) {
                if (p.getArriveTime() <= currentTime && !p.isCompleted() && !processQueue.contains(p)) {
                    processQueue.add(p);
                }
            }

            if (processQueue.isEmpty()) {
                // 도착한 프로세스가 없는 경우, 현재 시간을 증가시킴
                currentTime++;
                continue;
            }

            // 큐에서 프로세스를 꺼내어 처리
            Process currentProcess = processQueue.poll();
            int executeTime = Math.min(currentProcess.getRemainingTime(), timeQuantum);
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - executeTime);
            currentTime += executeTime;

            // 프로세스가 완료된 경우
            if (currentProcess.getRemainingTime() == 0) {
                int arriveTime = currentProcess.getArriveTime();
                int burstTime = currentProcess.getBurstTime();
                int waitingTime = currentTime - arriveTime - burstTime;
                totalWaitingTime += waitingTime;
                int turnaroundTime = waitingTime + burstTime;
                totalTurnaroundTime += turnaroundTime;
                int responseTime = waitingTime; // HRRN의 경우 waiting time이 response time과 동일

                resultList.add(new Result(currentProcess.getProcessId(), burstTime, waitingTime, turnaroundTime, responseTime));
                currentProcess.setCompleted(true);
                completedProcesses++;
            } else {
                // 프로세스가 완료되지 않은 경우 다시 큐에 추가
                processQueue.add(currentProcess);
            }
        }

        double averageWaitingTime = (double) totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / jobList.size();

        printResults(averageWaitingTime, currentTime, averageTurnaroundTime, resultList);
    }

    private void printResults(double averageWaitingTime, int cpuExecutionTime, double averageTurnaroundTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("Priority-Based RR 결과");
        System.out.println("평균 대기 시간 : " + averageWaitingTime);
        System.out.println("평균 Turnaround Time : " + averageTurnaroundTime);
        System.out.println("CPU 실행 시간 : " + cpuExecutionTime);

        resultList.sort(Comparator.comparingInt(Result::getProcessID));

        resultList.forEach(result -> {
            System.out.println("Process" + result.getProcessID() + "의 waiting time : " + result.getWaitingTime() +", turnaround Time : " + result.getTurnaroundTime() +", response Time : " + result.getResponseTime());
        });

        System.out.println("=================================================================");
    }
}