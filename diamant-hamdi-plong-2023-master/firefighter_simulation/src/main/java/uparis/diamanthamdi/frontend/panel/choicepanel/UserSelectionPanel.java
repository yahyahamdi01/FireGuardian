package uparis.diamanthamdi.frontend.panel.choicepanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import uparis.diamanthamdi.frontend.button.choicepanelbutton.OpenFileChooserButton;
import uparis.diamanthamdi.frontend.button.choicepanelbutton.ValidationButton;
import uparis.diamanthamdi.frontend.filechooser.ChoiceMapFileChooser;
import uparis.diamanthamdi.frontend.label.MapChoiceLabel;
import uparis.diamanthamdi.frontend.textfield.ScaleTextField;

/**
 * Panel for the user selection.
 * 
 * This panel is the menu of the application. It contains the file chooser, the map choice label, the scale text field.
 * 
 * <p> The file chooser is used to select the map file to use. </p>
 * <p> The map choice label is used to display the name of the selected map. </p>
 * <p> The scale text field is used to set the scale of the map. </p>
 * <p> The validation button is used to validate the user selection. </p>
 * 
 * @see ChoiceMapFileChooser
 * @see OpenFileChooserButton
 * @see MapChoiceLabel
 * @see ScaleTextField
 * @see ValidationButton
 */
public class UserSelectionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    /** Constraints for the layout of the panel. */
    private GridBagConstraints constraints;

    /** File chooser for the map file. */
    private ChoiceMapFileChooser fileChooser;
    /** Button to open the file chooser. */
    private OpenFileChooserButton openFileChooser;
    /** Label to display the name of the selected map. */
    private MapChoiceLabel choice;
    /** Text field to set the scale of the map. */
    private ScaleTextField scale;
    /** Button to validate the user selection. */
    private ValidationButton validation;

    /**
     * Constructor of the panel.
     */
    public UserSelectionPanel() {
        initFileChooser();

        initLayout();

        openFileChooser = new OpenFileChooserButton(fileChooser);
        choice = new MapChoiceLabel();
        scale = new ScaleTextField();
        validation = new ValidationButton(fileChooser, scale);

        choice.setVisible(false);

        fileChooser.addObserver(choice);

        init();
    }

    /**
     * Initialize the layout of the panel.
     */
    private void initLayout() {
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets.set(5, 5, 5, 5);

        setLayout(new GridBagLayout());
    }

    /**
     * Increment the grid y of the constraints.
     * 
     * @param constraints The constraints to increment.
     */
    private void incrementGridY(GridBagConstraints constraints) {
        constraints.gridy++;
    }

    /**
     * Initialize the file chooser.
     * 
     * The file chooser has a filter to only display the files with the extensions "png", "jpg", "jpeg", "json" and "tif".
     */
    private void initFileChooser() {
        String[] extensions = { "png", "jpg", "jpeg", "json", "tif"};
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Map files", extensions);
        fileChooser = new ChoiceMapFileChooser(filter);
    }
 
    /**
     * Add all the components to the panel.
     */
    public void init() {
        add(openFileChooser, constraints);
        add(choice, constraints);
        add(scale, constraints);
        add(validation, constraints);
    }

    /**
     * Override the add method to increment the grid y of the constraints.
     */
    @Override
    public void add(Component comp, Object constraints) {
        super.add(comp, constraints);

        incrementGridY(this.constraints);
    }

    public ValidationButton getValidationButton() {
        return validation;
    }
}
