// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)
//
// LLM Statement:
// See README.md
//

package studyspot;

import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for StudySpot. Coordinates the model and its views.
 *
 * @author Armaan
 * @version 2026.11.09
 */
public class StudySpotController {
    private final StudySpot model;
    private final MainMenuView mainMenuView;
    private final StudyRoomView studyRoomView;
    private final StudentView studentView;
    private final WaitlistView waitlistView;
    private final MessageView messageView;
    private Student selectedStudent;

    /**
     * Creates a controller connected to the model and all views.
     *
     * @param model
     *            application model
     * @param mainMenuView
     *            main menu view
     * @param studyRoomView
     *            study room view
     * @param studentView
     *            student selection view
     * @param waitlistView
     *            waitlist view
     * @param messageView
     *            message view
     */
    public StudySpotController(
        StudySpot model,
        MainMenuView mainMenuView,
        StudyRoomView studyRoomView,
        StudentView studentView,
        WaitlistView waitlistView,
        MessageView messageView) {
        if (model == null || mainMenuView == null || studyRoomView == null
            || studentView == null || waitlistView == null
            || messageView == null) {
            throw new IllegalArgumentException(
                "Controller dependencies cannot be null.");
        }
        this.model = model;
        this.mainMenuView = mainMenuView;
        this.studyRoomView = studyRoomView;
        this.studentView = studentView;
        this.waitlistView = waitlistView;
        this.messageView = messageView;
    }


    /**
     * Handles a numbered main-menu selection.
     *
     * @param selection
     *            selected menu option (1–7)
     */
    public void handleMenuSelection(int selection) {
        try {
            switch (selection) {
                case 1:
                    processAddStudent();
                    break;
                case 2:
                    processRemoveStudent();
                    break;
                case 3:
                    processAdmitStudent();
                    break;
                case 4:
                    processWaitlist();
                    break;
                case 5:
                    processStudyRoom();
                    break;
                case 6:
                    processAdvanceHour();
                    break;
                case 7:
                    messageView.showMessage("Thank you for using StudySpot!");
                    break;
                default:
                    messageView.showError("Invalid menu selection.");
            }
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            messageView.showError(e.getMessage());
        }
    }


    /**
     * Displays the main menu with the current time and generated to-do list.
     */
    public void displayMainMenu() {
        mainMenuView.displayMenu(model.getCurrentHour(), getToDoList());
    }


    /**
     * Selects a student and remembers the selection for the next action.
     */
    public void getStudent() {
        Student student = studentView.getStudent();
        if (student == null) {
            selectedStudent = null;
            messageView.showError("No student selected.");
            return;
        }
        selectedStudent = student;
        messageView.showMessage("Selected student: " + student.getName());
    }


    /**
     * Adds the selected student to the next appropriate destination. If the
     * student is already waiting at the current hour, this action admits them
     * to the room; otherwise it creates a reservation for the next available
     * hour.
     */
    public void processAddStudent() {
        Student student = studentView.getStudent();
        if (student == null) {
            student = selectedStudent;
        }
        if (student == null) {
            messageView.showError("No student selected.");
            return;
        }
        selectedStudent = student;

        int currentHour = model.getCurrentHour();
        if (currentHour <= StudySpot.CLOSING_HOUR && model.isWaiting(student,
            currentHour)) {
            Student next = model.getWaitlist(currentHour).peek();
            if (next != student) {
                messageView.showError(
                    "Students must be admitted in waitlist order. Next: "
                        + (next == null ? "none" : next.getName()) + ".");
                return;
            }
            Student admitted = model.admitStudent();
            if (admitted != null) {
                messageView.showMessage(admitted.getName()
                    + " has been added to the study room.");
            }
            return;
        }

        int hour = model.getNextAvailableHour();
        if (hour < 0) {
            messageView.showError("There are no remaining time slots today.");
            return;
        }
        if (model.isStudentRegistered(student)) {
            messageView.showError(student.getName()
                + " is already registered.");
            return;
        }
        model.addStudent(student, hour);
        messageView.showMessage(student.getName()
            + " has been added to the waitlist for " + formatHourOfDay(hour)
            + ".");
    }


    /**
     * Removes a selected student from all waitlists.
     */
    public void processRemoveStudent() {
        Student student = studentView.getStudent();
        if (student == null) {
            student = selectedStudent;
        }
        if (student == null) {
            messageView.showError("No student selected.");
            return;
        }
        model.removeStudent(student);
        selectedStudent = null;
        messageView.showMessage(student.getName()
            + " has been removed from the waitlist.");
    }


    /**
     * Admits the next student on the current hour's waitlist.
     */
    public void processAdmitStudent() {
        Student admittedStudent = model.admitStudent();
        if (admittedStudent == null) {
            messageView.showError("No students are waiting to be admitted at "
                + formatHourOfDay(model.getCurrentHour()) + ".");
        }
        else {
            messageView.showMessage(admittedStudent.getName()
                + " has been admitted to the study room.");
        }
    }


    /**
     * Displays the waitlist for a user-selected time slot.
     */
    public void processWaitlist() {
        int hour = waitlistView.getHour();
        waitlistView.displayWaitlist(model.getWaitlist(hour));
    }


    /**
     * Displays the current study room occupants.
     */
    public void processStudyRoom() {
        List<Student> students = model.getStudyRoom().getStudents();
        studyRoomView.displayRoom(students);
    }


    /**
     * Advances the simulated clock by one hour.
     */
    public void processAdvanceHour() {
        model.advanceHour();
        messageView.showMessage("Current time: " + formatHourOfDay(model
            .getCurrentHour()));
    }


    /**
     * Creates the to-do items implied by the current model state.
     *
     * @return a list of human-readable pending actions
     */
    public List<String> getToDoList() {
        List<String> todos = new ArrayList<>();
        int hour = model.getCurrentHour();
        if (hour <= StudySpot.CLOSING_HOUR) {
            Queue currentQueue = model.getWaitlist(hour);
            for (Student student : currentQueue.toArray()) {
                todos.add("Add " + student.getName() + " to the study room");
            }
        }
        if (todos.isEmpty() && selectedStudent != null && !model
            .isStudentRegistered(selectedStudent) && model
                .getNextAvailableHour() >= 0) {
            todos.add("Add " + selectedStudent.getName()
                + " to the waitlist for " + formatHourOfDay(model
                    .getNextAvailableHour()) + ".");
        }
        return todos;
    }


    /**
     * Formats an hour in 24-hour notation as a 12-hour clock label.
     *
     * @param hour
     *            hour from 0 through 24
     * @return formatted time
     */
    public String formatHour(int hour) {
        return formatHourOfDay(hour);
    }


    /**
     * Formats an hour in 24-hour notation as a 12-hour clock label.
     *
     * @param hour
     *            hour from 0 through 24
     * @return formatted time
     */
    public static String formatHourOfDay(int hour) {
        if (hour < 0 || hour > 24) {
            throw new IllegalArgumentException(
                "Hour must be between 0 and 24.");
        }
        if (hour == 0 || hour == 24) {
            return "12 AM";
        }
        if (hour == 12) {
            return "12 PM";
        }
        return hour < 12 ? hour + " AM" : (hour - 12) + " PM";
    }


    /**
     * Returns the model used by this controller.
     *
     * @return application model
     */
    public StudySpot getModel() {
        return model;
    }
}
