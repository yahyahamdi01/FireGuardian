package uparis.diamanthamdi.backend.io;

/**
 * Utility class for commands format.
 */
public final class CommandsFormat {
    /** The option for displaying information about the program. */
    public static final String INFO = "--info";
    /** The option for running the program with a graphical user interface. */
    public static final String GUI = "--gui";
    /** The option for displaying help about the program. */
    public static final String HELP = "--help";
    /** The option for running the program in debug mode. */
    public static final String DEBUG = "--debug";

    /** The index of the option in the command. */
    public static final int OPTION_INDEX = 0;
    /** The length of the command. */
    public static final int LENGTH = 1;

    private static final String[] VALID_OPTIONS = {INFO, GUI, HELP, DEBUG};

    private CommandsFormat() {}

    /**
     * Checks if the given option is valid. A valid option is one of the options defined in this class.
     *
     * @param option The option to check.
     * @return True if the option is valid, false otherwise.
     */
    public static boolean isValidOption(String option) {
        for (String validOption : VALID_OPTIONS) {
            if (validOption.equals(option)) {
                return true;
            }
        }

        return false;
    }
}
