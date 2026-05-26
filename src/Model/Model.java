package Model;

import java.util.Observable;

public class Model extends Observable {
    SortingType sort;
    AssignmentTracker tracker;
    public void changeSorting(SortingType sort) {
        this.sort = sort;
        this.tracker.changeSorting(sort);
        this.setChanged();
        this.notifyObservers();
    }
}
