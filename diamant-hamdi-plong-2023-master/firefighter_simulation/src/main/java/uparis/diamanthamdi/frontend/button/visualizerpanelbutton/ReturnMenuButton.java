package uparis.diamanthamdi.frontend.button.visualizerpanelbutton;

import java.util.ArrayList;
import java.util.List;

import uparis.diamanthamdi.frontend.button.CustomButton;
import uparis.diamanthamdi.frontend.listener.ReturnMenuButtonListener;
import uparis.diamanthamdi.frontend.observer.ChangeContextObserver;

/**
 * Class that represents the button to return to the menu.
 * 
 * @see CustomButton
 * @see ReturnMenuButtonListener
 */
public class ReturnMenuButton extends CustomButton {
    /** Text of the button */
    private static final String RETURN_MENU_BUTTON_TEXT = "Retour au menu";
    /** List of change context observer */
    private transient List<ChangeContextObserver> observers = new ArrayList<>();

    /**
     * Create a new button to return to the menu.
     */
    public ReturnMenuButton() {
        super(RETURN_MENU_BUTTON_TEXT);

        init();
    }

    public void addCCObserver(ChangeContextObserver observer) {
        observers.add(observer);
    }

    public void notifyCCObservers() {
        for (ChangeContextObserver observer: observers) {
            observer.changeContext();
        }
    }

    @Override
    public void init() {
        addActionListener(new ReturnMenuButtonListener(this));
    }
    
}
