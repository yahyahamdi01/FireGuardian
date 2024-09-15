package uparis.diamanthamdi.frontend.button.visualizerpanelbutton;

import java.util.ArrayList;
import java.util.List;

import uparis.diamanthamdi.frontend.button.CustomButton;
import uparis.diamanthamdi.frontend.listener.ClearButtonListener;
import uparis.diamanthamdi.frontend.observer.ClearResultObserver;

/**
 * Class that represents the button to clear the result of the algorithm.
 * 
 * @see CustomButton
 * @see ClearButtonListener
 */
public class ClearButton extends CustomButton {
    /** Text of the button */
    private static final String TEXT = "Effacer résultat";
    /** List of clear result observer */
    private transient List<ClearResultObserver> observers = new ArrayList<>();

    /**
     * Create a new button to clear the result of the algorithm.
     */
    public ClearButton() {
        super(TEXT);

        init();
    }

    /** Initialize the listener of the button */
    @Override
    public void init() {
        addActionListener(new ClearButtonListener(this));
    }

    public void addObserver(ClearResultObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }

        observers.add(observer);
    }

    public void notifyObservers() {
        for (ClearResultObserver observer : observers) {
            observer.onClearResult();
        }
    }
}
