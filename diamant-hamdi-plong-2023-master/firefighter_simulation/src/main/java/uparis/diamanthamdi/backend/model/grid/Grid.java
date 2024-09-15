package uparis.diamanthamdi.backend.model.grid;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;
import uparis.diamanthamdi.backend.utils.CellAdapter;
import uparis.diamanthamdi.backend.utils.CellUtils;
import uparis.diamanthamdi.backend.utils.FileUtils;

/**
 * Represents a grid.
 */
public class Grid {
    private static final Logger logger = Logger.getLogger(Grid.class.getName());

    /** The width of the grid. */
    private final int width;
    /** The height of the grid. */
    private final int height;
    /** The scale of the grid. */
    private final int scale;
    /** The cells of the grid. */
    private final Cell[][] cells;

    /**
     * Creates a new grid with the given width, height, scale and cells.
     *
     * @param width The width of the grid.
     * @param height The height of the grid.
     * @param scale The scale of the grid.
     * @param cells The cells of the grid.
     */
    public Grid(final int width, final int height, final int scale, final Cell[][] cells) {
        this.width = width;
        this.height = height;
        this.scale = scale;

        this.cells = cells;
    }

    /**
     * Returns the firestations of the grid.
     *
     * @return The firestations of the grid.
     */
    public List<UrbanCell> getFirestations() {
        if (cells == null) {
            return Collections.emptyList();
        }

        // filter the urban cells
        List<Cell> urbanCells = CellUtils.filterGridCells(cells, Celltype.URBAN);

        return urbanCells.stream()
                .map(UrbanCell.class::cast)
                .filter(UrbanCell::hasFirestation)
                .collect(Collectors.toList());
    }

    /**
     * Returns the nearest firestation from the given cell.
     *
     * @param cell The cell from which to find the nearest firestation.
     * @return The nearest firestation from the given cell.
     */
    public UrbanCell getNearestFirestation(final Cell cell) {
        List<UrbanCell> firestations = getFirestations();
        UrbanCell nearestFirestation = null;
        double minDistance = Double.MAX_VALUE;

        for (UrbanCell firestation : firestations) {
            double distance = cell.distanceTo(firestation);
            if (distance < minDistance) {
                minDistance = distance;
                nearestFirestation = firestation;
            }
        }

        return nearestFirestation;
    }

    /**
     * Returns the neighbours of the given cell.
     *
     * @param cell The cell from which to find the neighbours.
     * @return The neighbours of the given cell.
     */
    public List<Cell> getNeighbours(final Cell cell) {
        return CellUtils.getNeighbours(cells, cell);
    }

    /**
     * Returns the cells on fire.
     *
     * @return The cells on fire.
     */
    public List<Cell> getCellOnFire() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(Cell::isOnFire)
                .collect(Collectors.toList());
    }

    /**
     * Returns the cells on fire with the given intensity.
     *
     * @param intensity The intensity of the fire.
     * @return The cells on fire with the given intensity.
     */
    public void serialize() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Cell.class, new CellAdapter())
            .create();
        String json = gson.toJson(this);

        String destinationPath = "src/main/resources/uparis/diamanthamdi/maps/json/";
        String uniqueFilename = FileUtils.generateUniqueFilename(scale, "grid", "json");
        File jsonFile = new File(destinationPath, uniqueFilename);

        try (FileWriter file = new FileWriter(jsonFile)) {
            file.write(json);
            logger.info("--- JSON written ---");
        } catch (Exception e) {
            logger.severe("Error writing JSON: " + e.getMessage());
        }
    }

    public void setFire(final int x, final int y, final int intensity) {
        this.cells[x][y].setFire(intensity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                sb.append(this.cells[x][y].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Grid)) {
            return false;
        }

        Grid grid = (Grid) obj;

        return this.width == grid.width 
            && this.height == grid.height 
            && Arrays.deepEquals(this.cells, grid.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.cells);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getScale() {
        return this.scale;
    }

    public Cell getCell(final int x, final int y) {
        return this.cells[x][y];
    }

    public Cell[][] getCells() {
        return this.cells;
    }
}
