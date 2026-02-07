package yoyo.task;

import java.time.LocalDate;

/**
 * A subclass of the Task class has a start and end date.
 */
public class Events extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Initialise an Event task.
     * @param name Name of the event
     * @param start Start date of the event
     * @param end End date of the event
     */
    public Events(String name, String start, String end) {
        super(name, "event");
        assert start != null : "Start date string cannot be null";
        assert end != null : "End date string cannot be null";
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Initialise an Event task with a completed status.
     * @param completed Completed status of the task.
     * @param name Name of the event
     * @param start Start date of the event
     * @param end End date of the event
     */
    public Events(boolean completed, String name, String start, String end) {
        super(completed, name);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Get the type of the task
     * @return The type of the task in String
     */
    public String getType() {
        return "Event";
    }

    /**
     * Check if the task is active on this date.
     * @param date The date to be checked.
     * @return True is the task is active on this date, False otherwise.
     */
    @Override
    public boolean checkDate(LocalDate date) {
        if (this.start.isEqual(date) || this.end.equals(date)) {
            return true;
        } else if (this.start.isBefore(date) && this.end.isAfter(date)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Override taskOutput to return information about the task.
     * @return Information about the task in String format.
     */
    @Override
    public String taskOutput() {
        return "E | " + super.taskOutput() + " | " + this.start + " | " + this.end;
    }

    /**
     * Override toString to return a customized representation of the task.
     * @return A customized representation of the task in String format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
