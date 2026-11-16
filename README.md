# StudySpot — Project 3

StudySpot is a Java console application for managing a study room and hourly student waitlists. It uses a linked FIFO queue so students waiting for the same hour are admitted in the order they joined.

Team: Armaan, Jaden, Catherine, and Ethan.

## How to Run

1. Import the repository into Eclipse as an existing Java project.
2. Open src/studyspot/Main.java.
3. Right-click Main.java and select **Run As > Java Application**. If prompted, choose **studyspot.Main**.
4. Use the numbered menu in the Eclipse Console.

The project includes JUnit tests. Make sure the required JUnit libraries are on the project's build path before running the tests.

## Current Features

- Add students and remove them from waitlists.
- Admit the next student from the current hour's waitlist using FIFO order.
- View an hourly waitlist or the current study room occupants.
- Advance the simulated clock by one hour. The day starts at 8 AM, and waitlist hours run from 8 AM through 11 PM.
- Change a student's scheduled waitlist hour.
- Save and load the current session.
- Display a to-do list based on the current application state.

### Saving and Loading

Choose **Save** to write the current hour, waitlists, and study room state to studyspot.save in the application's working directory. Choose **Load** to restore the saved session; this replaces the current in-memory state. The program also attempts to load this file at startup and save when **Exit** is selected.

SaveManager uses Java serialization to store the model. If a save cannot be loaded at startup, the program displays an error and starts a new session. Save and load errors from the menu are also displayed to the user.

### Changing a Waitlist Hour

Choose **Change Waitlist Hour**, select a waiting student, and enter the destination hour. StudySpot checks that the hour is valid, has not passed, and is different from the student's existing waitlist hour. It also checks that the student is currently waiting. A valid move removes the student from the original queue and adds them to the back of the destination queue. Invalid moves leave the original queue unchanged.

## Reflection

We kept most of the original specification, including separate model, view, and controller classes. Student, Queue, StudyRoom, and StudySpot manage the application data and rules. The view classes handle console input and output, while StudySpotController connects the menu actions to the model.

The to-do list was one change from our original design. It helps show which students need to be admitted. We also added helper methods for finding the next available hour and locating a registered student by name.

Since the preliminary build, SaveManager has been implemented and connected to the application. It saves the full model so the current hour, waitlists, and room occupants can be restored together. Main handles startup loading, and the controller handles the Save and Load menu options.

The planned stretch feature, changing a student's waitlist hour, is also implemented. This required changes across the model, controller, and menu. The model validates the move before removing the student from the original queue, which prevents an invalid request from losing their reservation.

GenAI assisted the development process, including work on hourly waitlists, FIFO behavior, serialization, debugging, and tests. Armaan also reported using GenAI for the latest implementation update.

## Testing and Remaining Checks

SaveManagerTest includes tests for restoring the current hour and waitlists, restoring room occupants, rejecting null arguments, and reporting a missing save file. The repository also contains tests for the queue, student, study room, model, controller, and several views.

The presence of test files does not establish that the current test suite passes. The tests were not executed as part of this README update.

Useful regression checks are to move a student between hours and confirm FIFO order, reject invalid moves without changing the original queue, and save/reload a session after making changes. Existing menu and controller tests should also be checked against the updated ten-option menu.

## Attribution

The original contribution records are retained below, with the latest implementation changes added. StudentViewTest.java is listed as a historical contribution because it was removed in the latest update.

| File | Contributor(s) | Contribution and method |
|---|---|---|
| Main.java | Armaan | Application entry point and startup save/load integration; GenAI assistance disclosed |
| MainMenuView.java | Jaden; Armaan | Original draft by Jaden with GenAI assistance; latest menu update committed by Armaan |
| MainMenuViewTest.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| MessageView.java | Ethan | Original draft; GenAI assistance recorded in the preliminary README |
| Node.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| NodeTest.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| Queue.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| QueueTest.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| SaveManager.java | Armaan | Save/load implementation; GenAI assistance disclosed for the latest update |
| SaveManagerTest.java | Armaan | Save/load tests; GenAI assistance disclosed for the latest update |
| Student.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| StudentTest.java | Jaden | Original draft; GenAI assistance recorded in the preliminary README |
| StudentView.java | Catherine; Armaan | Original draft by Catherine with GenAI assistance; registered-student lookup update committed by Armaan |
| StudentViewTest.java (removed) | Catherine | Original draft with GenAI assistance; removed in the latest update |
| StudyRoom.java | Ethan; Armaan | Original draft by Ethan with GenAI assistance; latest cleanup committed by Armaan |
| StudyRoomTest.java | Ethan | Original draft; GenAI assistance recorded in the preliminary README |
| StudyRoomView.java | Ethan | Original draft; GenAI assistance recorded in the preliminary README |
| StudySpot.java | Armaan | Model and waitlist-hour changes; GenAI assistance disclosed |
| StudySpotController.java | Armaan | Controller, save/load, and waitlist-hour integration; GenAI assistance disclosed |
| StudySpotControllerTest.java | Armaan | Original draft; GenAI assistance recorded in the preliminary README |
| StudySpotTest.java | Armaan | Original draft; GenAI assistance recorded in the preliminary README |
| WaitlistView.java | Armaan | Original draft; GenAI assistance recorded in the preliminary README |
| WaitlistViewTest.java | Armaan | Original draft; GenAI assistance recorded in the preliminary README |
| README.md | All members; Jaden | Original team documentation; latest revision prepared at Jaden's request with ChatGPT assistance |

## GenAI Use

The preliminary README recorded ChatGPT assistance with debugging, JUnit tests, and integration, and Claude and Gemini assistance with some team members' designs. It also recorded GenAI assistance with hourly waitlists, FIFO queues, and Java serialization.

Armaan confirmed that he used GenAI for the latest implementation update, which added SaveManager, waitlist-hour changes, and related integration and tests. This disclosure does not identify a specific tool or prompt for that update because those details were not provided.

ChatGPT was used to review the visible repository code and revise this README, including the feature descriptions, run instructions, reflection, and attribution. No Java source files were changed as part of this documentation update.
