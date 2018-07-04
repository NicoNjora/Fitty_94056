package com.fitty.njora.nicollet.fitty_94056.Model;

public class Session {

    private String gym;
    private String date;
    private String exercise_type;
    private int reps_no;
    private int sets_no;

    private String trainer;

    public Session(String gym, String date, String exercise_type, String trainer, int reps_no, int sets_no) {
        this.gym = gym;
        this.date = date;
        this.exercise_type = exercise_type;
        this.trainer = trainer;
        this.reps_no = reps_no;
        this.sets_no = sets_no;

    }

//    Gym
    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

//    Date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    Exercise Type
    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
    }

//    Trainer
    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

//    Sets
    public Integer getSets() {
        return sets_no;
    }

    public void setSets(Integer sets_no) {
        this.sets_no = sets_no;
    }

//    Reps
    public Integer getReps() {
        return reps_no;
    }

    public void setReps(Integer reps_no) {
        this.reps_no = reps_no;
    }
}
