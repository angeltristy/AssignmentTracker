package View;
import Model.Assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AssignmentFrame extends JFrame implements Observer {
    private ArrayList<Assignment> assignments;
    private JComboBox<String> sortingmethod;


    @Override
    public void update(Observable o, Object arg) {

    }

    public AssignmentFrame() {
        // Initialize with assignments
        assignments = new ArrayList<>();

        JFrame frame = new JFrame("Assignment Tracker");

        // Assignment Tracker part
        String[] columns = {
                "Assignment Name",
                "Class",
                "Progress",
                "Priority",
                "Due Date"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tableModel.addRow(new Object[]{"1", "John Doe", "Active", "Test", "Test"});

        frame.add(scroll);


        frame.pack();
        frame.setSize(890, 1080);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }
}

