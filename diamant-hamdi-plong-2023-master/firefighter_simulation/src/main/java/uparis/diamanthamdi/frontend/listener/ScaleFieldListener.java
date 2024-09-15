package uparis.diamanthamdi.frontend.listener;

import uparis.diamanthamdi.frontend.textfield.ScaleTextField;

/**
 * Listener for the scale text field.
 * 
 * @see PlaceHolderTextFieldListener
 * @see ScaleTextField
 */
public class ScaleFieldListener extends PlaceHolderTextFieldListener<ScaleTextField> {
    /**
     * Create a new listener for the scale text field.
     * 
     * @param textfield Scale text field
     */
    public ScaleFieldListener(ScaleTextField textfield) {
        super(textfield);
    }
}
