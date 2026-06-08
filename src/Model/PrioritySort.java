package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class PrioritySort<T extends Task> implements SortingAlgorithm<T> {
    /**
     * Returns list sorted by Priority
     * @return ArrayList of assignments, sorted by priority
     */
    public ArrayList<T> sort(ArrayList<T> items) {
        items.sort(Comparator.comparing(Task::getPriority).reversed());
        return items;
    }
}