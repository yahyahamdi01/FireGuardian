package uparis.diamanthamdi.backend.model.cell.type;

import uparis.diamanthamdi.backend.model.cell.Celltype;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CellTest {
    private static Cell cell;

    @BeforeAll
    static void setup() {
        cell = new Cell(0, 0) {
            @Override
            public Celltype getCellType() {
                return null;
            }
        };
    }

    @Test
    void positionTest() {
        assertEquals(0, cell.getX(), "The x position of the cell should be 0, but it is " + cell.getX() + ".");
        assertEquals(0, cell.getY(), "The y position of the cell should be 0, but it is " + cell.getY() + ".");
    }

    @Test
    void isAdjacentToSameCellTest() {
        assertTrue(cell.isAdjacentTo(cell), "The cell should be adjacent to itself.");
    }

    @Test
    void isAdjacentToAdjacentCellTest() {
        Cell other = new Cell(1, 1) {
            @Override
            public Celltype getCellType() {
                return null;
            }
        };
        assertTrue(cell.isAdjacentTo(other), "The cell should be adjacent to the other cell.");
    }

    @Test
    void isAdjacentToNotAdjacentNotGoodXCellTest() {
        Cell other = new Cell(2, 1) {
            @Override
            public Celltype getCellType() {
                return null;
            }
        };
        assertFalse(cell.isAdjacentTo(other), "The cell should not be adjacent to the other cell.");
    }

    @Test
    void isAdjacentToNotAdjacentNotGoodYCellTest() {
        Cell other = new Cell(1, 2) {
            @Override
            public Celltype getCellType() {
                return null;
            }
        };
        assertFalse(cell.isAdjacentTo(other), "The cell should not be adjacent to the other cell.");
    }

    @Test
    void isAdjacentToDiagongalCellTest() {
        Cell other = new Cell(1, 1) {
            @Override
            public Celltype getCellType() {
                return null;
            }
        };
        assertTrue(cell.isAdjacentTo(other), "The cell should be adjacent to the other cell.");
    }

    @Test
    void setFireTest() {
        cell.setFire(1);
        assertTrue(cell.isOnFire(), "The cell should be on fire.");
    }

    @Test
    void equalsSameObjectTest() {
        assertEquals(cell, cell,
                "The cell should be equal to itself.");
    }

    @Test
    void notEqualsNotCellTest() {
        assertNotEquals(cell, new Object(),
                "The cell should not be equal to an object.");
    }

    @Test
    void notEqualsTest() {
        Cell other = new Cell(1, 1) {
            @Override
            public Celltype getCellType() {
                return null;
            }
        };
        assertNotEquals(cell, other,
                "The cell should not be equal to the other cell.");
    }
}