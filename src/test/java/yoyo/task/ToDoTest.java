package yoyo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testGetTaskOutput() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | false | read book", todo.getTaskOutput());
        ToDo todo2 = new ToDo(true, "read book");
        assertEquals("T | true | read book", todo2.getTaskOutput());
    }

    @Test
    public void testGetType() {
        ToDo todo = new ToDo("read book");
        assertEquals("Todo", todo.getType());
    }
}
