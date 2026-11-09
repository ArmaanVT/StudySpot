package studyspot;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudyRoomTest {

    private StudyRoom studyRoom;

    @BeforeEach
    public void setUp() {
        studyRoom = new StudyRoom();
    }


    @Test
    public void testRoomStartsEmpty() {
        assertTrue(studyRoom.getStudents().isEmpty());
    }


    @Test
    public void testAddStudent() {
        studyRoom.addStudent(null);

        assertEquals(1, studyRoom.getStudents().size());
        assertNull(studyRoom.getStudents().get(0));
    }


    @Test
    public void testRemoveStudent() {
        studyRoom.addStudent(null);

        studyRoom.removeStudent(null);

        assertTrue(studyRoom.getStudents().isEmpty());
    }


    @Test
    public void testStudentAccessorsReturnSameList() {
        assertSame(studyRoom.getStudent(), studyRoom.getStudents());
    }
}