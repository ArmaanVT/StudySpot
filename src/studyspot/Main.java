// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// See README.md

package studyspot;

import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * This class serves as the entry point.
 * 
 * @author Armaan
 * @version 2026.11.08
 */
public class Main {

    /**
     * The main driver class.
     * 
     * @param args
     *            default arguments.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudyRoom studyRoom = new StudyRoom();
        
        // Model.
        StudySpot model = new StudySpot(studyRoom);
        
        // Views.
        MainMenuView mainMenuView = new MainMenuView();
        StudyRoomView studyRoomView = new StudyRoomView();
        StudentView studentView = new StudentView();
        WaitlistView waitlistView = new WaitlistView();
        MessageView messageView = new MessageView();
        
        // Controller.
        StudySpotController controller = new StudySpotController(model,
            mainMenuView, studyRoomView, studentView, waitlistView,
            messageView);

        boolean running = true;
        
        // Main loop.
        while (running) {

            try {
                controller.displayMainMenu();

                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }

                int selection = scanner.nextInt();

                // Exit option
                if (selection == 8) {
                    running = false;
                    System.out.println("Thank you for using StudySpot!");
                }

                else {
                    controller.handleMenuSelection(selection);
                }

            }
            
            // Catches any exceptions.
            catch (Exception e) {

                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Returning to the main menu...");
            }
        }

        scanner.close();
    }
}
