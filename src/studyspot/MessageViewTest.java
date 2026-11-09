package studyspot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageViewTest {

    private PrintStream originalOut;
    private PrintStream originalErr;
    private ByteArrayOutputStream output;
    private ByteArrayOutputStream errorOutput;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        originalErr = System.err;
        output = new ByteArrayOutputStream();
        errorOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setErr(new PrintStream(errorOutput));
    }


    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void testShowMessagePrintsToConsole() {
        MessageView view = new MessageView();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        view.showMessage("Hello");

        assertTrue(outContent.toString().contains("Hello"));
    }


    @Test
    public void testShowErrorStoresAndDisplaysWarning() {
        MessageView view = new MessageView();

        view.showError("No student selected.");

        assertEquals("No student selected.", view.error);
        assertTrue(errorOutput.toString().contains(
            "Warning: No student selected."));
    }

    @Test 
    public void testShowMessageOverwritesPreviousMessage() {
        MessageView view = new MessageView();

        view.showMessage("First message");
        view.showMessage("Second message");

        assertEquals("Second message", view.message);
    }

    @Test
    public void testShowMessageWithNull()
    {
        MessageView view = new MessageView();

        view.showMessage(null);

        assertNull(view.message);
    }
    
}