import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A slider for controlling the jitter effect of a group of HotAirBalloons.
 * This slider adjusts the horizontal movement intensity of the balloons to simulate a jitter effect.
 *
 * @author Nikola Desnica (ndd2131)
 * @version 5
 */
public class JitterSlider extends JSlider implements ChangeListener {

    /**
     * Constructs a JitterSlider to control the jitter effect on a given Ascension group.
     *
     * @param ascensionGroup The Ascension group to be controlled by this slider.
     */
    public JitterSlider(Ascension ascensionGroup) {
        this.ascensionGroup = ascensionGroup;
        setupSlider();
    }

    /**
     * Initializes the slider with its properties.
     */
    private void setupSlider() {
        setOrientation(HORIZONTAL);
        setMinimum(0); // Minimum jitter (none)
        setMaximum(10); // Maximum jitter effect
        setValue(0); // Starting with no jitter
        setMajorTickSpacing(2);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(this);
    }

    /**
     * Responds to slider value changes and updates the jitter intensity of the controlled Ascension group.
     *
     * @param e The event that characterizes the change.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (ascensionGroup instanceof JitterControl) {
            int jitterIntensity = getValue();
            ((JitterControl) ascensionGroup).updateJitterIntensity(jitterIntensity);
        }
    }

    // Fields
    private Ascension ascensionGroup;
}

/**
 * Interface for jitter control in balloon groups, allowing dynamic adjustment of horizontal movement intensity.
 */
interface JitterControl {
    /**
     * Updates the jitter intensity for the group.
     *
     * @param intensity The new jitter intensity level.
     */
    void updateJitterIntensity(int intensity);
}