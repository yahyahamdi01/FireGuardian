package uparis.diamanthamdi;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import uparis.diamanthamdi.backend.io.CommandsFormat;
import uparis.diamanthamdi.frontend.window.Window;

/**
 * Main class of the application.
 * 
 * <p> This class is used to launch the application. </p>
 * <p> The application can be launched in three modes: </p>
 * <ul>
 * <li> Info mode: Print the application information. </li>
 * <li> Help mode: Print the help message. </li>
 * <li> GUI mode: Launch the application in GUI mode. </li>
 * </ul>
 * 
 * @see Window
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());
    private static final String UNSPECIFIED = "Unspecified";

    /**
     * Main method of the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (!isCorrectFormat(args)) {
            printUsage();
            return;
        }

        String option = args[CommandsFormat.OPTION_INDEX];
        processOption(option);
    }

    /**
     * Process the option. If the option is not valid, print the usage.
     * 
     * @param option the option to process
     */
    private static void processOption(String option) {
        switch (option) {
            case CommandsFormat.INFO:
                printInfo(System.out);
                break;
            case CommandsFormat.HELP:
                printUsage();
                break;
            case CommandsFormat.GUI:
                launchGui();
                break;
            default:
                printUsage();
                break;
        }
    }

    /**
     * Launch the application in GUI mode.
     */
    private static void launchGui() {
        Properties props = readApplicationProperties();
        String title = props.getProperty("app.name", UNSPECIFIED);

        SwingUtilities.invokeLater(() -> {
            Window window = new Window(title);
            window.start();
        });
    }

    /**
     * Print the application information.
     * 
     * @param out the output stream
     */
    private static void printInfo(PrintStream out) {
        Properties props = readApplicationProperties();

        out.println(String.format("Application: %s", props.getProperty("app.name", UNSPECIFIED)));
        out.println(String.format("Version: %s", props.getProperty("app.version", UNSPECIFIED)));
        out.println(String.format("By: %s", props.getProperty("app.author", UNSPECIFIED)));
    }

    /**
     * Read the application properties.
     * 
     * @return the application properties
     */
    private static Properties readApplicationProperties() {
        Properties props = new Properties();

        try(InputStream is = App.class.getResourceAsStream("application.properties")) {
            props.load(is);
        } catch(IOException e) {
            logger.warning("Failed to read application properties");
        }
        return props;
    }

    /**
     * Print the usage of the application.
     */
    private static void printUsage() {
        System.out.println("Usage: java -jar <app.jar> [options]");
        System.out.println("Options:");
        System.out.printf("%-10s - Print application information.%n", CommandsFormat.INFO);
        System.out.printf("%-10s - Print this help message.%n", CommandsFormat.HELP);
        System.out.printf("%-10s - Launch the application in GUI mode.%n", CommandsFormat.GUI);
        System.out.printf("%-10s - Launch the application in debug mode.%n", CommandsFormat.DEBUG);
    }

    /**
     * Check if the command line arguments are in the correct format.
     * 
     * The command line arguments are in the correct format if the length is equal to the expected length and the option is
     * valid.
     * 
     * @param args the command line arguments
     * @return true if the arguments are in the correct format, false otherwise
     */
    private static boolean isCorrectFormat(String[] args) {
        return args.length == CommandsFormat.LENGTH 
            && CommandsFormat.isValidOption(args[CommandsFormat.OPTION_INDEX]);
    }
}
