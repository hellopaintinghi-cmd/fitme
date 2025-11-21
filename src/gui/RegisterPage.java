package gui;

import javax.swing.*;
import java.awt.*;
import service.UserService;
import model.User;

public class RegisterPage extends JFrame {

    public RegisterPage() {

        setTitle("FitMe | Sign Up");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFC1D6"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("୨୧ Create Account ୨୧");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField ageField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton regBtn = new JButton("Sign Up");
        regBtn.setBackground(Color.WHITE);

        gbc.insets = new Insets(10,10,10,10);

        gbc.gridx=0; gbc.gridy=0; panel.add(title, gbc);

        gbc.gridy++; panel.add(new JLabel("Name:"), gbc);
        gbc.gridy++; panel.add(nameField, gbc);

        gbc.gridy++; panel.add(new JLabel("Email:"), gbc);
        gbc.gridy++; panel.add(emailField, gbc);

        gbc.gridy++; panel.add(new JLabel("Age:"), gbc);
        gbc.gridy++; panel.add(ageField, gbc);

        gbc.gridy++; panel.add(new JLabel("Password:"), gbc);
        gbc.gridy++; panel.add(passwordField, gbc);

        gbc.gridy++; panel.add(regBtn, gbc);

        add(panel);
        setVisible(true);

        UserService service = new UserService();

        regBtn.addActionListener(e -> {
            User user = new User(
                nameField.getText(),
                emailField.getText(),
                new String(passwordField.getPassword()),
                Integer.parseInt(ageField.getText())
            );

            if (service.registerUser(user)) {
                JOptionPane.showMessageDialog(this, "Account created 💗");
                new LoginPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed 😭");
            }
        });
    }
}
