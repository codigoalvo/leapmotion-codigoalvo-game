package codigoalvo.gameobject;


public interface Moveable2DGameObject extends Screen2DGameObject{
	
	public int getMinSpeedX();
	public int getMaxSpeedX();
	public int getMinSpeedY();
	public int getMaxSpeedY();
	public int getSpeedX();
	public int getSpeedY();
	public void setMinSpeed(int minSpeed);
	public void setMaxSpeed(int maxSpeed);
	public void setMinSpeedX(int minSpeedX);
	public void setMaxSpeedX(int maxSpeedX);
	public void setMinSpeedY(int minSpeedY);
	public void setMaxSpeedY(int maxSpeedY);
	public void setSpeedX(int speedX);
	public void setSpeedY(int speedY);
	public void move();
	public void stop();
}
