package uparis.diamanthamdi.frontend.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listener for the scale field.
 * 
 * @see KeyAdapter
 */
public class ScaleFieldKeyListener extends KeyAdapter {
    /**
     * Create a new listener for the scale field.
     */
    public ScaleFieldKeyListener() {
        super();
    }

    /**
     * When a key is typed, consume the event if the key is not a digit.
     * 
     * @param evt Key event
     */
    @Override
    public void keyTyped(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }
}
