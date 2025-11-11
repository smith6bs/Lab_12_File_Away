import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver
{
    public static void main(String[] args)
    {
        // Scanner so user can input
        Scanner in = new Scanner(System.in);

        // ArrayList to temporarily store all the CSV records as strings
        ArrayList<String> records = new ArrayList<>();

        // Used to track how many records have been entered
        int recordCount = 0;

        // Flag to control whether the user wants to keep entering data
        boolean keepGoing = true;

        System.out.println("=== Data Saver (CSV Creator) ===");

        // MAIN DATA ENTRY LOOP
        do
        {
            System.out.println("\nEnter data for record #" + (recordCount + 1)); // asks for input and has newline char

            // Use SafeInput to get user input and validate non-empty responses
            String firstName = SafeInput.getNonZeroLenString(in, "First Name");
            String lastName  = SafeInput.getNonZeroLenString(in, "Last Name");

            // Auto-generate an ID number — a zero-padded string with 6 digits (e.g. 000001)
            recordCount++;
            String idNumber = String.format("%06d", recordCount);

            String email    = SafeInput.getNonZeroLenString(in, "Email");

            // Get a 4-digit year of birth within a reasonable range (1920–2025)
            int yearOfBirth = SafeInput.getRangedInt(in, "Year of Birth", 1920, 2025);

            // Format the collected data into a CSV record
            String record = String.format("%s, %s, %s, %s, %d",
                    firstName, lastName, idNumber, email, yearOfBirth);

            // Add the record to the ArrayList
            records.add(record);

            // Ask the user if they want to create another record (Y/N)
            keepGoing = SafeInput.getYNConfirm(in, "Do you want to enter another record?");
        }
        while (keepGoing);  // Continue until user answers "No"

        // END OF DATA ENTRY

        if (records.isEmpty())
        {
            // If the user didn’t enter anything, there’s nothing to save
            System.out.println("\nNo records entered. Nothing to save. Exiting program.");
            return;
        }

        // Ask the user for a file name (without extension)
        String fileName = SafeInput.getNonZeroLenString(in, "\nEnter the CSV file name (without extension)");

        // Ensure the file name ends with ".csv"
        if (!fileName.toLowerCase().endsWith(".csv"))
        {
            fileName = fileName + ".csv";
        }

        // Define the output file location inside the src directory
        // Example: src/PeopleData.csv
        File outFile = new File("src/" + fileName);

                // WRITE THE DATA TO THE FILE
        try (PrintWriter out = new PrintWriter(new FileWriter(outFile)))
        {
            // Loop through each record and write it to the CSV file
            for (String rec : records)
            {
                out.println(rec);
            }

            // Notify the user that the file was successfully saved
            System.out.println("\nSaved " + records.size() + " record(s) to: " + outFile.getPath());
        }
        catch (IOException e)
        {
            // If something goes wrong print an error message
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Close the scanner
        in.close();
    }
}
