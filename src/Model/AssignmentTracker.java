package Model;

import java.util.ArrayList;


public class AssignmentTracker {
    private SortingAlgorithm strategy;

    public ArrayList<Assignment> sort(ArrayList<Assignment> assignmentList) {
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
