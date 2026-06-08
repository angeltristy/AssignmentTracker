package Model;

import java.util.ArrayList;

public interface TaskList<T extends Task> {
    public ArrayList<T> getList();
    public void addItem(T item);
    public void deleteItem(T item);
    public ArrayList<T> getOverdue();
    public int getNumberItems();
}
