package Controller;

import Model.Model;
import View.AssignmentFrame;

public class Starter {
    public static void main(String[] args) {
        // Create Assignment Frame and model objects
        AssignmentFrame frame = new AssignmentFrame();
        Model model = new Model();

        // Set up Observer Pattern for MVC communication b/t model and view
        model.addObserver(frame);
        Controller controller = new Controller(frame, model);

    }
}
