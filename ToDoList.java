package ToDoList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    // Task class to store details of each task
    static class Task {
        String description;
        String priority;
        boolean isCompleted;

        Task(String description, String priority) {
            this.description = description;
            this.priority = priority;
            this.isCompleted = false;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s (Priority: %s)", isCompleted ? "Completed" : "Pending", description, priority);
        }
    }

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadTasks();

        int choice;
        System.out.println("Welcome to the Enhanced To-Do List App!");

        do {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. View Tasks");
            System.out.println("2. Add a Task");
            System.out.println("3. Remove a Task");
            System.out.println("4. Update a Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Search Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    addTask(scanner);
                    break;
                case 3:
                    removeTask(scanner);
                    break;
                case 4:
                    updateTask(scanner);
                    break;
                case 5:
                    markTaskAsCompleted(scanner);
                    break;
                case 6:
                    searchTasks(scanner);
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
        
        scanner.close();
    }

    // Method to view tasks with status and priority
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nYour to-do list is empty.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Method to add a task with priority
    private static void addTask(Scanner scanner) {
        System.out.print("\nEnter the task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter the priority (High/Medium/Low): ");
        String priority = scanner.nextLine();
        tasks.add(new Task(description, priority));
        saveTasks();
        System.out.println("Task added successfully!");
    }

    // Method to remove a task
    private static void removeTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nYour to-do list is empty. Nothing to remove.");
            return;
        }

        viewTasks();
        System.out.print("Enter the task number to remove: ");
        int taskNumber = scanner.nextInt();
        
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            saveTasks();
            System.out.println("Removed task: " + removedTask.description);
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Method to update a task
    private static void updateTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nYour to-do list is empty. Nothing to update.");
            return;
        }

        viewTasks();
        System.out.print("Enter the task number to update: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            System.out.print("Enter the new task description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter the new priority (High/Medium/Low): ");
            String newPriority = scanner.nextLine();
            tasks.get(taskNumber - 1).description = newDescription;
            tasks.get(taskNumber - 1).priority = newPriority;
            saveTasks();
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Method to mark a task as completed
    private static void markTaskAsCompleted(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nYour to-do list is empty. Nothing to mark as completed.");
            return;
        }

        viewTasks();
        System.out.print("Enter the task number to mark as completed: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).isCompleted = true;
            saveTasks();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Method to search for tasks by keyword
    private static void searchTasks(Scanner scanner) {
        System.out.print("\nEnter the keyword to search for: ");
        String keyword = scanner.nextLine();
        System.out.println("\nSearch Results:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Save tasks to file
    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.description + "|" + task.priority + "|" + task.isCompleted);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file
    private static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Task task = new Task(parts[0], parts[1]);
                task.isCompleted = Boolean.parseBoolean(parts[2]);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // No saved tasks
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
