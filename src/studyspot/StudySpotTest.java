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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the StudySpot model.
 *
 * @author Armaan
 * @version 2026.11.09
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
    public void testInitialTimeAndRoom() {
        assertEquals(8, studySpot.getCurrentHour());
        assertSame(studyRoom, studySpot.getStudyRoom());
    }


    @Test
    public void testHourlyWaitlistsAndFifo() {
        studySpot.addStudent(john, 9);
        studySpot.addStudent(jane, 9);
        studySpot.addStudent(new Student("Alex"), 10);
        assertSame(john, studySpot.getWaitlist(9).dequeue());
        assertSame(jane, studySpot.getWaitlist(9).dequeue());
        assertEquals(1, studySpot.getWaitlist(10).size());
        assertTrue(studySpot.getWaitlist(8).isEmpty());
    }


    @Test
    public void testAdmitStudentFromCurrentHour() {
        studySpot.addStudent(john, 8);
        assertSame(john, studySpot.admitStudent());
        assertTrue(studyRoom.getStudents().contains(john));
        assertTrue(john.isInStudyRoom());
        assertNull(studySpot.admitStudent());
    }


    @Test
    public void testAdmissionOnlyUsesCurrentHour() {
        studySpot.addStudent(john, 9);
        assertNull(studySpot.admitStudent());
        assertTrue(studySpot.getWaitlist(9).contains(john));
        studySpot.advanceHour();
        assertSame(john, studySpot.admitStudent());
    }


    @Test
    public void testRemoveStudentAcrossWaitlists() {
        studySpot.addStudent(john, 9);
        studySpot.addStudent(jane, 10);
        studySpot.removeStudent(john);
        assertFalse(studySpot.getWaitlist(9).contains(john));
        assertTrue(studySpot.getWaitlist(10).contains(jane));
    }


    @Test
    public void testDuplicateStudentRejectedAcrossAllLocations() {
        studySpot.addStudent(john, 9);
        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 10));
        studySpot.advanceHour();
        assertSame(john, studySpot.admitStudent());
        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 10));
    }


    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            null, 9));
        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 7));
        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 24));
        assertThrows(IllegalArgumentException.class, () -> studySpot
            .removeStudent(null));
        assertThrows(IllegalArgumentException.class, () -> studySpot
            .getWaitlist(24));
    }


    @Test
    public void testCannotAddToPastSlot() {
        studySpot.advanceHour();
        assertThrows(IllegalArgumentException.class, () -> studySpot.addStudent(
            john, 8));
    }


    @Test
    public void testAdvanceThroughEndOfDay() {
        for (int i = 0; i < 16; i++) {
            studySpot.advanceHour();
        }
        assertEquals(24, studySpot.getCurrentHour());
        assertEquals(-1, studySpot.getNextAvailableHour());
        assertThrows(IllegalStateException.class, () -> studySpot
            .advanceHour());
    }


    @Test
    public void testNullStudyRoomRejected() {
        assertThrows(IllegalArgumentException.class, () -> new StudySpot(null));
    }


    @Test
    public void testNextAvailableHour() {
        assertEquals(9, studySpot.getNextAvailableHour());
        studySpot.advanceHour();
        assertEquals(10, studySpot.getNextAvailableHour());
    }


    @Test
    public void testStudyRoomCapacityPreventsAdmission() {
        StudyRoom smallRoom = new StudyRoom(1);
        StudySpot smallSpot = new StudySpot(smallRoom);
        Student first = new Student("First");
        Student second = new Student("Second");
        smallSpot.addStudent(first, 8);
        assertSame(first, smallSpot.admitStudent());
        smallSpot.addStudent(second, 9);
        smallSpot.advanceHour();
        assertThrows(IllegalStateException.class, smallSpot::admitStudent);
        assertTrue(smallSpot.getWaitlist(9).contains(second));
    }
}
