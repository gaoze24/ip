public class Deadlines extends Task{
    private String deadline;
    public Deadlines(int id, String name, String deadline) {
        super(id, name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }
}

