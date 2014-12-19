package codigoalvo.gameobject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class BaseGameObject implements Screen2DGameObject, Moveable2DGameObject, ViewableGameObject, RelativeGameObject {

	public BaseGameObject() {
		super();
		id = ++CTD;
	}

	public static int CTD = 0;

	public abstract Enum getTipo();

	public abstract void drawObject(Graphics graphics);

	protected int id;
	protected int minSpeedX;
	protected int maxSpeedX;
	protected int minSpeedY;
	protected int maxSpeedY;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int speedX;
	protected int speedY;
	protected boolean visible;
	protected boolean solid;

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSpeedX(int speedX) {
		boolean neg = speedX < 0;
		speedX = (neg ? speedX * -1 : speedX); // deixa positivo para fazer comparações
		if (speedX > this.maxSpeedX)
			this.speedX = this.maxSpeedX;
		else if (speedX < this.minSpeedX)
			this.speedX = this.minSpeedX; // volta para negativo caso era negativo originalmente
		else
			this.speedX = speedX;
		this.speedX = (neg ? this.speedX * -1 : this.speedX);
	}

	public void setSpeedY(int speedY) {
		boolean neg = speedY < 0;
		speedY = (neg ? speedY * -1 : speedY); // deixa positivo para fazer comparações
		if (speedY > this.maxSpeedY)
			this.speedY = this.maxSpeedY;
		else if (speedY < this.minSpeedY)
			this.speedY = this.minSpeedY;
		else
			this.speedY = speedY;
		this.speedY = (neg ? this.speedY * -1 : this.speedY); // volta para negativo caso era negativo originalmente
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void move() {
		this.x += this.speedX;
		this.y += this.speedY;
	}

	public int getMinSpeedX() {
		return minSpeedX;
	}

	public int getMaxSpeedX() {
		return maxSpeedX;
	}

	public int getMinSpeedY() {
		return minSpeedY;
	}

	public int getMaxSpeedY() {
		return maxSpeedY;
	}

	public void setMinSpeed(int minSpeed) {
		this.minSpeedX = minSpeed;
		this.minSpeedY = minSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeedX = maxSpeed;
		this.maxSpeedY = maxSpeed;
	}

	public void setMinSpeedX(int minSpeedX) {
		this.minSpeedX = minSpeedX;
	}

	public void setMaxSpeedX(int maxSpeedX) {
		this.maxSpeedX = maxSpeedX;
	}

	public void setMinSpeedY(int minSpeedY) {
		this.minSpeedY = minSpeedY;
	}

	public void setMaxSpeedY(int maxSpeedY) {
		this.maxSpeedY = speedY;
	}

	public void incrementSpeedX(int incSpeedX) {
	    setSpeedX(getSpeedX()+incSpeedX);
	}

	public void incrementSpeedY(int incSpeedY) {
	    setSpeedY(getSpeedY()+incSpeedY);
	}

	public void stop() {
		this.speedX = 0;
		this.speedY = 0;
	}

	public Rectangle2D getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void printPosition() {
		StringBuilder pos = new StringBuilder();
		pos.append("x: ").append(x).append(", y:").append(y);
		System.out.println(pos.toString());
	}

	public int distanceTo(Screen2DGameObject otherObject) {
		int xA = this.getX()+(int)(this.getWidth()/2);
		int xB = otherObject.getX()+(int)(otherObject.getWidth()/2);
		int yA = this.getY()+(int)(this.getHeight()/2);
		int yB = otherObject.getY()+(int)(otherObject.getHeight()/2);
		return (int)Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
	}

}
