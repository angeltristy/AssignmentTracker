package View;
import Controller.Controller;
import Model.Assignment;
import Model.Model;
import Model.Reminder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Frame extends JFrame implements Observer {
    private ArrayList<Assignment> assignments;
    private JComboBox<String> sortingmethod;
    private JButton aAddButton;
    private JOptionPane addAssignmentDialogue;
    private Controller controller;
    private JTable aTable;
    private JButton aSortMenu;
    private JPopupMenu aSortPopup;
    private JPopupMenu aRightClickPopup;
    private JMenuItem aPrioSortOption;
    private JMenuItem aUrgentSortOption;
    private JMenuItem aRandSortOption;
    private JMenuItem aDeleteButton;

    private ArrayList<Reminder> reminders;

    private JButton rAddButton;
    private JOptionPane addReminderDialogue;

    private JTable rTable;
    private JButton rSortMenu;
    private JPopupMenu rSortPopup;
    private JPopupMenu rRightClickPopup;
    private JMenuItem rPrioSortOption;
    private JMenuItem rUrgentSortOption;
    private JMenuItem rRandSortOption;
    private JMenuItem rDeleteButton;



    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        refreshAssignmentTable(model.getAssignmentList());
        refreshReminderTable(model.getReminderList());
    }
    public void refreshAssignmentTable(ArrayList<Assignment> assignments) {
        DefaultTableModel aTableModel = (DefaultTableModel) aTable.getModel();
        aTableModel.setRowCount(0);

        for (Assignment a : assignments) {
            aTableModel.addRow(new Object[] {
                    a.getName(),
                    a.getCourseCode(),
                    a.getStatus(),
                    a.getPriority(),
                    a.getDueDate().toString(),
            });
        }
    }


    private void refreshReminderTable(ArrayList<Reminder> reminders) {
        DefaultTableModel rTableModel = (DefaultTableModel) rTable.getModel();
        rTableModel.setRowCount(0);

        for (Reminder r : reminders) {
            rTableModel.addRow(new Object[] {
                    r.getName(),
                    r.getPriority(),
                    r.getDueDate()
            });
        }
    }

    public Frame() {
        // Initialize with assignments
        assignments = new ArrayList<>();
        reminders = new ArrayList<>();

        JFrame frame = new JFrame("Assignment Tracker");
        JPanel rTracker = new JPanel();
        JToolBar rToolbar = new JToolBar();
        rAddButton = new JButton("+");
        rAddButton.setFont(new Font("Arial", Font.BOLD, 26));
        rToolbar.add(rAddButton);
        rToolbar.setFloatable(false);
        rTracker.add(rToolbar, new FlowLayout());

        rSortMenu = new JButton();
        rSortPopup = new JPopupMenu();
        rPrioSortOption = new JMenuItem("Priority");
        rUrgentSortOption = new JMenuItem("Urgency");
        rRandSortOption = new JMenuItem("Random");

        rSortPopup.add(rPrioSortOption);
        rSortPopup.add(rUrgentSortOption);
        rSortPopup.add(rRandSortOption);
        rToolbar.add(rSortMenu);

        // Assignment Tracker part
        String[] columns = {
                "Name",
                "Priority",
                "Due Date"
        };
        DefaultTableModel rTableModel = new DefaultTableModel(columns, 0);

        rTable = new JTable(rTableModel);
        rTable.setAutoCreateRowSorter(true);
        JScrollPane rScroll = new JScrollPane(rTable);
        rTracker.add(rScroll);

        // Right click popup menu on table cell
        rRightClickPopup = new JPopupMenu();
        rDeleteButton = new JMenuItem("Delete");
        rRightClickPopup.add(rDeleteButton);

        // Add trackers to frame
        frame.setLayout(new BorderLayout());
        JPanel aTracker = new JPanel();
        frame.add(rTracker, BorderLayout.NORTH);
        frame.add(aTracker, BorderLayout.CENTER);

        // Add buttons for adding assignment and sorting pop-up menu
        JToolBar aToolbar = new JToolBar();
        aAddButton = new JButton("+");
        aAddButton.setFont(new Font("Arial", Font.BOLD, 26));
        aToolbar.add(aAddButton);
        aToolbar.setFloatable(false);
        aTracker.add(aToolbar, new FlowLayout());

        aSortMenu = new JButton();
        aSortPopup = new JPopupMenu();
        aPrioSortOption = new JMenuItem("Priority");
        aUrgentSortOption = new JMenuItem("Urgency");
        aRandSortOption = new JMenuItem("Random");

        aSortPopup.add(aPrioSortOption);
        aSortPopup.add(aUrgentSortOption);
        aSortPopup.add(aRandSortOption);
        aToolbar.add(aSortMenu);

        // Assignment Tracker part
        String[] aColumns = {
                "Assignment",
                "Class",
                "Progress",
                "Priority",
                "Due Date"
        };
        DefaultTableModel aTableModel = new DefaultTableModel(aColumns, 0);

        aTable = new JTable(aTableModel);
        aTable.setAutoCreateRowSorter(true);
        JScrollPane aScroll = new JScrollPane(aTable);
        aTracker.add(aScroll);

        // Right click popup menu on table cell
        aRightClickPopup = new JPopupMenu();
        aDeleteButton = new JMenuItem("Delete");
        aRightClickPopup.add(aDeleteButton);

        // Global visual settings
        frame.setSize(890, 1080);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refreshAssignmentTable(assignments);
        refreshReminderTable(reminders);
    }

    public void assignAddListener(ActionListener listener) {
        this.aAddButton.addActionListener(listener);
    }

    public void showAddAssignmentDialogue() {
        new AddAssignmentDialogue(this, controller).setVisible(true);
    }

    public void assignSortMenuListener(ActionListener listener) {this.aSortMenu.addActionListener(listener);}

    public void showSortMenu() {
        aSortPopup.show(aSortMenu, 0, aSortMenu.getHeight());
    }

    public void showTableMenu(MouseEvent e) {
        aRightClickPopup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void assignPrioSortListener(ActionListener listener) {
        aPrioSortOption.addActionListener(listener);
    }

    public void assignUrgentSortListener(ActionListener listener) {
        aUrgentSortOption.addActionListener(listener);
    }

    public void assignRandSortListener(ActionListener listener) {
        aRandSortOption.addActionListener(listener);
    }

    public void assignDeleteListener(ActionListener listener) {
        this.aDeleteButton.addActionListener(listener);
    }

    public void assignTableListener(MouseListener listener) {
        this.aTable.addMouseListener(listener);
    }

    public int getSelectedRow() {
        return aTable.getSelectedRow();
    }
}