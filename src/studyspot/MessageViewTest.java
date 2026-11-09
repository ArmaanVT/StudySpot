package studyspot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testShowMessageStoresAndDisplaysConfirmation() {
        MessageView view = new MessageView();

        view.showMessage("Student added.");

        assertEquals("Student added.", view.message);
        assertTrue(output.toString().contains("Student added."));
    }


    @Test
    public void testShowErrorStoresAndDisplaysWarning() {
        MessageView view = new MessageView();

        view.showError("No student selected.");

        assertEquals("No student selected.", view.error);
        assertTrue(errorOutput.toString().contains(
            "Warning: No student selected."));
    }
}