package Controller;

import Model.Model;
import View.AssignmentFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private AssignmentFrame view;
    private Model model;

    public Controller(AssignmentFrame v, Model m) {
        this.view = v;
        this.model = m;

        // Create and assign an ActionListener to the Add Assignment button
        ActionListener al = new AddButtonListener();
    }

    public class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
