// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// See README.md

package studyspot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// -------------------------------------------------------------------------
/**
 * This class serves as the model for StudySpot.
 * 
 * @author Armaan
 * @version 2026.11.08
 */
public class StudySpot {

    // The current hour.
    private int currentHour;

    // A map of all the waiting queues.
    private Map<Integer, Queue> waitingQueues;

    // A reference to the study room.
    private StudyRoom studyRoom;

    /**
     * Creates a new StudySpot object.
     * 
     * @param studyRoom
     *            the study room.
     */
    public StudySpot(StudyRoom studyRoom) {
        this.currentHour = 8;
        this.waitingQueues = new HashMap<>();
        this.studyRoom = studyRoom;
    }


    /**
     * Adds a student to the waiting list for a specified hour.
     * 
     * @param s
     *            a student.
     * @param hour
     *            the hour to add the student.
     */
    public void addStudent(Student s, int hour) {

        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (hour < 8 || hour > 23) {
            throw new IllegalArgumentException(
                "Time must be between 8 AM and 11 PM.");
        }

        if (isStudentRegistered(s)) {
            throw new IllegalArgumentException(
                "Student is already registered.");
        }

        waitingQueues.putIfAbsent(hour, new Queue());

        waitingQueues.get(hour).enqueue(s);
    }


    /**
     * Admit a student to the queue.
     */
    public Student admitStudent() {

        Queue queue = waitingQueues.get(currentHour);

        if (queue == null || queue.isEmpty()) {
            return null;
        }

        Student student = queue.dequeue();

        studyRoom.addStudent(student);

        return student;
    }


    /**
     * Removes a student from the waitlist.
     * 
     * @param s
     *            the student to remove.
     */
    public void removeStudent(Student s) {

        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        for (Queue queue : waitingQueues.values()) {
            queue.remove(s);
        }
    }


    /**
     * Advances the hour.
     */
    public void advanceHour() {

        if (currentHour >= 24) {
            throw new IllegalStateException("The study day has ended.");
        }

        currentHour++;
    }


    /**
     * Return the waitlist for a specified hour.
     * 
     * @param hour
     *            the hour to return.
     */
    public Queue getWaitlist(int hour) {

        if (hour < 8 || hour > 23) {
            throw new IllegalArgumentException("Invalid time slot.");
        }

        waitingQueues.putIfAbsent(hour, new Queue());

        return waitingQueues.get(hour);
    }


    /**
     * Returns the current hour.
     */
    public int getCurrentHour() {
        return currentHour;
    }


    /**
     * Returns the study room.
     */
    public StudyRoom getStudyRoom() {
        return studyRoom;
    }


    /**
     * Checks whether a student is already registered
     * 
     * @param s
     *            the student to check.
     */
    private boolean isStudentRegistered(Student s) {

        // Check study room
        if (studyRoom.getStudents().contains(s)) {
            return true;
        }

        // Check every waitlist
        for (Queue queue : waitingQueues.values()) {

            if (queue.contains(s)) {
                return true;
            }
        }

        return false;
    }
}
