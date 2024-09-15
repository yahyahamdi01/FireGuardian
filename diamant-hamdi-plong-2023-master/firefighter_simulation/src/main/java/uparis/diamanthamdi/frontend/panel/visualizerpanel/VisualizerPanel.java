package uparis.diamanthamdi.frontend.panel.visualizerpanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;
import uparis.diamanthamdi.backend.model.grid.Grid;
import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.AlgoLauncherButton;
import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.ClearButton;
import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.ReturnMenuButton;
import uparis.diamanthamdi.frontend.button.visualizerpanelbutton.SaveButton;
import uparis.diamanthamdi.frontend.label.ScaleLabel;
import uparis.diamanthamdi.frontend.observer.ChangeContextObserver;
import uparis.diamanthamdi.frontend.observer.GridGenerationObserver;

/**
 * Panel containing the visualizer and the buttons to interact with it.
 * 
 * @see GridGenerationObserver
 * @see ChangeContextObserver
 * @see ReturnMenuButton
 * @see AlgoLauncherButton
 * @see SaveButton
 * @see ClearButton
 * @see ScaleLabel
 * @see GridVisualizer
 * @see JPanel
 */
public class VisualizerPanel extends JPanel implements GridGenerationObserver, ChangeContextObserver {
    /** Top panel containing the return button and the scale label. */
    private JPanel topPanel;
    /** Panel containing the return button. */
    private JPanel topButtonsPanel;
    /** Center panel containing the visualizer. */
    private JPanel centerPanel;
    /** The visualizer */
    private GridVisualizer visualizer;
    /** Bottom panel containing the buttons to interact with the visualizer. */
    private JPanel bottomPanel;

    /** Button to return to the menu. */
    private ReturnMenuButton returnMenu;
    /** Button to launch the algorithm. */
    private AlgoLauncherButton algoLauncher;
    /** Button to serialize the grid. */
    private SaveButton save;
    /** Button to clear the path found, if it exists. */
    private ClearButton clear;
    /** Label displaying the scale of the visualizer. */
    private ScaleLabel scaleLabel;

    /**
     * Constructor of the visualizer panel.
     */
    public VisualizerPanel() {
        initComponents();

        initLayout();

        returnMenu.addCCObserver(this);
        algoLauncher.addObserver(visualizer);
        clear.addObserver(visualizer);
        
        addComponents();
    }

    /**
     * Initialize the components of the visualizer panel.
     */
    private void initComponents() {
        topPanel = new JPanel();
        topButtonsPanel = new JPanel();
        centerPanel = new JPanel();
        bottomPanel = new JPanel();
        visualizer = new GridVisualizer();
        returnMenu = new ReturnMenuButton();
        algoLauncher = new AlgoLauncherButton();
        save = new SaveButton();
        clear = new ClearButton();
        scaleLabel = new ScaleLabel("");
    }

    /**
     * Initialize the layout of the visualizer panel.
     */
    private void initLayout() {
        topPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new GridBagLayout());
        setLayout(new BorderLayout());
    }

    /**
     * Add the components to the visualizer panel.
     */
    private void addComponents() {
        topButtonsPanel.add(returnMenu);
        bottomPanel.add(algoLauncher, BorderLayout.WEST);
        bottomPanel.add(save, BorderLayout.CENTER);
        bottomPanel.add(clear, BorderLayout.EAST);

        topPanel.add(topButtonsPanel, BorderLayout.WEST);
        topPanel.add(scaleLabel, BorderLayout.EAST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(visualizer, gbc);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public ReturnMenuButton getReturnButton() {
        return returnMenu;
    }

    public GridVisualizer getVisualizer() {
        return visualizer;
    }

    public ScaleLabel getScaleLabel() {
        return scaleLabel;
    }

    /**
     * Called when a new grid is generated.
     * 
     * Call the onGeneration method of the visualizer, the algoLauncher and the save button.
     * 
     * @param grid The grid generated.
     * @param image The image of the grid generated.
     */
    @Override
    public void onGeneration(Grid grid, BufferedImage image) {
        List<UrbanCell> firestations = grid.getFirestations();

        visualizer.onGeneration(image, firestations);
        algoLauncher.onGeneration(grid);
        save.onGeneration(grid);
    }

    /**
     * Called when the context is changed.
     * 
     * Clear the visualizer, the algoLauncher and the save button.
     */
    @Override
    public void changeContext() {
        visualizer.clear();
        algoLauncher.clear();
        save.clear();
    }
}
