public class ToDos extends Task{
    public ToDos(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
