import java.util.*;

public class FCFS {
    private List<Process> jobList; // 작업 큐
    private List<Result> resultList; // 결과 리스트

    public List<Result> run(List<Process> jobList, List<Result> resultList){
        // 도착 시간 기준으로 정렬
        jobList.sort(Comparator.comparingInt(Process::getArriveTime));

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process p : jobList) {
            int arriveTime = p.getArriveTime();
            int burstTime = p.getBurstTime();

            // 현재 시간이 프로세스의 도착 시간보다 작은 경우, 도착할 때까지 기다림
            currentTime = Math.max(currentTime, arriveTime); //현재 시간, 도착 시간 둘 중 큰 값으로 현재 시간 변경

            int waitingTime = currentTime - arriveTime; // 대기 시간 계산 = 현재 시간 - 도착 시간
            totalWaitingTime += waitingTime; //전체 대기 시간 += 이 프로세스의 대기 시간 <- 평균 대기 시간을 구하기 위해
            currentTime += burstTime; // 현재 시간 증가 <- 프로세스 실행 완료!
            int turnaroundTime = waitingTime + burstTime; //turnaroundTime = 대기 시간 + cpu 실행 시간
            totalTurnaroundTime += turnaroundTime; //평균 구하기 위해
            int responseTime = waitingTime; //응답 시간은 대기 시간과 동일 (응답이 시작된 시간이기 때문에)

            resultList.add(new Result(p.getProcessId(), waitingTime, turnaroundTime, responseTime)); //결과 리스트에 구한 값 객체 넣기
        }

        double averageWaitingTime = (double)totalWaitingTime / jobList.size();
        double averageTurnaroundTime = (double)totalTurnaroundTime / jobList.size();

        printResults(averageWaitingTime, currentTime, averageTurnaroundTime, resultList); //결과 출력
        return resultList;
    }

    private void printResults(double averageWaitingTime, int cpuExecutionTime, double averageTurnaroundTime, List<Result> resultList) {
        System.out.println("=================================================================");
        System.out.println("FCFS 결과");
        System.out.println("평균 대기 시간 : " + averageWaitingTime);
        System.out.println("평균 Turnaround Time : " + averageTurnaroundTime);
        System.out.println("CPU 실행 시간 : " + cpuExecutionTime);

        resultList.sort(Comparator.comparingInt(Result::getProcessID));

        resultList.forEach(result -> {
            System.out.println("Process" + result.getProcessID() + "의 waiting time : " + result.getWaitingTime() +", turnaround Time : " + result.getTurnaroundTime() +", response Time : " + result.getResponseTime());
        });
    }
}
