package Controller;

import Model.Model;
import Model.Assignment;
import View.AssignmentFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class Controller {
    private AssignmentFrame view;
    private Model model;

    public Controller(AssignmentFrame v, Model m) {
        this.view = v;
        this.model = m;

        // Create and assign an ActionListener to the Add Assignment button
        ActionListener al = new AddButtonListener();
        this.view.assignAddListener(al);
    }

    public class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showAddAssignmentDialogue();
        }
    }

    public void handleAddAssignment(String name, String courseCode, String status, Integer priority, LocalDate dueDate) throws SQLException {
        Assignment assn = new Assignment(name, dueDate, status, priority, courseCode);
        model.addAssignment(assn);
    }
}
