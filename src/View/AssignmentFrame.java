package View;
import Controller.Controller;
import Model.Assignment;
import Model.Model;

import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
    private JMenuItem prioSortOption;
    private JMenuItem urgentSortOption;
    private JMenuItem randSortOption;
    private JMenuItem deleteButton;

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
        prioSortOption = new JMenuItem("Priority");
        urgentSortOption = new JMenuItem("Urgency");
        randSortOption = new JMenuItem("Random");

        sortPopup.add(prioSortOption);
        sortPopup.add(urgentSortOption);
        sortPopup.add(randSortOption);
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
        deleteButton = new JMenuItem("Delete");
        rightClickPopup.add(deleteButton);




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

    public void showTableMenu(MouseEvent e) {
        rightClickPopup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void assignPrioSortListener(ActionListener listener) {
        prioSortOption.addActionListener(listener);
    }

    public void assignUrgentSortListener(ActionListener listener) {
        urgentSortOption.addActionListener(listener);
    }

    public void assignRandSortListener(ActionListener listener) {
        randSortOption.addActionListener(listener);
    }

    public void assignDeleteListener(ActionListener listener) {
        this.deleteButton.addActionListener(listener);
    }

    public void assignTableListener(MouseListener listener) {
        this.table.addMouseListener(listener);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

}

