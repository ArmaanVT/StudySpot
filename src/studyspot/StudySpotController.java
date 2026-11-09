// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// See README.md

package studyspot;

import java.util.List;

// -------------------------------------------------------------------------
/**
 * This class serves as the controller for StudySpot.
 * 
 * @author Armaan
 * @version 2026.11.08
 */
public class StudySpotController {

    // Model.
    private StudySpot model;

    // Views.
    private MainMenuView mainMenuView;
    private StudyRoomView studyRoomView;
    private StudentView studentView;
    private WaitlistView waitlistView;
    private MessageView messageView;

    /**
     * Creates a new StudySpotController object.
     * 
     * @param model
     *            the model.
     * @param studyRoomView
     *            the view for the study room.
     * @param studentView
     *            the view for the students.
     * @param waitlistView
     *            the view for the waitlist.
     * @param messageView
     *            the view for the messages.
     */
    public StudySpotController(
        StudySpot model,
        MainMenuView mainMenuView,
        StudyRoomView studyRoomView,
        StudentView studentView,
        WaitlistView waitlistView,
        MessageView messageView) {

        this.model = model;
        this.mainMenuView = mainMenuView;
        this.studyRoomView = studyRoomView;
        this.studentView = studentView;
        this.waitlistView = waitlistView;
        this.messageView = messageView;
    }


    /**
     * Handles main menu selection.
     * 
     * @param selection
     *            the choice.
     */
    public void handleMenuSelection(int selection) {

        try {

            switch (selection) {

                case 1:
                    getStudent();
                    break;

                case 2:
                    processAddStudent();
                    break;

                case 3:
                    processRemoveStudent();
                    break;

                case 4:
                    processAdmitStudent();
                    break;

                case 5:
                    processWaitlist();
                    break;

                case 6:
                    processStudyRoom();
                    break;

                case 7:
                    processAdvanceHour();
                    break;

                default:
                    messageView.showError("Invalid menu selection.");
            }

        }

        // Catches any error exceptions.
        catch (IllegalArgumentException | IllegalStateException e) {

            messageView.showError(e.getMessage());
        }
    }


    /**
     * Display the main menu.
     */
    public void displayMainMenu() {
        mainMenuView.displayMenu();
    }


    /**
     * Gets a student.
     */
    public void getStudent() {

        Student student = studentView.getStudent();

        if (student == null) {
            messageView.showError("No student selected.");
            return;
        }

        messageView.showMessage("Selected student: " + student.getName());
    }


    /**
     * Adds a student to the queue.
     */
    public void processAddStudent() {

        Student student = studentView.getStudent();

        if (student == null) {
            messageView.showError("No student selected.");
            return;
        }

        int hour = waitlistView.getHour();

        model.addStudent(student, hour);

        messageView.showMessage(student.getName()
            + " has been added to the waitlist.");
    }


    /**
     * Removes a student from the queue.
     */
    public void processRemoveStudent() {

        Student student = studentView.getStudent();

        if (student == null) {
            messageView.showError("No student selected.");
            return;
        }

        model.removeStudent(student);

        messageView.showMessage(student.getName()
            + " has been removed from the waitlist.");
    }


    /**
     * Admits a student to the study room.
     */
    private void processAdmitStudent() {
        Student admittedStudent = studySpot.admitStudent();

        if (admittedStudent == null) {
            messageView.showMessage("No students are waiting to be admitted at "
                + formatHour(studySpot.getCurrentHour()) + ".");
        }
        else {
            messageView.showMessage(admittedStudent.getName()
                + " has been admitted to the study room.");
        }
    }


    /**
     * Processes the waitlist.
     */
    public void processWaitlist() {

        int hour = waitlistView.getHour();

        Queue queue = model.getWaitlist(hour);

        waitlistView.displayWaitlist(queue);
    }


    /**
     * Processes the study roomm.
     */
    public void processStudyRoom() {

        List<Student> students = model.getStudyRoom().getStudents();

        studyRoomView.displayRoom(students);
    }


    /**
     * Advances the time.
     */
    public void processAdvanceHour() {

        model.advanceHour();

        messageView.showMessage("Current time: " + formatHour(model
            .getCurrentHour()));
    }


    /**
     * Converts the time.
     */
    public String formatHour(int hour) {

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


    /**
     * Gets the model used in this class.
     */
    public StudySpot getModel() {
        return model;
    }
}
