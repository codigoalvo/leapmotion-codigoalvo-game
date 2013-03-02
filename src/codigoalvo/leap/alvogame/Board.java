package codigoalvo.leap.alvogame;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.Timer;
import codigoalvo.gameobject.*;
import com.leapmotion.leap.Controller;

public class Board extends Frame implements ActionListener {

	private Controller leapController;
	private LeapListener leapListener;
	private Timer drawTimer;
	private Position position = new Position();
	private int frameRate = 50;
	private int cursorSize = 20;
	private int initialObjects = 10;
	private int score = 0;
	private int grabedObjects = 0;
	private boolean spawnByTime = true;
	private boolean spawnByMinimal = true;
	private boolean debug = false;
	private StringBuilder debugMsg = new StringBuilder();
	private Vector<BaseGameObject> gameObjects = new Vector<>();
	private BaseGameObject lastObject;
	private int oopsTimer = 0;
	private long timer = 0;
	private long newObjects = 0;
	private Cursor originalCursor;

	public Board() {
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {}

			public void keyReleased(KeyEvent arg0) {}

			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
					LeapAlvoGame.restoreOriginalDisplayMode();
					System.exit(0);
				}
			}
		});
		for (int i = 0; i < initialObjects; i++) {
			gameObjects.add(new SolidGameObject((int)(Math.random() * 1000), (int)(Math.random() * 500), 30, 30));
		}
		setBackground(Color.BLACK);
		setUndecorated(true);
		drawTimer = new Timer(frameRate, this);
		drawTimer.start();

		leapListener = new LeapListener(position);
		leapController = new Controller();

		leapController.addListener(leapListener);
		originalCursor = getCursor();

		if (leapController.isConnected()) {
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(""), new Point(), null));
		} else {
			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent mouseEv) {
					position.setZ(0);
				}

				@Override
				public void mouseDragged(MouseEvent mouseEv) {
					position.setZ(-1);
					position.setX(mouseEv.getX());
					position.setY(mouseEv.getY());
				}
			});
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		desenha2(g);
		g.dispose();
	}

	private void checaColisoes() {
		if (position.getZ() >= 0) // Do not check for collisions when cursor isn't visible.
			return;

		Rectangle curRect = new Rectangle((int)(position.getX() - cursorSize / 2), (int)(position.getY() - cursorSize / 2), cursorSize,
		                                  cursorSize);

		synchronized (gameObjects) {

			if (lastObject == null) {
				for (BaseGameObject gameObj : gameObjects) {
					if (gameObj.getBounds().intersects(curRect)) {
						lastObject = gameObj;
						lastObject.stop();
						grabedObjects = 1;;
						gameObjects.remove(gameObj);
						break;
					}
				}
			} else {
				for (BaseGameObject gameObj : gameObjects) {
					if (gameObj.getBounds().intersects(lastObject.getBounds())) {
						if (((TipoObjeto)gameObj.getTipo()) == ((TipoObjeto)lastObject.getTipo())) {
							grabedObjects++;
							lastObject.setVisible(false);
							lastObject = gameObj;
							lastObject.stop();
							gameObjects.remove(gameObj);
						} else {
							grabedObjects *= -1;
							oopsTimer++;
							gameObjects.remove(gameObj);
							releaseObject();
						}
						break;
					}
				}
			}
		}
	}

	protected void desenhaCursor(Graphics2D g2d) {
		if (position.getZ() < 0) {
			if (lastObject == null) {
				g2d.setStroke(new BasicStroke(2));
				g2d.setColor(Color.GREEN);
				g2d.drawOval(position.getX(), position.getY(), cursorSize, cursorSize);
			} else {
				lastObject.setX(position.getX() - lastObject.getWidth());
				lastObject.setY(position.getY() - lastObject.getHeight());
				lastObject.drawObject(g2d);
			}
		} else {
			releaseObject();
		}
	}

	public void releaseObject() {
		if (lastObject != null) {
			lastObject.setVisible(false);
			if (grabedObjects < 0)
				grabedObjects *= 5;
			else if (grabedObjects > 3)
				grabedObjects *= 4;
			else if (grabedObjects > 2)
				grabedObjects *= 2;
			score += (100 * (grabedObjects));
			if (score < 0)
				score = 0;
			grabedObjects = 0;
			lastObject = null;
		}
	}

	private void desenhaScore(Graphics2D g2d) {
		String msg = "codigoalvo";
		Font small = new Font("Helvetica", Font.BOLD, 24);
		Font big = new Font("Courier", Font.BOLD, 72);
		FontMetrics metr = this.getFontMetrics(small);
		g2d.setColor(Color.WHITE);
		g2d.setFont(small);
		g2d.drawString(msg, LeapAlvoGame.currentDisplayMode.getWidth() - 50 - metr.stringWidth(msg), 40);
		if (oopsTimer > 20) {
			oopsTimer = 0;
		}
		if (oopsTimer > 0) {
			oopsTimer++;
			g2d.setColor(Color.RED);
		}
		g2d.setFont(big);
		g2d.drawString(String.valueOf(score), 30, 50 + 30);
		if (!leapController.isConnected()) {
			g2d.setColor(Color.RED);
			g2d.setFont(small);
			msg = "LEAP device disconnected!";
			g2d.drawString(msg, LeapAlvoGame.currentDisplayMode.getWidth() - 50 - metr.stringWidth(msg),
			               LeapAlvoGame.currentDisplayMode.getHeight() - 60);
		}

	}
	
	private void desenhaDebugMsg(Graphics2D g2d) {
		Font small = new Font("Helvetica", Font.BOLD, 12);
		g2d.setColor(Color.YELLOW);
		g2d.setFont(small);
		g2d.drawString(debugMsg.toString(), 40, LeapAlvoGame.currentDisplayMode.getHeight() - 60);
		debugMsg.delete(0, debugMsg.length());
	}

	private void desenhaObjetos(Graphics2D g2d) {
		synchronized (gameObjects) {
			for (BaseGameObject gameObj : gameObjects) {
				gameObj.drawObject(g2d);
			}
		}
	}

	protected void desenha2(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		desenhaObjetos(g2d);
		desenhaCursor(g2d);
		desenhaScore(g2d);
		if (debug)
			desenhaDebugMsg(g2d);
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		timer++;
		atualizaObjects();
		if (debug)
			debugCalculaDistancia();
		checaColisoes();
		repaint();
		newObjects();
	}

	private void atualizaObjects() {
		synchronized (gameObjects) {
			for (BaseGameObject gameObj : gameObjects) {
				gameObj.move();
				if (gameObj.getX() < 0 ||
					(gameObj.getX() + gameObj.getWidth()) > LeapAlvoGame.currentDisplayMode.getWidth())
					gameObj.setSpeedX(gameObj.getSpeedX() * -1);

				if (gameObj.getY() < 0 ||
					(gameObj.getY() + gameObj.getHeight()) > LeapAlvoGame.currentDisplayMode.getHeight())
					gameObj.setSpeedY(gameObj.getSpeedY() * -1);
			}
		}
	}
	
	private void debugCalculaDistancia() {
		synchronized (gameObjects) {
			for (int i = 0; i < gameObjects.size(); i++) {
				BaseGameObject gameObj1 = gameObjects.get(i);
				for (int j = i+1; j < gameObjects.size(); j++) {
					BaseGameObject gameObj2 = gameObjects.get(j);
					int dist = gameObj1.distanceTo(gameObj2);
					if (debugMsg.length() > 0)
						debugMsg.append("   |   ");
					debugMsg.append(gameObj1.toString()).append(" -> ").append(gameObj2.toString()).append(" = ").append(dist);
				}
			}
		}
	}

	private void newObjects() {
		boolean drawNewObjects = false;
		if (spawnByTime  &&  timer > (newObjects + 200)) {
			newObjects = timer;
			drawNewObjects = true;
		}
		if (spawnByMinimal) {
			int minObjs = (int)(Math.random() * 5) + 1;
			if (gameObjects.size() < minObjs) {
				drawNewObjects = true;
			}
		}
		if (drawNewObjects) {
			int qtdNewObjs = (int)(Math.random() * 9) + 1;
			synchronized (gameObjects) {
				for (int i = 0; i < qtdNewObjs; i++) {
					gameObjects.add(new SolidGameObject((int)(Math.random() * 1000), (int)(Math.random() * 500), 30, 30));
				}
			}
		}
	}

}
