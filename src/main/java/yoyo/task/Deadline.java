package yoyo.task;

import java.time.LocalDate;

/**
 * A subclass of the Task class, with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Initialise a Deadlines task.
     * @param name Name of the task
     * @param deadline Deadline of the task
     */
    public Deadline(String name, String deadline) {
        super(name, "deadline");
        assert deadline != null : "Deadline string cannot be null";
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Initialise a Deadlines task with a completed status.
     * @param isCompleted Completed status of the task
     * @param description Description of the task
     * @param deadline Deadline of the task
     */
    public Deadline(boolean isCompleted, String description, String deadline) {
        super(isCompleted, description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Get the type of the task
     * @return The type of the task in String
     */
    @Override
    public String getType() {
        return "Deadline";
    }

    /**
     * Check if the task is active on this date.
     * @param date The date to be checked.
     * @return True is the task is active on this date, False otherwise.
     */
    @Override
    public boolean isCorrectDate(LocalDate date) {
        return this.deadline.isEqual(date);
    }

    /**
     * Override taskOutput to return information about the task.
     * @return Information about the task in String format.
     */
    @Override
    public String getTaskOutput() {
        return "D | " + super.getTaskOutput() + " | " + this.deadline;
    }

    /**
     * Override toString to return a customized representation of the task.
     * @return A customized representation of the task in String format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}

