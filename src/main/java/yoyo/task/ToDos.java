package yoyo.task;

import java.time.LocalDate;

/**
 * A subclass of the Task class, with no special features.
 */
public class ToDos extends Task {
    /**
     * Initialise a ToDos task.
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description, "todo");

    }

    /**
     * Initialise a ToDos task with a completed status.
     * @param completed The completed status of the task.
     * @param description The description of the task.
     */
    public ToDos(boolean completed, String description) {
        super(completed, description);
    }

    /**
     * Get the type of the task
     * @return The type of the task in String
     */
    @Override
    public String getType() {
        return "Todo";
    }

    /**
     * Check if the task is active on this date.
     * @param date The date to be checked.
     * @return True is the task is active on this date, False otherwise.
     */
    @Override
    public boolean checkDate(LocalDate date) {
        return false;
    }

    /**
     * Override taskOutput to return information about the task.
     * @return Information about the task in String format.
     */
    @Override
    public String taskOutput() {
        return "T | " + super.taskOutput();
    }

    /**
     * Override toString to return a customized representation of the task.
     * @return A customized representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
