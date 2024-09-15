package uparis.diamanthamdi.backend.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class ESAWorldCoverColorTest {
    @Test
    void isListGoodSizeTest() {
        List<Color> colors = ESAWorldCoverColor.getColors();
        
        int expectedSize = 11;
        int actualSize = colors.size();

        assertEquals(expectedSize, actualSize,
                "The size of the list of colors should be 11, but it is " + actualSize + ".");
    }

    @Test
    void isListGoodContentTest() {
        List<Color> colors = ESAWorldCoverColor.getColors();

        Color[] expectedColors = { new Color(0, 100, 0), new Color(255, 187, 34), new Color(180, 180, 180),
                new Color(255, 255, 76), new Color(240, 150, 255), new Color(250, 0, 0), new Color(240, 240, 240),
                new Color(0, 100, 200), new Color(0, 150, 160), new Color(0, 207, 117), new Color(250, 230, 160) };

        for (int i = 0; i < expectedColors.length; i++) {
            Color expectedColor = expectedColors[i];
            Color actualColor = colors.get(i);

            assertEquals(expectedColor, actualColor,
                    "The color at index " + i + " should be " + expectedColor + ", but it is " + actualColor + ".");
        }
    }

    @Test
    void getColorTest() {
        Celltype[] celltypes = Celltype.values();

        for (int i = 0; i < celltypes.length; i++) {
            Celltype celltype = celltypes[i];
            Color expectedColor = ESAWorldCoverColor.getColor(celltype);
            Color actualColor = ESAWorldCoverColor.getColors().get(i);

            assertEquals(expectedColor, actualColor,
                    "The color for celltype " + celltype + " should be " + expectedColor + ", but it is " + actualColor + ".");
        }
    }
}
