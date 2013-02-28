package codigoalvo.leap.alvogame;

import java.awt.*;
import java.util.ArrayList;

public class LeapAlvoGame {

	private static java.util.List<DisplayMode> MODES = new ArrayList<>();
	static {
		// 16::9 resolutions
		MODES.add(new DisplayMode(1600, 900, 32, 0));
		MODES.add(new DisplayMode(1360, 768, 32, 0));
		MODES.add(new DisplayMode(1280, 720, 32, 0));
		// 4:3 resolutions
		MODES.add(new DisplayMode(1024, 768, 32, 0));
		MODES.add(new DisplayMode(800, 600, 32, 0));
	}
	private static DisplayMode originalDisplayMode;
	public static DisplayMode currentDisplayMode;
	private static GraphicsDevice graphicsDevice;

	private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
		for (DisplayMode mode : MODES) {
			DisplayMode[] modes = device.getDisplayModes();
			for (int i = 0, in = modes.length; i < in; i++) {
				if (modes[i].getWidth() == mode.getWidth() && modes[i].getHeight() == mode.getHeight() && modes[i].getBitDepth() == mode.getBitDepth()) {
					currentDisplayMode = mode;
					// Adiciona uma margem de 5% de pixels em cada borda da tela.
					int percX = mode.getWidth()/20;
					int percY = mode.getHeight()/20;
					LeapListener.SCREEN_MIN_X = (0 - percX);
					LeapListener.SCREEN_MIN_Y = (0 - percY);
					LeapListener.SCREEN_MAX_X = (mode.getWidth()+percX);
					LeapListener.SCREEN_MAX_Y = (mode.getHeight()+percY);
					return currentDisplayMode;
				}
			}
		}
		return null;
	}

	public LeapAlvoGame() {

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		originalDisplayMode = graphicsDevice.getDisplayMode();
		currentDisplayMode = originalDisplayMode;
		Board mainScreen = new Board();
		graphicsDevice.setFullScreenWindow(mainScreen);
		if (graphicsDevice.isDisplayChangeSupported()) {
			graphicsDevice.setDisplayMode(getBestDisplayMode(graphicsDevice));
		}
		mainScreen.createBufferStrategy(2);
	}

	public static void restoreOriginalDisplayMode() {
		graphicsDevice.setDisplayMode(originalDisplayMode);
		currentDisplayMode = originalDisplayMode; 
	}

	public static void main(String[] args) {
		new LeapAlvoGame();
	}

}
