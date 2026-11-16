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

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests the SaveManager class.
 * 
 * @author Armaan
 * @version 2026.11.16
 */
class SaveManagerTest {
    // Creates a temporary directory to test.
    @TempDir
    Path temporaryDirectory;

    @Test
    void savesAndLoadsCurrentHourAndWaitlists() throws IOException {
        StudySpot original = new StudySpot(new StudyRoom());
        Student student = new Student("Avery", 3);
        original.addStudent(student, 11);
        original.addStudent(new Student("Jordan"), 14);
        original.advanceHour();

        Path file = temporaryDirectory.resolve("studyspot.save");
        SaveManager manager = new SaveManager();
        manager.save(original, file);

        StudySpot restored = manager.load(file);

        assertEquals(9, restored.getCurrentHour());
        assertEquals(1, restored.getWaitlist(11).size());
        assertEquals("Avery", restored.getWaitlist(11).peek().getName());
        assertEquals("Jordan", restored.getWaitlist(14).peek().getName());
        assertTrue(restored.isWaiting(restored.getWaitlist(11).peek(), 11));
    }


    @Test
    void savesAndLoadsStudyRoomOccupants() throws IOException {
        StudySpot original = new StudySpot(new StudyRoom());
        Student student = new Student("Avery");
        original.getStudyRoom().addStudent(student);
        Path file = temporaryDirectory.resolve("studyspot-room.save");

        SaveManager manager = new SaveManager();
        manager.save(original, file);
        StudySpot restored = manager.load(file);

        assertEquals(1, restored.getStudyRoom().getStudents().size());
        Student restoredStudent = restored.getStudyRoom().getStudents().get(0);
        assertEquals("Avery", restoredStudent.getName());
        assertTrue(restoredStudent.isInStudyRoom());
    }


    @Test
    void rejectsNullArguments() {
        SaveManager manager = new SaveManager();
        Path file = temporaryDirectory.resolve("save");

        assertThrows(IllegalArgumentException.class, () -> manager.save(null,
            file));
        assertThrows(IllegalArgumentException.class, () -> manager.save(
            new StudySpot(new StudyRoom()), null));
        assertThrows(IllegalArgumentException.class, () -> manager.load(null));
    }


    @Test
    void missingFileReportsIOException() {
        SaveManager manager = new SaveManager();
        Path missing = temporaryDirectory.resolve("missing.save");
        assertThrows(IOException.class, () -> manager.load(missing));
    }
}
