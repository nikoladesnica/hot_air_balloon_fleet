import java.awt.*;
import javax.swing.*;

/**
 * Custom icon for displaying a BalloonFleet.
 */
public class MyIcon implements Icon {
    /**
     * Constructs an icon for a given BalloonFleet.
     *
     * @param fleet The BalloonFleet to display.
     * @param width The width of the icon.
     * @param height The height of the icon.
     */
    public MyIcon(BalloonFleet fleet, int width, int height) {
        this.fleet = fleet;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2D = (Graphics2D) g;
        fleet.draw(g2D);
    }

    private int width;
    private int height;
    private BalloonFleet fleet;

}