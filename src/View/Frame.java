package View;
import Controller.Controller;
import Model.Assignment;
import Model.Model;
import Model.Reminder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Frame extends JFrame implements Observer {
    private Controller controller;

    // Assignment data
    private ArrayList<Assignment> assignments;

    // Assignment table
    private JTable aTable;

    // Assignment sort menu
    private JButton aSortMenu;
    private JPopupMenu aSortPopup;
    private JMenuItem aPrioSortOption;
    private JMenuItem aUrgentSortOption;
    private JMenuItem aRandSortOption;

    // Assignment add + remove
    private JButton aAddButton;
    private JOptionPane addAssignmentDialogue;
    private JPopupMenu aRightClickPopup;
    private JMenuItem aDeleteButton;

    // Reminder Data
    private ArrayList<Reminder> reminders;

    // Reminder table
    private JTable rTable;

    // Reminder sort menu/popup
    private JButton rSortMenu;
    private JPopupMenu rSortPopup;
    private JMenuItem rPrioSortOption;
    private JMenuItem rUrgentSortOption;
    private JMenuItem rRandSortOption;

    // Reminder add + remove
    private JButton rAddButton;
    private JOptionPane addReminderDialogue;
    private JPopupMenu rRightClickPopup;
    private JMenuItem rDeleteButton;

    // Timer
    private JLabel timerLabel;
    private Timer swingTimer;
    private Duration duration;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        refreshAssignmentTable(model.getAssignmentList());
        refreshReminderTable(model.getReminderList());

        this.duration = model.getRemainingTime();
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
        // Initialize
        assignments = new ArrayList<>();
        reminders = new ArrayList<>();

        JFrame frame = new JFrame("Assignment Tracker");

        // Add trackers to frame
        frame.setLayout(new BorderLayout());
        JPanel aTracker = new JPanel();
        JPanel rTracker = new JPanel();

        //------------------------------------------------------------------------------
        // REMINDER
        //------------------------------------------------------------------------------

        // Reminder toolbar
        JToolBar rToolbar = new JToolBar();
        rAddButton = new JButton("+");
        rAddButton.setFont(new Font("Arial", Font.BOLD, 26));
        rToolbar.add(rAddButton);
        rToolbar.setFloatable(false);
        rTracker.add(rToolbar, new FlowLayout());

        // Reminder sort menu
        rSortMenu = new JButton();
        rSortPopup = new JPopupMenu();
        rPrioSortOption = new JMenuItem("Priority");
        rUrgentSortOption = new JMenuItem("Urgency");
        rRandSortOption = new JMenuItem("Random");

        rSortPopup.add(rPrioSortOption);
        rSortPopup.add(rUrgentSortOption);
        rSortPopup.add(rRandSortOption);
        rToolbar.add(rSortMenu); // Add toolbar

        // Reminder delete right click menu
        rRightClickPopup = new JPopupMenu();
        rDeleteButton = new JMenuItem("Delete");
        rRightClickPopup.add(rDeleteButton);

        // Reminder Tracker table
        String[] columns = {"Name", "Priority", "Due Date"};
        DefaultTableModel rTableModel = new DefaultTableModel(columns, 0);

        rTable = new JTable(rTableModel);
        rTable.setAutoCreateRowSorter(true);
        JScrollPane rScroll = new JScrollPane(rTable);
        rTracker.add(rScroll);

        //------------------------------------------------------------------------------
        // ASSIGNMENT
        //------------------------------------------------------------------------------

        // Assignment toolbar
        JToolBar aToolbar = new JToolBar();
        aAddButton = new JButton("+");
        aAddButton.setFont(new Font("Arial", Font.BOLD, 26));
        aToolbar.add(aAddButton);
        aToolbar.setFloatable(false);
        aTracker.add(aToolbar, new FlowLayout());

        // Assignment sort menu
        aSortMenu = new JButton();
        aSortPopup = new JPopupMenu();
        aPrioSortOption = new JMenuItem("Priority");
        aUrgentSortOption = new JMenuItem("Urgency");
        aRandSortOption = new JMenuItem("Random");

        aSortPopup.add(aPrioSortOption);
        aSortPopup.add(aUrgentSortOption);
        aSortPopup.add(aRandSortOption);
        aToolbar.add(aSortMenu);

        // Assignment Tracker table
        String[] aColumns = {"Assignment", "Class", "Progress", "Priority", "Due Date"};
        DefaultTableModel aTableModel = new DefaultTableModel(aColumns, 0);

        aTable = new JTable(aTableModel);
        aTable.setAutoCreateRowSorter(true);
        JScrollPane aScroll = new JScrollPane(aTable);
        aTracker.add(aScroll);

        // Assignment delete right click menu
        aRightClickPopup = new JPopupMenu();
        aDeleteButton = new JMenuItem("Delete");
        aRightClickPopup.add(aDeleteButton);

        //------------------------------------------------------------------------------
        // TIMER
        //------------------------------------------------------------------------------

        timerLabel = new JLabel("", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        //------------------------------------------------------------------------------
        // VISUAL SETTINGS
        //------------------------------------------------------------------------------

        frame.add(rTracker, BorderLayout.NORTH);
        frame.add(aTracker, BorderLayout.SOUTH);
        frame.add(timerLabel, BorderLayout.CENTER);

        frame.setSize(890, 1080);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refreshAssignmentTable(assignments);
        refreshReminderTable(reminders);

        startUiTimer();
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

    public void startUiTimer() {
        // Triggers UI repaint action once every second (1000ms)
        swingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLabel();
            }
        });
        swingTimer.start();
    }

    private void updateLabel() {
        if (controller == null) {
            return;
        }

        duration = controller.getRemainingTime();

        if (duration == null || duration.isNegative() || duration.isZero()) {
            timerLabel.setText("00:00:00:00");
            return;
        }

        long totalSeconds = duration.getSeconds();
        long days = totalSeconds / 86400;
        long hours = (totalSeconds % 86400) / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        timerLabel.setText(String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds));
    }
}