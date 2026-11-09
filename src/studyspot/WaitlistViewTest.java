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
 * Tests for WaitlistView.
 *
 * @author Catherine
 * @version 2026.11.09
 */
public class WaitlistViewTest {
    @Test
    public void testReadValidHour() {
        WaitlistView view = new WaitlistView(new Scanner("22\n"));
        assertEquals(22, view.getHour());
    }


    @Test
    public void testRejectInvalidHour() {
        WaitlistView view = new WaitlistView(new Scanner("7\n"));
        assertThrows(IllegalArgumentException.class, view::getHour);
    }


    @Test
    public void testDisplayWaitlistSnapshot() {
        WaitlistView view = new WaitlistView(new Scanner(""));
        Queue queue = new Queue();
        Student student = new Student("John");
        queue.enqueue(student);
        view.displayWaitlist(queue);
        assertSame(queue, view.displayedQueue);
    }
}
