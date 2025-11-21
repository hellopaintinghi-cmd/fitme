package service;

import database.DBConnection;
import model.Workout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WorkoutService {

    public void saveWorkout(Workout workout) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO workouts(user_id, type, duration, calories, date) VALUES (?, ?, ?, ?, CURDATE())";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, workout.getUserId());
            stmt.setString(2, workout.getType());
            stmt.setInt(3, workout.getDuration());
            stmt.setInt(4, workout.calculateCalories());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Saving workout failed");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getWorkoutHistory(int userId) {
        ArrayList<String> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT type, duration, calories, date FROM workouts WHERE user_id=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getDate("date") + " — " + rs.getString("type") +
                        " (" + rs.getInt("duration") + " min) → " + rs.getInt("calories") + " cal");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTodayCalories(int userId) {
    try (Connection con = DBConnection.getConnection()) {
        String query = "SELECT SUM(calories) as total FROM workouts WHERE user_id=? AND date = CURDATE()";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("total");
    } catch(Exception e){ }
    return 0;
}

}
