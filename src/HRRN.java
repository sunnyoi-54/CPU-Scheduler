import java.util.Comparator;
import java.util.List;


public class HRRN {
    public void run(List<Process> jobList, List<Result> resultList) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int completedProcesses = 0;
        int totalTurnaroundTime = 0;

        while (completedProcesses < jobList.size()) {
            // 현재 시간까지 도착한 프로세스 중에서 응답 비율이 가장 높은 프로세스 선택
            Process nextProcess = null;
            double highestResponseRatio = -1;

            for (Process p : jobList) {
                if (p.getArriveTime() <= currentTime && !p.isCompleted()) {
                    int waitingTime = currentTime - p.getArriveTime();
                    double responseRatio = (double) (waitingTime + p.getBurstTime()) / p.getBurstTime();

                    if (responseRatio > highestResponseRatio) {
                        highestResponseRatio = responseRatio;
                        nextProcess = p;
                    }
                }
            }

            if (nextProcess == null) {
                // 도착한 프로세스가 없는 경우, 현재 시간을 증가시킴
                currentTime++;
                continue;
            }

            // 선택된 프로세스를 처리
            int arriveTime = nextProcess.getArriveTime();
            int burstTime = nextProcess.getBurstTime();
            int waitingTime = currentTime - arriveTime;
            totalWaitingTime += waitingTime;
            currentTime += burstTime;
            int turnaroundTime = waitingTime + burstTime;
            totalTurnaroundTime += turnaroundTime;
            int responseTime = waitingTime;

            resultList.add(new Result(nextProcess.getProcessId(), burstTime, waitingTime, turnaroundTime, responseTime));
            nextProcess.setCompleted(true);
            completedProcesses++;
        }

        double averageWaitingTime = (double) totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / jobList.size();

        printResults(averageWaitingTime, currentTime, averageTurnaroundTime, resultList);
    }

    private void printResults(double averageWaitingTime, int cpuExecutionTime, double averageTurnaroundTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("HRRN 결과");
        System.out.println("평균 대기 시간 : " + averageWaitingTime);
        System.out.println("평균 Turnaround Time : " + averageTurnaroundTime);
        System.out.println("CPU 실행 시간 : " + cpuExecutionTime);

        resultList.sort(Comparator.comparingInt(Result::getProcessID));

        resultList.forEach(result -> {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Process " + result.getProcessID() + "의 waiting time : " + result.getWaitingTime());
            System.out.println("Process " + result.getProcessID() + "의 turnaround Time : " + result.getTurnaroundTime());
            System.out.println("Process " + result.getProcessID() + "의 response Time : " + result.getResponseTime());
        });

        System.out.println("=================================================================");
    }
}

