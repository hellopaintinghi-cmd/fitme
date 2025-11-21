package model;

public class StrengthWorkout extends Workout {

    public StrengthWorkout(int userId, int duration) {
        super(userId, "Strength", duration);
    }

    @Override
    public int calculateCalories() {
        return duration * 6;
    }
}
