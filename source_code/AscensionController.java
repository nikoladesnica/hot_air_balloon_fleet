import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/**
 * AscensionController manages and displays the mass ascension of multiple fleets of hot air balloons.
 * Each fleet has its own jitter effect and ascension control.
 *
 * @author Nikola Desnica (ndd2131)
 * @version 7
 */
public class AscensionController {

    /**
     * The main method sets up the JFrame and initiates the mass balloon ascension.
     * It creates multiple BalloonFleets, each with its own MyIcon and JitterSlider for control.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Mass Balloon Ascension");
        myFrame.setLayout(new BorderLayout());

        JPanel fleetPanel = new JPanel(new GridLayout(1, NUM_FLEETS));
        List<Ascension> fleets = new ArrayList<>();

        for (int i = 0; i < NUM_FLEETS; i++) {
            BalloonFleet fleet = createBalloonFleet();
            fleets.add(fleet);
            MyIcon myIcon = new MyIcon(fleet, ICON_W / NUM_FLEETS, ICON_H);
            JLabel myLabel = new JLabel(myIcon);
            fleetPanel.add(myLabel);
            startAnimation(fleet, myLabel);
        }

        myFrame.add(fleetPanel, BorderLayout.CENTER);

        JPanel sliderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        for (Ascension fleet : fleets) {
            JitterSlider jitterSlider = new JitterSlider(fleet);
            sliderPanel.add(jitterSlider);
        }

        AscensionSlider ascensionslider = new AscensionSlider(fleets);
        myFrame.add(ascensionslider, BorderLayout.WEST);
        myFrame.add(sliderPanel, BorderLayout.SOUTH);

        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }

    /**
     * Creates and returns a BalloonFleet with a specified number of hot air balloons.
     * Each balloon is randomly sized and positioned.
     *
     * @return BalloonFleet object containing a collection of HotAirBalloon objects.
     */
    private static BalloonFleet createBalloonFleet() {
        BalloonFleet fleet = new BalloonFleet();
        Random random = new Random();
        int minBalloonNumber = 3;
        int maxBalloonNumber = 7;
        int balloonNumber = minBalloonNumber + random.nextInt(maxBalloonNumber - minBalloonNumber + 1);

        for (int i = 0; i < balloonNumber; i++) {
            HotAirBalloon balloon = RandomBalloonGenerator.generateBalloon(ICON_W / NUM_FLEETS, ICON_H);
            fleet.addBalloon(balloon);
        }
        return fleet;
    }

    /**
     * Starts the animation of a BalloonFleet's ascension.
     * A timer triggers periodic updates to the fleet's position and repaints the JLabel.
     *
     * @param fleet The BalloonFleet to be animated.
     */
    private static void startAnimation(BalloonFleet fleet, JLabel label) {
        final int DELAY = 100;
        new Timer(DELAY, event -> {
            fleet.ascend(1); // Ascend at a constant speed
            fleet.applyJitter(); // Apply jitter effect
            label.repaint();
        }).start();
    }

    /**
     * Number of fleets in the mass ascension.
     */
    private static final int NUM_FLEETS = 3;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int ICON_W = screenSize.width;
    private static final int ICON_H = screenSize.height;
}