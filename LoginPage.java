package DAO;

import Beans.Users;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    private UsersDAO userDAO;

    public LoginPage() {
        userDAO = new UsersDAO();
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        frame.add(new JLabel("Username:"));
        usernameField = new JTextField();
        frame.add(usernameField);

        frame.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        frame.add(loginButton);

        messageLabel = new JLabel("");
        frame.add(messageLabel);

        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Users user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            messageLabel.setText("Login Successful!");
            JOptionPane.showMessageDialog(frame, "Welcome, " + user.getUsername());

            // Close login frame and open MainSwingApp
            frame.dispose();
            SwingUtilities.invokeLater(() -> new MainSwingApp());
        } else {
            messageLabel.setText("Invalid credentials, try again.");
            JOptionPane.showMessageDialog(frame, "Login Failed. Invalid Username or Password.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }

}
