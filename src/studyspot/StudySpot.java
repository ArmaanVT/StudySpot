// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)
//
// LLM Statement:
// See README.md


package studyspot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The StudySpot model. Maintains the simulated time, hourly waitlists, and
 * active study room.
 *
 * @author Armaan
 * @version 2026.11.09
 */
public class StudySpot implements Serializable {
    private static final long serialVersionUID = 1L;

    /** First available hour in the simulated day. */
    public static final int OPENING_HOUR = 8;
    /** Last available waitlist hour in the simulated day. */
    public static final int CLOSING_HOUR = 23;
    /** Hour value representing midnight/end of day. */
    public static final int END_OF_DAY = 24;

    private int currentHour;
    private final Map<Integer, Queue> waitingQueues;
    private final StudyRoom studyRoom;

    /**
     * Creates a StudySpot model, starting at 8 AM.
     *
     * @param studyRoom
     *            the study room managed by this model
     * @throws IllegalArgumentException
     *             if studyRoom is null
     */
    public StudySpot(StudyRoom studyRoom) {
        if (studyRoom == null) {
            throw new IllegalArgumentException("Study room cannot be null.");
        }
        currentHour = OPENING_HOUR;
        waitingQueues = new HashMap<>();
        this.studyRoom = studyRoom;
    }


    /**
     * Adds a student to the waitlist for the specified hour.
     *
     * @param s
     *            student to add
     * @param hour
     *            hour in 24-hour format, from 8 through 23
     * @throws IllegalArgumentException
     *             if the student is null, the hour is
     *             invalid, the hour has passed, or the student is already
     *             registered
     */
    public void addStudent(Student s, int hour) {
        validateHour(hour);
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (hour < currentHour) {
            throw new IllegalArgumentException(
                "Cannot add a student to a past time slot.");
        }
        if (isStudentRegistered(s)) {
            throw new IllegalArgumentException(
                "Student is already registered.");
        }
        waitingQueues.computeIfAbsent(hour, ignored -> new Queue()).enqueue(s);
    }


    /**
     * Removes and admits the first student waiting for the current hour.
     *
     * @return the admitted student, or null if nobody is waiting or the room
     *         cannot accept another student
     */
    public Student admitStudent() {
        Queue queue = waitingQueues.get(currentHour);
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        if (studyRoom.isFull()) {
            throw new IllegalStateException("The study room is full.");
        }
        Student student = queue.dequeue();
        studyRoom.addStudent(student);
        return student;
    }


    /**
     * Removes a student from every waitlist.
     *
     * @param s
     *            student to remove
     * @throws IllegalArgumentException
     *             if s is null
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
     * Advances the simulated clock by one hour.
     *
     * @throws IllegalStateException
     *             if the simulated day has ended
     */
    public void advanceHour() {
        if (currentHour >= END_OF_DAY) {
            throw new IllegalStateException("The study day has ended.");
        }
        currentHour++;
    }


    /**
     * Returns the waitlist for a specified hour, creating it if necessary.
     *
     * @param hour
     *            hour in 24-hour format
     * @return the queue for the requested hour
     * @throws IllegalArgumentException
     *             if the hour is outside 8 AM–11 PM
     */
    public Queue getWaitlist(int hour) {
        validateHour(hour);
        return waitingQueues.computeIfAbsent(hour, ignored -> new Queue());
    }


    /**
     * Returns the current simulated hour.
     *
     * @return current hour in 24-hour format
     */
    public int getCurrentHour() {
        return currentHour;
    }


    /**
     * Returns the active study room.
     *
     * @return the study room
     */
    public StudyRoom getStudyRoom() {
        return studyRoom;
    }


    /**
     * Checks whether a student is in any waitlist or in the study room.
     *
     * @param s
     *            student to check
     * @return true if the student is already registered
     */
    public boolean isStudentRegistered(Student s) {
        if (s == null) {
            return false;
        }
        if (studyRoom.getStudents().contains(s)) {
            return true;
        }
        for (Queue queue : waitingQueues.values()) {
            if (queue.contains(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns whether the student is waiting at the specified hour.
     *
     * @param s
     *            student to find
     * @param hour
     *            requested hour
     * @return true if s is in that hour's queue
     */
    public boolean isWaiting(Student s, int hour) {
        validateHour(hour);
        Queue queue = waitingQueues.get(hour);
        return queue != null && queue.contains(s);
    }


    /**
     * Returns the next hour that can be used for a new reservation.
     *
     * @return next hour, or -1 if no future slot remains
     */
    public int getNextAvailableHour() {
        return currentHour < CLOSING_HOUR ? currentHour + 1 : -1;
    }


    /**
     * Validates an hour in the supported daily schedule.
     *
     * @param hour
     *            hour to validate
     * @throws IllegalArgumentException
     *             if the hour is outside 8–23
     */
    private void validateHour(int hour) {
        if (hour < OPENING_HOUR || hour > CLOSING_HOUR) {
            throw new IllegalArgumentException(
                "Time must be between 8 AM and 11 PM.");
        }
    }
}
