package uparis.diamanthamdi.frontend.observer;

import java.util.List;

import uparis.diamanthamdi.backend.model.cell.type.Cell;

/**
 * Observer for the result algorithm.
 * 
 * This observer is used to notify the visualizer to display the result of the path found by the algorithm.
 */
public interface ResultAlgorithmObserver {
    void onResult(List<Cell> route, List<Cell> fire);
}
