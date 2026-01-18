public class Task {
    private int id;
    private boolean completed;
    private String name;

    public Task(int id, String name) {
        this.id = id;
        this.completed = false;
        this.name = name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getName() {
        return this.name;
    }

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String status = this.completed ? "X" : " ";
        return "[" + status + "] " + this.name;
    }
}
