package uparis.diamanthamdi.backend.utils;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Contains utility methods for mathematical operations.
 */
public class MathUtils {
    private MathUtils() {}

    /**
     * Returns the maximum scale that can be applied to the specified width and height.
     *
     * @param width the width to calculate the maximum scale for
     * @param height the height to calculate the maximum scale for
     * @param size the size to calculate the maximum scale for
     * @return the maximum scale that can be applied to the specified width and height
     */
    public static int maxScale(int width, int height, int size) {
        return Math.min(width, height) / size;
    }

    /**
     * Returns the distance between two points in one dimension.
     *
     * @param v1 the first point
     * @param v2 the second point
     * @return the distance between the two points
     */
    public static int distance1D(int v1, int v2) {
        return Math.abs(v1 - v2);
    }

    /**
     * Returns the distance between two points in two dimensions.
     *
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param x2 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     * @return the distance between the two points
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Returns the scaling factor for the specified width and height to fit within the specified maximum width and height.
     *
     * @param width the width to scale
     * @param height the height to scale
     * @param maxWidth the maximum width to fit within
     * @param maxHeight the maximum height to fit within
     * @return the scaling factor for the specified width and height to fit within the specified maximum width and height
     */
    public static double scaling(int width, int height, int maxWidth, int maxHeight) {
        double scaleX = (double) maxWidth / width;
        double scaleY = (double) maxHeight / height;
        return Math.min(scaleX, scaleY);
    }

    /**
     * Scales the specified width and height by the specified scale.
     *
     * @param width the width to scale
     * @param height the height to scale
     * @param scale the scale to apply
     * @return the scaled width and height
     */
    public static Dimension scale(int width, int height, double scale) {
        return new Dimension((int) (width * scale), (int) (height * scale));
    }

    /**
     * Returns the center position of the specified width and height within the specified maximum width and height.
     *
     * @param width the width to center
     * @param height the height to center
     * @param maxWidth the maximum width to center within
     * @param maxHeight the maximum height to center within
     * @return the center position of the specified width and height within the specified maximum width and height
     */
    public static Point centerPosition(int width, int height, int maxWidth, int maxHeight, double scale) {
        Dimension size = scale(width, height, scale);
        int x = (maxWidth - size.width) / 2;
        int y = (maxHeight - size.height) / 2;
        return new Point(x, y);
    }

    /**
     * Convert a x or y coordinate to the corresponding value on the screen.
     * @param value x or y coordinate
     * @param scale scale of the grid
     * @param offset offset of the grid
     * @return the corresponding value on the screen
     */
    public static double convertValueToScreenValue(int value, double scale, double offset) {
        return (offset * scale) + value;
    }
}
