package Model;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class AssignmentList {
    private ArrayList<Assignment> assignmentList;

    /**
     * Constructor for initializing an Assignment List object
     */
    public AssignmentList() {
        assignmentList = new ArrayList<>();
    }

    /**
     * Adds an assignment to the assignmentList
     * @param assn Assignment object to be added
     */
    public void addAssignment(Assignment assn) {

    }

    /**
     * Removes an assignment from the assignmentList
     * @param assn Assignment object to be removed
     */
    public void removeAssignment(Assignment assn) {

    }

    /**
     * Returns a list of overdue assignments
     * @return ArrayList of overdue assignments
     */
    public ArrayList<Assignment> getOverdue() {

    }

    /**
     * Shows number of assignments to do
     * @return int for number of assignments
     */
    public int getNumberAssignments() {

    }

    /**
     * Returns list of assignments that have the course code specified
     * @return ArrayList of assignments with the specified course code
     */
    public ArrayList<Assignment> getByCourseCode(String code) {

    }

    /**
     * Returns list sorted by Urgency
     * @return ArrayList of assignments, sorted by urgency
     */
    public ArrayList<Assignment> getSortedByUrgency() {

    }

    /**
     * Returns list sorted by Priority
     * @return ArrayList of assignments, sorted by priority
     */
    public ArrayList<Assignment> getSortedByPriority() {

    }


    public static void main(String[] args) {

        // Test initialized Assignment List
        AssignmentList assnlist = new AssignmentList();

        if (!(assnlist.getNumberAssignments() == 0)) {
            System.out.println("Error: getNumberAssignments() does not return 0 after initializing assignment list");
        }

        // Test addAssignment() and removeAssignment()

        Assignment assn1 = new Assignment("Assignment Name", LocalDate.of(2025, 10, 28), "CMPT215", "Not started", 1);
        assnlist.addAssignment(assn1);

        if (!(assnlist.getNumberAssignments() == 1)) {
            System.out.println("Error: getNumberAssignments() does not return 1 after using addAssignment() ");
        }

        assnlist.removeAssignment(assn1);
        if (!(assnlist.getNumberAssignments() == 0)) {
            System.out.println("Error: getNumberAssignments() does not return 0 after using removeAssignment() ");
        }

        // Test getOverdue()
        assnlist.addAssignment(assn1);
        ArrayList<Assignment> testList;
        testList = assnlist.getOverdue();

        if (!(testList.getFirst().equals(assn1))) {
            System.out.println("Error: getOverdue() does not return list of overdue assignments ");
        }

        // Test getByCourseCode()
        Assignment assn2 = new Assignment("Assignment Name", LocalDate.of(2025, 10, 28), "CMPT215", "Not started", 2);
        Assignment assn3 = new Assignment("Assignment", LocalDate.now(), "CMPT280", "Not started", 3);

        ArrayList<Assignment> courseList = new ArrayList<>();
        courseList = assnlist.getByCourseCode("CMPT215");

        if (!(courseList.size() == 2)) {
            System.out.println("Error: getByCourseCode() does not return list with the correct amount of elements ");
        }

        // Test getSortedByUrgency()
        Assignment urgent1 = new Assignment("Soonest", LocalDate.now().plusDays(1), "CMPT215", "Not started", 1);
        Assignment urgent2 = new Assignment("Later",   LocalDate.now().plusDays(10), "CMPT280", "Not started", 3);
        Assignment urgent3 = new Assignment("Middle",  LocalDate.now().plusDays(3), "CMPT215", "Not started", 2);

        // Same due date to test tie-breaker by priority:
        Assignment urgent4 = new Assignment("SameDayLowPrio",  LocalDate.now().plusDays(5), "CMPT215", "Not started", 1);
        Assignment urgent5 = new Assignment("SameDayHighPrio", LocalDate.now().plusDays(5), "CMPT215", "Not started", 3);

        // Make sure they're in the list (add them intentionally in a scrambled order)
        assnlist.addAssignment(urgent2);
        assnlist.addAssignment(urgent4);
        assnlist.addAssignment(urgent1);
        assnlist.addAssignment(urgent5);
        assnlist.addAssignment(urgent3);

        ArrayList<Assignment> urgencyList = new ArrayList<>();
        urgencyList = assnlist.getSortedByUrgency();

        if (!(urgencyList.size() >= 5)) {
            System.out.println("Error: getSortedByUrgency() returned a list with the wrong size");
        } else {
            // Expected order by due date:
            // urgent1 (1 day), urgent3 (3 days), then the two 5-day ones (prio 3 then prio 1), then urgent2 (10 days)
            if (!(urgencyList.get(0).equals(urgent1) &&
                    urgencyList.get(1).equals(urgent3) &&
                    urgencyList.get(2).equals(urgent5) &&
                    urgencyList.get(3).equals(urgent4) &&
                    urgencyList.get(4).equals(urgent2))) {
                System.out.println("Error: List isn't sorted properly by getSortedByUrgency()");
            }
        }



        // Test getSortedByPriority()
        ArrayList<Assignment> prioSortedList = new ArrayList<>();
        prioSortedList = assnlist.getSortedByPriority();

        if (!(prioSortedList.getFirst().equals(assn3) && prioSortedList.get(1).equals(assn2) && prioSortedList.getLast().equals(assn1))) {
            System.out.println("Error: List isn't sorted properly by getSortedByPriority() ");
        }


    }
}
