import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Process> jobList1 = jobList();
        List<Result> resultList1 = new ArrayList<>();
        FCFS fcfs = new FCFS();
        fcfs.run(jobList1, resultList1);

        List<Process> jobList2 = jobList();
        List<Result> resultList2 = new ArrayList<>();
        HRRN hrrn = new HRRN();
        hrrn.run(jobList2, resultList2);

        List<Process> jobList3 = jobList();
        List<Result> resultList3 = new ArrayList<>();
        SJF sjf = new SJF();
        sjf.run(jobList3, resultList3);

        List<Process> jobList4 = jobList();
        List<Result> resultList4 = new ArrayList<>();
        PriorityBasedRR pbr = new PriorityBasedRR();
        pbr.run(jobList4, resultList4, 2); //timeQuantum이 늘어날 수록 성능 우수 해짐..

        List<Process> jobList5 = jobList();
        List<Result> resultList5 = new ArrayList<>();
        RoundRobin rr = new RoundRobin();
        rr.run(jobList5, resultList5, 3);
    }

    private static List<Process> jobList() {
        List<Process> jobList = new ArrayList<>();
        jobList.add(new Process(1, 6, 0));
        jobList.add(new Process(2, 3, 1));
        jobList.add(new Process(3, 1, 2));
        jobList.add(new Process(4, 4, 3));
        return jobList;
    }
}