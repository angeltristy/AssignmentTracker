package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class PrioritySort implements SortingAlgorithm {
    /**
     * Returns list sorted by Priority
     * @return ArrayList of assignments, sorted by priority
     */
    public ArrayList<Assignment> sort(ArrayList<Assignment> assignmentList) {

        assignmentList.sort(Comparator.comparing(Assignment::getPriority).reversed());
        return assignmentList;
    }
}