import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class RoundRobin {
    public static void RR(List<Process> jobList, List<Result> resultList, int timeSlice) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int cpuExecutionTime = 0;
        List<Process> queue = new ArrayList<>(jobList);
        queue.sort(Comparator.comparingInt(Process::getArriveTime));

        while (true) {
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

            Process currentProcess = queue.get(0);
            int workingTime = Math.min(timeSlice, currentProcess.getRemainingTime());
            cpuExecutionTime += workingTime;
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - workingTime);

            for (int i = 1; i < queue.size(); i++) {
                if (cpuExecutionTime >= queue.get(i).getArriveTime() && queue.get(i).getRemainingTime() > 0) {
                    totalWaitingTime += cpuExecutionTime - queue.get(i).getArriveTime();
                }
            }

            if (currentProcess.getRemainingTime() == 0) {
                currentProcess.setCompleted(true);
                int waitingTime = Math.max(0, cpuExecutionTime - currentProcess.getArriveTime() - currentProcess.getBurstTime());
                int turnaroundTime = Math.max(1, cpuExecutionTime - currentProcess.getArriveTime());
                int responseTime = Math.max(0, cpuExecutionTime - currentProcess.getArriveTime());
                resultList.add(new Result(currentProcess.getProcessId(), currentProcess.getBurstTime(), waitingTime, turnaroundTime, responseTime));
                totalTurnaroundTime += turnaroundTime;
                queue.remove(currentProcess);
            } else {
                queue.remove(currentProcess);
                queue.add(currentProcess);
            }
        }

        double averageWaitingTime = (double) totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / jobList.size();
        printResults(averageWaitingTime, cpuExecutionTime, averageTurnaroundTime, resultList);
    }

    private static void printResults(double averageWaitingTime, int cpuExecutionTime, double averageTurnaroundTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("RR 결과");
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
