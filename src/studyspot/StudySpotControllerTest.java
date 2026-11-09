// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// See README.md

package studyspot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Tests for the StudySpotController class.
 * 
 * @author Armaan
 * @version 2026.11.08
 */
public class StudySpotControllerTest {

    private StudySpot model;
    private StudySpotController controller;

    private StudyRoom studyRoom;
    private Student john;

    private MainMenuView mainMenu;
    private StudyRoomView roomView;
    private StudentView studentView;
    private WaitlistView waitlistView;
    private MessageView messageView;

    @BeforeEach
    public void setUp() {
        studyRoom = new StudyRoom();
        model = new StudySpot(studyRoom);

        john = new Student("John");

        mainMenu = new MainMenuView();
        roomView = new StudyRoomView();
        studentView = new StudentView();
        waitlistView = new WaitlistView();
        messageView = new MessageView();

        controller = new StudySpotController(model, mainMenu, roomView,
            studentView, waitlistView, messageView);
    }


    @Test
    public void testAddStudent() {
        studentView.selectedStudent = john;
        waitlistView.selectedHour = 9;

        controller.processAddStudent();

        assertEquals(john, model.getWaitlist(9).peek());

        assertNotNull(messageView.message);
    }


    @Test
    public void testRemoveStudent() {
        model.addStudent(john, 9);
        studentView.selectedStudent = john;

        controller.processRemoveStudent();

        assertTrue(model.getWaitlist(9).isEmpty());
    }


    @Test
    public void testAdmitStudent() {
        model.addStudent(john, 8);

        controller.processAdmitStudent();

        assertTrue(studyRoom.getStudents().contains(john));
    }


    @Test
    public void testAdmitStudentEmptyQueue() {
        controller.processAdmitStudent();

        assertNotNull(messageView.error);
    }


    @Test
    public void testViewWaitlist() {
        model.addStudent(john, 9);
        waitlistView.selectedHour = 9;

        controller.processWaitlist();

        assertEquals(john, waitlistView.displayedQueue.peek());
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
    }


    @Test
    public void testInvalidMenuSelection() {
        controller.handleMenuSelection(99);

        assertNotNull(messageView.error);
    }


    @Test
    public void testFormatHour() {
        assertEquals("8 AM", controller.formatHour(8));
        assertEquals("12 PM", controller.formatHour(12));
        assertEquals("1 PM", controller.formatHour(13));
        assertEquals("11 PM", controller.formatHour(23));
        assertEquals("12 AM", controller.formatHour(24));
    }
