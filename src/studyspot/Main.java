// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan
//
// LLM Statement:
// See README.md

package studyspot;

import java.util.Scanner;

/**
 * This class serves as the entry point.
 *
 * @author Armaan
 * @version 2026.11.09
 */
public class Main {
    /**
     * Starts the application.
     *
     * @param args
     *            The default args parameter.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Model.
        SaveManager saveManager = new SaveManager();
        java.nio.file.Path saveFile = java.nio.file.Path.of("studyspot.save");
        StudySpot model;
        try {
            model = java.nio.file.Files.exists(saveFile)
                ? saveManager.load(saveFile)
                : new StudySpot(new StudyRoom());
        }
        catch (java.io.IOException e) {
            System.out.println("Could not load save; starting a new session: "
                + e.getMessage());
            model = new StudySpot(new StudyRoom());
        }

        // Views.
        MainMenuView mainMenu = new MainMenuView();
        StudyRoomView roomView = new StudyRoomView();
        StudentView studentView = new StudentView(scanner);
        WaitlistView waitlistView = new WaitlistView(scanner);
        MessageView messageView = new MessageView();

        // Controller.
        StudySpotController controller = new StudySpotController(model,
            mainMenu, roomView, studentView, waitlistView, messageView,
            saveManager, saveFile);

        boolean running = true;

        // Starts the main loop.
        while (running) {
            controller.displayMainMenu();

            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            int selection;

            try {
                selection = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                messageView.showError("Invalid input. Please enter a number.");
                continue;
            }

            if (selection == 10) {
                running = false;
            }
            controller.handleMenuSelection(selection);
        }
    }
}
