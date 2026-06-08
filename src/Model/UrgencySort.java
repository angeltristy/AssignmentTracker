package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class UrgencySort<T extends Task> implements SortingAlgorithm<T> {
    /**
     * Returns list sorted by Urgency
     * Earliest due date comes first, and if they have the same due date, the higher priority comes first, if same priority, name alphabetical
     * @return ArrayList of assignments, sorted by urgency.
     *
     */
    public ArrayList<T> sort(ArrayList<T> items) {
        items.sort(
                Comparator.comparing(Task::getDueDate)
                        .thenComparing(Comparator.comparing(Task::getPriority).reversed())
                        .thenComparing(Task::getName));
        return items;
    }
}