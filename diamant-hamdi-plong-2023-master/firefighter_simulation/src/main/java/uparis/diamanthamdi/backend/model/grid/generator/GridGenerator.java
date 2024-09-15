package uparis.diamanthamdi.backend.model.grid.generator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.factory.CellFactory;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;
import uparis.diamanthamdi.backend.model.firestation.Firestation;
import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.backend.utils.CellAdapter;
import uparis.diamanthamdi.backend.utils.CellUtils;
import uparis.diamanthamdi.backend.utils.Constants;
import uparis.diamanthamdi.backend.utils.MathUtils;

/**
 * Generates a grid from an image.
 */
public class GridGenerator {
    private static final Logger logger = Logger.getLogger(GridGenerator.class.getName());
    private static final SecureRandom random = new SecureRandom();

    /** The image used to generate the grid. */
    private BufferedImage image;

    /** The width of the grid. */
    private final int width;
    /** The height of the grid. */
    private final int height;
    /** The scale of the grid. */
    private final int scale;

    /**
     * Creates a new grid generator with the given image and scale.
     *
     * @param image The image used to generate the grid.
     * @param userScale The scale of the grid.
     */
    public GridGenerator(BufferedImage image, int userScale) {
        this.image = image;

        scale = processScale(image.getWidth(), image.getHeight(), userScale);
        width = image.getWidth() / scale;
        height = image.getHeight() / scale;
    }

    /**
     * Generates the grid from the image.
     *
     * @return The generated grid.
     */
    public Grid generate() {
        // we generate the grid from the image
        Cell[][] cells = fillCells(image);

        generateFirestation(cells);

        return new Grid(width, height, scale, cells);
    }

    /**
     * Generates the grid from the given path of a json file.
     *
     * @param jsonFileName The name of the json file.
     * @return The generated grid.
     */
    public static Grid generate(String jsonFileName) {
        Grid grid;

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Cell.class, new CellAdapter())
            .create();
        try (FileReader reader = new FileReader(jsonFileName)) {
            grid = gson.fromJson(reader, Grid.class);
        } catch (Exception e) {
            System.err.println("Error while reading the json file: " + e.getMessage());
            grid = null;
        }

        return grid;
    }

    /**
     * Fills the cells of the grid from the image.
     * 
     * Find the most frequent color of a square of scale x scale pixels in the image and creates a cell with this color.
     * 
     * @param image The image used to generate the grid.
     * @return The generated cells.
     */
    private Cell[][] fillCells(BufferedImage image) {
        Cell[][] cells = new Cell[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = findMostFrequentColor(image, x * scale, y * scale, scale);
                cells[x][y] = CellFactory.createCell(x, y, Celltype.fromColor(color));
            }
        }

        return cells;
    }

    /**
     * Generates a firestation on a random urban cell.
     * 
     * @param cells The cells of the grid.
     */
    private void generateFirestation(Cell[][] cells) {
        // we filter all the cells that are urban
        List<Cell> urbanCells = CellUtils.filterGridCells(cells, Celltype.URBAN);

        // we randomly select a cell to place the firestation
        UrbanCell randomUrbanCell = getRandomUrbanCell(urbanCells);

        // we place the firestation on the selected cell
        int x = randomUrbanCell.getX();
        int y = randomUrbanCell.getY();
        randomUrbanCell.setFirestation(new Firestation(x, y));
    }

    /**
     * Processes the scale of the grid.
     * 
     * The scale is the size of the square of pixels that will be used to generate a cell.
     * 
     * @param width The width of the image.
     * @param height The height of the image.
     * @param userScale The scale given by the user.
     * @return The processed scale.
     */
    private int processScale(int width, int height, int userScale) {
        int maxScale = MathUtils.maxScale(width, height, Constants.MIN_SIZE);

        // set the scale to the user scale if it is greater than the default scale (1)
        int minScale = Math.max(userScale, Constants.DEFAULT_SCALE);
            
        return Math.min(minScale, maxScale);
    }

    /**
     * Finds the most frequent color of a square of scale x scale pixels in the image.
     * 
     * @param image The image.
     * @param startX The x coordinate of the top left corner of the square.
     * @param startY The y coordinate of the top left corner of the square.
     * @param scale The scale of the square.
     * @return The most frequent color.
     */
    private static Color findMostFrequentColor(BufferedImage image, int startX, int startY, int scale) {
        Map<Integer, Integer> colorCount = new HashMap<>();
        for (int x = startX; x < startX + scale; x++) {
            for (int y = startY; y < startY + scale; y++) {
                int rgb = image.getRGB(x, y);
                colorCount.put(rgb, colorCount.getOrDefault(rgb, 0) + 1);
            }
        }

        return colorCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> new Color(entry.getKey()))
            .orElse(Color.BLACK);
    }

    /**
     * Gets a random urban cell from a list of cells.
     * 
     * @param urbanCells The list of urban cells.
     * @return The random urban cell.
     */
    private UrbanCell getRandomUrbanCell(List<Cell> urbanCells) {
        int randomIndex = random.nextInt(urbanCells.size());
        return (UrbanCell) urbanCells.get(randomIndex);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScale() {
        return scale;
    }
}
