public class ToDos extends Task{
    public ToDos(int id, String name) {
        super(id, name);
    }

    // Override the toString method to return a customized representation of the task.
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
