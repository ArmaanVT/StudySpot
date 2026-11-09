package studyspot;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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