package yoyo.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yoyo.task.Task;
import yoyo.task.Tasks;
import yoyo.task.ToDos;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    private final Command command = new Command();
    private final Tasks tasks = new Tasks();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_mark_success() {
        tasks.storeTask(new ToDos("test task"));
        Task markTask = new Task("1", "mark");
        command.execute(tasks, markTask);
        assertTrue(outContent.toString().contains("Nice! I've marked this task as done:"));
        assertTrue(tasks.showTask(1).contains("[X] test task"));
    }

    @Test
    public void execute_unmark_success() {
        tasks.storeTask(new ToDos("test task"));
        tasks.completeTask(1);
        Task unmarkTask = new Task("1", "unmark");
        command.execute(tasks, unmarkTask);
        assertTrue(outContent.toString().contains("OK, I've marked this task as not done yet:"));
        assertTrue(tasks.showTask(1).contains("[ ] test task"));
    }

    @Test
    public void execute_list_success() {
        tasks.storeTask(new ToDos("test task"));
        outContent.reset(); // Clear output from storeTask
        Task listTask = new Task("list");
        command.execute(tasks, listTask);
        String output = outContent.toString();
        assertTrue(output.contains("1. [T][ ] test task"));
    }

    @Test
    public void execute_delete_success() {
        tasks.storeTask(new ToDos("test task"));
        Task deleteTask = new Task("1", "delete");
        command.execute(tasks, deleteTask);
        assertTrue(outContent.toString().contains("Nice! I've deleted this task as done:"));
    }

    @Test
    public void execute_addTodo_success() {
        Task todoTask = new ToDos("new todo");
        command.execute(tasks, todoTask);
        assertTrue(outContent.toString().contains("Got it. I've added this task:"));
        assertTrue(tasks.showTask(1).contains("[ ] new todo"));
    }
}
