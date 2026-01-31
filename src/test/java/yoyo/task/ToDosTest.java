package yoyo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testToString() {
        ToDos todo = new ToDos("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testTaskOutput() {
        ToDos todo = new ToDos("read book");
        assertEquals("T | false | read book", todo.taskOutput());
        ToDos todo2 = new ToDos(true, "read book");
        assertEquals("T | true | read book", todo2.taskOutput());
    }

    @Test
    public void testGetType() {
        ToDos todo = new ToDos("read book");
        assertEquals("Todo", todo.getType());
    }
}
