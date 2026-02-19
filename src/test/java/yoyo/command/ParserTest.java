package yoyo.command;

import org.junit.jupiter.api.Test;
import yoyo.exception.YoyoException;
import yoyo.task.Tasks;
import yoyo.task.ToDo;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private final Parser parser = new Parser();
    private final Tasks tasks = new Tasks();

    @Test
    public void parse_todo_success() throws YoyoException {
        Task result = parser.parse("todo borrow book", tasks);
        assertTrue(result instanceof ToDo);
        assertEquals("borrow book", result.getDescription());
        assertEquals("Todo", result.getType());
    }

    @Test
    public void parse_deadline_success() throws YoyoException {
        Task result = parser.parse("deadline return book /by 2024-12-31", tasks);
        assertTrue(result instanceof Deadline);
        assertEquals("return book", result.getDescription());
        assertEquals("Deadline", result.getType());
    }

    @Test
    public void parse_event_success() throws YoyoException {
        Task result = parser.parse("event project meeting /from 2024-12-31 /to 2025-01-01", tasks);
        assertTrue(result instanceof Event);
        assertEquals("project meeting", result.getDescription());
        assertEquals("Event", result.getType());
    }

    @Test
    public void parse_list_success() throws YoyoException {
        Task result = parser.parse("list", tasks);
        assertEquals("list", result.getType());
    }

    @Test
    public void parse_mark_success() throws YoyoException {
        tasks.storeTask(new ToDo("test"));
        Task result = parser.parse("mark 1", tasks);
        assertEquals("mark", result.getType());
        assertEquals("1", result.getDescription());
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        YoyoException exception = assertThrows(YoyoException.class, () -> {
            parser.parse("blah blah", tasks);
        });
        assertEquals("Sorry, I do not recognise this.", exception.getMessage());
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        assertThrows(YoyoException.class, () -> {
            parser.parse("todo", tasks);
        });
    }

    @Test
    public void parse_invalidDate_exceptionThrown() {
        assertThrows(YoyoException.class, () -> {
            parser.parse("deadline return book /by 2024-13-31", tasks);
        });
    }
    @Test
    public void parse_event_reversedOrder_success() throws YoyoException {
        Task result = parser.parse("event project meeting /to 2025-01-01 /from 2024-12-31", tasks);
        assertTrue(result instanceof Event);
        assertEquals("project meeting", result.getDescription());
        assertEquals("[E][ ] project meeting (from: 2024-12-31 to: 2025-01-01)", result.toString());
    }

    @Test
    public void parse_deadline_extraSpaces_success() throws YoyoException {
        Task result = parser.parse("deadline  return book  /by  2024-12-31 ", tasks);
        assertTrue(result instanceof Deadline);
        assertEquals("return book", result.getDescription());
        assertEquals("[D][ ] return book (by: 2024-12-31)", result.toString());
    }
}
