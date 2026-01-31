package yoyo.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TasksTest {
    private Tasks tasks;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        tasks = new Tasks();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testStoreTask() {
        tasks.storeTask(new ToDos("test"));
        assertTrue(outContent.toString().contains("Got it. I've added this task:"));
        assertTrue(tasks.showTask(1).contains("test"));
    }

    @Test
    public void testStoreTaskFromArray() {
        tasks.storeTask(new String[]{"false", "todo desc", "todo"}); // ToDos list.length == 3
        assertTrue(tasks.showTask(1).contains("todo desc"));
        tasks.storeTask(new String[]{"true", "deadline desc", "2024-12-31"}); // Deadlines list.length == 4
        assertTrue(tasks.showTask(2).contains("deadline desc"));
        tasks.storeTask(new String[]{"false", "event desc", "2024-12-31", "2025-01-01"}); // Events list.length == 5
        assertTrue(tasks.showTask(3).contains("event desc"));
    }

    @Test
    public void testCompleteAndIncompleteTask() {
        tasks.storeTask(new ToDos("test"));
        tasks.completeTask(1);
        assertTrue(tasks.showTask(1).contains("[X]"));
        tasks.incompleteTask(1);
        assertTrue(tasks.showTask(1).contains("[ ]"));
    }

    @Test
    public void testDeleteTask() {
        tasks.storeTask(new ToDos("test"));
        tasks.deleteTask(1);
        assertFalse(tasks.checkExists(1));
    }

    @Test
    public void testCheckExists() {
        assertFalse(tasks.checkExists(1));
        tasks.storeTask(new ToDos("test"));
        assertTrue(tasks.checkExists(1));
        assertFalse(tasks.checkExists(2));
        assertFalse(tasks.checkExists(0));
    }

    @Test
    public void testFileOutput() {
        tasks.storeTask(new ToDos("test1"));
        tasks.storeTask(new Deadlines("test2", "2024-12-31"));
        String output = tasks.fileOutput();
        assertTrue(output.contains("T | false | test1"));
        assertTrue(output.contains("D | false | test2 | 2024-12-31"));
    }

    @Test
    public void testCheckDate() {
        tasks.storeTask(new Deadlines("test1", "2024-12-31"));
        tasks.storeTask(new Events("test2", "2024-12-30", "2025-01-02"));
        String result = tasks.checkDate(LocalDate.parse("2024-12-31"));
        assertTrue(result.contains("test1"));
        assertTrue(result.contains("test2"));
        result = tasks.checkDate(LocalDate.parse("2024-12-29"));
        assertEquals("", result);
    }
}
