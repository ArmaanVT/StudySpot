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

/**
 * Displays hourly time slots and the waitlist for a selected slot.
 *
 * @author Catherine
 * @version 2026.11.09
 */
public class WaitlistView {
    int selectedHour = -1;
    Queue displayedQueue;
    private final Scanner scanner;

    /**
     * Creates the view using standard input.
     */
    public WaitlistView() {
        this(new Scanner(System.in));
    }


    /**
     * Creates the view using a shared input scanner.
     *
     * @param scanner
     *            input source
     */
    public WaitlistView(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }
        this.scanner = scanner;
    }


    /**
     * Prompts for an hour between 8 AM and 11 PM.
     *
     * @return selected hour in 24-hour format
     */
    public int getHour() {
        displayList();
        if (selectedHour >= 8 && selectedHour <= 23) {
            int hour = selectedHour;
            selectedHour = -1;
            return hour;
        }
        System.out.print("Choose a time slot (8-23, 24-hour format): ");
        String input = scanner.nextLine().trim();
        try {
            int hour = Integer.parseInt(input);
            if (hour >= 8 && hour <= 23) {
                return hour;
            }
        }
        catch (NumberFormatException ignored) {
            // Reported uniformly below.
        }
        throw new IllegalArgumentException(
            "Please choose a time between 8 AM and 11 PM.");
    }


    /**
     * Displays all supported time slots.
     */
    public void displayList() {
        System.out.println("Choose a time slot:");
        for (int hour = 8; hour <= 23; hour++) {
            System.out.println(StudySpotController.formatHourOfDay(hour));
        }
    }


    /**
     * Displays a queue without modifying it.
     *
     * @param queue
     *            waitlist to display
     */
    public void displayWaitlist(Queue queue) {
        displayedQueue = queue;
        Student[] students = queue.toArray();
        if (students.length == 0) {
            System.out.println(
                "The waitlist for this time currently has no students.");
            return;
        }
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
