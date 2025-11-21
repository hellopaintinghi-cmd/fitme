package model;

public abstract class Workout {
    protected int userId;
    protected String type;
    protected int duration;

    public Workout(int userId, String type, int duration) {
        this.userId = userId;
        this.type = type;
        this.duration = duration;
    }

    public abstract int calculateCalories();

    public int getUserId() { return userId; }
    public String getType() { return type; }
    public int getDuration() { return duration; }
}
