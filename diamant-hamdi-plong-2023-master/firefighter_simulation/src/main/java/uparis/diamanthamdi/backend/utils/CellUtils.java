package uparis.diamanthamdi.backend.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.type.Cell;

/**
 * Utility class for working with Cell objects.
 */
public class CellUtils {
    private CellUtils() {}

    /**
     * Filters a 2D array of Cell objects by their type.
     *
     * @param cells the 2D array of Cell objects to filter
     * @param celltype the type of Cell objects to filter by
     * @return a list of Cell objects of the specified type
     */
    public static List<Cell> filterGridCells(Cell[][] cells, Celltype celltype) {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getCellType() == celltype)
                .collect(Collectors.toList());
    }

    /**
     * Gets the neighbours of a Cell object in a 2D array of Cell objects.
     *
     * @param cells the 2D array of Cell objects
     * @param cell the Cell object to get the neighbours of
     * @return a list of the neighbours of the Cell object
     */
    public static List<Cell> getNeighbours(Cell[][] cells, Cell cell) {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell::isAdjacentTo)
                .collect(Collectors.toList());
    }
}
