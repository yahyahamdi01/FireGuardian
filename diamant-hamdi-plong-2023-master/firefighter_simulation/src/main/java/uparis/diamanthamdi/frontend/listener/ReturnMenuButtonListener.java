package uparis.diamanthamdi.frontend.listener;

import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.ReturnMenuButton;

/**
 * Listener for the return menu button.
 * 
 * @see CustomButtonListener
 * @see ReturnMenuButton
 */
public class ReturnMenuButtonListener extends CustomButtonListener<ReturnMenuButton> {
    /**
     * Create a new listener for the return menu button.
     * 
     * @param button Return menu button
     */
    public ReturnMenuButtonListener(ReturnMenuButton button) {
        super(button);
    }

    /**
     * When the return menu button is clicked, notify the observers to return to the menu.
     */
    @Override
    public void onClick() {
        button.notifyCCObservers();
    }
    
}
