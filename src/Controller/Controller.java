package Controller;

import Model.Model;
import Model.Assignment;
import View.AssignmentFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;

import static Model.SortingType.*;

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

        ActionListener prioSortListener = new PrioSortListener();
        this.view.assignPrioSortListener(prioSortListener);

        ActionListener urgentSortListener = new UrgentSortListener();
        this.view.assignUrgentSortListener(urgentSortListener);

        ActionListener randSortListener = new RandSortListener();
        this.view.assignRandSortListener(randSortListener);

        ActionListener deleteListener = new DeleteListener();
        this.view.assignDeleteListener(deleteListener);

        MouseListener tableListener = new TableListener();
        this.view.assignTableListener(tableListener);

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



    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getSelectedRow();
            Assignment a = model.getSortedAssignments().get(row);
            try {
                model.deleteAssignment(a);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class SortMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showSortMenu();
        }
    }

    public class PrioSortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.changeSorting(PRIORITY);
            view.refreshTable(model.getSortedAssignments());
        }
    }

    public class UrgentSortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.changeSorting(URGENCY);
            view.refreshTable(model.getSortedAssignments());
        }
    }

    public class RandSortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.changeSorting(RANDOM);
            view.refreshTable(model.getSortedAssignments());
        }
    }

    public class TableListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                JTable table = (JTable) e.getSource();
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.setRowSelectionInterval(row, row);
                }
                view.showTableMenu(e);
            }
        }
    }


}
