package studyspot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Student. AI assistance: drafted with ChatGPT. */
public class StudentTest {
    @Test
    public void testDefaults() {
        Student s = new Student("Jaden");
        assertEquals("Jaden", s.getName());
        assertEquals(1, s.getStudyHoursNeeded());
        assertFalse(s.isInStudyRoom());
    }


    @Test
    public void testUpdateName() {
        Student s = new Student("Jaden", 2);
        s.setName("  Anne-Marie O'Neil  ");
        assertEquals("Anne-Marie O'Neil", s.getName());
        s.setName("李");
        assertEquals("李", s.getName());
    }


    @Test
    public void testInvalidNames() {
        Student s = new Student("Jaden");
        String[] invalid = { null, "", "   ", "12345", "---" };
        for (String name : invalid) {
            assertThrows(IllegalArgumentException.class, () -> new Student(
                name));
            assertThrows(IllegalArgumentException.class, () -> s.setName(name));
            assertEquals("Jaden", s.getName());
        }
    }


    @Test
    public void testHours() {
        Student s = new Student("Jaden", 2);
        assertEquals(2, s.getStudyHoursNeeded());
        s.setStudyHoursNeeded(3);
        assertEquals(3, s.getStudyHoursNeeded());
        for (int hours : new int[] { 0, -1 }) {
            assertThrows(IllegalArgumentException.class, () -> new Student(
                "Jaden", hours));
            assertThrows(IllegalArgumentException.class, () -> s
                .setStudyHoursNeeded(hours));
        }
        assertEquals(3, s.getStudyHoursNeeded());
    }


    @Test
    public void testStudyStatus() {
        Student s = new Student("Jaden");
        s.setInStudyRoom(true);
        assertTrue(s.isInStudyRoom());
        s.setInStudyRoom(false);
        assertFalse(s.isInStudyRoom());
    }
}
