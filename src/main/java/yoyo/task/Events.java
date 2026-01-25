package yoyo.task;

import java.time.LocalDate;

public class Events extends Task {
    private LocalDate start;
    private LocalDate end;

    public Events(String name, String start, String end) {
        super(name);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

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

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
