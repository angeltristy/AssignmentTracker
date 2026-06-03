package Model;

import java.util.ArrayList;
import java.util.Collections;

public class RandomSort implements SortingAlgorithm {

    @Override
    public ArrayList<Assignment> sort(ArrayList<Assignment> assignments) {
        Collections.shuffle(assignments);
        return assignments;
    }
}
