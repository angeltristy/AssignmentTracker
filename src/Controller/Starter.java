package Controller;

import Model.Model;
import View.Frame;

public class Starter {
    public static void main(String[] args) {
        // Create Assignment Frame and model objects
        Frame view = new Frame();
        Model model = new Model();
        model.addObserver(view);
        Controller controller = new Controller(view, model);
        view.setController(controller);
    }
}