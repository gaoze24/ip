package yoyo.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", "2024-12-31");
        assertEquals("[D][ ] return book (by: 2024-12-31)", deadline.toString());
    }

    @Test
    public void testGetTaskOutput() {
        Deadline deadline = new Deadline("return book", "2024-12-31");
        assertEquals("D | false | return book | 2024-12-31", deadline.getTaskOutput());
        Deadline deadline2 = new Deadline(true, "return book", "2024-12-31");
        assertEquals("D | true | return book | 2024-12-31", deadline2.getTaskOutput());
    }

    @Test
    public void testGetType() {
        Deadline deadline = new Deadline("return book", "2024-12-31");
        assertEquals("Deadline", deadline.getType());
    }

    @Test
    public void testIsCorrectDate() {
        Deadline deadline = new Deadline("return book", "2024-12-31");
        assertTrue(deadline.isCorrectDate(LocalDate.parse("2024-12-31")));
        assertFalse(deadline.isCorrectDate(LocalDate.parse("2024-12-30")));
    }
}
