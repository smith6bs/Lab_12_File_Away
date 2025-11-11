import javax.swing.*;
import java.io.*;

public class FileInspector {

    public static void main(String[] args) {
        // Create a JFileChooser to let the user pick a text file
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
                    lineCount++;  // Increment line counter
                    System.out.println(line);  // Echo the line to the console

                    // Count words in this line
                    // Split the line by whitespace (one or more spaces, tabs, etc.)
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
                // Handle any input/output errors gracefully
                System.out.println("Error reading the file: " + e.getMessage());
            }

        } else {
            // If user cancels or closes the dialog
            System.out.println("No file was selected. Program exiting...");
        }
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
