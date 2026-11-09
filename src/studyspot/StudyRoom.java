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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the students currently occupying a study room.
 *
 * @author Ethan
 * @version 2026.11.09
 */
public class StudyRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Default maximum number of students in the room. */
    public static final int DEFAULT_CAPACITY = 5;

    private final List<Student> students;
    private final int capacity;

    /**
     * Creates an empty study room with the default capacity.
     */
    public StudyRoom() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Creates an empty study room with a specified capacity.
     *
     * @param capacity
     *            maximum number of students
     * @throws IllegalArgumentException
     *             if capacity is not positive
     */
    public StudyRoom(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        students = new ArrayList<>();
    }


    /**
     * Adds a student to the study room.
     *
     * @param s
     *            the student to add
     * @throws IllegalArgumentException
     *             if the student is null, already in the
     *             room, or the room is full
     */
    public void addStudent(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (students.contains(s)) {
            throw new IllegalArgumentException(
                "Student is already in the room.");
        }
        if (students.size() >= capacity) {
            throw new IllegalStateException("The study room is full.");
        }
        students.add(s);
        s.setInStudyRoom(true);
    }


    /**
     * Removes a student from the study room.
     *
     * @param s
     *            the student to remove
     */
    public void removeStudent(Student s) {
        if (s != null && students.remove(s)) {
            s.setInStudyRoom(false);
        }
    }


    /**
     * Returns a read-only view of the students in the room.
     *
     * @return students currently in the room
     */
    public List<Student> getStudent() {
        return getStudents();
    }


    /**
     * Returns a read-only view of the students in the room.
     *
     * @return students currently in the room
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }


    /**
     * Returns the room capacity.
     *
     * @return maximum number of students
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Checks whether the room has reached capacity.
     *
     * @return true if the room is full
     */
    public boolean isFull() {
        return students.size() >= capacity;
    }
}
