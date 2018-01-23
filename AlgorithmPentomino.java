import java.util.*;
import java.util.LinkedList;
import java.util.Collections;
import java.lang.Exception;
import  java.lang.Math.*;
public class AlgorithmPentomino extends Algorithm
{
private Container _container;
private SolutionSet _solution;
private List<ExtremePoint> _listEP;
private boolean _started;
private List<Parcel> _baseParcels;
private List<Parcel> _parcelList;
private List<Pentomino> _pentominoList;
private Parcel[][][] _containerSpace;
private int _scalingArrayConst = 2;
private int _wallsCount;
private enum SetType { A, B, C, AB, AC, BC, ABC, BEST, RANDOM, DEBUG }
public enum Axis { X, Y, Z }
private SetType _type;
////////Temporary container boundaries from c#///////////
private double _xBound = 16.5;
private double _yBound = 2.5;
private double _zBound = 4;
///////////////////////////////////////
public AlgorithmPentomino(){
        _type = SetType.C;
        _container = new Container();
        _xBound = _container.getSize().x;
        _yBound = _container.getSize().y;
        _zBound = _container.getSize().z;
        _containerSpace = new Parcel[spaceIndex(_xBound)][spaceIndex(_yBound)][spaceIndex(_zBound)];
        _started = false;
        _listEP = new LinkedList<ExtremePoint>();
        _solution = new SolutionSet(System.currentTimeMillis());
}
public void Start(List<Parcel> list) {

        _type = SetType.B;
        //_solution = new SolutionSet(System.currentTimeMillis());
        initSetType();
        //_parcelList = getOrderParcels(0,list);
        _pentominoList = new LinkedList<Pentomino>();
        _pentominoList.add(new Pentomino(Pentomino.PentominoType.T));
        createContainerWalls();
        computeSolution(1);
        ExtremePoint.displayExtremePoints(_listEP);
        _solution.calculateCurrentValue();
        System.out.println("Current container value: " + _solution.getValue());
        _solutions.add(_solution);
}
public void nextStep()
{
        computeSolution(10);
        _solution.calculateCurrentValue();
        System.out.println("Current container value: " + _solution.getValue());
        _solutions.add(_solution);
        CreateParcel.clearAllParcels();
        display();
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
        //if(_parcelList.size() == 0) return; //No more parcels to be placed
        if (_started == false) insertFirstParcel();
        else {

                Pentomino currentParcel = _pentominoList.get(0).clone();
                int[] bestInfo = findBestEP(currentParcel);
                int epIndex = bestInfo[0];
                if(epIndex == -1) return;    //No available space
                int rotation = bestInfo[1];
                //currentParcel = Parcel.getRotated(currentParcel, rotation);

                placePentomino(currentParcel, _listEP.get(epIndex));
                _listEP.remove(epIndex);
                //_parcelList.remove(0);
        }
}
private int[] findBestEP(Pentomino pParcel)
{

        int[] result = new int[2];
        result[0] = -1;
        if(_listEP.size() == 0)
                return result;

        //Collections.sort(_listEP);
        int bestEpIndex = 0;
        int rotation = 0;
        int MAXROTATION = 6;
        double difference = 0;
        double currentDiff = 0;
        int unusableAxis = 0;
        Vector3D sizeParcel = pParcel.getSize();
        boolean leaveUsableSpace = false;
        //double smallestSize = Math.min(Math.min(sizeParcel.x,sizeParcel.y),sizeParcel.z);

        int bestEpWithUnusableAxis = 0;
        int bestRotationWithUnusableAxis = 0;
        double differenceWithUnusableAxis = Double.MAX_VALUE;
        //TODO: check if it will leave a side with unable space
        //TODO: Instead of keeping the smallest difference, grade them with importance of:
        //TODO: (1) diff == 0 is the best,
        //TODO: (2) then the smallest difference which is >= smallestSide of any parcel in the list
        //TODO: (3) and finally if all leave unasable space then choose the smallest one
        for (int i = 0; i < _listEP.size(); i++) {
            for(int j = 0; j < MAXROTATION; j++) {
                Pentomino rotatedParcel = Pentomino.getRotated(pParcel, j);
                unusableAxis = 0;
                currentDiff = calculateDifferenceRsPentomino(rotatedParcel, _listEP.get(i));
                //unusableAxis = checkMakesUnusableSpace(rotatedParcel, _listEP.get(i), sizeParcel);
                if(currentDiff > difference && currentDiff >= 0 &&  checkPentominoFits(rotatedParcel, _listEP.get(i)))
                {

                        difference = currentDiff;
                        bestEpIndex = i;
                        rotation = j;
                        result[0] = bestEpIndex;
                        result[1] = rotation;
                        //  if(difference == 0 )
                        //return result;

                }
                       }
        }
        return result;
}
private boolean checkPentominoFits(Pentomino p, Vector3D pos)
{
  Parcel[][][] parcels = p.getShapeList();
  for(int i = 0; i < parcels.length; i++)
          for(int j = 0; j < parcels[0].length; j++)
                  for(int k = 0; k < parcels[0][0].length; k++) {
                  if(parcels[i][j][k] != null)
                  {
                          Vector3D parcelPos = pos.clone();
                          parcelPos.z += k * 0.5;
                          parcelPos.y += j * 0.5;
                          parcelPos.x += i * 0.5;
                          if(checkFit(parcels[i][j][k], parcelPos) == false)
                                  return false;
                  }
          }
        return true;
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
        Vector3D posArray = new Vector3D(spaceIndex(pos.x), spaceIndex(pos.y), spaceIndex(pos.z));
        if( posArray.x + spaceIndex(size.x) <= spaceIndex(containerSize.x) &&
            posArray.y + spaceIndex(size.y) <= spaceIndex(containerSize.y) &&
            posArray.z + spaceIndex(size.z) <= spaceIndex(containerSize.z)) {
                for(int i = (int)posArray.x; i < posArray.x + spaceIndex(size.x); i++)
                        for(int j = (int)posArray.y; j < posArray.y + spaceIndex(size.y); j++)
                                for(int k = (int)posArray.z; k < posArray.z + spaceIndex(size.z); k++)
                                        if(_containerSpace[i][j][k] != null) {
                                              //  System.out.println(_containerSpace[i][j][k]);
                                                return false;
                                        }
                return true;//Didnt found stop condition so it fits
        }else
                return false;
}
/**
 * Check if placing a Parcel in an Ep will leave unasable space in an axis
 * @param  Parcel       pParcel       [Parcel to be placed]
 * @param  ExtremePoint pEp           [Ep where the parcel will be placed]
 * @param  Vector3D     pMinAxis      [Sizes of the smallest box]
 * @return              [description]
 */
private int checkMakesUnusableSpace(Parcel pParcel, ExtremePoint pEp, Vector3D pMinAxis)
{
        //TODO: maybe try all rotations of the size
        int amount = 0;
        Vector3D size = pParcel.getSize();
        Vector3D EpSize = pEp.getRS();
        if((EpSize.x - size.x) < pMinAxis.x)
                amount++;
        if((EpSize.y - size.y) < pMinAxis.y)
                amount++;
        if((EpSize.z - size.z) < pMinAxis.z)
                amount++;
        return amount;
}

private double calculateDifferenceRsAndParcel(Parcel pParcel, ExtremePoint pEp)
{
        Vector3D size = pParcel.getSize();
        Vector3D EpSize = pEp.getRS();
        double result = 1000000;
        if(EpSize.x >= size.x &&  EpSize.y >= size.y && EpSize.z >= size.z)
                result = ((EpSize.x - size.x) + (EpSize.y - size.y) + (EpSize.z - size.z));
        return result;
}
private double calculateDifferenceRsPentomino(Pentomino pParcel, ExtremePoint pEp)
{
        Vector3D size = pParcel.getSize();
        Vector3D EpSize = pEp.getRS();
        double result = 1000000;
        if(EpSize.x >= size.x &&  EpSize.y >= size.y && EpSize.z >= size.z)
                result = ((EpSize.x - size.x) + (EpSize.y - size.y) + (EpSize.z - size.z));
        return result;
}

private void placePentomino(Pentomino pParcel, Vector3D pos)
{
      //  System.out.println("Pentomino being placed at "+ pos);
        Parcel[][][] parcels = pParcel.getShapeList();
        for(int i = 0; i < parcels.length; i++)
                for(int j = 0; j < parcels[0].length; j++)
                        for(int k = 0; k < parcels[0][0].length; k++) {
                        if(parcels[i][j][k] != null)
                        {
                                Vector3D parcelPos = pos.clone();
                                parcelPos.z += k * 0.5;
                                parcelPos.y += j * 0.5;
                                parcelPos.x += i * 0.5;
                                placeParcel(parcels[i][j][k], parcelPos);
                        }
                }

        //printExtremePointsConsole();
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
        //printExtremePointsConsole();
}
/**
 * Updates the 3D array to avoid parcels overlaping
 * @param Parcel   pParcel [Parcel reference to be added]
 * @param Vector3D pos     [Position from which we start adding the new parcel]
 */
private void addParcelToArraySpace(Parcel pParcel, Vector3D pos)
{
        Vector3D size = pParcel.getSize();
        Vector3D posArray = new Vector3D(spaceIndex(pos.x), spaceIndex(pos.y), spaceIndex(pos.z));
        for(int i = (int)posArray.x; i < posArray.x + spaceIndex(size.x); i++)
                for(int j = (int)posArray.y; j < posArray.y + spaceIndex(size.y); j++)
                        for(int k = (int)posArray.z; k < posArray.z + spaceIndex(size.z); k++)
                                _containerSpace[i][j][k] = pParcel;
}
/**
 * Update the EP after a new parcel has been placed by proyection the potential points, eliminating duplicated EP and sorting the new list
 * @param SolutionSet    placedParcels [Parcels already inside the container]
 * @param List<Vector3D> EP            [The current list of EP]
 * @param Parcel         newParcel     [The new parcel that has been added to the container]
 */
private void updateEP(SolutionSet placedParcels, Parcel newParcel )
{
        double[] maxBound = { -1000000, -1000000, -1000000, -1000000, -1000000, -1000000};
        ExtremePoint[] newEP = ExtremePoint.createNewEps(placedParcels, newParcel); //Proyect the potential points into the placed boxes
        for (int i = 0; i < newEP.length; i++)
                if (Vector3D.getZero().equals(newEP[i]) == false && newEP[i] != null) //We dont want point 0, TODO check if it even give us this case in java Reason first was coded in c unity
                        _listEP.add(newEP[i]);

        _listEP = ExtremePoint.removeDuplicatedEP(_listEP, newParcel);
        deleteUselessEP(_listEP);
        updateResidualSpace();
        Collections.sort(_listEP);

}
private void updateResidualSpace()
{
        for(int i = 0; i < _listEP.size(); i++)
                calculateResidualSpace(_listEP.get(i));
}
private boolean calculateResidualSpace(ExtremePoint pEP)
{
        int x = spaceIndex(pEP.x);
        int y = spaceIndex(pEP.y);
        int z = spaceIndex(pEP.z);
        Vector3D residualSpace = Vector3D.getZero();
        int temporalx = spaceIndex(_xBound);
        int temporaly = spaceIndex(_yBound);
        int temporalz = spaceIndex(_zBound);
        if(x >= spaceIndex(_xBound) || y >= spaceIndex(_yBound) || z >= spaceIndex(_zBound))//Out of boundaries so useless, will be deleted later in the process
        {
                pEP.setRS(new Vector3D(999999,99999,99999));// set huge RS since we always want to minimize the difference between Parcel size and RS size
                return false;
        }
        try{
                for(int i = x; i < temporalx; i++) {
                        for(int j = y; j < temporaly; j++) {
                                for(int k = z; k < temporalz; k++) {
                                        if(_containerSpace[x][y][k] != null) {//If never triggered should be safe to say that we should delete it
                                                residualSpace.z = undoSpaceIndex(k - z);
                                                k = temporalz;
                                        }
                                        else if(k == temporalz - 1)
                                        {
                                                residualSpace.z = undoSpaceIndex(temporalz - z);
                                        }
                                }
                                if(_containerSpace[x][j][z] != null)//If never triggered should be safe to say that we should delete it
                                {
                                        residualSpace.y = undoSpaceIndex(j - y);
                                        j = temporaly;
                                }
                                else if(j == temporaly - 1 )
                                {
                                        residualSpace.y = undoSpaceIndex(temporaly - y);
                                }
                        }
                        if(_containerSpace[i][y][z] != null) {//If never triggered should be safe to say that we should delete it

                                residualSpace.x = undoSpaceIndex(i - x);
                                i = temporalx;
                        }
                        else if(i == temporalx - 1)
                        {
                                residualSpace.x = undoSpaceIndex(temporalx - x);
                        }
                }
        }
        catch(Exception e)
        {
                System.out.println(e.toString());
        }
        pEP.setRS(residualSpace);
        return true;
}
/**
 * Delete the EP that are in the to Delete list since no box can be placed at that position
 * @param List<Vector3D> originalEP [The current list of EP]
 * @param List<Vector3D> toDeleteEp [The list of EP that need to be deleted from the originalEP]
 */
private void deleteUselessEP(List<ExtremePoint> originalEP)
{
        List<ExtremePoint> toDeleteEp = new LinkedList<ExtremePoint>();
        for(int i = 0; i < originalEP.size(); i++)
        {
                if (originalEP.get(i).x  >= _xBound || originalEP.get(i).y  >= _yBound || originalEP.get(i).z  >= _zBound ) //container boundaries
                        toDeleteEp.add(originalEP.get(i));

        }    //ep out of boudaries so delete
        for(int i = 0; i < toDeleteEp.size(); i++)
        {
                //  System.out.println("EP to delete from method "+toDeleteEp.get(i));
                originalEP.remove(toDeleteEp.get(i));
        }
}
/**
 * Creates the visualization of the parcels in the 3D world
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
        //  System.out.println("Display button pressed");
        CreateParcel.clearAllParcels();
        displayParcels();
        ExtremePoint.displayExtremePoints(_listEP);
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
        _listEP.add(new ExtremePoint(0,0,0));
        System.out.println("Algorithm Z first parcel inserted with SetType: " + _type);
        _started = true;

}
/**
 * Reorders the _baseParcels depending on the kind of set that we want to compute
 */
private List<Parcel> getOrderParcels(int pOrderingType, List<Parcel> pList)
{
        List<Parcel> aList  = new LinkedList<Parcel>();
        List<Parcel> bList  = new LinkedList<Parcel>();
        List<Parcel> cList  = new LinkedList<Parcel>();
        List<Parcel> newOrderedList  = new LinkedList<Parcel>();
        Double[] orderValues = {-1.0,-1.0,-1.0};
        //double parcelValues = new Vector3D(aValue, bValue, cValue);
        if(pOrderingType == 0) //Order by value
        {
                for(int i = 0; i < pList.size(); i++)
                {
                        Parcel p = pList.get(i);
                        if(p instanceof ParcelA)
                        {
                                aList.add(p);
                                orderValues[0] = p.getValue();
                        }
                        else if(p instanceof ParcelB)
                        {
                                bList.add(p);
                                orderValues[1] = p.getValue();
                        }
                        else if(p instanceof ParcelC)
                        {
                                cList.add(p);
                                orderValues[2] = p.getValue();
                        }
                }
                // = new Double[]{aValue, bValue, cValue};
                //  Arrays.sort(orderValues);
                for(int i = 0; i < orderValues.length; i++)
                {
                        if(aList.size() > 0 && orderValues[i] == aList.get(0).getValue())
                                for(int j = 0; j < aList.size(); j++)
                                        newOrderedList.add(aList.get(j));
                        if(bList.size() > 0 && orderValues[i] == bList.get(0).getValue())
                                for(int j = 0; j < bList.size(); j++)
                                        newOrderedList.add(bList.get(j));
                        if(cList.size() > 0 && orderValues[i] == cList.get(0).getValue())
                                for(int j = 0; j < cList.size(); j++)
                                        newOrderedList.add(cList.get(j));
                }
        }
        return newOrderedList;
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
                case 0:   //Negative X
                        size.y = 100;
                        size.z = 100;
                        pos.x = -size.x;
                        break;
                case 1:    //Negative Y
                        size.x = 100;
                        size.z = 100;
                        pos.y = -size.y;
                        break;
                case 2:   //Negative Z
                        size.x = 100;
                        size.y = 100;
                        pos.z = -size.z;
                        break;
                }
                c.setSize(size);
                c.setPosition(pos);
                _solution.addParcel(c);
        }
}
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
                //_baseParcels = randomizeBaseParcelList();
                break;
        case DEBUG:
                _baseParcels.add(a);
                break;

        }
}
/**
 * Method to be called from the UI to start calculating a solution
 */
public void Start(){
        _type = SetType.B;

        initSetType();
        createContainerWalls();
        computeSolution(80);
        //debugTest();
        ExtremePoint.displayExtremePoints(_listEP);
        _solution.calculateCurrentValue();
        System.out.println("Current container value: " + _solution.getValue());

        _solutions.add(_solution);
}
private void printExtremePointsConsole(){
        for(int j = 0; j < _listEP.size(); j++)
        {
                System.out.println("\n  EP number is " +j+ " values are" + _listEP.get(j));
        }
}
}
