package codigoalvo.gameobject;

public class MagneticFieldGameObject implements MagneticGameObject, Screen2DGameObject, RelativeGameObject {

	public MagneticFieldGameObject(int centerX, int centerY, int magneticForce, int magneticRadius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.magneticForce = magneticForce;
		this.magneticRadius = magneticRadius;
	}

	private int magneticForce;
	private int magneticRadius;
	private int centerX;
	private int centerY;

	public int getMagneticRadius() {
		return magneticRadius;
	}

	public void setMagneticRadius(int magneticRadius) {
		this.magneticRadius = magneticRadius;
	}

	public int getMagneticForce() {
		return this.magneticForce;
	}

	public void setMagneticForce(int magneticForce) {
		this.magneticForce = magneticForce;
	}

	public boolean isForceAffectable(Screen2DGameObject screen2DGameObject) {
		if (distanceTo(screen2DGameObject) < getMagneticRadius())
			return true;
		return false;
	}

	public void applyForce(Moveable2DGameObject moveable2dGameObject) {
		if (isForceAffectable(moveable2dGameObject)) {
			int force = getMagneticForce();
			if (moveable2dGameObject.getX() > getX())
				force *= -1;
			moveable2dGameObject.incrementSpeedX(force);
			force = getMagneticForce();
			if (moveable2dGameObject.getY() > getY())
				force *= -1;
			moveable2dGameObject.incrementSpeedY(force);
		}
	}

	public int getX() {
		return this.centerX;
	}

	public int getY() {
		return this.centerY;
	}

	public int getWidth() {
		return this.magneticRadius * 2;
	}

	public int getHeight() {
		return this.magneticRadius * 2;
	}

	public void setX(int centerX) {
		this.centerX = centerX;
	}

	public void setY(int centerY) {
		this.centerY = centerY;
	}

	public void setWidth(int width) {
		this.magneticRadius = (int)(width / 2);
	}

	public void setHeight(int height) {
		this.magneticRadius = (int)(height / 2);
	}

	public int distanceTo(Screen2DGameObject otherObject) {
		int xA = this.getX();
		int xB = otherObject.getX() + (int)(otherObject.getWidth() / 2);
		int yA = this.getY();
		int yB = otherObject.getY() + (int)(otherObject.getHeight() / 2);
		return (int)Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
	}

}
