package Model;

import java.time.LocalDate;

public class Assignment {
    private String assignmentName;
    private LocalDate dueDate;
    private String assignmentStatus;
    private Integer assignmentPriority;
    private String courseCode;

    public Assignment(String name, LocalDate dueDate, String courseCode, String status, Integer priority ) {

    }

    public String getName() {

    }
    public LocalDate getDueDate() {

    }

    public String getStatus() {

    }

    public int getPriority() {

    }

    public String getCourseCode() {

    }

    public void updateStatus(String status) {

    }
    public void updatePriority(int prio) {

    }

    public void updateDueDate(LocalDate date) {

    }


    public static void main(String[] args) {
        // Test if initialization worked
        Assignment assn = new Assignment("Assignment Name", LocalDate.of(2025, 10, 28), "CMPT215", "Not started", 3);

        if (!(assn.getName().equals("Assignment Name"))) {
            System.out.println("Error: getName() did not return the correct name after initialization ");
        }

        LocalDate date = LocalDate.of(2025, 10, 28);
        if (!(assn.getDueDate() = date)) {
            System.out.println("Error: getDueDate() not returning correct due date after initialization");
        }

        if (!(assn.getCourseCode().equals("CMPT215"))) {
            System.out.println("Error: getCourseCode() not returning correct course code ");
        }

        // Test updateStatus()
        assn.updateStatus("In Progress");
        if (!(assn.getStatus().equals("InProgress"))) {
            System.out.println("Error: assignment did not correctly update status");
        }

        // Test updatePriority()
        assn.updatePriority(1);
        if(!(assn.getPriority() = 1)) {
            System.out.println("Error: Assignment did not update priority correctly");
        }

        // Test updateDueDate()
        assn.updateDueDate(LocalDate.of(2025, 10, 25));

        if (!(assn.getDueDate() = LocalDate.of(2025, 10, 25))) {
            System.out.println("Error: getDueDate() not returning correct due date after using updateDueDate()");
        }

    }
}
