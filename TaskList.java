import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private static int idCounter = 1; // Static counter for sequential IDs
    private int id;
    private String name;
    private String description;
    private boolean completed;

    public Task(String name, String description) {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.completed = false; // By default, the task is not completed
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Description: " + description + ", Completed: " + (completed ? "Yes" : "No");
    }
}

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void markTaskAsCompleted(int id) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markAsCompleted();
                System.out.println("Task marked as completed.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Invalid task ID.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            System.out.println("1. Add Task\n2. Display Tasks\n3. Mark Task as Completed\n4. Exit");
            System.out.print("Choose an option: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter task name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        taskList.addTask(new Task(name, description));
                        break;
                    case 2:
                        System.out.println("Tasks:");
                        taskList.displayTasks();
                        break;
                    case 3:
                        System.out.print("Enter task ID to mark as completed: ");
                        if (scanner.hasNextInt()) {
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            taskList.markTaskAsCompleted(id);
                        } else {
                            System.out.println("Invalid input. Please enter a valid task ID.");
                            scanner.next(); // Clear invalid input
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
