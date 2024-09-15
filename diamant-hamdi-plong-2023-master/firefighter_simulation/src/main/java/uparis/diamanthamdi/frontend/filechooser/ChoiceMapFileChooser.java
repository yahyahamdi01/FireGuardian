package uparis.diamanthamdi.frontend.filechooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import uparis.diamanthamdi.frontend.observer.MapChoiceObserver;

/**
 * File chooser to choose a map.
 * A map is a file (png, jpg, jpeg, tiff) with specific characteristics (size, colors, scale).
 * A map can also be from a serialized object writed in a json file.
 * 
 * @see JFileChooser
 */
public class ChoiceMapFileChooser extends JFileChooser {
    /** Path to the maps */
    private static final String PATH_TO_MAPS = "src/main/resources/uparis/diamanthamdi/maps";
    /** List of map choice observer */
    private transient List<MapChoiceObserver> observers = new ArrayList<>();
    /** Initial directory to reset the current directory */
    private File initialDirectory;

    /**
     * Create a new file chooser to choose a map.
     * 
     * @param filter
     */
    public ChoiceMapFileChooser(FileFilter filter) {
        super(PATH_TO_MAPS);

        initialDirectory = new File(PATH_TO_MAPS);

        setFileSelectionMode(JFileChooser.FILES_ONLY);

        setFileFilter(filter);

        setAcceptAllFileFilterUsed(false);
        setDialogTitle("Choose a map");
        setMultiSelectionEnabled(false);
        setFileHidingEnabled(true);
    }

    public void addObserver(MapChoiceObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (MapChoiceObserver observer: observers) {
            observer.update(getSelectedFile().getName());
        }
    }

    /** Open the file chooser when the button is cliked */
    public void openFileChooser() {
        showOpenDialog(null);
    }

    /**
     * Approve the selection of the file, notify the observers and reset the current directory.
     * 
     * @see JFileChooser#approveSelection()
     */
    @Override
    public void approveSelection() {
        super.approveSelection();

        notifyObservers();

        // Reset the current directory to the initial directory
        setCurrentDirectory(initialDirectory);
    }

    @Override
    public void cancelSelection() {
        super.cancelSelection();

        setCurrentDirectory(initialDirectory);
    }
}
