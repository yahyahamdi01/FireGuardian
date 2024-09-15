package uparis.diamanthamdi.frontend.button;

import java.awt.Dimension;

import javax.swing.JButton;

/**
 * Abstract class that represents a custom button.
 * The button has a fixed width and height.
 * 
 * @see JButton
 */
public abstract class CustomButton extends JButton {
    /** Width of the button */
    private static final int WIDTH = 200;
    /** Height of the button */
    private static final int HEIGHT = 30;

    /**
     * Create a new custom button with the given text.
     * 
     * @param text
     */
    protected CustomButton(String text) {
        super(text);
    
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setFocusable(false);
    }

    public abstract void init();
}
