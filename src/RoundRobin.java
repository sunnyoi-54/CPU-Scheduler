import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoundRobin {
    public static void run(List<Process> jobList, List<Result> resultList, int timeSlice) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int cpuExecutionTime = 0;

        List<Process> queue = new ArrayList<>(jobList);
        queue.sort(Comparator.comparingInt(Process::getArriveTime));

        while (!queue.isEmpty()) {
            boolean allProcessesCompleted = true;
            for (Process process : queue) {
                if (process.getRemainingTime() > 0) {
                    allProcessesCompleted = false;
                    break;
                }
            }
            if (allProcessesCompleted) {
                break;
            }

            Process currentProcess = queue.remove(0); //현재 프로세스 - 큐 맨 앞에 있는 거 가져오기
            if (currentProcess.getArriveTime() > cpuExecutionTime) { //도착 시간이 더 느리면 현재 시간 변경
                cpuExecutionTime = currentProcess.getArriveTime();
            }

            int workingTime = Math.min(timeSlice, currentProcess.getRemainingTime()); //cpu 일하는 시간 결정
            if (currentProcess.getRemainingTime() == currentProcess.getBurstTime()) { //첫 응답이 시작하는 시간이 응답 시간
                currentProcess.setResponseTime(cpuExecutionTime - currentProcess.getArriveTime());
                totalResponseTime += currentProcess.getResponseTime();
            }

            cpuExecutionTime += workingTime; //cpu 일하자!
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - workingTime); //남은 시간 설정

            for (Process process : queue) {
                if (process.getArriveTime() <= cpuExecutionTime && process.getRemainingTime() > 0) { //waiting time 늘리기
                    totalWaitingTime += workingTime;
                }
            }

            if (currentProcess.getRemainingTime() == 0) {
                int turnaroundTime = cpuExecutionTime - currentProcess.getArriveTime();
                int waitingTime = Math.max(0, turnaroundTime - currentProcess.getBurstTime());
                resultList.add(new Result(currentProcess.getProcessId(), currentProcess.getBurstTime(), waitingTime, turnaroundTime, currentProcess.getResponseTime()));
                totalTurnaroundTime += turnaroundTime;
            } else {
                queue.add(currentProcess);
            }
        }

        double averageWaitingTime = (double) totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / jobList.size();
        double averageResponseTime = (double) totalResponseTime / jobList.size();
        printResults(averageWaitingTime, cpuExecutionTime, averageTurnaroundTime, averageResponseTime, resultList);
    }

    private static void printResults(double averageWaitingTime, int cpuExecutionTime, double averageTurnaroundTime, double averageResponseTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("RR 결과");
        System.out.println("평균 대기 시간 : " + averageWaitingTime);
        System.out.println("평균 Turnaround Time : " + averageTurnaroundTime);
        System.out.println("평균 응답 시간 : " + averageResponseTime);
        System.out.println("CPU 실행 시간 : " + cpuExecutionTime);

        resultList.sort(Comparator.comparingInt(Result::getProcessID));

        resultList.forEach(result -> {
            System.out.println("Process" + result.getProcessID() + "의 waiting time : " + result.getWaitingTime() +", turnaround Time : " + result.getTurnaroundTime() +", response Time : " + result.getResponseTime());
        });
        System.out.println("=================================================================");
    }
}

