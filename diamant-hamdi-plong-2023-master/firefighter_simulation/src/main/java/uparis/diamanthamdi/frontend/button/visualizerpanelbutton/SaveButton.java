package uparis.diamanthamdi.frontend.button.visualizerpanelbutton;

import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.frontend.button.CustomButton;
import uparis.diamanthamdi.frontend.listener.SaveButtonListener;

/**
 * Class that represents the button to save the grid.
 * 
 * @see CustomButton
 * @see SaveButtonListener
 */
public class SaveButton extends CustomButton {
    /** Text of the button */
    private static final String TEXT = "Save";
    /** The grid */
    private transient Grid grid;

    /**
     * Create a new button to save the grid.
     */
    public SaveButton() {
        super(TEXT);

        init();
    }

    /** Initialize the listener of the button */
    @Override
    public void init() {
        addActionListener(new SaveButtonListener(this));
    }

    public void onGeneration(Grid grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null");
        }

        this.grid = grid;
    }
    
    public void clear() {
        this.grid = null;
    }

    public Grid getGrid() {
        return grid;
    }
}
