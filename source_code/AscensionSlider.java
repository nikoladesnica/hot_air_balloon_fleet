import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

/**
 * A slider for controlling the ascent speed of a group of HotAirBalloons.
 *
 * @author Nikola Desnica (ndd2131)
 * @version 5
 */
public class AscensionSlider extends JSlider implements ChangeListener {
    /**
     * Constructs an AscensionSlider to control the ascent speed of multiple Ascension groups.
     *
     * @param ascensionGroups The list of Ascension groups to be controlled by this slider.
     */
    public AscensionSlider(List<Ascension> ascensionGroups) {
        this.ascensionGroups = ascensionGroups;
        setupSlider();
    }

    /**
     * Initializes the slider with its properties.
     */
    private void setupSlider() {
        setOrientation(VERTICAL);
        setMinimum(-10); // Minimum speed (descending)
        setMaximum(10);  // Maximum speed (ascending)
        setValue(0);     // Starting at 0 (stationary)
        setMajorTickSpacing(5);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(this);
    }

    /**
     * Responds to slider value changes and updates the ascent speed of all controlled Ascension groups.
     *
     * @param e The event that characterizes the change.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        int speed = getValue();
        for (Ascension group : ascensionGroups) {
            group.updateSpeed(speed);
        }
    }

    /**
     * The collection of BalloonFleet groups.
     */
    private List<Ascension> ascensionGroups;
}
