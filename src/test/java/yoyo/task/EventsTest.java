package yoyo.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventsTest {
    @Test
    public void testToString() {
        Events event = new Events("project meeting", "2024-12-31", "2025-01-01");
        assertEquals("[E][ ] project meeting (from: 2024-12-31 to: 2025-01-01)", event.toString());
    }

    @Test
    public void testTaskOutput() {
        Events event = new Events("project meeting", "2024-12-31", "2025-01-01");
        assertEquals("E | false | project meeting | 2024-12-31 | 2025-01-01", event.taskOutput());
        Events event2 = new Events(true, "project meeting", "2024-12-31", "2025-01-01");
        assertEquals("E | true | project meeting | 2024-12-31 | 2025-01-01", event2.taskOutput());
    }

    @Test
    public void testGetType() {
        Events event = new Events("project meeting", "2024-12-31", "2025-01-01");
        assertEquals("Event", event.getType());
    }

    @Test
    public void testCheckDate() {
        Events event = new Events("project meeting", "2024-12-31", "2025-01-01");
        assertTrue(event.checkDate(LocalDate.parse("2024-12-31")));
        assertTrue(event.checkDate(LocalDate.parse("2025-01-01")));
        assertTrue(event.checkDate(LocalDate.parse("2024-12-31")));
        Events longEvent = new Events("long event", "2024-12-01", "2024-12-10");
        assertTrue(longEvent.checkDate(LocalDate.parse("2024-12-05")));
        assertFalse(longEvent.checkDate(LocalDate.parse("2024-11-30")));
        assertFalse(longEvent.checkDate(LocalDate.parse("2024-12-11")));
    }
}
