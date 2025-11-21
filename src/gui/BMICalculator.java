package gui;

import java.awt.*;
import javax.swing.*;

public class BMICalculator extends JFrame {

    public BMICalculator() {

        setTitle("BMI Calculator");
        setSize(350, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFC1D6"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("୨୧ BMI Calculator ୨୧");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField heightField = new JTextField(10); // in cm
        JTextField weightField = new JTextField(10); // in kg

        JButton calcBtn = new JButton("Calculate");
        calcBtn.setBackground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Height (cm):"), gbc);
        gbc.gridy++;
        panel.add(heightField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Weight (kg):"), gbc);
        gbc.gridy++;
        panel.add(weightField, gbc);

        gbc.gridy++;
        panel.add(calcBtn, gbc);

        add(panel);
        setVisible(true);

        calcBtn.addActionListener(e -> {
            try {
                double h = Double.parseDouble(heightField.getText()) / 100; // convert to meters
                double w = Double.parseDouble(weightField.getText());
                double bmi = w / (h * h);

                String category;
                if (bmi < 18.5) category = "Underweight 🌙";
                else if (bmi < 24.9) category = "Normal & Healthy 🍃";
                else if (bmi < 29.9) category = "Overweight 🌼";
                else category = "Obese 💛";

                JOptionPane.showMessageDialog(this, 
                        "Your BMI: " + String.format("%.2f", bmi) + "\nCategory: " + category,
                        "BMI Result", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid numbers 😭");
            }
        });
    }
}
