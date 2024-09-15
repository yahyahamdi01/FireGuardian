package uparis.diamanthamdi.backend.model.cell.factory;

import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.Celltype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellFactoryTest {
    private static Cell cell;

    @Test
    void positionTest() {
        cell = CellFactory.createCell(0, 0, Celltype.BAREVEGETATION);
        assertEquals(0, cell.getX(), "The x position of the cell should be 0, but it is " + cell.getX() + ".");
        assertEquals(0, cell.getY(), "The y position of the cell should be 0, but it is " + cell.getY() + ".");
    }

    @Test
    void createBareVegetationCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.BAREVEGETATION);
        assertEquals(Celltype.BAREVEGETATION, cell.getCellType(),
                "The type of the cell should be BAREVEGETATION, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createCroplandCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.CROPLAND);
        assertEquals(Celltype.CROPLAND, cell.getCellType(),
                "The type of the cell should be CROPLAND, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createGrasslandCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.GRASSLAND);
        assertEquals(Celltype.GRASSLAND, cell.getCellType(),
                "The type of the cell should be GRASSLAND, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createMangroveCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.MANGROVE);
        assertEquals(Celltype.MANGROVE, cell.getCellType(),
                "The type of the cell should be MANGROVE, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createMossCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.MOSS);
        assertEquals(Celltype.MOSS, cell.getCellType(),
                "The type of the cell should be MOSS, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createShrublandCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.SHRUBLAND);
        assertEquals(Celltype.SHRUBLAND, cell.getCellType(),
                "The type of the cell should be SHRUBLAND, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createSnowCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.SNOW);
        assertEquals(Celltype.SNOW, cell.getCellType(),
                "The type of the cell should be SNOW, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createTreeCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.TREE);
        assertEquals(Celltype.TREE, cell.getCellType(),
                "The type of the cell should be TREE, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createUrbanCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.URBAN);
        assertEquals(Celltype.URBAN, cell.getCellType(),
                "The type of the cell should be URBAN, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createWaterCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.WATER);
        assertEquals(Celltype.WATER, cell.getCellType(),
                "The type of the cell should be WATER, but it is " + cell.getCellType() + ".");
    }

    @Test
    void createWetlandCellTest() {
        cell = CellFactory.createCell(0, 0, Celltype.WETLAND);
        assertEquals(Celltype.WETLAND, cell.getCellType(),
                "The type of the cell should be WETLAND, but it is " + cell.getCellType() + ".");
    }
}