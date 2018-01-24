/**
 * This class creates parcel objects.
 */
public class Parcel {
protected Vector3D _position;
protected Vector3D _size;
protected double _value;
protected double _volume;
protected double _densityValue;
protected boolean _invisible = false;

/**
 * Parcel construtor where size and value is given.
 * @param pSize Size of the parcel.
 * @param pValue Value of the parcel.
 */
public Parcel(Vector3D pSize, double pValue)
{
        _size = pSize;
        _value = pValue;
        _volume = _size.x*_size.y*_size.z;
        _densityValue = _value / _volume;
}

/**
 * Sets the position of the parcel.
 * @param newPos Position for the parcel.
 */
public void setPosition(Vector3D newPos)
{
        _position = newPos;
}

/**
 * Gets the position of the parcel.
 * @return The parcel's position.
 */
public Vector3D getPosition()
{
        return _position;
}

/**
 * Sets the size of the parcel.
 * @param newSize The new size for the parcel.
 */
public void setSize(Vector3D newSize)
{
        _size = newSize;
        updateValues();
}

/**
 * Sets the parcel a new value.
 * @param newValue The new value for the parcel.
 */
public void setValue(double newValue) {
        _value = newValue;
        updateValues();
}

/**
 * Gets the size of the parcel.
 * @return The parcel's size.
 */
public Vector3D getSize()
{
        return _size;
}

/**
 * Gets the value of the parcel.
 * @return The parcel's value.
 */
public double getValue()
{
        return _value;
}

/**
 * Gets the volume of the parcel.
 * @return The parcel's volume.
 */
public double getVolume()
{
        return _volume;
}

/**
 * Sets whether the parcel is invisible or not.
 * @param b Boolean to set the parcel's invisibility.
 */
public void setInvisible(boolean b)
{
        _invisible = b;
}

/**
 * Gets whether the parcel is invisible.
 * @return Boolean whether the parcel is invisible or not.
 */
public boolean getInvisible()
{
        return _invisible;
}

/**
 * Gets the density value of the parcel.
 * @return The parcel's value density.
 */
public double getDensityValue()
{
        return _densityValue;
}

/**
 * Updates the volume and density value of the parcel.
 */
private void updateValues()
{
        _volume = _size.x*_size.y*_size.z;
        _densityValue = _value / _volume;
}

/**
 * Check if a vector v is inside the parcel
 * @param Vector3D v Vector to be checked
 * @return True if the vector point is inside the parcel
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
                ParcelA clone = new ParcelA();
                clone.setSize(this._size.clone());
                clone.setValue(this._value);
                return clone;
        } else if(this instanceof ParcelB) {
                ParcelB clone = new ParcelB();
                clone.setSize(this._size.clone());
                clone.setValue(this._value);
                return clone;
        } else if(this instanceof ParcelC) {
                ParcelC clone = new ParcelC();
                clone.setSize(this._size.clone());
                clone.setValue(this._value);
                return clone;
        } else {
                return new Parcel(_size.clone(), _value);
        }
}

/**
 * Returns the parcel's size and position as a string.
 * @return The size and the position of the parcel as a string.
 */
public String toString() {
        return getClass() + ": [Size = " + getSize() + "] [Position = " + getPosition() + "]";
}
public static Parcel getRotated(Parcel pParcel, int pID)
{
        Parcel buffer = pParcel.clone();
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
 * @param Parcel pParcel [Parcel to be rotated]
 * @param Axis   pAxis   [Axis in which it will be rotated]
 */
public static void rotateParcel(Parcel pParcel, AlgorithmZ.Axis pAxis)
{
        Vector3D size = pParcel.getSize();
        switch(pAxis)
        {
        case X:
                pParcel.setSize(new Vector3D(size.x, size.z, size.y));
                break;
        case Y:
                pParcel.setSize(new Vector3D(size.z, size.y, size.x));
                break;
        case Z:
                pParcel.setSize(new Vector3D(size.y, size.x, size.z));
                break;
        }
}

}
