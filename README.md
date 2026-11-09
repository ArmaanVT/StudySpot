# StudySpot — Project 3 preliminary Build

StudySpot is a Java console application that allows you to admit or remove students into a study room.

## How to Run

Import the repository into eclipse, right-click the project and select "run-as Java application." Finally, if it prompts, selected "main."

## Reflection

When moving from specification to code, we realized that a to-do-list view would be beneficial to show the user what is needed to be done. The project has multiple moving parts, and we thought it would be beneficial to include it. However, we largely kept the application the same from the specification document. The to-do-list was our biggest change.

We added helper behavior (helper methods), such as determining the next available hour.

We kept the model separate from the view classes. Classes, such as Student, Queue, StudyRoom, and StudySpot do not need to know how the menu is displayed or how the user enters information. The controller communicates between the model and view classes.

We did not have to move view-related code into the model to make it work.

During our implementation, and with the help of generative AI, we figured out how to maintain separate waitlists for different hours, and how to use a FIFO queue to determine which students should be admitted first. 

Also, according to GenAI, we decided to implement a "serializable" data structure to make writing to files easier when implementing the save feature. A serializable data structure allows you to store a copy of an object as bytes.

As of now, our save manager does not work yet. We still have to implement that feature.

## Next Steps

In the MVP, we still need to add our save manager. 

A non-trivial feature we will attempt to implement will be changing a students scheduled waitlist hour.
We predict that this feature will affect the model, as StudySpot will need a new method to remove a student from their current waitlist and add them to a different hour; the controller, as StudySpotController will need to process and handle this request; and view, as the view will need a new menu option.
We will add tests to confirm that a student has been moved from one waitlist to another. 

Jaden and I will handle the save manager, while Catherine and Ethan will handle the stretch goal.

## Attribution

| File | Contributor(s) | Contribution and method |
|---|---|---|
| `MainMenuView.java` | Jaden | Drafted; Used the assistance of GenAI |
| `MainMenuViewTest.java` | Jaden | Drafted; Used the assistance of GenAI |
| `MessageView.java` | Ethan | Drafted; Used the assistance of GenAI |
| `Node.java` | Jaden | Drafted; Used the assistance of GenAI |
| `NodeTest.java` | Jaden | Drafted; Used the assistance of GenAI |
| `Queue.java` | Jaden | Drafted; Used the assistance of GenAI |
| `QueueTest.java` | Jaden | Drafted; Used the assistance of GenAI |
| `Student.java` | Jaden | Drafted; Used the assistance of GenAI |
| `StudentTest.java` | Jaden | Drafted; Used the assistance of GenAI |
| `StudentView.java` | Catherine | Drafted; Used the assistance of GenAI |
| `StudentViewTest.java` | Catherine | Drafted; Used the assistance of GenAI |
| `StudyRoom.java` | Ethan | Drafted; Used the assistance of GenAI |
| `StudyRoomTest.java` | Ethan | Drafted; Used the assistance of GenAI |
| `StudyRoomView.java` | Ethan | Drafted; Used the assistance of GenAI |
| `StudySpot.java` | Armaan | Drafted; Used the assistance of GenAI |
| `StudySpotController.java` | Armaan | Drafted; Used the assistance of GenAI |
| `StudySpotControllerTest.java` | Armaan | Drafted; Used the assistance of GenAI |
| `StudySpotTest.java` | Armaan | Drafted; Used the assistance of GenAI |
| `WaitlistView.java` | Armaan | Drafted; Used the assistance of GenAI |
| `WaitlistViewTest.java` | Armaan | Drafted; Used the assistance of GenAI |
| `README.md` | All members | Team reviewied |

## GenAI use

We used GenAI tools solely for assistance and understanding, now for reviewing the program. ChatGPT was used to assist us in debugging, JUnit tests, and integration. Some team members used Claude and Gemini for their designs. However, our team reviews allowed us to overcome presenting code we cannot explain.
