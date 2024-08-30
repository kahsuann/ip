import java.io.IOException;
import java.util.*;

public class Susan {
    public static void main(String[] args) throws IOException {
        // Greet
        System.out.println("Hi there, Susan's at your service!");
        System.out.println("How may I assist you?");

        // Prepare User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Initialise Task Arraylist
        List<Task> tasks = new ArrayList<>();

        // Make File
        Save listSaver = new Save();

        // Add, List
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("~~~");
            // Print the list
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Babes. YOU NEED TO:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
            // Mark as Done
            else if (userInput.startsWith("mark")) {
                int n = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                tasks.get(n - 1).markAsDone();
                System.out.println("Good job! This task has been conquered:");
                System.out.println(" " + tasks.get(n - 1));
            }
            // Mark as Undone
            else if (userInput.startsWith("unmark")) {
                int n = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                tasks.get(n - 1).undoMark();
                System.out.println("OK. This task is not done yet:");
                System.out.println(" " + tasks.get(n - 1));
            }
            // Delete
            else if (userInput.startsWith("delete")) {
                int n = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                Task deletedTask = tasks.get(n - 1);
                tasks.remove(n - 1);
                System.out.println("OK. This task has been removed:");
                System.out.println(deletedTask);
            }
            // Add Task to list
            else {
                // ToDo
                if (userInput.startsWith("todo")) {
                    // Get task description
                    tasks.add(new ToDo(userInput.replace("todo", "").trim()));

                }
                // Deadline
                else if (userInput.startsWith("deadline")) {
                    // Get task description and by
                    String[] taskDetails = userInput.split("/by");
                    tasks.add(new Deadline(taskDetails[0].replace("deadline", "").trim(),
                            taskDetails[1].trim()));
                }
                // Event
                else if (userInput.startsWith("event")) {
                    // Get task description, from and to
                    String[] taskDetails = userInput.split("/");
                    tasks.add(new Event(taskDetails[0].replace("event", "").trim(),
                            taskDetails[1].replace("from ", "").trim(),
                            taskDetails[2].replace("to ", "").trim()));
                }
                // Unrecognised Command
                else {
                    System.out.println("You cry a lot and you are not productive! " +
                            "I do not have this command :-(");
                    // Skip to next input
                    userInput = scanner.nextLine();
                    continue;
                }
                // Test for Validity (Naive)
                Task newTask = tasks.get(tasks.size() - 1);
                if (newTask.isInvalid()) {
                    System.out.println("You want to do nothing?");
                    tasks.remove(tasks.size() - 1);
                } else {
                    // Update Completed
                    System.out.println("OK busy girl! added: " + newTask);
                    System.out.println("You have " + tasks.size() + " tasks in the list.");
                }
                // Update Completed
                System.out.println("OK busy girl! added:");
                System.out.println(" " + tasks.get(tasks.size() - 1));
                System.out.println("You have " + tasks.size() + " task(s) in the list.");
            }
            // Save the list
            listSaver.SaveList(tasks);

            // Read next input
            userInput = scanner.nextLine();
        }
        // Exit
        System.out.println("Good bye, slay the day!");
    }
}
