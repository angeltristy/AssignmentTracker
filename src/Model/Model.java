package Model;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import static Model.SortingType.PRIORITY;

public class Model extends Observable {
    private SortingType assignmentSort;
    private SortingType reminderSort;
    private TaskTracker assignmentTracker;
    private AssignmentList assignmentList;
    private AssignmentDAO assignmentDAO;
    private TaskTracker reminderTracker;
    private ReminderList reminderList;
    private ReminderDAO reminderDAO;

    public Model() {
        this.reminderDAO = new ReminderDAO();
        this.reminderTracker = new TaskTracker<Reminder>();
        this.assignmentDAO = new AssignmentDAO();
        this.assignmentTracker = new TaskTracker<Assignment>();
        this.assignmentSort = PRIORITY;
        assignmentTracker.changeSorting(assignmentSort);
        reminderTracker.changeSorting(assignmentSort);
        try {
            reminderDAO.initializeDB();
            assignmentDAO.initializeDB();
            this.assignmentList = new AssignmentList();
            this.reminderList = new ReminderList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadRemindersFromDB() throws SQLException {
        ArrayList<Reminder> loaded = reminderDAO.loadAll();
        reminderList.clear();
        for (Reminder r : loaded) {
            reminderList.addItem(r);
        }
        setChanged();
        notifyObservers();
    }

    public void loadAssignmentsFromDB() throws SQLException {
        ArrayList<Assignment> loaded = assignmentDAO.loadAll();
        assignmentList.clear();
        for (Assignment a : loaded) {
            assignmentList.addItem(a);
        }
        setChanged();
        notifyObservers();
    }

    public void addReminder(Reminder reminder) throws SQLException {
        reminderList.addItem(reminder);
        reminderDAO.insert(reminder);
        setChanged();
        notifyObservers();
    }

    /**
     * Adds assignment to AssignmentList
     */
    public void addAssignment(Assignment a) throws SQLException {
        assignmentList.addItem(a);
        assignmentDAO.insert(a);
        setChanged();
        notifyObservers();
    }

    public void deleteReminder(Reminder r) throws SQLException {
        reminderList.deleteItem(r);
        reminderDAO.delete(r);
        setChanged();
        notifyObservers();

    }
    public void deleteAssignment(Assignment a) throws SQLException {
        assignmentList.deleteItem(a);
        assignmentDAO.delete(a);
        setChanged();
        notifyObservers();
    }

    public void changeReminderSort(SortingType sort) {
        this.reminderSort = sort;
        this.reminderTracker.changeSorting(sort);
        this.setChanged();
        this.notifyObservers();
    }
    /**
     * Changes the sorting algorithm type
     * @param sort
     */
    public void changeAssignmentSort(SortingType sort) {
        this.assignmentSort = sort;
        this.assignmentTracker.changeSorting(sort);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Reminder> getReminderList() { return reminderList.getList();}

    /**
     * @return All assignments in AssignmentList
     */
    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList.getList();
    }

    public ArrayList<Reminder> getSortedReminders() { return reminderTracker.sort(reminderList.getList());}
    /**
     * @return All assignments in AssignmentList, sorted according to current sorting algorithm type
     */
    public ArrayList<Assignment> getSortedAssignments() {

        return assignmentTracker.sort(assignmentList.getList());
    }

    /**
     * @return current SortingType value
     */
    public SortingType getSortingType() {
        return this.assignmentSort;
    }
}
