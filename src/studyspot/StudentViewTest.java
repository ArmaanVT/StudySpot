package studyspot;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentViewTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream output;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }


    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }


    @Test
    public void testReturnsAndDisplaysSelectedStudent() {
        Student student = new Student("John");
        StudentView view = new StudentView();
        view.setSelectedStudent(student);

        assertSame(student, view.getStudent());
        assertTrue(output.toString().contains("Selected student: John"));
    }


    @Test
    public void testDisplaysWhenNoStudentIsSelected() {
        StudentView view = new StudentView();

        assertNull(view.getStudent());
        assertTrue(output.toString().contains("No student selected."));
    }
}