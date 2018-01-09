public class Vector3D{
  public double x;
  public double y;
  public double z;
  public Vector3D(float x, float y, float z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public Vector3D(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public Vector3D(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public Vector3D(Vector3D copy)
  {
    this.x = copy.x;
    this.y = copy.y;
    this.z = copy.z;
  }
  public Vector3D clone()
  {
    return new Vector3D(this);
  }
  @Override
  public boolean equals(Object o)
  {
    if(o == null) return false;
    if(o == this) return true;
    if(o instanceof Vector3D == false) return false;
    Vector3D other = (Vector3D) o;
    if(x == other.x && y == other.y && z == other.z)  return true;
    else return false;
  }
}
