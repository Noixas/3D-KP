/**
 * Class which creates Vector3D objects to represent the parcels' sizes and positions.
 */
public class Vector3D implements Comparable<Vector3D> {
public double x;
public double y;
public double z;
//  public static final Vector3D zero = new Vector3D(0,0,0);

/**
 * Constructor of Vector3D using floats.
 * @param x Vector value in the x axis.
 * @param y Vector value in the y axis.
 * @param z Vector value in the z axis.
 */
public Vector3D(float x, float y, float z)
{
        this.x = x;
        this.y = y;
        this.z = z;
}

/**
 * Constructor of Vector3D using doubles.
 * @param x Vector value in the x axis.
 * @param y Vector value in the y axis.
 * @param z Vector value in the z axis.
 */
public Vector3D(double x, double y, double z)
{
        this.x = x;
        this.y = y;
        this.z = z;
}

/**
 * Constructor of Vector3D using ints.
 * @param x Vector value in the x axis.
 * @param y Vector value in the y axis.
 * @param z Vector value in the z axis.
 */
public Vector3D(int x, int y, int z)
{
        this.x = x;
        this.y = y;
        this.z = z;
}

/**
 * Copy the vector values from another Vector3D object to this one.
 * @param copy The other Vector3D object to copy the value from.
 */
public Vector3D(Vector3D copy)
{
        this.x = copy.x;
        this.y = copy.y;
        this.z = copy.z;
}

/**
 * Clones the Vector3D object.
 * @param the new cloned Vector3D object.
 */
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
        if(x == other.x && y == other.y && z == other.z) return true;
        else return false;
}
public Vector3D substract(Vector3D o)
{
        return new Vector3D(this.x - o.x, this.y - o.y, this.z - o.z);
}
@Override
public int compareTo(Vector3D o) {

        if (x > o.x) {
                return 1;
        } else if (x == o.x) {
                if (y > o.y) {
                        return 1;
                } else if (y == y) {
                        if (z > o.z) {
                                return 1;
                        } else if (z == o.z) {
                                return 0;
                        } else {
                                return -1;
                        }
                } else {
                        return -1;
                }
        } else {
                return -1;
        }
}
@Override
public String toString()
{
        return "Vector: x: "+ x + " y: " + y + " z: " + z;
}
public static Vector3D getZero()
{
        return new Vector3D(0,0,0);
}

}
