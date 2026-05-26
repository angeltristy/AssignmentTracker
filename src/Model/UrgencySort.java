package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class UrgencySort implements SortingAlgorithm {
    /**
     * Returns list sorted by Urgency
     * Earliest due date comes first, and if they have the same due date, the higher priority comes first, if same priority, name alphabetical
     * @return ArrayList of assignments, sorted by urgency.
     *
     */
    public ArrayList<Assignment> sort(ArrayList<Assignment> assignmentList) {
        assignmentList.sort(
                Comparator.comparing(Assignment::getDueDate)
                        .thenComparing(Comparator.comparing(Assignment::getPriority).reversed())
                        .thenComparing(Assignment::getName));
        return assignmentList;
    }

}
