package uparis.diamanthamdi.backend.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.ESAWorldCoverColor;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;

/**
 * Contains utility methods for image operations.
 */
public class ImageUtils {
    private ImageUtils() {}

    /**
     * Reads an image from the specified path.
     *
     * @param path the path of the image to read
     * @return the image read from the specified path
     */
    public static BufferedImage readImage(final String path) {
        try {
            File file = new File(path);

            return ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("Error while reading image: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates an image from the specified grid of colors.
     *
     * @param width the width of the image
     * @param height the height of the image
     * @param grid the grid of colors to create the image from
     * @return the image created from the specified grid of colors
     */
    public static BufferedImage createImage(final int width, final int height, final Color[][] grid) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, grid[x][y].getRGB());
            }
        }

        return image;
    }

    /**
     * Creates an image from the specified grid of cells.
     *
     * @param cells the grid of cells to create the image from
     * @return the image created from the specified grid of cells
     */
    public static BufferedImage createImage(final Cell[][] cells) {
        int width = cells.length;
        int height = cells[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color;

                if (cells[x][y].getCellType() == Celltype.URBAN 
                && ((UrbanCell) cells[x][y]).hasFirestation()) {
                    color = new Color(138, 67, 29);
                } else {
                    color = ESAWorldCoverColor.getColor(cells[x][y].getCellType());
                }

                image.setRGB(x, y, color.getRGB());
            }
        }

        return image;
    }

    /**
     * Writes an image to the specified path.
     *
     * @param image the image to write
     * @param path the path to write the image to
     */
    public static void writeImage(final BufferedImage image, final String path) {
        try {
            File file = new File(path);

            ImageIO.write(image, "png", file);
        } catch (Exception e) {
            System.out.println("Error while writing image: " + e.getMessage());
        }
    }
}
