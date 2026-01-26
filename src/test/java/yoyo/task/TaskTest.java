package yoyo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    @Test
    public void testStatusIcon() {
        Task task = new Task("test task", "todo");
        assertEquals("[ ] test task", task.toString());
        task.complete();
        assertEquals("[X] test task", task.toString());
    }

    @Test
    public void testGetDescription() {
        Task task = new Task("test task", "todo");
        assertEquals("test task", task.getDescription());
    }

    @Test
    public void testIsCompleted() {
        Task task = new Task("test task", "todo");
        assertFalse(task.isCompleted());
        task.complete();
        assertTrue(task.isCompleted());
        task.incomplete();
        assertFalse(task.isCompleted());
    }

    @Test
    public void testTaskOutput() {
        Task task = new Task("test task", "todo");
        assertEquals("false | test task", task.taskOutput());
        task.complete();
        assertEquals("true | test task", task.taskOutput());
    }
}
