package Model;

import java.util.ArrayList;
import java.util.List;

public interface SortingAlgorithm<T> {
   ArrayList<T> sort(ArrayList<T> items);
}