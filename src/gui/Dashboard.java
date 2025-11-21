package gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.CardioWorkout;
import model.StrengthWorkout;
import model.User;
import service.WorkoutService;

public class Dashboard extends JFrame {

    private User user;
    private WorkoutService workoutService = new WorkoutService();

    public Dashboard(User user) {
        this.user = user;

        setTitle("FitMe Dashboard");
        setSize(480, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFC1D6"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("୨୧ Welcome, " + user.getName() + " ୨୧", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton addWorkoutBtn = createCuteButton("Add Workout");
        JButton viewHistoryBtn = createCuteButton("View History");
        JButton bmiBtn = createCuteButton("BMI Calculator");
        bmiBtn.addActionListener(e -> new BMICalculator());

        JButton profileBtn = createCuteButton("Profile");
        profileBtn.addActionListener(e -> new ProfilePage(user));

        JButton goalsBtn = createCuteButton("Daily Goal");
        goalsBtn.addActionListener(e -> new DailyGoalPage(user));

        // JButton graphBtn = createCuteButton("Weekly Progress");
        JButton logoutBtn = createCuteButton("Logout");

        panel.add(title);
        panel.add(addWorkoutBtn);
        panel.add(viewHistoryBtn);
        panel.add(bmiBtn);
        panel.add(profileBtn);
        panel.add(goalsBtn);
        //panel.add(graphBtn);
        panel.add(logoutBtn);

        add(panel);
        setVisible(true);

        addWorkoutBtn.addActionListener(e -> showAddWorkoutDialog());
        viewHistoryBtn.addActionListener(e -> showHistory());
        logoutBtn.addActionListener(e -> { new LoginPage(); dispose(); });
    }

    private JButton createCuteButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBorder(BorderFactory.createLineBorder(Color.PINK, 2, true));
        return btn;
    }

    private void showAddWorkoutDialog() {
        String[] options = {"Cardio", "Strength"};
        String type = (String) JOptionPane.showInputDialog(this, "Select Workout Type:",
                "Add Workout", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (type == null) return;

        String durationStr = JOptionPane.showInputDialog(this, "Enter duration (minutes):");
        if (durationStr == null) return;

        int duration = Integer.parseInt(durationStr);

        if (type.equals("Cardio")) {
            workoutService.saveWorkout(new CardioWorkout(user.getId(), duration));
        } else {
            workoutService.saveWorkout(new StrengthWorkout(user.getId(), duration));
        }

        JOptionPane.showMessageDialog(this, "Workout Added 💗");
    }

    private void showHistory() {
        ArrayList<String> list = workoutService.getWorkoutHistory(user.getId());

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No workouts yet 😭");
            return;
        }

        JTextArea area = new JTextArea(String.join("\n", list));
        area.setEditable(false);
        area.setBackground(Color.WHITE);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(300, 300));

        JOptionPane.showMessageDialog(this, scroll, "Workout History", JOptionPane.PLAIN_MESSAGE);
    }
}
