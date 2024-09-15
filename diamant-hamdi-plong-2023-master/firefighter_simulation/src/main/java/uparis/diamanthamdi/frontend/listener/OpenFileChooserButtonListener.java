package uparis.diamanthamdi.frontend.listener;

import uparis.diamanthamdi.frontend.button.choicepanelbutton.OpenFileChooserButton;
import uparis.diamanthamdi.frontend.filechooser.ChoiceMapFileChooser;

/**
 * Listener for the open file chooser button.
 * 
 * @see CustomButtonListener
 * @see OpenFileChooserButton
 */
public class OpenFileChooserButtonListener extends CustomButtonListener<OpenFileChooserButton> {
    /**
     * File chooser to open the file.
     */
    private ChoiceMapFileChooser fileChooser;

    /**
     * Create a new listener for the open file chooser button.
     * 
     * @param button Open file chooser button
     * @param fileChooser File chooser
     */
    public OpenFileChooserButtonListener(OpenFileChooserButton button, ChoiceMapFileChooser fileChooser) {
        super(button);

        this.fileChooser = fileChooser;
    }

    /**
     * When the open file chooser button is clicked, open the file chooser.
     */
    @Override
    public void onClick() {
        fileChooser.openFileChooser();
    }
    
}
