package uparis.diamanthamdi.frontend.button.visualizerpanelbutton;

import java.util.ArrayList;
import java.util.List;

import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.frontend.button.CustomButton;
import uparis.diamanthamdi.frontend.listener.AlgoLauncherButtonListener;
import uparis.diamanthamdi.frontend.observer.ResultAlgorithmObserver;

/**
 * Class that represents the button to launch the algorithm.
 * 
 * @see CustomButton
 * @see AlgoLauncherButtonListener
 */
public class AlgoLauncherButton extends CustomButton {
    /** Text of the button */
    private static final String ALGO_LAUNCHER_BUTTON_TEXT = "Lancer l'algorithme";  
    /** List of result algorithm observer */
    private transient List<ResultAlgorithmObserver> observers = new ArrayList<>();
    /** The grid */
    private transient Grid grid;

    /**
     * Create a new button to launch the algorithm.
     */
    public AlgoLauncherButton() {
        super(ALGO_LAUNCHER_BUTTON_TEXT);

        init();
    }

    /** Initialize the listener of the button */
    @Override
    public void init() {
        addActionListener(new AlgoLauncherButtonListener(this));
    }

    public void addObserver(ResultAlgorithmObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }

        observers.add(observer);
    }

    public void removeObserver(ResultAlgorithmObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }

        observers.remove(observer);
    }

    public void notifyObservers(List<Cell> route, List<Cell> fire) {
        for (ResultAlgorithmObserver observer : observers) {
            observer.onResult(route, fire);
        }
    }

    public void onGeneration(Grid grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null");
        }

        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public void clear() {
        this.grid = null;
    }
}
