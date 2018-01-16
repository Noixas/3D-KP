public class Parcel{
  protected Vector3D _position;
  protected Vector3D _size;
  protected double _value;
  protected double _volume;
  protected double _densityValue;
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
  public double getDensityValue()
  {
    return _densityValue;
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
