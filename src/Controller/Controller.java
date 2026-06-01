package Controller;

import Model.Model;
import Model.Assignment;
import View.AssignmentFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class Controller {
    private AssignmentFrame view;
    private Model model;

    public Controller(AssignmentFrame v, Model m) {
        this.model = m;
        this.view = v;

        model.addObserver(view);

        try {
            model.loadFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Create and assign an ActionListener to the Add Assignment button
        ActionListener addListener = new AddButtonListener();
        this.view.assignAddListener(addListener);

        ActionListener sortListener = new SortMenuListener();
        this.view.assignSortMenuListener(sortListener);

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

    public class RightClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {


            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class SortMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showSortMenu();
        }
    }

    public class PrioritySortButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    public void handleSortPriority() {

    }
}
