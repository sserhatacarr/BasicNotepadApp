import java.io.*;
import java.util.Scanner;

public class NotepadApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Enter Text");
            System.out.println("2. Show Last Saved Text");
            System.out.println("3. Exit");
            System.out.print("Make your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Read the empty line

            if (choice == 1) {
                System.out.print("Enter text: ");
                String text = scanner.nextLine();
                save(text);
            } else if (choice == 2) {
                String lastText = load();
                if (lastText != null) {
                    System.out.println("Last saved text:");
                    System.out.println(lastText);
                } else {
                    System.out.println("No text has been saved yet.");
                }
            } else if (choice == 3) {
                System.out.println("Closing the Notepad application.");
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void save(String text) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("notes.txt"))) {
            writer.println(text);
            System.out.println("Text has been successfully saved.");
        } catch (IOException e) {
            System.err.println("Error: Text couldn't be saved.");
        }
    }

    public static String load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("notes.txt"))) {
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            return text.toString();
        } catch (IOException e) {
            return null; // File reading error
        }
    }
}
