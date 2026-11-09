package studyspot;

// -------------------------------------------------------------------------
/**
 * Displays and provides access to the selected student.
 */
public class StudentView {

    Student selectedStudent;

    /**
     * Returns and displays the selected student, if one is selected.
     *
     * @return the selected student, or null if no student is selected.
     */
    public Student getStudent() {
        if (selectedStudent == null) {
            System.out.println("No student selected.");
        }
        else {
            System.out.println("Selected student: "
                + selectedStudent.getName());
        }

        return selectedStudent;
    }


    /**
     * Sets the selected student.
     *
     * @param student
     *            the student to select.
     */
    public void setSelectedStudent(Student student) {
        selectedStudent = student;
    }
}