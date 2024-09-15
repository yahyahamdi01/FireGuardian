package uparis.diamanthamdi.frontend.panel.visualizerpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.BasicStroke;
import java.awt.Stroke;

import javax.swing.JPanel;

import uparis.diamanthamdi.backend.model.cell.type.Cell;
import uparis.diamanthamdi.backend.model.cell.type.UrbanCell;
import uparis.diamanthamdi.backend.utils.MathUtils;
import uparis.diamanthamdi.frontend.observer.ClearResultObserver;
import uparis.diamanthamdi.frontend.observer.ResultAlgorithmObserver;
import uparis.diamanthamdi.frontend.listener.adapter.VisualizerMouseAdapter;

/**
 * Panel for the grid visualizer.
 * 
 * This panel is used to display the grid of the map.
 * 
 * <p> The grid visualizer is used to display the map, the firestations, the fires and the route. </p>
 * <p> The map is displayed as an image. </p>
 * <p> The firestations are displayed as white circles. </p>
 * <p> The fires are displayed as purple circles. </p>
 * <p> The route is displayed as black lines. </p>
 * 
 * @see ResultAlgorithmObserver
 * @see ClearResultObserver
 * @see VisualizerMouseAdapter
 */
public class GridVisualizer extends JPanel implements ResultAlgorithmObserver, ClearResultObserver {
    /** Fixed width of the panel. */
    private static final int WIDTH = 800;
    /** Fixed height of the panel. */
    private static final int HEIGHT = 800;
    /** Default Minimum zoom level. */
    public static final double MIN_ZOOM = 1.0;
    /** Maximum zoom level. */
    public static final double MAX_ZOOM = 50.0;
    /** Step of the zoom level. */
    public static final double ZOOM_STEP = 0.05;
    /** Color of the firestations. */
    private static final Color FIRESTATION_COLOR = Color.WHITE;
    /** Color of the fires. */
    private static final Color FIRE_COLOR = new Color(183, 0, 255);

    /** Graphics 2D object to draw on the panel. */
    private transient Graphics2D g2d;

    /** Position of the view. */
    private Point viewPosition = new Point(0, 0);
    /** Minimum zoom level. Defined by the scale. */
    private double minZoom;
    /** Current zoom level. */
    private double zoomLevel;
 
    /** Image to display. */
    private transient BufferedImage image;
    /** List of firestations. If the list is not empty, the firestations are displayed. */
    private transient List<UrbanCell> firestations = null;
    /** List of fires. If the list is not empty, the fires are displayed. */
    private transient List<Cell> fire = null;
    /** List of cells of the route. If the list is not empty, the route is displayed. */
    private transient List<Cell> route;
    
    /**
     * Constructor of the panel.
     */
    public GridVisualizer() {
        minZoom = MIN_ZOOM;

        setBackground(Color.BLACK);

        setupMouseListners();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    /**
     * Calculate the minimum zoom level.
     * 
     * The minimum zoom level is defined by the scale.
     * 
     * The minumum zoom level is the maximum between 1.0 and the scale.
     * If the image is null the minimum zoom level can't be calculated.
     */
    private void calculateMinimumZoom() {
        if (image == null) {
            return;
        }

        double scale = MathUtils.scaling(image.getWidth(), image.getHeight(), WIDTH, HEIGHT);
        minZoom = Math.max(1.0, scale);
    }

    /**
     * Setup the mouse listeners.
     */
    private void setupMouseListners() {
        VisualizerMouseAdapter mouseAdapter = new VisualizerMouseAdapter(this);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
    }

    /**
     * Called when the generation of the map is finished.
     * 
     * @param image The image of the map.
     * @param firestations The list of firestations.
     * 
     * @throws IllegalArgumentException If the image is null.
     */
    public void onGeneration(BufferedImage image, List<UrbanCell> firestations) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }

        this.image = image;
        this.firestations = firestations;

        calculateMinimumZoom();
        zoomLevel = minZoom;

