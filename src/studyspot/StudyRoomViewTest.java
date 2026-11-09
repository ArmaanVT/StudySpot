package studyspot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudyRoomViewTest {

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
    public void testDisplaysStudentsInRoom() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John"));
        students.add(new Student("Jane"));

        new StudyRoomView().displayRoom(students);

        String displayedText = output.toString();
        assertTrue(displayedText.contains("John"));
        assertTrue(displayedText.contains("Jane"));
    }


    @Test
    public void testDisplaysEmptyRoomMessage() {
        new StudyRoomView().displayRoom(new ArrayList<Student>());

        assertTrue(output.toString().contains("The study room is empty."));
    }
}