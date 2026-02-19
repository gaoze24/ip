package yoyo.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskFileTest {
    private final Path path = Path.of("./data/yoyo.txt");
    private String originalContent;
    private boolean isFileExists;

    @BeforeEach
    public void setUp() throws IOException {
        // Save original file if it exists
        if (Files.exists(path)) {
            originalContent = Files.readString(path);
            isFileExists = true;
        } else {
            isFileExists = false;
        }
        // Ensure directory exists
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Restore original file
        if (isFileExists) {
            Files.writeString(path, originalContent);
        } else {
            Files.deleteIfExists(path);
        }
    }

    @Test
    public void testConstructor_createsFile() throws IOException {
        Files.deleteIfExists(path);
        new TaskFile();
        assertTrue(Files.exists(path));
    }

    @Test
    public void testWriteList_success() throws IOException {
        TaskFile taskFile = new TaskFile();
        String message = "T | true | read book";
        boolean result = taskFile.writeList(message);
        assertTrue(result);
        assertEquals(message, Files.readString(path));
    }

    @Test
    public void testReadList_success() throws IOException {
        TaskFile taskFile = new TaskFile();
        String content = "T | true | read book\nD | false | return book | 2024-12-31";
        Files.writeString(path, content);
        List<String> list = taskFile.readList();
        assertEquals(2, list.size());
        assertEquals("T | true | read book", list.get(0));
        assertEquals("D | false | return book | 2024-12-31", list.get(1));
    }

    @Test
    public void testReadList_emptyFile() throws IOException {
        TaskFile taskFile = new TaskFile();
        Files.writeString(path, "");
        List<String> list = taskFile.readList();
        assertTrue(list.isEmpty());
    }
}
