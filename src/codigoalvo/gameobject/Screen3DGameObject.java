package codigoalvo.gameobject;


public interface Screen3DGameObject extends Screen2DGameObject {
	public int getZ();
	public int getDepth();
	public void setZ(int z);
	public void setDepth(int depth);
	
}
