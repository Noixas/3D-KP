public class Pentomino extends Parcel {
public enum PentominoType {L, P, T};
private PentominoType _type;
private Parcel[][][] _parcels;
private Vector3D _position;
private Parcel _baseParcel;
public Pentomino(PentominoType pType)
{
        super(new Vector3D(0.5,0.5,0.5), 0);
        _baseParcel = new Parcel(new Vector3D(0.5,0.5,0.5), 0);
        _type = pType;
        initForm();
}

private void initForm()
{
        switch(_type)
        {
        case L:
                _parcels = new Parcel[4][1][2];
                _value = 3;
                _baseParcel.setValue(_value/5.0);
                _parcels[0][0][0] = _baseParcel.clone();
                _parcels[1][0][0] = _baseParcel.clone();
                _parcels[2][0][0] = _baseParcel.clone();
                _parcels[3][0][0] = _baseParcel.clone();
                _parcels[3][0][1] = _baseParcel.clone();
                break;
        case P:
                _parcels = new Parcel[3][1][2];
                _value = 4;
                _baseParcel.setValue(_value/5.0);
                _parcels[0][0][0] = _baseParcel.clone();
                _parcels[0][0][1] = _baseParcel.clone();
                _parcels[1][0][0] = _baseParcel.clone();
                _parcels[1][0][1] = _baseParcel.clone();
                _parcels[2][0][0] = _baseParcel.clone();
                break;
        case T:
                _parcels = new Parcel[3][1][3];
                _value = 5;
                _baseParcel.setValue(_value/5.0);
                _parcels[0][0][0] = _baseParcel.clone();
                _parcels[0][0][1] = _baseParcel.clone();
                _parcels[0][0][2] = _baseParcel.clone();
                _parcels[1][0][1] = _baseParcel.clone();
                _parcels[2][0][1] = _baseParcel.clone();
                break;
        }
        _size = new Vector3D(_parcels.length, _parcels[0].length, _parcels[0][0].length);//amount of possible boxes per axis
}
public Parcel[][][] getShapeList()
{
        return _parcels;
}
public double getValue()
{
        return _value;
}
public Pentomino clone()
{
  Pentomino newPentomino = new Pentomino(_type);
  return newPentomino;
}
public void setPosition(Vector3D pPosition)
{
        _position = pPosition;
        /*Vector3D size = _parcels[0][0].getSize();
           for(int i = 0; i < _parcels.length; i++)
           for(int j = 0; j < _parcels[0].length; j++){
            if(_parcels[i][j] != null)
            _parcels[i][j].setPosition(new Vector3D(pPosition.x + (i * size.x), pPosition.y, pPosition.z));
           }*/
}
public static Pentomino getRotated(Pentomino pPentomino, int pID)
{
        Pentomino buffer = pPentomino.clone();
        switch(pID)
        {
        case 0:
                //Just the same
                break;
        case 1:
                rotateParcel(buffer, AlgorithmZ.Axis.X);
                break;
        case 2:
                rotateParcel(buffer, AlgorithmZ.Axis.Y);
                break;
        case 3:
                rotateParcel(buffer, AlgorithmZ.Axis.Y);
                rotateParcel(buffer, AlgorithmZ.Axis.X);
                break;
        case 4:
                rotateParcel(buffer, AlgorithmZ.Axis.Z);
                break;
        case 5:
                rotateParcel(buffer, AlgorithmZ.Axis.Z);
                rotateParcel(buffer, AlgorithmZ.Axis.X);
                break;
        }
        return buffer;
}
/**
 * Rotate a parcel in an axis by 90° degrees.
 * @param Parcel pPentomino [Parcel to be rotated]
 * @param Axis   pAxis   [Axis in which it will be rotated]
 */
public static void rotateParcel(Pentomino pPentomino, AlgorithmZ.Axis pAxis)
{
        Vector3D size = pPentomino.getSize();
        switch(pAxis)
        {
        case X:
                pPentomino.setSize(new Vector3D(size.x, size.z, size.y));
                break;
        case Y:
                pPentomino.setSize(new Vector3D(size.z, size.y, size.x));
                break;
        case Z:
                pPentomino.setSize(new Vector3D(size.y, size.x, size.z));
                break;
        }
}
}
