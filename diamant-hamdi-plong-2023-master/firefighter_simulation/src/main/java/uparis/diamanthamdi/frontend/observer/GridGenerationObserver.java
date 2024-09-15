package uparis.diamanthamdi.frontend.observer;

import java.awt.image.BufferedImage;

import uparis.diamanthamdi.backend.model.grid.Grid;

/**
 * Observer for the grid generation.
 * 
 * This observer is used to notify the visualizer to display the generated grid.
 */
public interface GridGenerationObserver {
    void onGeneration(Grid grid, BufferedImage image);
}
