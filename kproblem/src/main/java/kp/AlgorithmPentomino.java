package kp;
/**
 * Algorithm to place Pentominoes made of Parcels using Extreme Points into a container
 * By: Rodrigo Alejandro Chavez Mulsa
 */
import java.util.*;
import java.util.LinkedList;
import java.util.Collections;
import java.lang.Exception;
import  java.lang.Math.*;
public class AlgorithmPentomino extends Algorithm{
private Container _container; // Container reference
private SolutionSet _solution; //Solution where we keep our progress
private List<ExtremePoint> _listEP; // List of Extreme Points
private boolean _started; //Flag to know when the first case has been used
private List<Pentomino> _pentominoList; //The list of pentominoes to be placed
private Parcel[][][] _containerSpace; //Array to check if we overlap parcels
private int _scalingArrayConst = 2; //Constant to enable .5 size parcels
private int _wallsCount; //Amount of walls in the _Solution set that are just for the EP, not actual solution
private enum SetType { A, B, C, AB, AC, BC, ABC, BEST, RANDOM, DEBUG }
public enum Axis { X, Y, Z }
private Vector3D amountParcels; //x = A, y = B, Z = C
private double _xBound = 16.5; //Accesible countainer x size
private double _yBound = 2.5;//Accesible countainer y size
private double _zBound = 4;//Accesible countainer z size

public AlgorithmPentomino(){
      //  _type = SetType.C;
        _container = new Container();
        _xBound = _container.getSize().x;
        _yBound = _container.getSize().y;
        _zBound = _container.getSize().z;
        _containerSpace = new Parcel[spaceIndex(_xBound)][spaceIndex(_yBound)][spaceIndex(_zBound)];
        _started = false;
        _listEP = new LinkedList<ExtremePoint>();
        _solution = new SolutionSet(System.currentTimeMillis());
}
/**
 * Method called from UI to compute the answer
 * @param List<Parcel> list [The list of Parcels to be placed in the container set in the ui]
 */
public void Start(List<Parcel> list) {

        _solution = new SolutionSet(System.currentTimeMillis());
        _pentominoList = transformToPentominoes(list);
        createContainerWalls();
        computeSolution(300);
        _solution.calculateCurrentValue();
        _solutions.add(_solution);
        _solution.endSolution(System.currentTimeMillis());
        System.out.println("Current container value: " + _solution.getValue());
        CreateParcel.clearAllParcels();
        display();

}
/**
 * Calculates just one step further (Places the next parcel only)
 */
public void nextStep(){
        computeSolution(5);
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
private void computeSolution(int pIterations){
        if(pIterations <= 0) return;
        for(int i = 0; i < pIterations; i++)
                if(_pentominoList.size() > 0)
                        computeSolutionStep();
}
/**
 * Computes the parcel and position at which the next one will be placed
 */
private void computeSolutionStep(){
        if (_started == false) insertFirstParcel();
        else {
                Pentomino currentPentomino = _pentominoList.get(0).clone();
                int[] bestInfo = findBestEP(currentPentomino);
                int epIndex = bestInfo[0];
                if(epIndex == -1) return;    //No available space
                int rotation = bestInfo[1];
                currentPentomino = Pentomino.getRotated(currentPentomino, rotation);
                placePentomino(currentPentomino, _listEP.get(epIndex));
                _listEP.remove(epIndex);
                _pentominoList.remove(0);
        }
}
/**
 * Loop through all the EP and apply the heuristics to get the best EP to place a parcel
 * @param  Pentomino pPentomino       [Parcel to be placed in the container]
 * @return           [return the index of the best EP in the EPList and the index of the rotation that best suit that point]
 */
private int[] findBestEP(Pentomino pPentomino){
        int[] result = new int[2];//Small array to pass the 2 values as result
        result[0] = -1; //set as -1 in case we return prematurely because a stop condition
        if(_listEP.size() == 0) return result; // No more EP so returns

        int MAXROTATION = 6;
        double difference = 0;
        double currentDiff = 0;

        for (int i = 0; i < _listEP.size(); i++)
                for(int j = 0; j < MAXROTATION; j++) {
                        Pentomino rotatedParcel = Pentomino.getRotated(pPentomino, j);
                        currentDiff = calculateDifferenceRsPentomino(rotatedParcel, _listEP.get(i));
                        if(currentDiff > difference && currentDiff >= 0 &&  checkPentominoFits(rotatedParcel, _listEP.get(i)))
                        {
                                difference = currentDiff;
                                result[0] = i; // Index of the best EP so far
                                result[1] = j; // Rotation number of the best way to fill this
                        }
                }

        return result;
}
/**
 * Loops through the 3D array to check wether a Pentomino fits in the position
 * @param  Pentomino p             [Pentomino to be placed]
 * @param  Vector3D  pos           [Position to in which will be placed]
 * @return           [true if fits, false if it would overlap with another parcel]
 */
private boolean checkPentominoFits(Pentomino p, Vector3D pos){
        Parcel[][][] parcels = p.getShapeList();
        for(int i = 0; i < parcels.length; i++)
                for(int j = 0; j < parcels[0].length; j++)
                        for(int k = 0; k < parcels[0][0].length; k++)
                                if(parcels[i][j][k] != null)
                                {
                                        Vector3D parcelPos = pos.clone();
                                        parcelPos.z += k * 0.5;
                                        parcelPos.y += j * 0.5;
                                        parcelPos.x += i * 0.5;
                                        if(checkParcelFit(parcels[i][j][k], parcelPos) == false)
                                                return false;
                                }
        return true;
}
/**
 * Check if the parcel fits in the 3D array at that position
 * @param  Parcel   p             [Parcel to be tested]
 * @param  Vector3D pos           [Position at which we want to put the parcel]
 * @return          [True if it fits, false if it doesnt]
 */
private boolean checkParcelFit(Parcel p, Vector3D pos){
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
 * Calculate an approximate of how much space an extreme point will have left after a pentomino is placed there
 * @param  Pentomino    pPentomino       [Pentomino to be palced]
 * @param  ExtremePoint pEp           [Extreme Point to be compared at]
 * @return             [The difference]
 */
private double calculateDifferenceRsPentomino(Pentomino pPentomino, ExtremePoint pEp){
        Vector3D size = pPentomino.getSize();
        Vector3D EpSize = pEp.getRS();
        double result = 1000000;
        if(EpSize.x >= size.x &&  EpSize.y >= size.y && EpSize.z >= size.z)
                result = ((EpSize.x - size.x) + (EpSize.y - size.y) + (EpSize.z - size.z));
        return result;
}
/**
 * Place each parcel that conforms the pentomino into the space Array
 * @param Pentomino pPentomino [Pentomino to be placed]
 * @param Vector3D  pos        [Position at which will be placed]
 */
private void placePentomino(Pentomino pPentomino, Vector3D pos){
        Parcel[][][] parcels = pPentomino.getShapeList();
        for(int i = 0; i < parcels.length; i++) //x
                for(int j = 0; j < parcels[0].length; j++) //y
                        for(int k = 0; k < parcels[0][0].length; k++) {//z
                                if(parcels[i][j][k] != null)
                                {
                                        Vector3D parcelPos = pos.clone();
                                        parcelPos.z += k * 0.5;
                                        parcelPos.y += j * 0.5;
                                        parcelPos.x += i * 0.5;
                                        placeParcel(parcels[i][j][k], parcelPos);
                                }
                        }
}
/**
 * Process to place the parcel in 3D world, 3D array, solution set and any other related behaviour to placing a new parcel
 * @param Parcel   pParcel [New parcel to be placed]
 * @param Vector3D pos     [Position at which it will be placed]
 */
private void placeParcel(Parcel pParcel, Vector3D pos){
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
private void addParcelToArraySpace(Parcel pParcel, Vector3D pos){
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
private void updateEP(SolutionSet placedParcels, Parcel newParcel ){
        double[] maxBound = { -1000000, -1000000, -1000000, -1000000, -1000000, -1000000}; //Set an extreme case just to initiliaze the array
        ExtremePoint[] newEP = ExtremePoint.createNewEps(placedParcels, newParcel); //Proyect the potential points into the placed boxes and return a list of the new Extreme Points created
        for (int i = 0; i < newEP.length; i++)
                if (Vector3D.getZero().equals(newEP[i]) == false && newEP[i] != null) //We dont want point 0, TODO check if it even give us this case in java, reason: first I coded it in c# unity
                        _listEP.add(newEP[i]);

        _listEP = ExtremePoint.removeDuplicatedEP(_listEP, newParcel); //Return a list of the EP without duplicated positions
        deleteUselessEP(_listEP);//Delete de ones that are in boundaries or cant be used
        updateResidualSpace();//Calculate the residual space of every EP
        Collections.sort(_listEP);//Lets keep EP sort and clean :)

}
/**
 * Update every EP Residual Space
 */
private void updateResidualSpace(){
        for(int i = 0; i < _listEP.size(); i++)
                calculateResidualSpace(_listEP.get(i));
}
/**
 * Calculate how much space this Extreme Point has in front (Residual Space)
 * @param  ExtremePoint pEP           [Extreme Point to be updated]
 * @return              [false if doesnt fit in container]
 */
private boolean calculateResidualSpace(ExtremePoint pEP){
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
private void deleteUselessEP(List<ExtremePoint> originalEP){
        List<ExtremePoint> toDeleteEp = new LinkedList<ExtremePoint>();
        for(int i = 0; i < originalEP.size(); i++)
        {
                if (originalEP.get(i).x  >= _xBound || originalEP.get(i).y  >= _yBound || originalEP.get(i).z  >= _zBound ) //container boundaries
                        toDeleteEp.add(originalEP.get(i));

        }    //ep out of boudaries so delete
        for(int i = 0; i < toDeleteEp.size(); i++)
        {
                originalEP.remove(toDeleteEp.get(i));
        }
}
/**
 * Creates the visualization of the parcels in the 3D world
 */
public void displayParcels(){
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
public void display(){
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
private int spaceIndex(double size){
        return (int)(size * _scalingArrayConst);
}
/**
 * Divides the number by a constant to return the represantion of decimals in the 3D array to the world
 * @param  double pPos          [Position to be modified]
 * @return        [The input divided by the constant]
 */
private double undoSpaceIndex(double pPos){
        return (pPos / _scalingArrayConst);
}
/**
 * Creates the first parcel and EP from which the rest will be derived
 */
private void insertFirstParcel(){
        _listEP.add(new ExtremePoint(0,0,0));
        //System.out.println("Algorithm Z first parcel inserted with SetType: " + _type);
        _started = true;

}
/**
 * Reorders the _baseParcels depending on the kind of set that we want to compute
 */
private List<Parcel> getOrderParcels(int pOrderingType, List<Parcel> pList){
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
private void createContainerWalls(){
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
/**
 * Get in a Vector3D object the amount of each type of Pentominoes (x = L, y = P, z = T)
 * @param  List<Parcel> pList         [List of Parcels to be converted]
 * @return              [The list of pentominoes equivalent to the input parcels]
 */
private Vector3D getAmountOfPentominoes(List<Parcel> pList){
        Vector3D typeCount = Vector3D.getZero();
        for(int i = 0; i < pList.size(); i++)
        {
                Parcel z = pList.get(i);
                if(z instanceof ParcelA)
                        typeCount.x += 1;
                if(z instanceof ParcelB)
                        typeCount.y += 1;
                if(z instanceof ParcelC)
                        typeCount.z += 1;
        }
        return typeCount;
}
/**
 * Depending on the kind of Parcel recevied as input, we add the corresponding pentomino
 * @param  List<Parcel> pList         [List of Parcels]
 * @return              [The list of corresponding pentominoes]
 */
private List<Pentomino> transformToPentominoes(List<Parcel> pList){
        Vector3D amountPent = getAmountOfPentominoes(pList);
        List<Pentomino> resultList = new LinkedList<Pentomino>();
        for(int i = 0; i < amountPent.z; i++)//Since form T has the highest value we add them first
        {
                resultList.add(new Pentomino(Pentomino.PentominoType.T));
        }
        for(int i = 0; i < amountPent.y; i++)
        {
                resultList.add(new Pentomino(Pentomino.PentominoType.P));
        }
        for(int i = 0; i < amountPent.x; i++)
        {
                resultList.add(new Pentomino(Pentomino.PentominoType.L));
        }
        return resultList;
}
/**
 * For debug only, print in console the position and residual space of each Extreme Point
 */
private void printExtremePointsConsole(){
        for(int j = 0; j < _listEP.size(); j++)
        {
                System.out.println("\n  EP number is " +j+ " values are" + _listEP.get(j));
        }
}
}
