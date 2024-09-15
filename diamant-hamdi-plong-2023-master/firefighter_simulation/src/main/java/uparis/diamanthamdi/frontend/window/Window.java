package uparis.diamanthamdi.frontend.window;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import uparis.diamanthamdi.frontend.observer.ChangeContextObserver;
import uparis.diamanthamdi.frontend.panel.choicepanel.UserSelectionPanel;
import uparis.diamanthamdi.frontend.panel.visualizerpanel.VisualizerPanel;

/**
 * Window of the application.
 * 
 * <p> This class is used to create the window of the application. </p>
 * <p> The window contains the user selection panel and the visualizer panel. </p>
 * 
 * @see JFrame
 * @see ChangeContextObserver
 * @see UserSelectionPanel
 * @see VisualizerPanel
 */
public class Window extends JFrame implements ChangeContextObserver {
    /** Fixed width of the window. */
    private static final int WIDTH = 1200;
    /** Fixed height of the window. */
    private static final int HEIGHT = 1000;

    /** Card layout of the window. Enable to switch between the user selection panel and the visualizer panel. */
    private CardLayout cardLayout;

    /** User selection panel. */
    private UserSelectionPanel userSelectionPanel;
    /** Visualizer panel. */
    private VisualizerPanel visualizerPanel;

    /**
     * Constructor of the window.
     * 
     * @param title the title of the window
     */
    public Window(String title) {
        initFrame(title);

        userSelectionPanel = new UserSelectionPanel();
        visualizerPanel = new VisualizerPanel();
        
        add(userSelectionPanel, "choice");
        add(visualizerPanel, "visualizer");

        userSelectionPanel.getValidationButton().addCCObserver(this);
        userSelectionPanel.getValidationButton().addGGObserver(visualizerPanel);
        userSelectionPanel.getValidationButton().addScaleObserver(visualizerPanel.getScaleLabel());
        visualizerPanel.getReturnButton().addCCObserver(this);
        
        cardLayout.show(getContentPane(), "choice");
        
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Initialize the frame.
     * 
     * @param title the title of the frame
     */
    private void initFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        cardLayout = new CardLayout();
        setLayout(cardLayout);
    }

    public void start() {
        setVisible(true);
    }

    @Override
    public void changeContext() {
        cardLayout.next(getContentPane());
    }
}
