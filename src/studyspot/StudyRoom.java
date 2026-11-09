
package studyspot;

import java.util.ArrayList;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Represents the students currently in a study room.
 * 
 * 
 * As of writting this the student class has not been made if you are in charge of writting it please
 * use my name variables so I don't have to change them later thanks.
 *
 * @author Ethan
 * @version 2026.11.08
 */
public class StudyRoom {

	private List<Student> students;

	/**
	 * Creates an empty study room.
	 */
	public StudyRoom() {
		students = new ArrayList<>();
	}


	/**
	 * Adds a student to the study room.
	 *
	 * @param s
	 *            the student to add.
	 */
	public void addStudent(Student s) {
		students.add(s);
	}


	/**
	 * Removes a student from the study room.
	 *
	 * @param s
	 *            the student to remove.
	 */
	public void removeStudent(Student s) {
		students.remove(s);
	}


	/**
	 * Returns all students in the study room.
	 *
	 * @return the students in the study room.
	 */
	public List<Student> getStudent() {
		return students;
	}


	/**
	 * Returns all students in the study room.
	 *
	 * @return the students in the study room.
	 */
	public List<Student> getStudents() {
		return getStudent();
	}
}
