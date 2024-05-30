import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class BarChart_AWT extends ApplicationFrame {
    private final List<Results> resultsList;

    public BarChart_AWT(String applicationTitle, String chartTitle, List<Results> resultsList) {
        super(applicationTitle);
        this.resultsList = resultsList;

        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "CPU scheduler",
                "time",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        final String awt = "average waiting time";
        final String art = "average response time";
        final String att = "average turnaround time";
        final String fcfs = "FCFS";
        final String sjf = "SJF";
        final String hrrn = "HRRN";
        final String rr = "RR";
        final String pbrr = "PBRR";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // average waiting time
        dataset.addValue(resultsList.get(0).getAverageWaitingTime(), awt, fcfs);
        dataset.addValue(resultsList.get(1).getAverageWaitingTime(), awt, sjf);
        dataset.addValue(resultsList.get(2).getAverageWaitingTime(), awt, hrrn);
        dataset.addValue(resultsList.get(3).getAverageWaitingTime(), awt, rr);
        dataset.addValue(resultsList.get(4).getAverageWaitingTime(), awt, pbrr);

        // average response time
        dataset.addValue(resultsList.get(0).getAverageResponseTime(), art, fcfs);
        dataset.addValue(resultsList.get(1).getAverageResponseTime(), art, sjf);
        dataset.addValue(resultsList.get(2).getAverageResponseTime(), art, hrrn);
        dataset.addValue(resultsList.get(3).getAverageResponseTime(), art, rr);
        dataset.addValue(resultsList.get(4).getAverageResponseTime(), art, pbrr);

        // average turnaround time
        dataset.addValue(resultsList.get(0).getAverageTurnaroundTime(), att, fcfs);
        dataset.addValue(resultsList.get(1).getAverageTurnaroundTime(), att, sjf);
        dataset.addValue(resultsList.get(2).getAverageTurnaroundTime(), att, hrrn);
        dataset.addValue(resultsList.get(3).getAverageTurnaroundTime(), att, rr);
        dataset.addValue(resultsList.get(4).getAverageTurnaroundTime(), att, pbrr);

        return dataset;
    }
}