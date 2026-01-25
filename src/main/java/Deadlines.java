import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task{
    private LocalDate deadline;

    public Deadlines(int id, String name, String deadline) {
        super(id, name);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadlines(int id, boolean completed, String description, String deadline) {
        super(id, completed, description);
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
    public boolean checkDate(LocalDate date) {
        if (this.deadline.isEqual(date)) {
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
        return "D | " + super.taskOutput() + " | " + this.deadline;
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}

