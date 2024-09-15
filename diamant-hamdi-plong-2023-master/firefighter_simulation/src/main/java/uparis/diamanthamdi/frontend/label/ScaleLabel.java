package uparis.diamanthamdi.frontend.label;

import javax.swing.SwingUtilities;

import uparis.diamanthamdi.backend.utils.Constants;
import uparis.diamanthamdi.frontend.observer.ScaleObserver;

/**
 * Label to display the scale of the map.
 * 
 * @see CustomLabel
 * @see ScaleObserver
 */
public class ScaleLabel extends CustomLabel implements ScaleObserver {
    private static final int KM = 1000; // 1 km = 1000 m
    /**
     * Create a new label to display the scale of the map.
     * 
     * @param text
     */
    public ScaleLabel(String text) {
        super(text);
    }

    /**
     * Update the text of the label. The text is formatted according to the scale.
     * 
     * @param scale
     */
    @Override
    public void update(int scale) {
        final String text;
        int size = Constants.DEFAULT_PIXEL_SIZE * scale;

        // Determine the appropriate unit and format the size
        if (size < KM) {
            text = "Pixel = " + size + " m²"; // Square meters
        } else {
            text = "Pixel = " + size / KM + " km²"; // Square kilometers
        }

        // Ensure the text update happens on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            setText(text);
            setVisible(true);
        });
    }
}
