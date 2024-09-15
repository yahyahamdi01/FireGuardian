package uparis.diamanthamdi.backend.model.cell;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Colors for the ESA World Cover.
 */
public final class ESAWorldCoverColor {
    private static final SecureRandom random = new SecureRandom();

    private ESAWorldCoverColor() {}

    public static final Color TREE = new Color(0, 100, 0);
    public static final Color SHRUB = new Color(255, 187, 34);
    public static final Color BAREVEGETATION = new Color(180, 180, 180);
    public static final Color GRASSLAND = new Color(255, 255, 76);
    public static final Color CROPLAND = new Color(240, 150, 255);
    public static final Color URBAN = new Color(250, 0, 0);
    public static final Color SNOW = new Color(240, 240, 240);
    public static final Color WATER = new Color(0, 100, 200);
    public static final Color WETLAND = new Color(0, 150, 160);
    public static final Color MANGROVE = new Color(0, 207, 117);
    public static final Color MOSS = new Color(250, 230, 160);

    private static List<Color> colors;

    static {
        colors = new ArrayList<>();
        colors.add(TREE);
        colors.add(SHRUB);
        colors.add(BAREVEGETATION);
        colors.add(GRASSLAND);
        colors.add(CROPLAND);
        colors.add(URBAN);
        colors.add(SNOW);
        colors.add(WATER);
        colors.add(WETLAND);
        colors.add(MANGROVE);
        colors.add(MOSS);
    }

    public static Color getColor(Celltype celltype) {
        Color color = null;
        switch (celltype) {
            case TREE:
                color = TREE;
                break;
            case SHRUBLAND:
                color = SHRUB;
                break;
            case BAREVEGETATION:
                color = BAREVEGETATION;
                break;
            case GRASSLAND:
                color = GRASSLAND;
                break;
            case CROPLAND:
                color = CROPLAND;
                break;
            case URBAN:
                color = URBAN;
                break;
            case SNOW:
                color = SNOW;
                break;
            case WATER:
                color = WATER;
                break;
            case WETLAND:
                color = WETLAND;
                break;
            case MANGROVE:
                color = MANGROVE;
                break;
            case MOSS:
                color = MOSS;
                break;
        }
        return color;
    }

    public static List<Color> getColors() {
        return Collections.unmodifiableList(colors);
    }

    public static Color getRandomColor() {
        return colors.get(random.nextInt(colors.size()));
    }
}
