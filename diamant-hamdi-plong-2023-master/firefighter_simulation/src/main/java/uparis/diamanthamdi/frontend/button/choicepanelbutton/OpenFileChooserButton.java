package uparis.diamanthamdi.frontend.button.choicepanelbutton;

import uparis.diamanthamdi.frontend.button.CustomButton;
import uparis.diamanthamdi.frontend.filechooser.ChoiceMapFileChooser;
import uparis.diamanthamdi.frontend.listener.OpenFileChooserButtonListener;

/**
 * Class that represents the button to open the file chooser.
 * 
 * @see CustomButton
 * @see OpenFileChooserButtonListener
 */
public class OpenFileChooserButton extends CustomButton {
    /** Text of the button */
    private static final String OPEN_FILE_CHOOSER_BUTTON_TEXT = "Ouvrir";
    /** File chooser */
    private ChoiceMapFileChooser fileChooser;

    /**
     * Create a new button to open the file chooser.
     * 
     * @param fileChooser
     */
    public OpenFileChooserButton(ChoiceMapFileChooser fileChooser) {
        super(OPEN_FILE_CHOOSER_BUTTON_TEXT);

        this.fileChooser = fileChooser;

        init();
    }

    /** Initialize the listener of the button */
    @Override
    public void init() {
        addActionListener(new OpenFileChooserButtonListener(this, fileChooser));
    }
}
