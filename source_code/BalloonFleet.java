import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a fleet of hot air balloons, managing their coordinated movements.
 * Implements Ascension to control the vertical movement and JitterControl for horizontal jitter.
 *
 * @author Nikola Desnica (ndd2131)
 * @version 5
 */
public class BalloonFleet implements Ascension, JitterControl {

    /**
     * Constructs a new BalloonFleet.
     */
    public BalloonFleet() {
        balloons = new ArrayList<>();
        ascentSpeed = 0;
        jitterIntensity = 0;
    }

    /**
     * Adds a new HotAirBalloon to the fleet.
     *
     * @param balloon The HotAirBalloon to be added.
     */
    @Override
    public void addBalloon(HotAirBalloon balloon) {
        balloons.add(balloon);
    }

    /**
     * Changes the vertical position of all balloons in the fleet.
     *
     * @param speed The rate of ascent or descent.
     */
    @Override
    public void ascend(int speed) {
        balloons.forEach(balloon -> balloon.translate(0, -ascentSpeed));
    }

    /**
     * Updates the ascent speed of the fleet.
     *
     * @param speed The new ascent speed.
     */
    public void updateSpeed(int speed) {
        ascentSpeed = speed;
    }

    /**
     * Updates the jitter intensity for horizontal movement of the fleet.
     *
     * @param intensity The new jitter intensity.
     */
    @Override
    public void updateJitterIntensity(int intensity) {
        jitterIntensity = intensity;
    }

    /**
     * Applies a random jitter effect to each balloon in the fleet based on the current jitter intensity.
     */
    protected void applyJitter() {
        Random random = new Random();
        balloons.forEach(balloon -> {
            int jitter = random.nextInt(jitterIntensity + 1);
            balloon.translate(jitter - jitterIntensity / 2, 0);
        });
    }

    /**
     * Draws all balloons in the fleet on a given Graphics2D context.
     *
     * @param g2D The Graphics2D context to draw on.
     */
    public void draw(Graphics2D g2D) {
        balloons.forEach(balloon -> balloon.draw(g2D));
    }

    /**
     * the list of balloons in th fleet.
     */
    private List<HotAirBalloon> balloons;
    private int ascentSpeed;
    private int jitterIntensity;
}