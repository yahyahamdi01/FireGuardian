package uparis.diamanthamdi.frontend.textfield;

import java.awt.Color;

import javax.swing.JTextField;

/**
 * Text field with a placeholder.
 * 
 * <p> This class is used to create a text field with a placeholder. </p>
 * <p> The placeholder text is displayed in gray when the text field is empty. </p>
 * <p> The placeholder text is displayed in black when the text field is not empty. </p>
 * 
 * @see JTextField
 */
public abstract class PlaceHolderTextField extends JTextField {
    /** Placeholder text. */
    private final String placeHolder;
    /** Color of the placeholder text. */
    private final Color placeHolderColor;
    /** Color of the text. */
    private final Color textColor;

    /**
     * Constructor of the placeholder text field.
     * 
     * @param placeHolder the placeholder text
     */
    protected PlaceHolderTextField(String placeHolder) {
        super(placeHolder);
        this.placeHolder = placeHolder;
        this.placeHolderColor = Color.GRAY;
        this.textColor = Color.BLACK;

        setForeground(placeHolderColor);

        init();
    }

    protected abstract void init();

    public String getPlaceHolder() {
        return placeHolder;
    }

    public Color getPlaceHolderColor() {
        return placeHolderColor;
    }

    public Color getTextColor() {
        return textColor;
    }
}
