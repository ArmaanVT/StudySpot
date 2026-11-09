// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Catherine
//
// LLM Statement:
// See README.md

package studyspot;

import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for StudentView.
 *
 * @author Catherine
 * @version 2026.11.09
 */
public class StudentViewTest {
    @Test
    public void testSelectStudentByName() {
        StudentView view = new StudentView(new Scanner("John\n"));
        assertEquals("John", view.getStudent().getName());
    }


    @Test
    public void testUnknownStudentReturnsNull() {
        StudentView view = new StudentView(new Scanner("Unknown\n"));
        assertNull(view.getStudent());
    }
}
