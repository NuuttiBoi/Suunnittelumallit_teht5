import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private static Logger instance;
    private PrintWriter writer;
    private String logFileName = "default_log.txt";

    private Logger() {
        initializeWriter();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        } return instance;
    }

    private void initializeWriter() {
        try {
            writer = new PrintWriter(new FileWriter(logFileName, true));
        } catch (IOException e) {
            System.out.println("Error initializing the log file: " + e.getMessage());
        }
    }

    // Method to change the log file
    public void setFileName(String fileName) {
        if (writer != null) {
            writer.close();
        }
        this.logFileName = fileName;
        initializeWriter();
    }


    public void write(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        } else {
            System.out.println("Logger is not initialized.");
        }
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
