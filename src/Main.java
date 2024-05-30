import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Results> results = new ArrayList<>();

        List<Process> jobList1 = jobList();
        List<Result> resultList1 = new ArrayList<>();
        FCFS fcfs = new FCFS();
        results.add(fcfs.run(jobList1, resultList1));

        List<Process> jobList2 = jobList();
        List<Result> resultList2 = new ArrayList<>();
        SJF sjf = new SJF();
        results.add(sjf.run(jobList2, resultList2));

        List<Process> jobList3 = jobList();
        List<Result> resultList3 = new ArrayList<>();
        HRRN hrrn = new HRRN();
        results.add(hrrn.run(jobList3, resultList3));

        List<Process> jobList4 = jobList();
        List<Result> resultList4 = new ArrayList<>();
        PriorityBasedRR pbr = new PriorityBasedRR();
        results.add(pbr.run(jobList4, resultList4, 3)); //timeQuantum이 늘어날 수록 성능 우수 해짐..

        List<Process> jobList5 = jobList();
        List<Result> resultList5 = new ArrayList<>();
        RoundRobin rr = new RoundRobin();
        results.add(rr.run(jobList5, resultList5, 3));

        BarChart_AWT chart = new BarChart_AWT("CPU scheduler",
                "data result" , results);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    private static List<Process> jobList() {
        List<Process> jobList = new ArrayList<>();
        jobList.add(new Process(1, 10, 0));
        jobList.add(new Process(2, 5, 2));
        jobList.add(new Process(3, 8, 3));
        jobList.add(new Process(4, 2, 4));
        jobList.add(new Process(5, 6, 8));

        return jobList;
    }
}