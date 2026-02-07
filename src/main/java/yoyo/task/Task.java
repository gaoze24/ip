package yoyo.task;

import java.time.LocalDate;

/**
 * Base class for all types of tasks.
 * Has attributes completed, description, and type.
 */
public class Task {
    private boolean isCompleted;
    private String description;
    private String type;

    /**
     * Initialise the Task class.
     */
    public Task() {}

    /**
     * Initialise the Task class with a type.
     * @param type type of the task
     */
    public Task(String type) {
        this.isCompleted = false;
        this.type = type;
    }

    /**
     * Initialise the Task class with a description and a type.
     * @param description description of the task
     * @param type type of the task
     */
    public Task(String description, String type) {
        assert description != null : "Task description cannot be null";
        assert type != null : "Task type cannot be null";

        this.isCompleted = false;
        this.description = description;
        this.type = type;
    }

    /**
     * Initialise the Task class with a completed status and a description.
     * @param completed completed status of the task
     * @param description description of the task
     */
    public Task(boolean completed, String description) {
        this.isCompleted = completed;
        this.description = description;
    }

    /**
     * Check if the task is completed.
     * @return True if the task is completed, False otherwise.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Get the description of the task.
     * @return The description of the task in String format.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the task as complete.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Set the task as incomplete.
     */
    public void incomplete() {
        this.isCompleted = false;
    }

    /**
     * Return a customized representation of the task for file storage purposes.
     * @return A customized representation of the task in String format.
     */
    public String getTaskOutput() {
        return this.isCompleted + " | " + this.description;
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
    public boolean isCorrectDate(LocalDate date) {
        return false;
    }

    /**
     * Return a customized representation of the task.
     * @return A customized representation of the task in String format.
     */
    @Override
    public String toString() {
        String status = this.isCompleted ? "X" : " ";
        return "[" + status + "] " + this.description;
    }
}
