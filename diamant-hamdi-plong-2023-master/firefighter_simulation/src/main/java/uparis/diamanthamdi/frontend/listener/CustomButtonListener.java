package uparis.diamanthamdi.frontend.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uparis.diamanthamdi.frontend.button.CustomButton;

/**
 * Listener for the custom button.
 * 
 * @param <T> Class which extends CustomButton
 * 
 * @see CustomButton
 * @see ActionListener
 */
public abstract class CustomButtonListener<T extends CustomButton> implements ActionListener {
    /**
     * Button to listen to.
     */
    protected T button;

    /**
     * Create a new listener for the custom button.
     * 
     * @param button Custom button
     */
    protected CustomButtonListener(T button) {
        this.button = button;
    }

    /**
     * Action to perform when the button is clicked.
     */
    public abstract void onClick();

    /**
     * When the button is clicked, perform the action.
     * 
     * @param e Action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        onClick();
    }
}
