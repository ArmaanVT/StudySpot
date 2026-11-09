package studyspot;

import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Displays the students currently in the study room.
 */
public class StudyRoomView {

    List<Student> displayedStudents = new ArrayList<>();

    /**
     * Displays the contents of the study room.
     *
     * @param students
     *            the students in the room.
     */
    public void displayRoom(List<Student> students) {
        displayedStudents = new ArrayList<>(students);

        System.out.println("\n=== Study Room ===");
        if (displayedStudents.isEmpty()) {
            System.out.println("The study room is empty.");
            return;
        }

        for (Student student : displayedStudents) {
            System.out.println(student.getName());
        }
    }
}