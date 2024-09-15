package uparis.diamanthamdi.frontend.listener;

import java.util.List;
import java.util.logging.Logger;

import uparis.diamanthamdi.backend.algo.FindBestExtinguishPath;
import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.AlgoLauncherButton;

/**
 * Listener for the algorithm launcher button.
 * 
 * @see CustomButtonListener
 * @see AlgoLauncherButton
 */
public class AlgoLauncherButtonListener extends CustomButtonListener<AlgoLauncherButton> {
    private static final Logger logger = Logger.getLogger(AlgoLauncherButtonListener.class.getName());

    /**
     * Create a new listener for the algorithm launcher button.
     * 
     * @param button Algorithm launcher button
     */
    public AlgoLauncherButtonListener(AlgoLauncherButton button) {
        super(button);
    }

    /**
     * Launch the algorithm to find the best path to extinguish the fire.
     * 
     * Run the algorithm and then notify the observers to display the planned path to the visualizer.
     */
    @Override
    public void onClick() {
        Grid grid = button.getGrid();

        FindBestExtinguishPath algo = new FindBestExtinguishPath(grid);
        List<Cell> route = algo.run();

        logger.info("--- END OF ALGORITHM ---");

        List<Cell> fire = grid.getCellOnFire();

        if (!route.isEmpty()) {
            button.notifyObservers(route, fire);
        }
    }
}
