package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReminderList<T extends Task> implements TaskList<Reminder> {

    private ArrayList<Reminder> reminders;

    /**
     * Constructor for initializing an ReminderList object
     */
    public ReminderList() {
        reminders = new ArrayList<>();
    }

    /**
     * Getter for reminder list
     */
    public ArrayList<Reminder> getList() {
        return reminders;
    }

    /**
     * Adds a Reminder to the ReminderList
     * @param r Reminder object to be added
     */
    public void addItem(Reminder r) {
        reminders.add(r);
    }

    /**
     * Removes a Reminder from the ReminderList
     * @param r Reminder object to be removed
     */
    public void deleteItem(Reminder r) {
        reminders.remove(r);
    }

    /**
     * Returns a list of overdue assignments
     * @return ArrayList of overdue assignments
     */
    public ArrayList<Reminder> getOverdue() {
        ArrayList<Reminder> overdue = new ArrayList<>();
        for (Reminder r : reminders ) {
            if (r.getDueDate().isBefore(LocalDate.now())) {
                overdue.add(r);
            }
        }
        return overdue;
    }

    /**
     * Shows number of assignments to do
     * @return int for number of assignments
     */
    public int getNumberItems() {
        return reminders.size();
    }

    public void clear() {
        reminders.clear();
    }
}