        repaint();
    }

    /**
     * Paint the route result.
     * 
     * @param route The list of cells of the route.
     */
    private void paintRouteResult(List<Cell> route) {
        if (route == null || route.isEmpty()) {
            return;
        }

        for (int i = 0; i < route.size() - 1; i++) {
            Cell start = route.get(i);
            Cell end = route.get(i + 1);

            paintPath(start, end);
        }
    }

    /**
     * Paint the path between two cells.
     * 
     * @param start The start cell.
     * @param end The end cell.
     */
    private void paintPath(Cell start, Cell end) {
        Point screenStart = convertXYToScreenCoordinates(start.getX(), start.getY(), zoomLevel);
        Point screenEnd = convertXYToScreenCoordinates(end.getX(), end.getY(), zoomLevel);

        // Define the thickness of the line
        float thickness = 2.0f;

        // Create a Stroke object with the desired thickness
        Stroke stroke = new BasicStroke(thickness);

        // Set the stroke of the Graphics2D object
        g2d.setStroke(stroke);

        // Draw the line between the two screen coordinates
        g2d.setColor(Color.BLACK);
        g2d.drawLine(screenStart.x, screenStart.y, screenEnd.x, screenEnd.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        super.paintComponents(g2d);
        if (image == null) {
            System.out.println("Image is null");
            return;
        }

        drawImage();

        if (route != null) {
            paintRouteResult(route);
        }

        if (fire != null) {
            drawFires();
        }

        drawFirestations();
    }

    /**
     * Draw the image on the panel.
     */
    private void drawImage() {
        Dimension size = MathUtils.scale(image.getWidth(), image.getHeight(), zoomLevel);
        int maxX = Math.max(0, (int) size.getWidth() - WIDTH);
        int maxY = Math.max(0, (int) size.getHeight() - HEIGHT);
        int x = Math.min(maxX, viewPosition.x);
        int y = Math.min(maxY, viewPosition.y);

        g2d.drawImage(image, -x, -y, (int) size.getWidth(), (int) size.getHeight(), null);
    }

    /**
     * Draw the firestations on the panel.
     */
    private void drawFirestations() {
        if (firestations == null) {
            return;
        }

        for (UrbanCell firestation : firestations) {
            Point screenCoordinates = convertXYToScreenCoordinates(firestation.getX(), firestation.getY(), zoomLevel);
            g2d.setColor(FIRESTATION_COLOR);
            g2d.fillOval(screenCoordinates.x - 5, screenCoordinates.y - 5, 10, 10);
        }
    }

    /**
     * Draw the fires on the panel.
     */
    private void drawFires() {
        if (fire == null) {
            return;
        }

        for (Cell cell : fire) {
            Point screenCoordinates = convertXYToScreenCoordinates(cell.getX(), cell.getY(), zoomLevel);
            g2d.setColor(FIRE_COLOR);
            g2d.fillOval(screenCoordinates.x - 5, screenCoordinates.y - 5, 10, 10);
        }
    }

    /**
     * Convert the coordinates of the grid to the screen coordinates.
     * 
     * @param x The x coordinate of the grid.
     * @param y The y coordinate of the grid.
     * @param scale The scale of the image.
     * 
     * @return The screen coordinates.
     */
    private Point convertXYToScreenCoordinates(int x, int y, double scale) {
        Point center = MathUtils.centerPosition(image.getWidth(), image.getHeight(), WIDTH, HEIGHT, scale);
        
        int screenX = (int) MathUtils.convertValueToScreenValue(center.x, scale, x+0.5);
        int screenY = (int) MathUtils.convertValueToScreenValue(center.y, scale, y+0.5);

        return new Point(screenX, screenY);
    }

    /**
     * Zoom in the panel.
     */
    public void zoomIn() {
        zoomLevel = Math.min(MAX_ZOOM, zoomLevel + ZOOM_STEP);
        repaint();
    }

    /**
     * Zoom out the panel.
     */
    public void zoomOut() {
        zoomLevel = Math.max(minZoom, zoomLevel - ZOOM_STEP);
        repaint();
    }

    /**
     * Called when the result of the algorithm is ready.
     */
    @Override
    public void onResult(List<Cell> route, List<Cell> fire) {
        this.route = route;
        this.fire = fire;

        repaint();
    }

    /**
     * Called when the result of the algorithm has to be cleared.
     */
    @Override
    public void onClearResult() {
        if (route == null) {
            return;
        }

        route = null;
        fire = null;
        repaint();
    }

    /**
     * Set to null all the attributes of the panel.
     */
    public void clear() {
        image = null;
        route = null;
        firestations = null;
        fire = null;
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public double getMinZoom() {
        return minZoom;
    }

    public void setZoomLevel(double zoomLevel) {
        this.zoomLevel = Math.max(minZoom, zoomLevel);
        repaint();
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public Point getViewPosition() {
        return viewPosition;
    }

    public void setViewPosition(Point viewPosition) {
        this.viewPosition = viewPosition;
        repaint();
    }
}
