package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RandomSort<T extends Task> implements SortingAlgorithm<T>  {

    @Override
    public ArrayList<T> sort(ArrayList<T> items) {
        Collections.shuffle(items);
        return items;
    }
}