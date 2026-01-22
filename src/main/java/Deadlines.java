public class Deadlines extends Task{
    private String deadline;
    public Deadlines(int id, String name, String deadline) {
        super(id, name);
        this.deadline = deadline;
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }
}

