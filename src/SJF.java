import java.util.Comparator;
import java.util.List;

public class SJF {
    public void run(List<Process> jobList, List<Result> resultList) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int completedProcesses = 0;

        // 도착 시간 기준으로 프로세스 리스트 정렬
        jobList.sort(Comparator.comparingInt(Process::getArriveTime));

        while (completedProcesses < jobList.size()) {
            // 현재 시간 이전에 도착한 프로세스 중에서 완료되지 않은 프로세스를 선택
            Process shortestJob = null;
            for (Process process : jobList) { //가장 짧은 프로세스 선택
                if (!process.isCompleted() && process.getArriveTime() <= currentTime) { //완료되지 않았고, 도착 시간이 현재 시간보다 작은 프로세스이면
                    if (shortestJob == null || process.getBurstTime() < shortestJob.getBurstTime()) { //제일 짧은 프로세스 고르기
                        shortestJob = process;
                    }
                }
            }

            if (shortestJob == null) { //아직 프로세스가 큐에 없음 -> 현재 시간 증가
                currentTime++;
                continue;
            }

            // 해당 프로세스를 실행
            int startTime = currentTime; //프로세스 시작 시간
            int completionTime = startTime + shortestJob.getBurstTime(); //프로세스 완료 시간
            int turnaroundTime = completionTime - shortestJob.getArriveTime(); //완료 시간 - 도착 시간
            int waitingTime = startTime - shortestJob.getArriveTime(); //시작 시간 - 도착 시간
            int responseTime = waitingTime;  // 비선점형 SJF에서는 waiting time이 response time과 동일

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            // 결과 리스트에 추가
            resultList.add(new Result(shortestJob.getProcessId(), waitingTime, turnaroundTime, responseTime));

            // 프로세스 완료 표시
            shortestJob.setCompleted(true);
            completedProcesses++;
            currentTime = completionTime; //프로세스 동작 완료!
        }


        double averageWaitingTime = (double) totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / jobList.size();
        printResults(averageWaitingTime, currentTime, averageTurnaroundTime, resultList);
    }

    private static void printResults(double averageWaitingTime, int cpuExecutionTime, double averageResponseTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("SJF 결과");
        System.out.println("평균 대기 시간 : " + averageWaitingTime);
        System.out.println("평균 응답 시간 : " + averageResponseTime);
        System.out.println("CPU 실행 시간 : " + cpuExecutionTime);

        resultList.sort(Comparator.comparingInt(Result::getProcessID));

        resultList.forEach(result -> {
            System.out.println("Process" + result.getProcessID() + "의 waiting time : " + result.getWaitingTime() +", turnaround Time : " + result.getTurnaroundTime() +", response Time : " + result.getResponseTime());
        });
    }
}
