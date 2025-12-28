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

    public String getCourseCode() {

    }
    public void updateStatus(String status) {

    }
    public void updatePriority(Integer prio) {

    }


    public static void main(String[] args) {
        // Test if initialization worked
        Assignment assn = new Assignment("Assignment Name", LocalDate.of(2025, 10, 28), "CMPT215", "Not started", 3);

        if (!assn.getName().equals("Assignment Name")) {
            System.out.println("Assignment did not initialize with correct name. ");
        }

    }
}
