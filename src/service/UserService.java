package service;

import database.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public boolean registerUser(User user) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO users(name, email, password, age) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getAge());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Registration failed");
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String email, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
            return new User(
                 rs.getInt("id"),
                 rs.getString("name"),
                 rs.getString("email"),
                 rs.getInt("age"),
                 rs.getInt("daily_goal")
        );

            }
            return null;

        } catch (Exception e) {
            System.out.println("Login failed");
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(User user) {
    try (Connection con = DBConnection.getConnection()) {
        String query = "UPDATE users SET name=?, email=?, age=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getAge());
        stmt.setInt(4, user.getId());
        stmt.executeUpdate();
        return true;
    } catch (Exception e) {
        System.out.println("Update failed");
        e.printStackTrace();
        return false;
    }
}

public boolean updateGoal(int userId, int goal) {
    try (Connection con = DBConnection.getConnection()) {
        String query = "UPDATE users SET daily_goal=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, goal);
        stmt.setInt(2, userId);
        stmt.executeUpdate();
        return true;
    } catch(Exception e) {
        return false;
    }
}

}



