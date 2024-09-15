package uparis.diamanthamdi.frontend.listener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.backend.model.grid.generator.GridGenerator;
import uparis.diamanthamdi.backend.utils.Constants;
import uparis.diamanthamdi.backend.utils.ImageUtils;
import uparis.diamanthamdi.frontend.button.choicepanelbutton.ValidationButton;

/**
 * Listener for the validation button.
 * 
 * @see CustomButtonListener
 * @see ValidationButton
 */
public class ValidationButtonListener extends CustomButtonListener<ValidationButton> {
    /**
     * Create a new listener for the validation button.
     * 
     * @param button Validation button
     */
    public ValidationButtonListener(ValidationButton button) {
        super(button);
    }

    /**
     * When the validation button is clicked, generate the grid for the selected map.
     * 
     * @see GridGenerator
     * @see ImageUtils#createImage(int[][])
     */
    @Override
    public void onClick() {
        // check if the file is valid
        if (isNotValid()) {
            return;
        }

        // call the function to generate the grid for the selected map
        File file = button.getFileChooser().getSelectedFile();

        Grid grid;

        if (isFileImage(file)) {
            grid = generateGridFromImage(file);
        } else if (isFileJson(file)){
            grid = generateGridFromJson(file);
        } else {
            System.out.println("File is not an image or a json file");
            return;
        }

        if (grid == null) {
            System.out.println("Error while generating grid");
            return;
        }

        BufferedImage newImage = ImageUtils.createImage(grid.getCells());

        // notify the visualizer to update the image shown on the screen
        button.notifyGGObservers(grid, newImage);
        // notify the window to change the context
        button.notifyCCObservers();
    }

    /**
     * Generate the grid from a json file.
     * 
     * @param file The path to the json file 
     * @return The grid generated from the json file
     */
    private Grid generateGridFromJson(File file) {
        return GridGenerator.generate(file.getAbsolutePath());
    }

    /**
     * Generate the grid from an image file.
     * 
     * @param file The path to the image file
     * @return The grid generated from the image file
     */
    private Grid generateGridFromImage(File file) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Error while reading image: " + e.getMessage());
            return null;
        }

        int userScale;
        try {
            // if the scale is empty, use the default scale
            // otherwise, parse the scale

            if (isScaleEmpty()) {
                userScale = Constants.DEFAULT_SCALE;
            } else {
                userScale = Integer.parseInt(button.getScale().getText());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error while parsing scale: " + e.getMessage());
            userScale = Constants.DEFAULT_SCALE;
        }

        GridGenerator gridGenerator = new GridGenerator(image, userScale);

        button.notifyScaleObservers(gridGenerator.getScale());

        return gridGenerator.generate();
    }

    /**
     * Check if the file is an image.
     * A file is considered as an image if its extension is one of the following: png, jpg, jpeg, tif.
     * 
     * @param file The file to check
     * @return True if the file is an image, false otherwise
     */
    private boolean isFileImage(File file) {
        String[] extensionsImage = {"png", "jpg", "jpeg", "tif"};

        for (String extension : extensionsImage) {
            if (file.getName().endsWith(extension)) {
                return true;
            }
        }

        return false;
    }

    private boolean isFileJson(File file) {
        return file.getName().endsWith("json");
    }

    private boolean isNotValid() {
        return button.getFileChooser().getSelectedFile() == null;
    }

    private boolean isScaleEmpty() {
        return button.getScale().isEmpty();
    }
}
