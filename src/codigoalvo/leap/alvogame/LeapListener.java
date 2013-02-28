package codigoalvo.leap.alvogame;

import java.util.Date;
import com.leapmotion.leap.*;

public class LeapListener extends Listener {

	public static final int LEAP_MIN_X = -120;
	public static final int LEAP_MAX_X = 120;
	public static final int LEAP_MIN_Y = 100;
	public static final int LEAP_MAX_Y = 250;

	public static int SCREEN_MIN_X = 0;
	public static int SCREEN_MAX_X = 0;
	public static int SCREEN_MIN_Y = 0;
	public static int SCREEN_MAX_Y = 0;

	private boolean debug = false;

	private Position position;
	private final int minFPS = 50;
	private long lastFPS = 0;

	private LeapListener() {
		super();
	}

	public LeapListener(Position position) {
		this();
		this.position = position;
	}

	public void onInit(Controller controller) {
		if (debug)
			System.out.println("Initialized");
	}

	public void onConnect(Controller controller) {
		if (debug) {
			System.out.println("Connected");
			if (controller.calibratedScreens().count() > 0) {
				System.out.println("Screens: " + controller.calibratedScreens().count());
				System.out.println("Screen (0); Height: " + controller.calibratedScreens().get(0).heightPixels() + " Width: "
				        + controller.calibratedScreens().get(0).widthPixels());
			}
		}
	}

	public void onDisconnect(Controller controller) {
		if (debug)
			System.out.println("Disconnected");
	}

	public void onExit(Controller controller) {
		if (debug)
			System.out.println("Exited");
	}

	/**
	 * 
	 * @param x
	 * @return f(x)
	 *         Thanks to my teacher Dariel Mazzoni Maranhão for the help with proportion math calc.
	 */
	private int converteX(float x) {
		float result = (((SCREEN_MAX_X - SCREEN_MIN_X) * x) / (LEAP_MAX_X - LEAP_MIN_X)) - LEAP_MIN_X * (SCREEN_MAX_X - SCREEN_MIN_X)
		        / (LEAP_MAX_X - LEAP_MIN_X) + SCREEN_MIN_X;
		return (int)result;
	}

	/**
	 * 
	 * @param y
	 * @return f(y)
	 *         Thanks to my teacher Dariel Mazzoni Maranhão for the help with proportion math calc.
	 */
	private int converteY(float y) {
		float result = (((SCREEN_MAX_Y - SCREEN_MIN_Y) * y) / (LEAP_MIN_Y - LEAP_MAX_Y)) - LEAP_MAX_Y * (SCREEN_MAX_Y - SCREEN_MIN_Y)
		        / (LEAP_MIN_Y - LEAP_MAX_Y) + SCREEN_MIN_Y;
		return (int)result;
	}

	public void onFrame(Controller controller) {

		// Minimal FPS control
		long actualFPS = new Date().getTime();
		if (actualFPS < lastFPS + minFPS)
			return;
		lastFPS = actualFPS;

		// Get the most recent frame and report some basic information
		Frame frame = controller.frame();
		if (debug) {
			System.out.println("Frame id: " + frame.id() + ", timestamp: " + frame.timestamp() + ", hands: " + frame.hands().count()
			        + ", fingers: " + frame.fingers().count() + ", tools: " + frame.tools().count());
		}

		if (!frame.hands().empty()) {
			// Get the first hand
			Hand hand = frame.hands().get(0);

			// Check if the hand has one fingers
			FingerList fingers = hand.fingers();
			// if (debug && !fingers.empty()) {
			// Vector avgPos = Vector.zero();
			// for (Finger finger : fingers) {
			// avgPos = avgPos.plus(finger.tipPosition());
			// }
			// avgPos = avgPos.divide(fingers.count());
			// System.out.println("Hand has " + fingers.count() + " fingers, average finger tip position: " + avgPos);
			// }

			if (fingers.count() == 1) {
				Finger finger = fingers.get(0);
				Vector fingerPos = finger.tipPosition();
				int xPos = converteX(fingerPos.getX());
				int yPos = converteY(fingerPos.getY());
				if (debug) {
					System.out.println("Vector: x:" + fingerPos.getX() + ", y:" + fingerPos.getY() + ", z:" + fingerPos.getZ()
					        + " Posicao: " + position.toString());
				}
				synchronized (position) {
					position.setX(xPos);
					position.setY(yPos);
					position.setZ(fingerPos.getZ());
				}
				return;
			}
		}
		synchronized (position) {
			position.setZ(0);
		}
	}
}
