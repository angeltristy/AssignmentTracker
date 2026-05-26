package Model;

import java.util.ArrayList;


public class AssignmentTracker {
    private SortingType currentSort;
    private SortingAlgorithm strategy;

    public ArrayList<Assignment> list(ArrayList<Assignment> assignmentList) {
        return strategy.sort(assignmentList);
    }

    public void changeSorting(SortingType sort) {
        switch(sort) {
            case PRIORITY -> {
                strategy = new PrioritySort();
            }
            case URGENCY -> {
                strategy = new UrgencySort();
            }
            case RANDOM -> {
                strategy = new RandomSort();
            }

        }
    }
}
