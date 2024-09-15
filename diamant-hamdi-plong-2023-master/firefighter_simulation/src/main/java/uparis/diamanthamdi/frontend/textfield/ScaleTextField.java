package uparis.diamanthamdi.frontend.textfield;

import java.awt.Dimension;

import uparis.diamanthamdi.frontend.listener.ScaleFieldKeyListener;
import uparis.diamanthamdi.frontend.listener.ScaleFieldListener;

/**
 * Text field for the scale.
 * 
 * <p> This class is used to create a text field for the scale. </p>
 * <p> The scale text field is used to enter the scale of the map. </p>
 * 
 * @see PlaceHolderTextField
 */
public class ScaleTextField extends PlaceHolderTextField {
    /** Default text of the text field. */
    private static final String DEFAULT_TEXT = "Scale";
    /** Width of the text field. */
    private static final int WIDTH = 200;
    /** Height of the text field. */
    private static final int HEIGHT = 30;

    /**
     * Constructor of the scale text field.
     */
    public ScaleTextField() {
        super(DEFAULT_TEXT);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Initialize the text field. Add the focus listener and the key listener.
     */
    @Override
    protected void init() {
        addFocusListener(new ScaleFieldListener(this));
        addKeyListener(new ScaleFieldKeyListener());
    }

    /**
     * Check if the text field is empty.
     * 
     * The text field is empty if the text is equal to the default text, if the text is empty or if the text is equal to
     * "0".
     * 
     * @return true if the text field is empty, false otherwise
     */
    public boolean isEmpty() {
        return getText().equals(DEFAULT_TEXT) || getText().isEmpty() || getText().equals("0");
    }
}
