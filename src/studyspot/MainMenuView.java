
package studyspot;

// -------------------------------------------------------------------------
/**
 * Displays the main menu and the current study spot status.
 *
 * @author Ethan
 * @version 2026.11.08
 */
public class MainMenuView {

    private int currentHour;

    /**
     * Creates the main menu view.
     */
    public MainMenuView() {
        currentHour = 8;
    }


    /**
     * Sets the current hour displayed by the menu.
     *
     * @param hour
     *            the current hour in 24-hour time.
     */
    public void setCurrentHour(int hour) {
        currentHour = hour;
    }


    /**
     * Displays the current time, to-do list, and available menu options.
     */
    public void displayMenu() {
        System.out.println("\n=== StudySpot Main Menu ===");
        System.out.println("Current time: " + formatHour(currentHour));
        System.out.println("\nTo-Do List:");
        System.out.println("- Manage student waitlists");
        System.out.println("- Admit waiting students");
        System.out.println("- Review the study room");
        System.out.println("- Advance the current hour");
        System.out.println("\nAvailable options:");
        System.out.println("1. Select a student");
        System.out.println("2. Add a student to a waitlist");
        System.out.println("3. Remove a student from waitlists");
        System.out.println("4. Admit a student to the study room");
        System.out.println("5. View a waitlist");
        System.out.println("6. View the study room");
        System.out.println("7. Advance the hour");
        System.out.println("8. Exit");
    }


    /**
     * Formats a 24-hour time as a 12-hour time.
     *
     * @param hour
     *            the hour in 24-hour time.
     * @return the formatted time.
     */
    private String formatHour(int hour) {
        if (hour == 24) {
            return "12 AM";
        }

        if (hour == 12) {
            return "12 PM";
        }

        if (hour < 12) {
            return hour + " AM";
        }

        return (hour - 12) + " PM";
    }
}