package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int age;
    private int dailyGoal;

    // Constructor used for registering new user
    public User(String name, String email, String password, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.dailyGoal = 0; // default
    }

    // Constructor used when loading user from DB
    public User(int id, String name, String email, int age, int dailyGoal) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dailyGoal = dailyGoal;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
    public int getDailyGoal() { return dailyGoal; }

    public void setDailyGoal(int goal) { this.dailyGoal = goal; }
}
