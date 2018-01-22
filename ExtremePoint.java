public class ExtremePoint extends Vector3D {
private Vector3D _residualSpace;   //Residual space
public ExtremePoint(Vector3D pPosition)
{
        super(pPosition);
        _residualSpace = Vector3D.getZero();
}
public ExtremePoint(float x, float y, float z)
{
        super( x,  y,  z);
        _residualSpace = Vector3D.getZero();
}
public ExtremePoint(double x, double y, double z)
{
        super( x,  y,  z);
        _residualSpace = Vector3D.getZero();
}
public ExtremePoint(int x, int y, int z)
{
        super( x,  y,  z);
        _residualSpace = Vector3D.getZero();
}
public Vector3D getRS()
{
        return _residualSpace;
}
public void setRS(Vector3D pRS)
{
        _residualSpace = pRS;
}
@Override
public String toString()
{
        return "Residual Space: " + _residualSpace.toString() + "\n "+ super.toString();
}
}
