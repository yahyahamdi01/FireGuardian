package uparis.diamanthamdi.backend.model.cell.factory;

import uparis.diamanthamdi.backend.model.cell.Celltype;
import uparis.diamanthamdi.backend.model.cell.type.BareVegetationCell;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.type.CroplandCell;
import uparis.diamanthamdi.backend.model.cell.type.GrasslandCell;
import uparis.diamanthamdi.backend.model.cell.type.MangroveCell;
import uparis.diamanthamdi.backend.model.cell.type.MossCell;
import uparis.diamanthamdi.backend.model.cell.type.ShrublandCell;
import uparis.diamanthamdi.backend.model.cell.type.SnowCell;
import uparis.diamanthamdi.backend.model.cell.type.TreeCell;
import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;
import uparis.diamanthamdi.backend.model.cell.type.WaterCell;
import uparis.diamanthamdi.backend.model.cell.type.WetlandCell;

/**
 * Factory for creating cells.
 * 
 * The factory creates cells of different types based on the given type.
 */
public class CellFactory {
    private CellFactory() {}

    /**
     * Creates a new cell with the given type.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @param type The type of the cell.
     * @return The created cell.
     */
    public static Cell createCell(int x, int y, Celltype type) {
        Cell cell;

        switch (type) {
            case BAREVEGETATION:
                cell = new BareVegetationCell(x, y);
                break;
            case CROPLAND:
                cell = new CroplandCell(x, y);
                break;
            case GRASSLAND:
                cell = new GrasslandCell(x, y);
                break;
            case MANGROVE:
                cell = new MangroveCell(x, y);
                break;
            case MOSS:
                cell = new MossCell(x, y);
                break;
            case SHRUBLAND:
                cell = new ShrublandCell(x, y);
                break;
            case SNOW:
                cell = new SnowCell(x, y);
                break;
            case TREE:
                cell = new TreeCell(x, y);
                break;
            case URBAN:
                cell = new UrbanCell(x, y);
                break;
            case WATER:
                cell = new WaterCell(x, y);
                break;
            case WETLAND:
                cell = new WetlandCell(x, y);
                break;
            default:
                throw new IllegalArgumentException("Unknown cell type: " + type);
        }

        return cell;
    }
}
