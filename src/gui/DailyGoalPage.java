package gui;

import javax.swing.*;
import java.awt.*;
import service.UserService;
import service.WorkoutService;
import model.User;

public class DailyGoalPage extends JFrame {

    public DailyGoalPage(User user) {
        setTitle("Your Daily Goal");
        setSize(350, 300);
        setLocationRelativeTo(null);

        UserService userService = new UserService();
        WorkoutService workoutService = new WorkoutService();

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFC1D6"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("୨୧ Daily Goal ୨୧");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField goalField = new JTextField(String.valueOf(user.getDailyGoal()), 10);

        int today = workoutService.getTodayCalories(user.getId());
        JLabel progressLabel = new JLabel("Today: " + today + " / " + user.getDailyGoal() + " cal");

        JButton saveBtn = new JButton("Save Goal");
        saveBtn.setBackground(Color.WHITE);

        gbc.gridx=0; gbc.gridy=0; panel.add(title, gbc);
        gbc.gridy++; panel.add(new JLabel("Set Goal (cal/day):"), gbc);
        gbc.gridy++; panel.add(goalField, gbc);
        gbc.gridy++; panel.add(progressLabel, gbc);
        gbc.gridy++; panel.add(saveBtn, gbc);

        add(panel);
        setVisible(true);

        saveBtn.addActionListener(e -> {
            int newGoal = Integer.parseInt(goalField.getText());
            if(userService.updateGoal(user.getId(), newGoal)) {
                user.setDailyGoal(newGoal);
                JOptionPane.showMessageDialog(this, "Goal Updated 💗");
                progressLabel.setText("Today: " + today + " / " + newGoal + " cal");
            }
        });
    }
}
