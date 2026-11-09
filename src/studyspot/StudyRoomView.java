// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ethan
//
// LLM Statement:
// See README.md

package studyspot;

import java.util.List;

/**
 * Displays the students occupying the study room.
 *
 * @author Ethan
 * @version 2026.11.09
 */
public class StudyRoomView {
    List<Student> displayedStudents;

    /**
     * Displays the current study room occupants.
     *
     * @param students
     *            students currently in the room
     */
    public void displayRoom(List<Student> students) {
        displayedStudents = List.copyOf(students);
        System.out.println("Study Room:");
        if (students.isEmpty()) {
            System.out.println("The study room is empty.");
            return;
        }
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
