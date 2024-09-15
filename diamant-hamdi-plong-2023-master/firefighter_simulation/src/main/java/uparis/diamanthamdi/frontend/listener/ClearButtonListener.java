package uparis.diamanthamdi.frontend.listener;

import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.ClearButton;

/**
 * Listener for the clear button.
 * 
 * @see CustomButtonListener
 * @see ClearButton
 */
public class ClearButtonListener extends CustomButtonListener<ClearButton> {
    /**
     * Create a new listener for the clear button.
     * 
     * @param button Clear button
     */
    public ClearButtonListener(ClearButton button) {
        super(button);
    }

    /**
     * When the clear button is clicked, notify the observers to clear the visualizer.
     */
    @Override
    public void onClick() {
        button.notifyObservers();
    }
    
}
