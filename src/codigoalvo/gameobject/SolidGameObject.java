package codigoalvo.gameobject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import codigoalvo.leap.alvogame.TipoObjeto;

public class SolidGameObject extends BaseGameObject {

	private Color color;
	private TipoObjeto tipoObjeto;
	
	public TipoObjeto getTipo() {
		return tipoObjeto;
	}

	public SolidGameObject(int x, int y, int width, int height) {
		super();
		double corNumFloat = 1+(Math.random()*6);
		int corNum = (int)Math.round(corNumFloat);
		tipoObjeto = TipoObjeto.objetoByNum(corNum);
		color = TipoObjeto.colorByTipo(tipoObjeto);
//		color = Color.getHSBColor((float)(Math.random() * 255) / 100, 0.5f, 0.5f);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setSolid(true);
		setMinSpeed(2);
		setMaxSpeed(10);
		setSpeedX(1 + (int)(Math.random() * 5));
		setSpeedY(1 + (int)(Math.random() * 5));
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void drawObject(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		// g2d.setColor(Color.BLUE);
		g2d.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
	}

	@Override
	public String toString() {
		StringBuilder resp = new StringBuilder();
		resp.append("x:").append(getX()).append(", y:").append(getY()).append(", spX:").append(getSpeedX()).append(", spY:").append(getSpeedY());
		return resp.toString();
	}

}
