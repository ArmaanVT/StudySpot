// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ethan
//
// LLM Statement:
// See README.md

package studyspot;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MainMenuView.
 *
 * @author Ethan
 * @version 2026.11.09
 */
public class MainMenuViewTest {
    @Test
    public void testDisplayMenu() {
        MainMenuView view = new MainMenuView();
        assertDoesNotThrow(() -> view.displayMenu(8, List.of()));
        assertDoesNotThrow(() -> view.displayMenu(9, List.of(
            "Add John to the study room")));
    }
}
