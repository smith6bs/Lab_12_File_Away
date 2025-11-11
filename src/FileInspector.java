import javax.swing.*;
import java.io.*;

public class FileInspector {

    public static void main(String[] args) {
        // Create a JFileChooser to let the user pick a text file and open it in src
        JFileChooser fileChooser = new JFileChooser("src");

        // Show the Open dialog and capture user response
        int result = fileChooser.showOpenDialog(null);

        // If the user selected a file (and did not cancel)
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); // Get the chosen file

            // Initialize counters for lines, words, and characters
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            System.out.println("\n===== FILE CONTENTS =====\n");

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;

                // Read the file line by line
                while ((line = reader.readLine()) != null) {
                    lineCount++;  // have linecount starting at zero above this increases by 1 every time it counts
                    System.out.println(line);  // Echo the line to the console

                    // This has space be the delimeter it splits by then counts words in the line
                    String[] words = line.trim().split("\\s+");
                    if (!line.trim().isEmpty()) {
                        wordCount += words.length;
                    }

                    // Count characters (including spaces)
                    charCount += line.length();
                }

                // Display the summary report
                System.out.println("\n===== SUMMARY REPORT =====");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Number of Lines: " + lineCount);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + charCount);

            } catch (IOException e) {
                // Handle any input/output errors
                System.out.println("Error reading the file: " + e.getMessage());
            }

        } else {
            // If user cancels or closes the dialog
            System.out.println("No file was selected. Program exiting...");
        }
    }
}

