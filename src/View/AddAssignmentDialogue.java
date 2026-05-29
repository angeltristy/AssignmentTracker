package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;

public class AddAssignmentDialogue extends JDialog {

    public AddAssignmentDialogue(JFrame parent, Controller controller) {
        super(parent, "Add Assignment", true);

        JTextField nameField = new JTextField(20);
        JTextField courseField = new JTextField(20);
        JTextField statusField = new JTextField(20);
        JTextField priorityField = new JTextField(5);
        JTextField dueDateField = new JTextField(10);

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            try {
                controller.handleAddAssignment(
                        nameField.getText(),
                        courseField.getText(),
                        statusField.getText(),
                        Integer.parseInt(priorityField.getText()),
                        LocalDate.parse(dueDateField.getText())
                );
                dispose();
            } catch (DateTimeException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        setLayout(new GridLayout(6, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Course:"));
        add(courseField);
        add(new JLabel("Status:"));
        add(statusField);
        add(new JLabel("Priority:"));
        add(priorityField);
        add(new JLabel("Due Date (YYYY-MM-DD):"));
        add(dueDateField);
        add(saveButton);

        pack();
        setLocationRelativeTo(parent);
    }
}
