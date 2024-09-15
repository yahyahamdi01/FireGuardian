package uparis.diamanthamdi.backend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains utility method for file operations.
 */
public class FileUtils {
    private FileUtils() {}

    /**
     * Generates a unique filename based on the prefix, scale, current timestamp and extension.
     *
     * @param scale the scale of the file
     * @param prefix the prefix of the filename
     * @param extension the extension of the filename
     * @return a unique filename
     */
    public static String generateUniqueFilename(int scale, String prefix, String extension) {
        // Get current timestamp
        LocalDateTime currentTime = LocalDateTime.now();

        // Format timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = currentTime.format(formatter);

        // Combine timestamp, UUID, prefix, and extension to create unique filename
        return prefix + "_" + timestamp + "_" + Integer.toString(scale) + "." + extension;
    }
}
