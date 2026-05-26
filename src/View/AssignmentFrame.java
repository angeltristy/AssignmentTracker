package View;
import Model.Assignment;

import javax.swing.*;
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
        assignments = new ArrayList<Assignment>();

        for(int i =0; i<5; i++) {
            assignments.add(new JLabel(list[i]));
        }

        JFrame frame = new JFrame("Assignment Tracker");
        frame.setResizable(true);
        frame.setVisible(true);




    }
}

