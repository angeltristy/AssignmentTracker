package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {
    private String name;
    private LocalDate dueDate;
    private String status;
    private Integer priority;
    private String courseCode;

    /**
     * Initializes object with specified attributes
     * @param name
     * @param dueDate
     * @param courseCode
     * @param status
     * @param priority
     */
    public Assignment(String name, LocalDate dueDate, String courseCode, String status, Integer priority ) {
        this.name = name;
        this.dueDate = dueDate;
        this.courseCode = courseCode;
        this.status = status;
        this.priority = priority;
    }

    /**
     * Getter for assignment name
     * @return String of assignment name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for due date
     * @return LocalDate of assignment due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Getter for status
     * @return String of assignment status, either "Not started", "In Progress", or "Finished"
     */
    public String getStatus() {
        return status;
    }

    /**
     * Getter for priority
     * @return Int of assignment priority, either 1, 2, or 3, where 3 is the most urgent
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Getter for course code
     * @return String of assignment's course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Updates status of assignment
     * @param status the new status of the assignment
     */
    public void updateStatus(String status) {
        this.status = status;
    }

    /**
     * Updates priority of assignment
     * @param prio the new priority of the assignment
     */
    public void updatePriority(int prio) {
        this.priority = prio;
    }

    /**
     * Updates due date of assignment
     * @param date the new due date of the assignment
     */
    public void updateDueDate(LocalDate date) {
        this.dueDate = date;
    }

    public static void main(String[] args) {
        // Test if initialization worked
        Assignment assn = new Assignment("Assignment Name", LocalDate.of(2025, 10, 28), "CMPT215", "Not started", 3);

        if (!(assn.getName().equals("Assignment Name"))) {
            System.out.println("Error: getName() did not return the correct name after initialization ");
        }

        LocalDate date = LocalDate.of(2025, 10, 28);
        if (!(Objects.equals(assn.getDueDate(), date))) {
            System.out.println("Error: getDueDate() not returning correct due date after initialization");
        }

        if (!(assn.getCourseCode().equals("CMPT215"))) {
            System.out.println("Error: getCourseCode() not returning correct course code ");
        }

        // Test updateStatus()
        assn.updateStatus("In Progress");
        if (!(assn.getStatus().equals("In Progress"))) {
            System.out.println("Error: assignment did not correctly update status");
        }

        // Test updatePriority()
        assn.updatePriority(1);
        if(!(assn.getPriority() == 1)) {
            System.out.println("Error: Assignment did not update priority correctly");
        }

        // Test updateDueDate()
        assn.updateDueDate(LocalDate.of(2025, 10, 25));
        LocalDate newDate = LocalDate.of(2025, 10, 25);
        if (!(Objects.equals(assn.getDueDate(), newDate))) {
            System.out.println("Error: getDueDate() not returning correct due date after using updateDueDate()");
        }

    }
}
