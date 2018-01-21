import java.util.*;
import java.util.LinkedList;
import java.util.Collections;
import java.lang.Exception;
import  java.lang.Math.*;
public class AlgorithmZ extends Algorithm
{
private Container _container;
private SolutionSet _solution;
private List<ExtremePoint> _listEP;
private boolean _started;
private List<Parcel> _baseParcels;
private Random rnd;
private Parcel[][][] _containerSpace;
private int _scalingArrayConst = 2;
private int _wallsCount;
private enum SetType { A, B, C, AB, AC, BC, ABC, BEST, RANDOM, DEBUG }
private enum Axis { X, Y, Z }
private SetType _type;
////////Temporary container boundaries from c#///////////
public double xBound = 16.5;
public double yBound = 2.5;
public double zBound = 4;
///////////////////////////////////////
public AlgorithmZ(){
        rnd = new Random();
        _type = SetType.A;
        _container = new Container();
        xBound = _container.getSize().x;
        yBound = _container.getSize().y;
        zBound = _container.getSize().z;
        _containerSpace = new Parcel[spaceIndex(xBound)][spaceIndex(yBound)][spaceIndex(zBound)];
        _started = false;
        _listEP = new LinkedList<ExtremePoint>();
        _solution = new SolutionSet(0);
}
/**
 * Method to be called from the UI to start calculating a solution
 */
public void Start(){
        _type = SetType.DEBUG;

        initSetType();
        createContainerWalls();
        //computeSolution(10);
        debugTest();
        displayExtremePoints();

}
private void debugTest()
{
        Parcel test = _baseParcels.get(0);
        rotateParcel(test, Axis.Z);
        computeSolutionStep();
}
/**
 * Reorders the _baseParcels depending on the kind of set that we want to compute
 */
private void initSetType()
{
        if(_started) return; //Dont initiate multiple times
        Parcel a = new ParcelA();
        Parcel b = new ParcelB();
        Parcel c = new ParcelC();
        _baseParcels = new LinkedList<Parcel>();

        switch(_type) {
        case A:
                _baseParcels.add(a);
                break;
        case B:
                _baseParcels.add(b);
                break;
        case C:
                _baseParcels.add(c);
                break;
        case AB:
                if(a.getDensityValue() >= b.getDensityValue()) {//Put parcel a first
                        _baseParcels.add(a);
                        _baseParcels.add(b);
                }
                else{//Put parcel b first
                        _baseParcels.add(b);
                        _baseParcels.add(a);
                }
                break;
        case AC:
                if(a.getDensityValue() >= c.getDensityValue()) {//Put parcel a first
                        _baseParcels.add(a);
                        _baseParcels.add(c);
                }
                else{//Put parcel c first
                        _baseParcels.add(c);
                        _baseParcels.add(a);
                }
                break;
        case BC:
                if(b.getDensityValue() >= c.getDensityValue()) {//Put parcel a first
                        _baseParcels.add(b);
                        _baseParcels.add(c);
                }
                else{//Put parcel b first
                        _baseParcels.add(c);
                        _baseParcels.add(b);
                }
                break;
        case BEST://TODO
                _baseParcels.add(a);
                _baseParcels.add(b);
                _baseParcels.add(c);
                break;
        case RANDOM:
                _baseParcels.add(a);
                _baseParcels.add(b);
                _baseParcels.add(c);
                _baseParcels = randomizeBaseParcelList();
                break;
        case DEBUG:
                _baseParcels.add(a);
                break;

        }
}
/**
 * Creates the invisible container walls of the container in which the EP will be proyected
 */
private void createContainerWalls()
{
        _wallsCount = 3;
        for(int i = 0; i < _wallsCount; i++)
        {
                Parcel c = new Parcel(new Vector3D(0,0,0), 0);
                c.setInvisible(true);
                Vector3D pos = Vector3D.getZero();
                Vector3D size = Vector3D.getZero();
                switch (i)
                {
                case 0:  //Negative X
                        size.y = 100;
                        size.z = 100;
                        pos.x = -size.x;
                        break;
                case 1:   //Negative Y
                        size.x = 100;
                        size.z = 100;
                        pos.y = -size.y;
                        break;
                case 2:  //Negative Z
                        size.x = 100;
                        size.y = 100;
                        pos.z = -size.z;
                        break;
                }
                c.setSize(size);
                c.setPosition(pos);
                _solution.addParcel(c);
                //  CreateParcel.createParcel(c);
        }
}
/**
 * Calls the step method n times to enable faster results
 * @param int pIterations [Amount of times that step method will be called]
 */
private void computeSolution(int pIterations)
{
        if(pIterations <= 0) return;
        for(int i = 0; i < pIterations; i++)
        {
                computeSolutionStep();
        }
}
/**
 * Computes the parcel and position at which the next one will be placed
 */
private void computeSolutionStep()
{
        if (_started == false) insertFirstParcel();
        else {
                List<Parcel> randomList = randomizeBaseParcelList();
                if(_type != SetType.RANDOM) randomList = _baseParcels; //comment this for random
                List<ExtremePoint> toDeleteEp = new LinkedList<ExtremePoint>();
                for (int i = 0; i < _listEP.size(); i++) {
                        ExtremePoint pos = _listEP.get(i);
                        //  pos = findBestEP();
                        if (pos.x + 1 < xBound && pos.y + 1 < yBound && pos.z + 1 < zBound ) {//container boundaries
                                for(int j = 0; j < _baseParcels.size(); j++) {
                                        //int s = rnd.nextInt(_baseParcels.size());
                                        int s = j;//since we want to check all kind of boxes for now is there in case we want to use random
                                        Parcel a = randomList.get(s).clone();
                                        if(checkFit(a, pos))
                                        {
                                                placeParcel(a,pos);
                                                _listEP.remove(i);
                                                i = _listEP.size();
                                                j = _baseParcels.size();
                                        }
                                        else if(j == _baseParcels.size()-1) {//Tried all the kind of parcels
                                                //System.out.println("EP to delete "+pos);
                                                //toDeleteEp.add(pos);
                                                if(i == _listEP.size()-1) {//We went through all EP
                                                        System.out.println("No more boxes can be placed");
                                                }
                                        }
                                }
                        }
                        else { toDeleteEp.add(pos); }//ep out of boudaries so delete
                }
                deleteUselessEP(_listEP, toDeleteEp);
        }
}
/**
 * Process to place the parcel in 3D world, 3D array, solution set and any other related behaviour to placing a new parcel
 * @param Parcel   pParcel [New parcel to be placed]
 * @param Vector3D pos     [Position at which it will be placed]
 */
private void placeParcel(Parcel pParcel, Vector3D pos)
{
        pParcel.setPosition(pos);
        addParcelToArraySpace(pParcel, pos);
        updateEP(_solution, pParcel);
        _solution.addParcel(pParcel);
        CreateParcel.createParcel(pParcel);

}
/**
 * Updates the 3D array to avoid parcels overlaping
 * @param Parcel   pParcel [Parcel reference to be added]
 * @param Vector3D pos     [Position from which we start adding the new parcel]
 */
private void addParcelToArraySpace(Parcel pParcel, Vector3D pos)
{
        int x = spaceIndex(pos.x);
        int y = spaceIndex(pos.y);
        int z = spaceIndex(pos.z);
        Vector3D size = pParcel.getSize();
        for(int i = x; i < x + spaceIndex(size.x); i++) {
                for(int j = y; j < y + spaceIndex(size.y); j++) {
                        for(int k = z; k < z + spaceIndex(size.z); k++) {
                                _containerSpace[i][j][k] = pParcel;
                                //System.out.println("Point placed at " + "X " + i + "Y " + j + "Z "+ k);
                                //System.out.println("Point" + _containerSpace[(int)i][(int)j][(int)k]);
                        }
                }
        }
}
/**
 * Update the EP after a new parcel has been placed by proyection the potential points, eliminating duplicated EP and sorting the new list
 * @param SolutionSet    placedParcels [Parcels already inside the container]
 * @param List<Vector3D> EP            [The current list of EP]
 * @param Parcel         newParcel     [The new parcel that has been added to the container]
 */
private void updateEP(SolutionSet placedParcels, Parcel newParcel)
{
        double[] maxBound = { -100000, -10000, -1000000, -1000000, -10000, -10000};
        ExtremePoint[] newEP = new ExtremePoint[6];

        Vector3D newParcelPos = newParcel.getPosition();
        Vector3D newParcelSize = newParcel.getSize();
        //maxBound[5] = 1000;
        //  maxBound[0] = 1000;
        //Potential points to be projected
        Vector3D X = new Vector3D(newParcelPos.x + newParcelSize.x, newParcelPos.y, newParcelPos.z);
        Vector3D Y = new Vector3D(newParcelPos.x, newParcelPos.y + newParcelSize.y, newParcelPos.z);
        Vector3D Z = new Vector3D(newParcelPos.x, newParcelPos.y, newParcelPos.z + newParcelSize.z);
        for (int i = 0; i < placedParcels.getLength(); i++)
        {
                // System.out.println(i);
                Parcel placedParcel = placedParcels.get(i);
                Vector3D placedSize = placedParcel.getSize();
                Vector3D placedPos = placedParcel.getPosition();
                //TODO; add 6 Parceles at the begginning so the points can be projected on the container itself or specifc walls of it (eg. only the base), probably 3 are required since we proyect only towards to the origin

                //Idea: check if the y +  hight of the placed Parcel is less or equal than the y of the new Parcel

                //Maybe because the Parcel in unity has pivot in the middle, the x of the Parcel is 50 and not 0
                //System.out.println("X of placed Parcel" + placedPos.x);
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
        for (int i = 0; i < newEP.length; i++)
        {
                //System.out.println("Length "+newEP.length);
                //System.out.println("i: "+ i);
                //System.out.println("newEP[i]: "+newEP[i]);
                if (Vector3D.getZero().equals(newEP[i]) == false && newEP[i] != null)//We dont want point 0, TODO check if it even give us this case in java Reason first was coded in c unity
                {
                        if(calculateResidualSpace(newEP[i]))
                                _listEP.add(newEP[i]);

                }
        }
        Collections.sort(_listEP);
        _listEP = removeDuplicatedEP(_listEP, newParcel);
        for(int i = 0; i < _listEP.size(); i++)
        {
          calculateResidualSpace(_listEP.get(i));
        }

}
private boolean calculateResidualSpace(ExtremePoint pEP)
{
        int x = spaceIndex(pEP.x);
        int y = spaceIndex(pEP.y);
        int z = spaceIndex(pEP.z);
        Vector3D residualSpace = Vector3D.getZero();
        int temporalx = spaceIndex(xBound);
        int temporaly = spaceIndex(yBound);
        int temporalz = spaceIndex(zBound);
        if(x >= spaceIndex(xBound) || y >= spaceIndex(yBound) || z >= spaceIndex(zBound))//Out of boundaries so useless, will be deleted later in the process
        {
                System.out.println("Useless EP found");
                pEP.setRS(new Vector3D(999999,99999,99999));// set huge RS since we always want to minimize the difference between Parcel size and RS size
                return false;
        }
        try{
                for(int i = x; i < temporalx; i++) {
                        for(int j = y; j < temporaly; j++) {
                                for(int k = z; k < temporalz; k++) {
                                        if(_containerSpace[x][y][k] != null) {//If never triggered should be safe to say that we should delete it
                                                residualSpace.z = undoSpaceIndex(temporalz -z);
                                                System.out.println("The z where the ep is placed is not empty z: " + k);
                                        }
                                        else if(k == temporalz - 1)
                                        {
                                                residualSpace.z = undoSpaceIndex(temporalz - z);
                                                //System.out.println("Container limit is the z Residual size: " + k + " in normal units: " + undoSpaceIndex(k));
                                        }
                                }
                                if(_containerSpace[x][j][z] != null)//If never triggered should be safe to say that we should delete it
                                {
                                        residualSpace.y = undoSpaceIndex(temporaly - y);
                                        System.out.println("The y where the ep is placed is not empty y: " + j);
                                }
                                else if(j == temporaly - 1 )
                                {
                                        residualSpace.y = undoSpaceIndex(temporaly - y);
                                        //System.out.println("Container limit is the y Residual size: " + j + " in normal units: " + undoSpaceIndex(j));
                                }
                        }
                        if(_containerSpace[i][y][z] != null) {//If never triggered should be safe to say that we should delete it

                                residualSpace.x = undoSpaceIndex(temporalx - x);
                                System.out.println("The x where the ep is placed is not empty x: " + i);
                        }
                        else if(i == temporalx - 1)
                        {
                                residualSpace.x = undoSpaceIndex(temporalx - x);
                                //System.out.println("Container limit is the x Residual size: " + i + " in normal units: " + undoSpaceIndex(i));
                        }
                }
        }
        catch(Exception e)
        {
          System.out.println(e.toString());
          System.out.println("Coordinates pos x: " + x + " y: " + y + " z: " + z + "\n");
        }
        //System.out.println("RS size: " + residualSpace);
        pEP.setRS(residualSpace);
        return true;
}
/**
 * Remove elements that are duplicated in the list
 * @param List<Vector3D> ep [List of current ExtremePoints]
 */
private List<ExtremePoint> removeDuplicatedEP(List<ExtremePoint> ep, Parcel p)
{
        List<ExtremePoint> newList = new LinkedList<ExtremePoint>();
        newList.add(ep.get(0));
        ep.remove(0);
        while(ep.size() != 0)
        {
                ExtremePoint v = ep.get(0);
                if(newList.get(0).equals(v) == false && p.contains(v) == false) //if its not repeated and doesnt overlap a point then add
                        newList.add(v);
                ep.remove(0);
        }
        ep = newList;
        return ep;
}
/**
 * Delete the EP that are in the to Delete list since no box can be placed at that position
 * @param List<Vector3D> originalEP [The current list of EP]
 * @param List<Vector3D> toDeleteEp [The list of EP that need to be deleted from the originalEP]
 */
private void deleteUselessEP(List<ExtremePoint> originalEP, List<ExtremePoint> toDeleteEp)
{

        for(int i = 0; i < toDeleteEp.size(); i++)
        {
                //  System.out.println("EP to delete from method "+toDeleteEp.get(i));
                originalEP.remove(toDeleteEp.get(i));
        }
}
/**
 * Checks if the already set parcel can take the proyection of the potential points of the new parcel just added
 * @param  Parcel newParcel     [New parcel just introduced into the container]
 * @param  Parcel prevParcel    [Parcel that was already inside the container]
 * @param  int    axis          [Axis to be checked]
 * @return        [True if it is in the side of the proyection]
 */
private boolean canTakeProjection(Parcel newParcel, Parcel prevParcel, int axis)
{
        //return true;
        Vector3D newpos = newParcel.getPosition();
        Vector3D newSize = newParcel.getSize();
        switch (axis)
        {
        case 0:
                if (newParcel.getPosition().x >= prevParcel.getPosition().x)
                        return true;
                break;
        case 1:
                if (newParcel.getPosition().y >= prevParcel.getPosition().y)
                        return true;
                break;
        case 2:
                if (newParcel.getPosition().z >= prevParcel.getPosition().z)
                        return true;
                break;
        case 3:
                if (newParcel.getPosition().y >= prevParcel.getPosition().y)
                        return true;
                break;
        case 4:
                if (newParcel.getPosition().x >= prevParcel.getPosition().x)
                        return true;
                break;
        case 5:
                if (newParcel.getPosition().z >= prevParcel.getPosition().z)
                        return true;
                break;


        }
        return false;
}
/**
 * Check if the parcel fits in the 3D array at that position
 * @param  Parcel   p             [Parcel to be tested]
 * @param  Vector3D pos           [Position at which we want to put the parcel]
 * @return          [True if it fits, false if it doesnt]
 */
private boolean checkFit(Parcel p, Vector3D pos)
{
        Vector3D size = p.getSize();
        Vector3D containerSize =_container.getSize();
        int x = spaceIndex(pos.x);
        int y = spaceIndex(pos.y);
        int z = spaceIndex(pos.z);
        if( x + spaceIndex(size.x) <= spaceIndex(containerSize.x) &&
            y + spaceIndex(size.y) <= spaceIndex(containerSize.y) &&
            z + spaceIndex(size.z) <= spaceIndex(containerSize.z)) {
                for(int i = x; i < x + spaceIndex(size.x); i++) {
                        for(int j = y; j < y + spaceIndex(size.y); j++) {
                                for(int k = z; k < z + spaceIndex(size.z); k++) {
                                        if(_containerSpace[i][j][k] != null) {
                                                //  System.out.println("Point in array ocuppied " + "X " + i + "Y " + j + "Z "+ k);
                                                //  System.out.println(_containerSpace[i][j][k]);
                                                return false;
                                        }
                                }
                        }
                }
                //System.out.println("can fit");
                return true;
        }
        //System.out.println("can't fit in container");
        return false;
}
/**
 * Creates the visualization of the Extreme Points in the 3D world
 */
public void displayExtremePoints()
{
        for(int i = 0; i < _listEP.size(); i++)
        {
                CreateParcel.createSphere(_listEP.get(i));
        }
}
/**
 * Createsthe visualization of the parcels in the 3D world
 */
public void displayParcels()
{
        for(int i = 0; i < _solution.getLength(); i++)
        {
                Parcel a = _solution.get(i);
                if(a.getInvisible() == false)
                        CreateParcel.createParcel(a);
        }
}
/**
 * Creates the visual boxes and EP in the current state of the algorithm
 */
public void display()
{
        System.out.println("Display button pressed");
        CreateParcel.clearAllParcels();
        displayParcels();
        displayExtremePoints();
        for(int i = 0; i < _listEP.size(); i++)
        {
        System.out.println("Residual Space of: " + _listEP.get(i) );
        }
}
/**
 * Multiplies the number by a constant to enable the represantion of decimals in the 3D array
 * @param  double size          [Number to be multiplied]
 * @return        [The input multiplied by the constant]
 */
private int spaceIndex(double size)
{
        return (int)(size * _scalingArrayConst);
}
private double undoSpaceIndex(double pPos)
{
        return (pPos / _scalingArrayConst);
}
/**
 * Creates the first parcel and EP from which the rest will be derived
 */
private void insertFirstParcel()
{
        System.out.println("Algorithm Z first parcel inserted with SetType: " + _type);
        Parcel a = _baseParcels.get(0).clone();
        a.setPosition(Vector3D.getZero());
        _listEP.add(new ExtremePoint(a.getSize().x, 0, 0));
        _listEP.add(new ExtremePoint(0, a.getSize().y, 0));
        _listEP.add(new ExtremePoint(0, 0, a.getSize().z));
        addParcelToArraySpace(a, Vector3D.getZero());
        _solution.addParcel(a);
        CreateParcel.createParcel(a);
        _started = true;
}
/**
 * Rotate a parcel in an axis by 90° degrees.
 * @param Parcel pParcel [Parcel to be rotated]
 * @param Axis   pAxis   [Axis in which it will be rotated]
 */
private void rotateParcel(Parcel pParcel, Axis pAxis)
{
        Vector3D size = pParcel.getSize();
        switch(pAxis)
        {
        case X:
                pParcel.setSize(new Vector3D(size.x, size.z, size.y));
                System.out.println("X Rotation");
                break;
        case Y:
                pParcel.setSize(new Vector3D(size.z, size.y, size.x));
                break;
        case Z:
                pParcel.setSize(new Vector3D(size.y, size.x, size.z));
                break;
        }
}
/**
 * Reorders randomely the base Parcels
 * @return [returns the list of parcels randomize from which we will take a parcel]
 */
private List<Parcel> randomizeBaseParcelList()
{
        int s = rnd.nextInt(_baseParcels.size());
        List<Parcel> newParcels = new LinkedList<Parcel>();
        for(int i = s; i < _baseParcels.size(); i++)
        {
                newParcels.add(_baseParcels.get(i));
        }
        for(int i = 0; i < s; i++)
        {
                newParcels.add(_baseParcels.get(i));
        }
        return newParcels;
}
public void Start(List<Parcel> list){
}
}
