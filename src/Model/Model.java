package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
    private SortingType sort;
    private AssignmentTracker tracker = new AssignmentTracker();
    private AssignmentList list;


    /**
     * Adds assignment to AssignmentList
     * @param assignment
     */
    public void addAssignment(Assignment assignment) {
        list.addAssignment(assignment);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Changes the sorting algorithm type
     * @param sort
     */
    public void changeSorting(SortingType sort) {
        this.sort = sort;
        this.tracker.changeSorting(sort);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return All assignments in AssignmentList
     */
    public ArrayList<Assignment> getAssignments() {
        return list.getAssignments();
    }

    /**
     * @return All assignments in AssignmentList, sorted according to current sorting algorithm type
     */
    public ArrayList<Assignment> getSortedAssignments() {
        return tracker.sort(list.getAssignments());
    }

    /**
     * @return current SortingType value
     */
    public SortingType getSortingType() {
        return this.sort;
    }
}
