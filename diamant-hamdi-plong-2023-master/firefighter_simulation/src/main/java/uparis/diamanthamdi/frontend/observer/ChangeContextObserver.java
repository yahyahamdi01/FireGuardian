package uparis.diamanthamdi.frontend.observer;

/**
 * Observer for the change context.
 * 
 * This observer is used to notify the window to change the context.
 * So the window can change between the menu and the visualizer.
 */
public interface ChangeContextObserver {
    void changeContext();
}
