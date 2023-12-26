import java.awt.Graphics2D;

/**
 * Represents a group of entities (individual HotAirBalloons or other groups)
 * that can ascend together. This interface is part of the Composite pattern,
 * allowing clients to treat individual objects and compositions uniformly.
 *
 * @author Nikola Desnica (ndd2131)
 * @version 4.0
 */
public interface Ascension {
    /**
     * Adds a new a HotAirBalloon to the composition.
     *
     * @param balloon The balloon to be added.
     */
    void addBalloon(HotAirBalloon balloon);

    /**
     * Changes the vertical position of all entities in the BalloonFleet composition.
     *
     * @param speed The rate of ascent or descent.
     */
    void ascend(int speed);

    /**
     * Draws all entities in the composition on a given Graphics2D context.
     *
     * @param g2D The Graphics2D context to draw on.
     */
    void draw(Graphics2D g2D);

    void updateSpeed(int speed);
}
