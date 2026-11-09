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

/**
 * Displays confirmation messages and warnings.
 *
 * @author Ethan
 * @version 2026.11.09
 */
public class MessageView {
    String message;
    String error;

    /**
     * Displays a normal message.
     *
     * @param message
     *            message to display
     */
    public void showMessage(String message) {
        this.message = message;
        System.out.println(message);
    }


    /**
     * Displays an error or warning.
     *
     * @param error
     *            error to display
     */
    public void showError(String error) {
        this.error = error;
        System.out.println("Error: " + error);
    }
}
