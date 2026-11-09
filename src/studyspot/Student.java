package studyspot;

/**
 * Stores a student's name, requested hours, and study status.
 * Students use object identity: two people may have the same name.
 * AI assistance: implementation and tests drafted with ChatGPT.
 * @author Jaden Moon
 * @version 2026.11.08
 */
public class Student {
    private String name;
    private int studyHoursNeeded;
    private boolean inStudyRoom;

    /** @param name student's name; defaults to one requested hour */
    public Student(String name) {
        this(name, 1);
    }

    /**
     * @param name student's name
     * @param studyHoursNeeded positive number of requested hours
     */
    public Student(String name, int studyHoursNeeded) {
        setName(name);
        setStudyHoursNeeded(studyHoursNeeded);
        inStudyRoom = false;
    }

    /** @return the student's name */
    public String getName() {
        return name;
    }

    /**
     * Names must contain at least one letter. Surrounding spaces are removed.
     * @param name new name
     * @throws IllegalArgumentException if the name is null or has no letters
     */
    public void setName(String name) {
        if (name == null || !name.codePoints().anyMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Name must contain a letter.");
        }
        this.name = name.trim();
    }

    /** @return requested study hours */
    public int getStudyHoursNeeded() {
        return studyHoursNeeded;
    }

    /**
     * @param hours positive number of requested hours
     * @throws IllegalArgumentException if hours is not positive
     */
    public void setStudyHoursNeeded(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Study hours must be positive.");
        }
        studyHoursNeeded = hours;
    }

    /** @return whether the student is currently studying */
    public boolean isInStudyRoom() {
        return inStudyRoom;
    }

    /** @param inStudyRoom whether the student is currently studying */
    public void setInStudyRoom(boolean inStudyRoom) {
        this.inStudyRoom = inStudyRoom;
    }
}
