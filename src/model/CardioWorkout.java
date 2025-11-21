package model;

public class CardioWorkout extends Workout {

    public CardioWorkout(int userId, int duration) {
        super(userId, "Cardio", duration);
    }

    @Override
    public int calculateCalories() {
        return duration * 8;
    }
}
