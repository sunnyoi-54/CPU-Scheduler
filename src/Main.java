import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static List<Process> jobList;
    private static DefaultTableModel tableModel;
    private static JTextField timeSliceField;
    private static int timeSlice;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        jobList = new ArrayList<>();
        JFrame frame = new JFrame("CPU Scheduler");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Process input panel
        JPanel inputPanel = createInputPanel(frame);
        mainPanel.add(inputPanel, "inputPanel");

        // Empty panel for the chart
        JPanel chartPanel = new JPanel(new BorderLayout());
        mainPanel.add(chartPanel, "chartPanel");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createInputPanel(JFrame frame) {
        JPanel inputPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Enter Process Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(titleLabel, BorderLayout.NORTH);

        JTextField idField = new JTextField(5);
        JTextField burstTimeField = new JTextField(5);
        JTextField arrivalTimeField = new JTextField(5);
        timeSliceField = new JTextField(5);

        JPanel fieldPanel = new JPanel();
        fieldPanel.add(new JLabel("ID:"));
        fieldPanel.add(idField);
        fieldPanel.add(new JLabel("Burst Time:"));
        fieldPanel.add(burstTimeField);
        fieldPanel.add(new JLabel("Arrival Time:"));
        fieldPanel.add(arrivalTimeField);
        fieldPanel.add(new JLabel("Time Slice:"));
        fieldPanel.add(timeSliceField);

        inputPanel.add(fieldPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Burst Time", "Arrival Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);
        scrollPane.setPreferredSize(new Dimension(580, 200));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, fieldPanel);
        splitPane.setResizeWeight(0.5);
        inputPanel.add(splitPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Process");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    int burstTime = Integer.parseInt(burstTimeField.getText());
                    int arrivalTime = Integer.parseInt(arrivalTimeField.getText());
                    jobList.add(new Process(id, burstTime, arrivalTime));
                    tableModel.addRow(new Object[]{id, burstTime, arrivalTime});
                    idField.setText("");
                    burstTimeField.setText("");
                    arrivalTimeField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for the process details.");
                }
            }
        });

        JButton doneButton = new JButton("Generate Chart");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jobList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please add at least one process.");
                    return;
                }
                timeSlice = Integer.parseInt(timeSliceField.getText());

                List<Results> results = new ArrayList<>();

                List<Process> jobList1 = cloneJobList(jobList);
                List<Process> jobList2 = cloneJobList(jobList);
                List<Process> jobList3 = cloneJobList(jobList);
                List<Process> jobList4 = cloneJobList(jobList);
                List<Process> jobList5 = cloneJobList(jobList);

                FCFS fcfs = new FCFS();
                results.add(fcfs.run(jobList1, new ArrayList<>()));

                SJF sjf = new SJF();
                results.add(sjf.run(jobList2, new ArrayList<>()));

                HRRN hrrn = new HRRN();
                results.add(hrrn.run(jobList3, new ArrayList<>()));

                RoundRobin rr = new RoundRobin();
                results.add(rr.run(jobList4, new ArrayList<>(), timeSlice));

                PriorityBasedRR pbr = new PriorityBasedRR();
                results.add(pbr.run(jobList5, new ArrayList<>(), timeSlice));

                BarChart_AWT chart = new BarChart_AWT("CPU Scheduler", "Data Result", results);
                JPanel chartPanel = new JPanel(new BorderLayout());
                chartPanel.add(chart.getContentPane(), BorderLayout.CENTER);

                mainPanel.add(chartPanel, "chartPanel");
                cardLayout.show(mainPanel, "chartPanel");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(doneButton);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        return inputPanel;
    }

    private static List<Process> cloneJobList(List<Process> original) {
        List<Process> clone = new ArrayList<>();
        for (Process process : original) {
            clone.add(new Process(process.getProcessId(), process.getBurstTime(), process.getArriveTime()));
        }
        return clone;
    }
}