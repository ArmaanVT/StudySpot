package studyspot;

// -------------------------------------------------------------------------
/**
 * Displays confirmation messages and warnings.
 */
public class MessageView {

    String message;
    String error;

    /**
     * Displays a confirmation message.
     *
     * @param message
     *            the message to display.
     */
    public void showMessage(String message) {
        this.message = message;
        System.out.println(message);
    }


    /**
     * Displays a warning or error message.
     *
     * @param error
     *            the warning or error to display.
     */
    public void showError(String error) {
        this.error = error;
        System.err.println("Warning: " + error);
    }
}