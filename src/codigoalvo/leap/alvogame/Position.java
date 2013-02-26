package codigoalvo.leap.alvogame;

import codigoalvo.gameobject.Screen3DGameObject;


public class Position implements Screen3DGameObject {

	private int x;
	private int y;
	private int z;
	
    public int getX() {
    	return x;
    }
	
    public int getY() {
    	return y;
    }
	
    public int getZ() {
    	return z;
    }

    public void setX(float x) {
    	this.x = (int)x;
    }

    public void setX(int x) {
    	this.x = x;
    }

    public void setY(float y) {
    	this.y = (int)y;
    }

    public void setY(int y) {
    	this.y = y;
    }

    public void setZ(float z) {
    	this.z = (int)z;
    }

    public void setZ(int z) {
    	this.z = z;
    }
	
    @Override
    public String toString() {
    	StringBuilder pos = new StringBuilder();
    	pos.append("x:").append(x);
    	pos.append(", y:").append(y);
    	pos.append(", z:").append(z);
        return pos.toString();
    }

	@Override
    public int getWidth() {
	    return 0;
    }

	@Override
    public int getHeight() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public void setWidth(int width) {
	    // TODO Auto-generated method stub
    }

	@Override
    public void setHeight(int height) {
	    // TODO Auto-generated method stub
    }

	@Override
    public int getDepth() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public void setDepth(int depth) {
	    // TODO Auto-generated method stub
	    
    }
}
