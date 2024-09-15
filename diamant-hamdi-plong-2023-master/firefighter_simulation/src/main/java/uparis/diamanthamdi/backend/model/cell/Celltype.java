package uparis.diamanthamdi.backend.model.cell;

import java.awt.Color;

public enum Celltype {
    TREE, SHRUBLAND, BAREVEGETATION, 
    GRASSLAND, CROPLAND, URBAN, SNOW, 
    WATER, WETLAND, MANGROVE, MOSS;

    public static Celltype fromColor(Color type) {
        if (type.equals(ESAWorldCoverColor.BAREVEGETATION)) {
            return BAREVEGETATION;
        } else if (type.equals(ESAWorldCoverColor.CROPLAND)) {
            return CROPLAND;
        } else if (type.equals(ESAWorldCoverColor.WETLAND)) {
            return WETLAND;
        } else if (type.equals(ESAWorldCoverColor.GRASSLAND)) {
            return GRASSLAND;
        } else if (type.equals(ESAWorldCoverColor.SHRUB)) {
            return SHRUBLAND;
        } else if (type.equals(ESAWorldCoverColor.TREE)) {
            return TREE;
        } else if (type.equals(ESAWorldCoverColor.URBAN)) {
            return URBAN;
        } else if (type.equals(ESAWorldCoverColor.WATER)) {
            return WATER;
        } else if (type.equals(ESAWorldCoverColor.SNOW)) {
            return SNOW;
        } else if (type.equals(ESAWorldCoverColor.MOSS)) {
            return MOSS;
        } else if (type.equals(ESAWorldCoverColor.MANGROVE)) {
            return MANGROVE;
        } else {
            return null;
        }
    }

    public static Celltype fromString(String type) {
        Celltype celltype = null;

        if (type.equals("BAREVEGETATION")) {
            celltype = BAREVEGETATION;
        } else if (type.equals("CROPLAND")) {
            celltype = CROPLAND;
        } else if (type.equals("WETLAND")) {
            celltype = WETLAND;
        } else if (type.equals("GRASSLAND")) {
            celltype = GRASSLAND;
        } else if (type.equals("SHRUBLAND")) {
            celltype = SHRUBLAND;
        } else if (type.equals("TREE")) {
            celltype = TREE;
        } else if (type.equals("URBAN")) {
            celltype = URBAN;
        } else if (type.equals("WATER")) {
            celltype = WATER;
        } else if (type.equals("SNOW")) {
            celltype = SNOW;
        } else if (type.equals("MOSS")) {
            celltype = MOSS;
        } else if (type.equals("MANGROVE")) {
            celltype = MANGROVE;
        }

        return celltype;
    }
}
