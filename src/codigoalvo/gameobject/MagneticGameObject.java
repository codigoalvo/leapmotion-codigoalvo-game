package codigoalvo.gameobject;

public interface MagneticGameObject {
    public int getMagneticForce();
    public void setMagneticForce(int magneticForce);
    public int getMagneticRadius();
    public void setMagneticRadius(int magneticRadius);
    public boolean isForceAffectable(Screen2DGameObject screen2DGameObject);
    public void applyForce(Moveable2DGameObject moveable2DGameObject);
}
