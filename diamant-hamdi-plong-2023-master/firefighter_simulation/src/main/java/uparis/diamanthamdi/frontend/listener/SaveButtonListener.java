package uparis.diamanthamdi.frontend.listener;

import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.SaveButton;

/**
 * Listener for the save button.
 * 
 * @see CustomButtonListener
 * @see SaveButton
 */
public class SaveButtonListener extends CustomButtonListener<SaveButton> {
    /**
     * Create a new listener for the save button.
     * 
     * @param button Save button
     */
    public SaveButtonListener(SaveButton button) {
        super(button);
    }

    /**
     * When the save button is clicked, serialize the grid and save it in the resources/maps/json folder.
     * 
     * @see Grid#serialize()
     * @throws IllegalArgumentException If the grid is null
     */
    @Override
    public void onClick() {
        Grid grid = button.getGrid();
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null");
        }

        // on serialize le grid
        grid.serialize();

        System.out.println("Grid saved");
    }
    
}
