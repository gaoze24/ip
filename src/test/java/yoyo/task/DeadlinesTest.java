package yoyo.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlinesTest {
    @Test
    public void testToString() {
        Deadlines deadline = new Deadlines("return book", "2024-12-31");
        assertEquals("[D][ ] return book (by: 2024-12-31)", deadline.toString());
    }

    @Test
    public void testTaskOutput() {
        Deadlines deadline = new Deadlines("return book", "2024-12-31");
        assertEquals("D | false | return book | 2024-12-31", deadline.taskOutput());
        Deadlines deadline2 = new Deadlines(true, "return book", "2024-12-31");
        assertEquals("D | true | return book | 2024-12-31", deadline2.taskOutput());
    }

    @Test
    public void testGetType() {
        Deadlines deadline = new Deadlines("return book", "2024-12-31");
        assertEquals("Deadline", deadline.getType());
    }

    @Test
    public void testCheckDate() {
        Deadlines deadline = new Deadlines("return book", "2024-12-31");
        assertTrue(deadline.checkDate(LocalDate.parse("2024-12-31")));
        assertFalse(deadline.checkDate(LocalDate.parse("2024-12-30")));
    }
}
