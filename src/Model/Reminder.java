package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Reminder implements Task {
    private String name;
    private LocalDate dueDate;
    private Integer priority;
    private int id;

    /**
     * Initializes object with specified attributes
     * @param name Reminder name
     * @param dueDate Reminder due date
     * @param priority Reminder priority, either 1, 2, 3, where 3 is the most urgent
     */
    public Reminder(String name, LocalDate dueDate, Integer priority ) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.id = -1;
    }

    public Reminder(String name, LocalDate dueDate, int priority, int id) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.id = id;
    }

    /**
     * Getter for reminder name
     * @return String of reminder name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for due date
     * @return LocalDate of reminder due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Getter for priority
     * @return Int of reminder priority, either 1, 2, or 3, where 3 is the most urgent
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Updates priority of reminder
     * @param priority the new priority of the assignment
     */
    public void updatePriority(int priority) {
        this.priority = priority;
    }

    /**
     * Updates due date of reminder
     * @param date the new due date of the reminder
     */
    public void updateDueDate(LocalDate date) {
        this.dueDate = date;
    }

    /**
     * Updates name of reminder
     * @param name String of new name of the reminder
     */
    public void updateName(String name) {
        this.name = name;
    }

    /**
     * @return reminder ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets new reminder ID
     * @param id Integer
     */
    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        // Test if initialization worked
        Reminder r = new Reminder("Task Name", LocalDate.of(2025, 10, 28), 3);

        if (!(r.getName().equals("Task Name"))) {
            System.out.println("Error: getName() did not return the correct name after initialization ");
        }

        LocalDate date = LocalDate.of(2025, 10, 28);
        if (!(Objects.equals(r.getDueDate(), date))) {
            System.out.println("Error: getDueDate() not returning correct due date after initialization");
        }

        // Test updatePriority()
        r.updatePriority(1);
        if(!(r.getPriority() == 1)) {
            System.out.println("Error: Assignment did not update priority correctly");
        }

        // Test updateDueDate()
        r.updateDueDate(LocalDate.of(2025, 10, 25));
        LocalDate newDate = LocalDate.of(2025, 10, 25);
        if (!(Objects.equals(r.getDueDate(), newDate))) {
            System.out.println("Error: getDueDate() not returning correct due date after using updateDueDate()");
        }

        // Test updateName()
        r.updateName("Reminder 2");
        if (!(r.getName().equals("Reminder 2"))) {
            System.out.println("Error: getName() doesn't return correct name after using updateName()");
        }
    }
}
