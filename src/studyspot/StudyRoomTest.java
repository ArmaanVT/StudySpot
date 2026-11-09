// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Jaden
//
// LLM Statement:
// See README.md

package studyspot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for StudyRoom.
 *
 * @author Ethan
 * @version 2026.11.09
 */
public class StudyRoomTest {
    @Test
    public void testAddRemoveAndStudyStatus() {
        StudyRoom room = new StudyRoom();
        Student student = new Student("John");
        room.addStudent(student);
        assertTrue(room.getStudents().contains(student));
        assertTrue(student.isInStudyRoom());
        room.removeStudent(student);
        assertFalse(room.getStudents().contains(student));
        assertFalse(student.isInStudyRoom());
    }


    @Test
    public void testCapacityAndInvalidStudents() {
        StudyRoom room = new StudyRoom(1);
        Student first = new Student("First");
        Student second = new Student("Second");
        room.addStudent(first);
        assertTrue(room.isFull());
        assertThrows(IllegalStateException.class, () -> room.addStudent(
            second));
        assertThrows(IllegalArgumentException.class, () -> room.addStudent(
            null));
        assertThrows(IllegalArgumentException.class, () -> new StudyRoom(0));
    }


    @Test
    public void testDuplicateStudentRejected() {
        StudyRoom room = new StudyRoom();
        Student student = new Student("John");
        room.addStudent(student);
        assertThrows(IllegalArgumentException.class, () -> room.addStudent(
            student));
    }
}
