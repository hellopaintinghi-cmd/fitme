package gui;

import javax.swing.*;
import java.awt.*;
import service.UserService;
import model.User;

public class ProfilePage extends JFrame {

    private User user;
    private UserService userService = new UserService();

    public ProfilePage(User user) {

        this.user = user;

        setTitle("Your Profile");
        setSize(380, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFC1D6"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("୨୧ Your Profile ୨୧");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JTextField nameField = new JTextField(user.getName(), 15);
        JTextField emailField = new JTextField(user.getEmail(), 15);
        JTextField ageField = new JTextField(String.valueOf(user.getAge()), 15);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(Color.WHITE);

        gbc.gridx=0; gbc.gridy=0; panel.add(title, gbc);
        gbc.gridy++; panel.add(new JLabel("Name:"), gbc);
        gbc.gridy++; panel.add(nameField, gbc);
        gbc.gridy++; panel.add(new JLabel("Email:"), gbc);
        gbc.gridy++; panel.add(emailField, gbc);
        gbc.gridy++; panel.add(new JLabel("Age:"), gbc);
        gbc.gridy++; panel.add(ageField, gbc);
        gbc.gridy++; panel.add(saveBtn, gbc);

        add(panel);
        setVisible(true);

        saveBtn.addActionListener(e -> {
            try {
                // Create a NEW User object using the correct constructor
                User updated = new User(
                        user.getId(),
                        nameField.getText(),
                        emailField.getText(),
                        Integer.parseInt(ageField.getText()),
                        user.getDailyGoal() // KEEP existing goal
                );

                if (userService.updateUser(updated)) {
                    JOptionPane.showMessageDialog(this, "Profile Updated 💗");
                    this.user = updated; // Update local reference
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid details 😭");
            }
        });
    }
}
