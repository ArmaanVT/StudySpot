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

/**
 * Displays the main menu, simulated time, and pending items on the to-do list.
 *
 * @author Ethan
 * @version 2026.11.09
 */
public class MainMenuView {
    private static final String[] OPTIONS = { "Add student",
        "Remove student", "Admit student", "View Waitlist", "View Study Room",
        "Advance Hour", "Exit" };

    /**
     * Displays the menu.
     */
    public void displayMenu() {
        displayMenu(StudySpot.OPENING_HOUR, List.of());
    }


    /**
     * Displays the menu and current to-do items.
     *
     * @param currentHour
     *            Current simulated hour
     * @param todos
     *            Pending tasks
     */
    public void displayMenu(int currentHour, List<String> todos) {
        System.out.println();
        System.out.println("Current Time: " + StudySpotController
            .formatHourOfDay(currentHour));
        System.out.println();
        System.out.println("Options:");

        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println((i + 1) + ". " + OPTIONS[i]);
        }

        System.out.println();
        System.out.println("To-do:");

        if (todos == null || todos.isEmpty()) {
            System.out.println("None");
        }
        else {
            for (String todo : todos) {
                System.out.println(todo);
            }
        }
    }
}
