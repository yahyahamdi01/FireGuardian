package uparis.diamanthamdi.frontend.label;

import uparis.diamanthamdi.frontend.observer.MapChoiceObserver;

/**
 * Label to display the choice of a map.
 * 
 * @see CustomLabel
 * @see MapChoiceObserver
 */
public class MapChoiceLabel extends CustomLabel implements MapChoiceObserver {
    /** Text to display */
    private static final String TEXT = "Choix: ";

    /**
     * Create a new label to display the choice of a map.
     */
    public MapChoiceLabel() {
        super(TEXT);
    }

    /**
     * Update the text of the label.
     * 
     * @param text
     */
    @Override
    public void update(String text) {
        setText(TEXT + text);

        setVisible(true);
    }
}
