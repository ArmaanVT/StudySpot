package studyspot;

import java.util.Scanner;

public class StudentView {
    private Student[] students;

    private Scanner scanner;

    public StudentView() {
        scanner = new Scanner(System.in);
        students = new Student[7];
        students[0] = new Student("Catherine");
        students[1] = new Student("Jaden");
        students[2] = new Student("Armaan");
        students[3] = new Student("Ethan");
        students[4] = new Student("Caroline");
        students[5] = new Student("Yug");
        students[6] = new Student("Cassandra");
    }


    public Student getStudent() {
        displayList();
        System.out.println(
            "Please enter the name of student you'd like to get: ");
        String userInput = scanner.nextLine();
        for (int i = 0; i < students.length; i++) {
            if (students[i].getName().equalsIgnoreCase(userInput)) {
                return students[i];
            }
        }

        return null;

    }


    public void displayList() {

        for (int i = 0; i < students.length; i++) {
            System.out.println((i + 1) + ": " + students[i].getName());
        }
    }

}
