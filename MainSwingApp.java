package DAO;

import Beans.Student;
import DAO.StudentDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainSwingApp {

    private StudentDAO studentDAO;
    private JFrame frame;
    private JTextField firstNameField, lastNameField, emailField, idField;
    private JTextArea displayArea;

    public MainSwingApp() {
        studentDAO = new StudentDAO();
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        // Input Fields
        panel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        // Action Buttons
        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton viewAllButton = new JButton("View All Students");

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewAllButton);

        // Display Area
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.setVisible(true);

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());  // Parse the ID from the text field
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();

            Student student = new Student(id, firstName, lastName, email);  // Use the parsed ID
            studentDAO.saveStudent(student);
            displayArea.setText("Student added successfully!");
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid ID format.");
        }
    }

    private void viewStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            Student student = studentDAO.getStudent(id);
            if (student != null) {
                displayArea.setText(student.toString());
            } else {
                displayArea.setText("Student not found.");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid ID format.");
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            Student studentToUpdate = studentDAO.getStudent(id);
            if (studentToUpdate != null) {
                studentToUpdate.setFirstName(firstNameField.getText());
                studentToUpdate.setLastName(lastNameField.getText());
                studentToUpdate.setEmail(emailField.getText());
                studentDAO.updateStudent(studentToUpdate);
                displayArea.setText("Student updated successfully!");
            } else {
                displayArea.setText("Student not found.");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid ID format.");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            studentDAO.deleteStudent(id);
            displayArea.setText("Student deleted successfully!");
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid ID format.");
        }
    }

    private void viewAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        StringBuilder studentList = new StringBuilder("All Students:\n");
        for (Student s : students) {
            studentList.append(s).append("\n");
        }
        displayArea.setText(studentList.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainSwingApp());
    }
}
