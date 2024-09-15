package uparis.diamanthamdi.frontend.button.choicepanelbutton;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.frontend.button.CustomButton;
import uparis.diamanthamdi.frontend.filechooser.ChoiceMapFileChooser;
import uparis.diamanthamdi.frontend.listener.ValidationButtonListener;
import uparis.diamanthamdi.frontend.observer.ChangeContextObserver;
import uparis.diamanthamdi.frontend.observer.GridGenerationObserver;
import uparis.diamanthamdi.frontend.observer.ScaleObserver;
import uparis.diamanthamdi.frontend.textfield.ScaleTextField;

/**
 * Class that represents the button to validate the choice of the map and the scale.
 * 
 * @see CustomButton
 * @see ValidationButtonListener
 */
public class ValidationButton extends CustomButton {
    /** Text of the button */
    private static final String VALIDATION_BUTTON_TEXT = "Valider";

    /** List of change context observer */
    private transient List<ChangeContextObserver> ccObservers = new ArrayList<>();
    /** List of grid generation observer */
    private transient List<GridGenerationObserver> ggObservers = new ArrayList<>();
    /** List of scale observer */
    private transient List<ScaleObserver> scaleObservers = new ArrayList<>();

    /** File chooser */
    private ChoiceMapFileChooser fileChooser;
    /** Scale text field */
    private ScaleTextField scale;

    /**
     * Create a new button to validate the choice of the map and the scale.
     * 
     * @param fileChooser
     * @param scale
     */
    public ValidationButton(ChoiceMapFileChooser fileChooser, ScaleTextField scale) {
        super(VALIDATION_BUTTON_TEXT);

        this.fileChooser = fileChooser;
        this.scale = scale;

        init();
    }

    public void addCCObserver(ChangeContextObserver observer) {
        ccObservers.add(observer);
    }

    public void addGGObserver(GridGenerationObserver observer) {
        ggObservers.add(observer);
    }

    public void addScaleObserver(ScaleObserver observer) {
        scaleObservers.add(observer);
    }

    public void notifyCCObservers() {
        for (ChangeContextObserver observer: ccObservers) {
            observer.changeContext();
        }
    }

    public void notifyGGObservers(Grid grid, BufferedImage image) {
        for (GridGenerationObserver observer: ggObservers) {
            observer.onGeneration(grid, image);
        }
    }

    public void notifyScaleObservers(int scale) {
        for (ScaleObserver observer: scaleObservers) {
            observer.update(scale);
        }
    }

    /** Initialize the listener of the button */
    @Override
    public void init() {
        addActionListener(new ValidationButtonListener(this));
    }

    public ChoiceMapFileChooser getFileChooser() {
        return fileChooser;
    }

    public ScaleTextField getScale() {
        return scale;
    }
    
}
