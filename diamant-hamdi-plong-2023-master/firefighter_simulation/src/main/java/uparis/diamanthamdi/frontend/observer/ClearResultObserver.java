package uparis.diamanthamdi.frontend.observer;

/**
 * Observer for the clear result.
 * 
 * This observer is used to notify the visualizer to clear the result of the last path found by the algorithm.
 */
public interface ClearResultObserver {
    void onClearResult();
}
