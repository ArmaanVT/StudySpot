// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Catherine
//
// LLM Statement:
// See README.md

package studyspot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Displays available students and prompts the user to select one.
 *
 * @author Catherine
 * @version 2026.11.09
 */
public class StudentView {
    Student selectedStudent;
    private final List<Student> students;
    private final Scanner scanner;

    /**
     * Creates the view with the standard demonstration student list.
     */
    public StudentView() {
        this(new Scanner(System.in));
    }


    /**
     * Creates the view using a shared input scanner.
     *
     * @param scanner
     *            input source
     */
    public StudentView(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }
        this.scanner = scanner;
        students = new ArrayList<>();
        String[] names = { "John", "Catherine", "Jaden", "Armaan", "Ethan",
            "Caroline", "Yug", "Cassandra" };
        for (String name : names) {
            students.add(new Student(name));
        }
    }


    /**
     * Prompts for and returns a student. A blank or unknown selection returns
     * null.
     *
     * @return selected student, or null when no valid student was selected
     */
    public Student getStudent() {
        // A preselected value is useful for controller tests and programmatic
        // callers; consume it once, then use the interactive prompt normally.
        if (selectedStudent != null) {
            Student choice = selectedStudent;
            selectedStudent = null;
            return choice;
        }
        displayList();
        System.out.print("Choose a student (enter a name): ");
        String userInput = scanner.nextLine().trim();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(userInput)) {
                return student;
            }
        }
        return null;
    }


    /**
     * Displays the available students.
     */
    public void displayList() {
        System.out.println("Choose a student:");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }


    /**
     * Returns a copy of the available student list.
     *
     * @return available students
     */
    public List<Student> getStudents() {
        return List.copyOf(students);
    }
}
