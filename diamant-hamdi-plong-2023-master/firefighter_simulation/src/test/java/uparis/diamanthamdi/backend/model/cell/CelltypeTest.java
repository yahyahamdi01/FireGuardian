package uparis.diamanthamdi.backend.model.cell;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CelltypeTest {
    @Test
    void fromColorExistTest() {
        Color[] colors = { new Color(0, 100, 0), new Color(255, 187, 34), new Color(180, 180, 180),
                new Color(255, 255, 76), new Color(240, 150, 255), new Color(250, 0, 0), new Color(240, 240, 240),
                new Color(0, 100, 200), new Color(0, 150, 160), new Color(0, 207, 117), new Color(250, 230, 160) };

        for (int i = 0; i < colors.length; i++) {
            Color color = colors[i];
            Celltype expectedCelltype = Celltype.values()[i];
            Celltype actualCelltype = Celltype.fromColor(color);

            assertEquals(expectedCelltype, actualCelltype,
                    "The celltype for color " + color + " should be " + expectedCelltype + ", but it is " + actualCelltype + ".");
        }
    }

    @Test
    void fromColorNotExistTest() {
        Color color = new Color(0, 0, 0);
        Celltype actualCelltype = Celltype.fromColor(color);

        assertNull(actualCelltype, 
                    "The celltype for color " + color + " should be null, but it is " + actualCelltype + ".");
    }

    @Test
    void fromStringExistTest() {
        String[] types = { "TREE", "SHRUBLAND", "BAREVEGETATION", 
                           "GRASSLAND", "CROPLAND", "URBAN", "SNOW", 
                           "WATER", "WETLAND", "MANGROVE", "MOSS" };

        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            Celltype expectedCelltype = Celltype.values()[i];
            Celltype actualCelltype = Celltype.fromString(type);

            assertEquals(expectedCelltype, actualCelltype,
                    "The celltype for type " + type + " should be " + expectedCelltype + ", but it is " + actualCelltype + ".");
        }
    }

    @Test
    void fromStringNotExistTest() {
        String type = "UNKNOWN";
        Celltype actualCelltype = Celltype.fromString(type);

        assertNull(actualCelltype, 
                    "The celltype for type " + type + " should be null, but it is " + actualCelltype + ".");
    }
}