package View;
import Controller.Controller;
import Model.Assignment;
import Model.Model;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AssignmentFrame extends JFrame implements Observer {
    private ArrayList<Assignment> assignments;
    private JComboBox<String> sortingmethod;
    private JButton addButton;
    private JOptionPane addAssignmentDialogue;
    private Controller controller;
    private JTable table;
    private JButton sortMenu;
    private JPopupMenu sortPopup;
    private JPopupMenu rightClickPopup;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        refreshTable(model.getAssignments());
    }
    public void refreshTable(ArrayList<Assignment> assignments) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Assignment a : assignments) {
            tableModel.addRow(new Object[] {
                    a.getName(),
                    a.getCourseCode(),
                    a.getStatus(),
                    a.getPriority(),
                    a.getDueDate().toString(),
            });
        }
    }

    public AssignmentFrame() {
        // Initialize with assignments
        assignments = new ArrayList<>();

        JFrame frame = new JFrame("Assignment Tracker");
        JPanel tracker = new JPanel();
        frame.add(tracker);

        // Add buttons for adding assignment and sorting pop-up menu
        JToolBar toolbar = new JToolBar();
        addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.BOLD, 26));
        toolbar.add(addButton);
        toolbar.setFloatable(false);
        tracker.add(toolbar, new FlowLayout());

        sortMenu = new JButton();
        sortPopup = new JPopupMenu();
        JMenuItem priorityOption = new JMenuItem("Priority");
        JMenuItem urgencyOption = new JMenuItem("Urgency");
        JMenuItem randomOption = new JMenuItem("Random");
        sortPopup.add(priorityOption);
        sortPopup.add(urgencyOption);
        sortPopup.add(randomOption);
        toolbar.add(sortMenu);

        // Assignment Tracker part
        String[] columns = {
                "Assignment",
                "Class",
                "Progress",
                "Priority",
                "Due Date"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(table);
        tracker.add(scroll);

        // Right click popup menu on table cell
        rightClickPopup = new JPopupMenu();
        JMenuItem deleteButton = new JMenuItem("Delete");
        rightClickPopup.add(deleteButton);
        table.setComponentPopupMenu(rightClickPopup);



        // Global visual settings
        frame.setSize(890, 1080);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refreshTable(assignments);
    }

    public void assignAddListener(ActionListener listener) {
        this.addButton.addActionListener(listener);
    }

    public void showAddAssignmentDialogue() {
        new AddAssignmentDialogue(this, controller).setVisible(true);
    }

    public void assignSortMenuListener(ActionListener listener) {this.sortMenu.addActionListener(listener);}

    public void showSortMenu() {
        sortPopup.show(sortMenu, 0, sortMenu.getHeight());
    }

    public void assignRightClickListener(MouseListener listener) {
        this.table.addMouseListener(listener);
    }

}

