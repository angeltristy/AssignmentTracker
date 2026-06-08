package Model;

import java.time.LocalDate;

public interface Task {
    public String getName();
    public LocalDate getDueDate();
    public int getPriority();
    public void updatePriority(int priority);
}
