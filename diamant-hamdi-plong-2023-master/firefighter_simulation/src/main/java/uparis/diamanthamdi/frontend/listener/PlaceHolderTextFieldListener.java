package uparis.diamanthamdi.frontend.listener;

import java.awt.event.FocusAdapter;

import uparis.diamanthamdi.frontend.textfield.PlaceHolderTextField;

/**
 * Listener for the place holder text field.
 * 
 * @param <T> Class which extends PlaceHolderTextField
 * 
 * @see PlaceHolderTextField
 * @see FocusAdapter
 */
public class PlaceHolderTextFieldListener<T extends PlaceHolderTextField> extends FocusAdapter {
    /**
     * Text field to listen to.
     */
    protected T textfield;

    /**
     * Create a new listener for the place holder text field.
     * 
     * @param textfield Place holder text field
     */
    protected PlaceHolderTextFieldListener(T textfield) {
        this.textfield = textfield;
    }

    /**
     * When the text field gains focus, remove the place holder text.
     * 
     * @param e Focus event
     */
    @Override
    public void focusGained(java.awt.event.FocusEvent e) {
        if (textfield.getText().equals(textfield.getPlaceHolder())) {
            textfield.setText("");
            textfield.setForeground(textfield.getTextColor());
        }
    }

    /**
     * When the text field loses focus, add the place holder text if the text field is empty.
     * 
     * @param e Focus event
     */
    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
        if (textfield.getText().isEmpty()) {
            textfield.setText(textfield.getPlaceHolder());
            textfield.setForeground(textfield.getPlaceHolderColor());
        }
    }
}
