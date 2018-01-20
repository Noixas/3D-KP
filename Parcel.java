public class Parcel{
  protected Vector3D _position;
  protected Vector3D _size;
  protected double _value;
  protected double _volume;
  protected double _densityValue;
  protected boolean _invisible = false;
  public Parcel(Vector3D pSize, double pValue)
  {
    _size = pSize;
    _value = pValue;
    _volume = _size.x*_size.y*_size.z;
    _densityValue = _value / _volume;
  }
  public void setPosition(Vector3D newPos)
  {
    _position = newPos;
  }
  public Vector3D getPosition()
  {
    return _position;
  }
  public void setSize(Vector3D newSize)
  {
    _size = newSize;
  }
  public void setValue(double newValue) {
    _value = newValue;
  }
  public Vector3D getSize()
  {
    return _size;
  }
  public double getValue()
  {
    return _value;
  }
  public double getVolume()
  {
    return _volume;
  }
  public void setInvisible(boolean b)
  {
    _invisible = b;
  }
  public boolean getInvisible()
  {
    return _invisible;
  }
  public double getDensityValue()
  {
    return _densityValue;
  }
  /**
   * Check if a vector v is inside the parcel
   * @param  Vector3D v             Vector to be checked
   * @return          [True if the vector point is inside the parcel]
   */
  public boolean contains(Vector3D v)
  {
    if(_position.x > v.x || v.x >= _position.x + _size.x) return false;
    else if(_position.y > v.y || v.y >= _position.y + _size.y) return false;
    else if(_position.z > v.z || v.z >= _position.z + _size.z) return false;
    else return true;

}
/**
   Clone Parcel without position parameter
   @return this Parcel clone without position
 */
public Parcel clone()
{
        if(this instanceof ParcelA) {
                return new ParcelA();
        } else if(this instanceof ParcelB) {
                return new ParcelB();
        } else if(this instanceof ParcelC) {
                return new ParcelC();
        } else {
                return new Parcel(_size.clone(), _value);
        }
}
public String toString() {
        return getClass() + ": [Size = " + getSize() + "] [Position = " + getPosition() + "]";
}

}
