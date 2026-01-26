package yoyo.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChatbotTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Path tempDir;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        tempDir = Path.of("./data");
        if (!Files.exists(tempDir)) {
            Files.createDirectories(tempDir);
        }
        tempFile = tempDir.resolve("yoyo.txt");
        Files.writeString(tempFile, "");
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void chat_bye_exits() {
        provideInput("bye\n");
        Chatbot chatbot = new Chatbot();
        chatbot.chat();
        String output = outContent.toString();
        assertTrue(output.contains("Hello! I'm yoyo.ui.Yoyo."));
        assertTrue(output.contains("Bye. Hope to see you again!"));
    }

    @Test
    public void chat_todo_list_bye() {
        provideInput("todo read book\nlist\nbye\n");
        Chatbot chatbot = new Chatbot();
        chatbot.chat();
        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("[T][ ] read book"));
        assertTrue(output.contains("1. [T][ ] read book"));
        assertTrue(output.contains("Bye. Hope to see you again!"));
    }

    @Test
    public void chat_invalidCommand() {
        provideInput("invalid_command info\nbye\n");
        Chatbot chatbot = new Chatbot();
        chatbot.chat();
        String output = outContent.toString();
        assertTrue(output.contains("Sorry, I do not recognise this."));
        assertTrue(output.contains("Bye. Hope to see you again!"));
    }

    @Test
    public void chat_missingDescription() {
        provideInput("todo\nbye\n");
        Chatbot chatbot = new Chatbot();
        chatbot.chat();
        String output = outContent.toString();
        assertTrue(output.contains("Missing description"));
        assertTrue(output.contains("Bye. Hope to see you again!"));
    }
}
