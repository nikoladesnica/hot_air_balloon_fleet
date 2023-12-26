import java.util.Random;

/**
 * Utility class for generating HotAirBalloon objects with random sizes and positions.
 */
public class RandomBalloonGenerator {

    /**
     * Generates a HotAirBalloon with random size and position.
     * THe random size is limited to 0.5 to 2 times the usual UNIT.
     *
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @return A randomly sized and positioned HotAirBalloon.
     */
    public static HotAirBalloon generateBalloon(int screenWidth, int screenHeight) {
        int balloonSize = random.nextInt(2 * UNIT) + (int)(0.5 * UNIT);
        int x = random.nextInt(screenWidth - balloonSize);
        int y = screenHeight - balloonSize;
        return new HotAirBalloon(x, y, balloonSize);
    }

    private static final Random random = new Random();
    private static final int UNIT = 50;
}