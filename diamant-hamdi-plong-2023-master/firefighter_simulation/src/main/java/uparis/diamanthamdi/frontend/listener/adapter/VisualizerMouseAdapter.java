package uparis.diamanthamdi.frontend.listener.adapter;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import uparis.diamanthamdi.frontend.panel.visualizerpanel.GridVisualizer;

/**
 * Mouse adapter for the visualizer.
 * 
 * @see MouseAdapter
 */
public class VisualizerMouseAdapter extends MouseAdapter {
    /** Visualizer where the mouse adapter is used */
    private GridVisualizer visualizer;
    /** Point where the drag starts */
    private Point dragStart;

    /**
     * Create a new mouse adapter for the visualizer.
     * 
     * @param visualizer Visualizer where the mouse adapter is used
     */ 
    public VisualizerMouseAdapter(GridVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    /**
     * Set the drag start point.
     * 
     * @param e Mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        dragStart = e.getPoint();
    }

    /**
     * Drag the visualizer.
     * 
     * @param e Mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // If the drag start point is not set, do nothing
        if (dragStart == null) {
            return;
        }

        // Calculate the distance between the drag start point and the current point
        int dx = e.getX() - dragStart.x;
        int dy = e.getY() - dragStart.y;

        Point viewPosition = visualizer.getViewPosition();

        // Calculate the new position of the view and ensure it is not negative
        int newX = Math.max(0, viewPosition.x - dx);
        int newY = Math.max(0, viewPosition.y - dy);

        Point newPosition = new Point(newX, newY);

        visualizer.setViewPosition(newPosition);

        dragStart = e.getPoint();
    }

    /**
     * Zoom in or out the visualizer depending on the mouse wheel rotation.
     * 
     * @param e Mouse wheel event
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();

        if (rotation > 0) {
            visualizer.zoomOut();
        } else {
            visualizer.zoomIn();
        }
    }

    /**
     * Reset the drag start point when the mouse is released.
     * 
     * @param e Mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        dragStart = null;
    }
}
