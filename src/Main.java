import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Process> FCFSList = Arrays.asList( //동시에 도착
                new Process(1, 24, 2),
                new Process(2, 3, 0),
                new Process(3, 3, 1)
        );

        List<Result> resultList1 = new ArrayList<>();

        FCFS fcfs = new FCFS();
        fcfs.run(FCFSList, resultList1);

        List<Process> HRRNList = new ArrayList<>();
        HRRNList.add(new Process(1, 3, 0));
        HRRNList.add(new Process(2, 7, 1));
        HRRNList.add(new Process(3, 2, 3));
        HRRNList.add(new Process(4, 5, 5));
        HRRNList.add(new Process(5, 3, 6));
        List<Result> resultList2 = new ArrayList<>();

        HRRN hrrn = new HRRN();
        hrrn.run(HRRNList, resultList2);

        List<Process> jobList = new ArrayList<>();
        // 프로세스 ID, 도착 시간, CPU 버스트 시간
        jobList.add(new Process(1, 7, 0));
        jobList.add(new Process(2, 4, 2));
        jobList.add(new Process(3, 1, 4));
        jobList.add(new Process(4, 4, 5));

        List<Result> resultList3 = new ArrayList<>();

        SJF sjf = new SJF();
        sjf.run(jobList, resultList3);

        List<Process> pbrList = new ArrayList<>();
        pbrList.add(new Process(1, 3, 0));
        pbrList.add(new Process(2, 7, 1));
        pbrList.add(new Process(3, 2, 3));
        pbrList.add(new Process(4, 5, 5));
        pbrList.add(new Process(5, 3, 6));

        List<Result> resultList4 = new ArrayList<>();

        PriorityBasedRR pbr = new PriorityBasedRR();
        pbr.run(pbrList, resultList4, 3); //timeQuantum이 늘어날 수록 성능 우수 해짐..


        List<Process> jobList4 = new ArrayList<>();
        jobList4.add(new Process(1, 53, 0));
        jobList4.add(new Process(2, 17, 0));
        jobList4.add(new Process(3, 68, 0));
        jobList4.add(new Process(4, 24, 0));

        List<Result> resultList = new ArrayList<>();
        RoundRobin.RR(jobList4, resultList, 20);
    }
}