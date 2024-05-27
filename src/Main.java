import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*List<Process> jobList = Arrays.asList(
                new Process(1, 7, 0),
                new Process(2, 4, 2),
                new Process(3, 1, 4),
                new Process(4, 4, 5)
        );sjf*/

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

        List<Result> resultList = new ArrayList<>();

        SJF sjf = new SJF();
        sjf.run(jobList, resultList);
    }
}