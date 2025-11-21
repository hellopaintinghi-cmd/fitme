# Fitness Tracker System 🏋️‍♀️

A Java Swing–based Fitness Tracker application with MySQL database.  
Users can sign up, log in, add strength/cardio workouts, calculate BMI, track daily goals, and estimate calories burned.

---

## ✨ Features

- 🔐 **Login & Sign Up**
  - User registration and secure login
- 🏋️ **Workout Management**
  - Add strength and cardio workouts
  - Store workout details in MySQL
- 📊 **Daily Goals**
  - Set and check daily fitness goals
  - Track progress based on completed workouts
- 🔢 **BMI Calculator**
  - Calculate Body Mass Index from height and weight
  - Simple UI to show result and category
- 🔥 **Calories Burned Estimation**
  - Estimate calories burned based on workout type & duration
- 🖥️ **Desktop UI**
  - Built using Java Swing (forms, buttons, tables, dialogs)

---

## 🛠 Tech Stack

- **Language:** Java
- **GUI:** Swing
- **Database:** MySQL
- **Other:** JDBC, OOP concepts (classes for User, Workout, Goals etc.)

---

## 📁 Project Structure (example)

```text
fitness-tracker/
  ├── src/
  │   ├── ui/              # Swing forms (login, signup, dashboard, etc.)
  │   ├── model/           # POJO classes (User, Workout, Goal)
  │   ├── dao/             # Database access classes using JDBC
  │   └── util/            # Helper classes (DB connection, validation…)
  └── README.md
