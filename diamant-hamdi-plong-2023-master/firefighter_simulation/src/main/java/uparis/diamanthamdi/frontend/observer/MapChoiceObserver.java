package uparis.diamanthamdi.frontend.observer;

/**
 * Observer for the map choice.
 * 
 * This observer is used to notify the label of the selected map.
 */
public interface MapChoiceObserver {
    void update(String text);
}
