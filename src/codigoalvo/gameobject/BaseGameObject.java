package codigoalvo.gameobject;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class BaseGameObject implements Screen2DGameObject, Moveable2DGameObject, ViewableGameObject {

	public BaseGameObject() {
		super();
	}

	public abstract Rectangle2D getBounds();
	
	public abstract Enum getTipo();

	public abstract void drawObject(Graphics graphics);

	public void printPosition() {
		StringBuilder pos = new StringBuilder();
		pos.append("x: ").append(x).append(", y:").append(y);
		System.out.println(pos.toString());
	}

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
		speedX = (neg?speedX*-1:speedX); //deixa positivo para fazer comparações
		if (speedX > this.maxSpeedX)
			this.speedX = this.maxSpeedX;
		else if (speedX < this.minSpeedX)
			this.speedX = this.minSpeedX; // volta para negativo caso era negativo originalmente
		else
			this.speedX = speedX;
		this.speedX = (neg?this.speedX*-1:this.speedX);
	}

	public void setSpeedY(int speedY) {
		boolean neg = speedY < 0;
		speedY = (neg?speedY*-1:speedY); //deixa positivo para fazer comparações
		if (speedY > this.maxSpeedY)
			this.speedY = this.maxSpeedY;
		else if (speedY < this.minSpeedY)
			this.speedY = this.minSpeedY;
		else
			this.speedY = speedY;
		this.speedY = (neg?this.speedY*-1:this.speedY); // volta para negativo caso era negativo originalmente
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

    public void stop() {
    	this.speedX = 0;
    	this.speedY = 0;
    }

}
