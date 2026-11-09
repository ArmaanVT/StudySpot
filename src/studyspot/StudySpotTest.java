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

import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Tests for the StudySpot class.
 * 
 * @author Armaan
 * @version 2026.11.08
 */
public class StudySpotTest {

    private StudySpot studySpot;
    private StudyRoom studyRoom;

    private Student john;
    private Student jane;

    @BeforeEach
    public void setUp() {

        studyRoom = new StudyRoom();
        studySpot = new StudySpot(studyRoom);

        john = new Student("John");
        jane = new Student("Jane");
    }


    @Test
    public void testInitialTime() {
        assertEquals(8, studySpot.getCurrentHour());
    }


    @Test
    public void testAddStudent() {

        studySpot.addStudent(john, 9);

        assertEquals(john, studySpot.getWaitlist(9).peek());
    }


    @Test
    public void testAddMultipleStudents() {

        studySpot.addStudent(john, 9);
        studySpot.addStudent(jane, 9);

        Queue queue = studySpot.getWaitlist(9);

        assertEquals(john, queue.dequeue());
        assertEquals(jane, queue.dequeue());
    }


    @Test
    public void testSeparateWaitlists() {

        studySpot.addStudent(john, 9);
        studySpot.addStudent(jane, 10);

        assertEquals(john, studySpot.getWaitlist(9).peek());

        assertEquals(jane, studySpot.getWaitlist(10).peek());
    }


    @Test
    public void testAdmitStudent() {

        studySpot.addStudent(john, 8);

        Student admitted = studySpot.admitStudent();

        assertEquals(john, admitted);

        assertTrue(studyRoom.getStudents().contains(john));
    }


    @Test
    public void testAdmitStudentEmptyQueue() {

        assertNull(studySpot.admitStudent());
    }


    @Test
    public void testRemoveStudent() {

        studySpot.addStudent(john, 9);

        studySpot.removeStudent(john);

        assertTrue(studySpot.getWaitlist(9).isEmpty());
    }


    @Test
    public void testAdvanceHour() {

        studySpot.advanceHour();

        assertEquals(9, studySpot.getCurrentHour());
    }


    @Test
    public void testAdvanceHourUntilMidnight() {

        for (int i = 0; i < 16; i++) {
            studySpot.advanceHour();
        }

        assertEquals(24, studySpot.getCurrentHour());

        assertThrows(IllegalStateException.class, () -> studySpot
            .advanceHour());
    }


    @Test
    public void testInvalidHour() {

        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 7));

        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 24));
    }


    @Test
    public void testDuplicateStudent() {

        studySpot.addStudent(john, 9);

        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 10));
    }


    @Test
    public void testNullStudent() {

        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            null, 9));
    }
}
