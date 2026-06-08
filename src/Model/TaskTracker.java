package Model;

import java.util.ArrayList;

public class TaskTracker<T extends Task> {

    private SortingAlgorithm<T> strategy;

    public ArrayList<T> sort(ArrayList<T> list) {
        return strategy.sort(list);
    }

    public void changeSorting(SortingType sort) {
        switch (sort) {
            case PRIORITY -> strategy = new PrioritySort<T>();
            case URGENCY  -> strategy = new UrgencySort<T>();
            case RANDOM   -> strategy = new RandomSort<T>();
        }
    }
}