import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * @author ndd2131
 * Represents a hot air balloon with a graphical representation that can be moved and drawn.
 * It consists of an envelope, a gondola, and cables connecting them.
 *
 * @author Nikola Desnica (ndd2131)
 * @version 4.0
 */
public class HotAirBalloon implements MovingObject {

    /**
     * Constructs a HotAirBalloon object with a random envelope color.
     *
     * @param x       The x-coordinate of the balloon's starting position.
     * @param y       The y-coordinate of the balloon's starting position.
     * @param unitSize The size unit used to scale the balloon's components.
     */
    public HotAirBalloon(int x, double y, int unitSize) {
        this.x = x;
        this.y = y;
        this.UNIT = unitSize;

        this.ENVELOPE_DIAMETER = UNIT;
        this.GONDOLA_WIDTH = 0.5 * UNIT;
        this.CABLE_LENGTH = 0.3 * UNIT;
    }

    /**
     * Translates the balloon by a given amount.
     *
     * @param xChange The amount to change in the x-direction.
     * @param yChange The amount to change in the y-direction.
     */
    public void translate(int xChange, int yChange) {
        x += xChange;
        y += yChange;
    }

    /**
     * Draws the hot air balloon on a given Graphics2D context.
     *
     * @param g2D The Graphics2D context to draw on.
     */
    public void draw(Graphics2D g2D) {
        drawEnvelope(g2D);
        drawGondola(g2D);
        drawCables(g2D);
    }

    /**
     * Draws the envelope of the balloon.
     *
     * @param g2D The Graphics2D context to draw on.
     */
    private void drawEnvelope(Graphics2D g2D) {
        g2D.setColor(ENVELOPE_COLOR);
        GeneralPath envelope = constructEnvelope();
        g2D.fill(envelope); // Fill the envelope with its color

        g2D.setColor(Color.BLACK); // Set color for the border
        g2D.draw(envelope); // Draw the border around the envelope
    }

    /**
     * Constructs the GeneralPath representing the envelope.
     *
     * @return A GeneralPath representing the envelope.
     */
    private GeneralPath constructEnvelope() {
        int xCenter = x + (UNIT / 2);
        double yCenter = y + (UNIT * 0.5);
        double pseudoTriangleHeight = 0.8 * UNIT;

        Arc2D.Double semiCircle = new Arc2D.Double(x, y, ENVELOPE_DIAMETER, ENVELOPE_DIAMETER, 0, 180, Arc2D.CHORD);
        Point2D.Double leftSideStart = new Point2D.Double(xCenter - (UNIT * 0.5), yCenter);
        Point2D.Double leftSideEnd = new Point2D.Double(xCenter - (UNIT * 0.15), yCenter + pseudoTriangleHeight);
        Point2D.Double rightSideEnd = new Point2D.Double(xCenter + (UNIT * 0.15), yCenter + pseudoTriangleHeight);
        Point2D.Double rightSideStart = new Point2D.Double(xCenter + (UNIT * 0.5), yCenter);

        GeneralPath envelope = new GeneralPath();
        envelope.append(semiCircle, false);
        envelope.moveTo(leftSideStart.getX(), leftSideStart.getY());
        envelope.lineTo(leftSideEnd.getX(), leftSideEnd.getY());
        envelope.lineTo(rightSideEnd.getX(), rightSideEnd.getY());
        envelope.lineTo(rightSideStart.getX(), rightSideStart.getY());
        envelope.closePath();

        return envelope;
    }

    /**
     * Generates a random color for the balloon's envelope using hex color codes.
     *
     * @return A randomly selected Color.
     */
    private Color getRandomEnvelopeColor() {
        String[] hexColors = new String[]{"#003049", "#d62828", "#f77f00", "#fcbf49", "#eae2b7"};
        Random random = new Random();
        return Color.decode(hexColors[random.nextInt(hexColors.length)]);
    }

    /**
     * Draws the gondola of the balloon.
     *
     * @param g2D The Graphics2D context to draw on.
     */
    private void drawGondola(Graphics2D g2D) {
        g2D.setColor(GONDOLA_COLOR);
        Rectangle2D.Double gondola = constructGondola();
        g2D.fill(gondola); // Fill the gondola with its color

        g2D.setColor(Color.BLACK); // Set color for the border
        g2D.draw(gondola); // Draw the border around the gondola
    }

    /**
     * Constructs the Rectangle2D representing the gondola.
     *
     * @return A Rectangle2D representing the gondola.
     */
    private Rectangle2D.Double constructGondola() {
        int xCenter = x + (UNIT / 2);
        double bottomY = y + 1.3 * UNIT;
        Point2D.Double gondolaTopLeft = new Point2D.Double(xCenter - (GONDOLA_WIDTH * 0.5), bottomY + CABLE_LENGTH);

        return new Rectangle2D.Double(gondolaTopLeft.getX(), gondolaTopLeft.getY(), GONDOLA_WIDTH, GONDOLA_WIDTH * 0.7);
    }

    /**
     * Draws the cables of the balloon.
     *
     * @param g2D The Graphics2D context to draw on.
     */
    private void drawCables(Graphics2D g2D) {
        Line2D.Double leftCable = constructCable(true);
        Line2D.Double rightCable = constructCable(false);

        g2D.setColor(Color.BLACK);
        g2D.draw(leftCable);
        g2D.draw(rightCable);
    }

    /**
     * Constructs a Line2D representing one of the balloon's cables.
     *
     * @param isLeft Specifies whether the cable is the left one.
     * @return A Line2D representing a cable.
     */
    private Line2D.Double constructCable(boolean isLeft) {
        int xCenter = x + (UNIT / 2);
        double yCenter = y + (UNIT * 0.5);
        double pseudoTriangleHeight = 0.8 * UNIT;

        double sideOffset = isLeft ? -0.15 : 0.15;
        Point2D.Double cableTop = new Point2D.Double(xCenter + (UNIT * sideOffset), yCenter + pseudoTriangleHeight);
        double gondolaY = y + 1.3 * UNIT + CABLE_LENGTH;
        Point2D.Double gondolaPoint = new Point2D.Double(xCenter + (GONDOLA_WIDTH * (isLeft ? -0.5 : 0.5)), gondolaY);

        return new Line2D.Double(cableTop, gondolaPoint);
    }

    private int x;
    private double y;
    private final int UNIT;
    private final double ENVELOPE_DIAMETER;
    private final double GONDOLA_WIDTH;
    private final double CABLE_LENGTH;
    private final Color ENVELOPE_COLOR = getRandomEnvelopeColor();
    private static final Color GONDOLA_COLOR = Color.decode("#bc6c25");
}