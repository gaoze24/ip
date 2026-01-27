package yoyo.task;

import java.time.LocalDate;

public class Task {
    private boolean isCompleted;
    private String description;
    private String type;

    public Task() {}

    public Task(String type) {
        this.isCompleted = false;
        this.type = type;
    }

    public Task(String description, String type) {
        this.isCompleted = false;
        this.description = description;
        this.type = type;
    }

    public Task(boolean completed, String description) {
        this.isCompleted = completed;
        this.description = description;
    }

    // Method that return a boolean value to indicate if the task is completed.
    public boolean isCompleted() {
        return this.isCompleted;
    }

    // Method that returns the description of the task.
    public String getDescription() {
        return this.description;
    }

    // Method that marks the task as completed.
    public void complete() {
        this.isCompleted = true;
    }

    // Method that marks the task as not completed.
    public void incomplete() {
        this.isCompleted = false;
    }

    public String taskOutput() {
        return this.isCompleted + " | " +  this.description;
    }

    /**
     * Get the type of the task
     * @return The type of the task in String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Check if the task is active on this date.
     * @param date The date to be checked.
     * @return True is the task is active on this date, False otherwise.
     */
    public boolean checkDate(LocalDate date) {
        return false;
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        String status = this.isCompleted ? "X" : " ";
        return "[" + status + "] " + this.description;
    }
}
