// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan
//
// LLM Statement:
// See README.md

package studyspot;

import java.util.ArrayDeque;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the StudySpotController class.
 *
 * @author Armaan
 * @version 2026.11.09
 */
public class StudySpotControllerTest {
    private StudySpot model;
    private StudySpotController controller;
    private StudyRoom studyRoom;
    private Student john;
    private StudentView studentView;
    private WaitlistView waitlistView;
    private MessageView messageView;
    private StudyRoomView roomView;
    private MainMenuView mainMenu;

    @BeforeEach
    public void setUp() {
        studyRoom = new StudyRoom();
        model = new StudySpot(studyRoom);
        john = new Student("John");
        studentView = new StudentView();
        waitlistView = new WaitlistView();
        messageView = new MessageView();
        roomView = new StudyRoomView();
        mainMenu = new MainMenuView();
        controller = new StudySpotController(model, mainMenu, roomView,
            studentView, waitlistView, messageView);
    }


    @Test
    public void testAdmitStudent() {
        model.addStudent(john, 8);
        controller.processAdmitStudent();
        assertTrue(studyRoom.getStudents().contains(john));
        assertTrue(john.isInStudyRoom());
    }


    @Test
    public void testAdmitStudentEmptyWaitlist() {
        controller.processAdmitStudent();
        assertTrue(messageView.error.contains("No students"));
    }


    @Test
    public void testViewStudyRoom() {
        model.addStudent(john, 8);
        model.admitStudent();
        controller.processStudyRoom();
        assertTrue(roomView.displayedStudents.contains(john));
    }


    @Test
    public void testAdvanceHour() {
        controller.processAdvanceHour();
        assertEquals(9, model.getCurrentHour());
        assertEquals("Current time: 9 AM", messageView.message);
    }


    @Test
    public void testInvalidMenuSelection() {
        controller.handleMenuSelection(99);
        assertEquals("Invalid menu selection.", messageView.error);
    }


    @Test
    public void testFormatHour() {
        assertEquals("8 AM", controller.formatHour(8));
        assertEquals("12 PM", controller.formatHour(12));
        assertEquals("1 PM", controller.formatHour(13));
        assertEquals("11 PM", controller.formatHour(23));
        assertEquals("12 AM", controller.formatHour(24));
        assertThrows(IllegalArgumentException.class, () -> controller
            .formatHour(25));
    }


    @Test
    public void testDisplayMenuDoesNotThrow() {
        assertDoesNotThrow(controller::displayMainMenu);
    }


    @Test
    public void testConstructorRejectsNullDependencies() {
        assertThrows(IllegalArgumentException.class,
            () -> new StudySpotController(null, mainMenu, roomView, studentView,
                waitlistView, messageView));
    }
}
