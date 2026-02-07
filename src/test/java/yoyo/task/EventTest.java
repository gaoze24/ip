package yoyo.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("project meeting", "2024-12-31", "2025-01-01");
        assertEquals("[E][ ] project meeting (from: 2024-12-31 to: 2025-01-01)", event.toString());
    }

    @Test
    public void testGetTaskOutput() {
        Event event = new Event("project meeting", "2024-12-31", "2025-01-01");
        assertEquals("E | false | project meeting | 2024-12-31 | 2025-01-01", event.getTaskOutput());
        Event event2 = new Event(true, "project meeting", "2024-12-31", "2025-01-01");
        assertEquals("E | true | project meeting | 2024-12-31 | 2025-01-01", event2.getTaskOutput());
    }

    @Test
    public void testGetType() {
        Event event = new Event("project meeting", "2024-12-31", "2025-01-01");
        assertEquals("Event", event.getType());
    }

    @Test
    public void testIsCorrectDate() {
        Event event = new Event("project meeting", "2024-12-31", "2025-01-01");
        assertTrue(event.isCorrectDate(LocalDate.parse("2024-12-31")));
        assertTrue(event.isCorrectDate(LocalDate.parse("2025-01-01")));
        assertTrue(event.isCorrectDate(LocalDate.parse("2024-12-31")));
        Event longEvent = new Event("long event", "2024-12-01", "2024-12-10");
        assertTrue(longEvent.isCorrectDate(LocalDate.parse("2024-12-05")));
        assertFalse(longEvent.isCorrectDate(LocalDate.parse("2024-11-30")));
        assertFalse(longEvent.isCorrectDate(LocalDate.parse("2024-12-11")));
    }
}
