import java.util.*;
import java.util.LinkedList;
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


/**
 * Update the EP after a new parcel has been placed by proyection the potential points, eliminating duplicated EP and sorting the new list
 * @param SolutionSet    placedParcels [Parcels already inside the container]
 * @param List<Vector3D> EP            [The current list of EP]
 * @param Parcel         newParcel     [The new parcel that has been added to the container]
 */
public static ExtremePoint[] createNewEps(SolutionSet placedParcels, Parcel newParcel )
{
        double[] maxBound = { -100000, -10000, -1000000, -1000000, -10000, -10000};
        ExtremePoint[] newEP = new ExtremePoint[6];

        Vector3D newParcelPos = newParcel.getPosition();
        Vector3D newParcelSize = newParcel.getSize();
        //Potential points to be projected
        Vector3D X = new Vector3D(newParcelPos.x + newParcelSize.x, newParcelPos.y, newParcelPos.z);
        Vector3D Y = new Vector3D(newParcelPos.x, newParcelPos.y + newParcelSize.y, newParcelPos.z);
        Vector3D Z = new Vector3D(newParcelPos.x, newParcelPos.y, newParcelPos.z + newParcelSize.z);
        for (int i = 0; i < placedParcels.getLength(); i++)
        {
                Parcel placedParcel = placedParcels.get(i);
                Vector3D placedSize = placedParcel.getSize();
                Vector3D placedPos = placedParcel.getPosition();
                //Xy:
                if ((X.x  >= placedPos.x && X.x  < (placedPos.x + placedSize.x)) && (X.z >= placedPos.z && X.z < (placedPos.z + placedSize.z))&&(placedPos.y + placedSize.y > maxBound[0]))
                {
                        newEP[0] = new ExtremePoint(X.x, placedPos.y + placedSize.y, X.z);
                        maxBound[0] = placedPos.y + placedSize.y;
                }
                //Xz:
                if ((X.x >= placedPos.x && X.x < (placedPos.x + placedSize.x)) && (X.y >= placedPos.y && X.y < (placedPos.y + placedSize.y)) && (placedPos.z + placedSize.z > maxBound[1]))
                {
                        newEP[1] = new ExtremePoint(X.x, X.y, placedPos.z + placedSize.z);
                        maxBound[1] = placedPos.z + placedSize.z;
                }
                //Yx:
                if ((Y.y >= placedPos.y && Y.y < (placedPos.y + placedSize.y)) && (Y.z >= placedPos.z && Y.z < (placedPos.z + placedSize.z)) && (placedPos.x + placedSize.x > maxBound[2]))
                {
                        newEP[2] = new ExtremePoint(placedPos.x + placedSize.x, Y.y, Y.z);
                        maxBound[2] = placedPos.x + placedSize.x;
                }
                //Yz:
                if ((Y.y >= placedPos.y && Y.y < (placedPos.y + placedSize.y)) && (Y.x >= placedPos.x && Y.x < (placedPos.x + placedSize.x)) && (placedPos.z + placedSize.z > maxBound[3]))
                {
                        newEP[3] = new ExtremePoint(Y.x, Y.y, placedPos.z + placedSize.z);
                        maxBound[3] = placedPos.z + placedSize.z;
                }
                //Zx:
                if ((Z.z >= placedPos.z && Z.z < (placedPos.z + placedSize.z)) && (Z.y >= placedPos.y && Z.y < (placedPos.y + placedSize.y)) && (placedPos.x + placedSize.x > maxBound[4]))
                {
                        newEP[4] = new ExtremePoint(placedPos.x + placedSize.x, Z.y, Z.z);
                        maxBound[4] = placedPos.x + placedSize.x;
                }
                //Zy:
                if ((Z.z >= placedPos.z && Z.z < (placedPos.z + placedSize.z)) && (Z.x >= placedPos.x && Z.x < (placedPos.x + placedSize.x)) && (placedPos.y + placedSize.y > maxBound[5]))
                {

                        newEP[5] = new ExtremePoint(Z.x, placedPos.y + placedSize.y, Z.z);
                        maxBound[5] = placedPos.y + placedSize.y;
                }
        }
        return newEP;
}
/**
 * Creates the visualization of the Extreme Points in the 3D world
 */
public static void displayExtremePoints(List<ExtremePoint> pListEp)
{
        for(int i = 0; i < pListEp.size(); i++)
        {
                CreateParcel.createSphere(pListEp.get(i));
        }
}
/**
 * Remove elements that are duplicated in the list
 * @param List<Vector3D> ep [List of current ExtremePoints]
 */
public static List<ExtremePoint> removeDuplicatedEP(List<ExtremePoint> pListEp, Parcel p)
{
        Collections.sort(pListEp);
        List<ExtremePoint> newList = new LinkedList<ExtremePoint>();
        newList.add(pListEp.get(0));
        pListEp.remove(0);
        while(pListEp.size() != 0)
        {
                ExtremePoint v = pListEp.get(0);
                if(newList.get(0).equals(v) == false && p.contains(v) == false) //if its not repeated and doesnt overlap a point then add
                        newList.add(v);
                pListEp.remove(0);
        }
        pListEp = newList;
        return pListEp;
}
}
