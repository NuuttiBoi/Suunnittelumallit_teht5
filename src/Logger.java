import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private static Logger instance;
    private String simulationStarted;


    // Log file writer
    private PrintWriter writer;

    // Default log file name
    private String logFileName = "default_log.txt";


    // Private constructor to prevent instantiation
    private Logger() {
        initializeWriter();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        } return instance;
    }

    // Initialize the PrintWriter
    private void initializeWriter() {
        try {
            writer = new PrintWriter(new FileWriter(logFileName, true)); // true for appending to the file
        } catch (IOException e) {
            System.out.println("Error initializing the log file: " + e.getMessage());
        }
    }

    // Method to change the log file
    public void setFileName(String fileName) {
        if (writer != null) {
            writer.close(); // Close the current file
        }
        this.logFileName = fileName;
        initializeWriter(); // Reinitialize the writer with the new file
    }


    public void write(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();  // Ensures that data is written to the file immediately
        } else {
            System.out.println("Logger is not initialized.");
        }
    }


    // Close the writer manually when done (optional but recommended for long-running applications)
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
