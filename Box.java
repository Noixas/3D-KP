public class Box{
  private Vector3D _position;
  private Vector3D _size;
  private double _value;
  private double _volume;
  private double _densityValue;
  public Box(Vector3D pSize, double pValue)
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
    Clone box without position parameter
    @return this box clone without position
   */
  public Box clone()
  {
    return new Box(_size.clone(), _value);
  }

}
