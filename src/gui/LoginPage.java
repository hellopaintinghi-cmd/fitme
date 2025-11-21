package gui;

import javax.swing.*;
import java.awt.*;
import service.UserService;
import model.User;

public class LoginPage extends JFrame {

    public LoginPage() {

        setTitle("FitMe | Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Soft pink background
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFC1D6"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("୨୧ FitMe Login ୨୧");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.BLACK);

        JTextField emailField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Sign Up");

        loginBtn.setBackground(Color.WHITE);
        registerBtn.setBackground(Color.WHITE);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridy++;
        panel.add(emailField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);

        gbc.gridy++;
        panel.add(loginBtn, gbc);

        gbc.gridy++;
        panel.add(registerBtn, gbc);

        add(panel);
        setVisible(true);

        UserService userService = new UserService();

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passwordField.getPassword());

            User user = userService.loginUser(email, pass);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Welcome " + user.getName() + " 💗");
                new Dashboard(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login 😭");
            }
        });

        registerBtn.addActionListener(e -> {
            new RegisterPage();
            dispose();
        });
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
