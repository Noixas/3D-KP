import java.util.*;
import java.util.LinkedList;
import java.util.Collections;
public class AlgorithmZ extends Algorithm
{
  private Container _container;
  private SolutionSet _solution;
  private List<Vector3D> _listEP;
  private boolean _started;
  private List<Parcel> _baseParcels;
  private Random rnd;
  private Parcel[][][] _containerSpace;
  private int _scalingArrayConst = 2;
  ////////Temporary container boundaries from c#///////////
    public double xBound = 16.5;
    public double yBound = 2.5;
    public double zBound = 4;
  ///////////////////////////////////////
  public AlgorithmZ(){
      rnd = new Random();

    _container = new Container();
    xBound = _container.getSize().x;
    yBound = _container.getSize().y;
    zBound = _container.getSize().z;
    _containerSpace = new Parcel[spaceIndex(xBound)][spaceIndex(yBound)][spaceIndex(zBound)];
    _baseParcels = new LinkedList<Parcel>();
    _baseParcels.add(new ParcelA());
    _baseParcels.add(new ParcelB());
    _baseParcels.add(new ParcelC());

    _started = false;
    _listEP = new LinkedList<Vector3D>();
    _solution = new SolutionSet(0);
    //Start();

  }
  public void Start(){
    System.out.println("Algorithm Z starting...");
    createContainerWalls();
    computeSolution();
    displayExtremePoints();

  }
  public void displayExtremePoints()
  {
    for(int i = 0; i < _listEP.size(); i++)
    {
      CreateParcel.createSphere(_listEP.get(i));
    }
  }
  private void createContainerWalls()
  {
    int wallsCount = 3;
    for(int i = 0; i < wallsCount; i++)
      {
          Parcel c = new Parcel(new Vector3D(0,0,0), 0);
          Vector3D pos = Vector3D.getZero();
          Vector3D size = Vector3D.getZero();
              switch (i)
              {
                  case 0://Negative X
                    size.y = 100;
                    size.z = 100;
                    pos.x = -size.x;
                      break;
                  case 1: //Negative Y
                    size.x = 100;
                    size.z = 100;
                    pos.y = -size.y;
                      break;
                  case 2://Negative Z
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
  public void display(){}
private void computeSolution()
{
          if ( _started == false)
          {
              int s = rnd.nextInt(_baseParcels.size());
              Parcel a = _baseParcels.get(s).clone();
              a.setPosition(Vector3D.getZero());
              _listEP.add(new Vector3D(a.getSize().x, 0, 0));
              _listEP.add(new Vector3D(0, a.getSize().y, 0));
              _listEP.add(new Vector3D(0, 0, a.getSize().z));
              addParcelToArraySpace(a, Vector3D.getZero());
              _solution.addParcel(a);
              CreateParcel.createParcel(a);
              _started = true;
          }
          else
          {
            List<Parcel> randomList = randomizeBaseParcelList();
              for (int i = 0; i < _listEP.size(); i++)
              {
                Vector3D pos = _listEP.get(i);
              //  System.out.println(pos);
                  if (pos.x + 1 < xBound && pos.y + 1 < yBound && pos.z + 1 < zBound )//container boundaries
                  {
                      for(int j = 0; j < _baseParcels.size(); j++)
                      {
                        //int s = rnd.nextInt(_baseParcels.size());
                        int s = j;//since we want to check all kind of boxes we dont use the random but for now is there in case we want to use random
                        Parcel a = randomList.get(s).clone();
                        //sprintArray(_containerSpace);
                        if(checkFit(a, pos))
                        {
                          //System.out.println("Parcel placed at; "+ pos);
                          placeParcel(a,pos);
                          _listEP.remove(i);
                          i = _listEP.size();
                          j = _baseParcels.size();
                        }
                        else if(j == _baseParcels.size()-1)//If we tried all and none fit
                        {
                        //  System.out.println("Box "+ a+" in pos "+ pos +" fit in EP: " + _listEP.get(i));
                        }
                      }
                  }
              }
          }
}
private void placeParcel(Parcel pParcel, Vector3D pos)
{
  pParcel.setPosition(pos);
  addParcelToArraySpace(pParcel, pos);
  updateEP(_solution, _listEP, pParcel);
  _solution.addParcel(pParcel);
  CreateParcel.createParcel(pParcel);

}
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
          System.out.println("Point placed at " + "X " + i + "Y " + j + "Z "+ k);

          System.out.println("Point" + _containerSpace[(int)i][(int)j][(int)k]);
        }
      }
    }

}
    public void updateEP(SolutionSet placedParcels, List<Vector3D> EP, Parcel newParcel)
    {
        double[] maxBound = { -100000, -10000, -1000000, -1000000, -10000, -10000};
        Vector3D[] newEP = new Vector3D[6];

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
            System.out.println("X of placed Parcel" + placedPos.x);
            //Xy:
            if ((X.x  >= placedPos.x && X.x  < (placedPos.x + placedSize.x)) && (X.z >= placedPos.z && X.z < (placedPos.z + placedSize.z))&&(placedPos.y + placedSize.y > maxBound[0]))
            {
                newEP[0] = new Vector3D(X.x, placedPos.y + placedSize.y, X.z);
                System.out.println("Y" + (placedPos.y + placedSize.y));
                maxBound[0] = placedPos.y + placedSize.y;
            }
            //Xz:
            if ((X.x >= placedPos.x && X.x < (placedPos.x + placedSize.x)) && (X.y >= placedPos.y && X.y < (placedPos.y + placedSize.y)) && (placedPos.z + placedSize.z > maxBound[1]))
            {
                newEP[1] = new Vector3D(X.x, X.y, placedPos.z + placedSize.z);
                maxBound[1] = placedPos.z + placedSize.z;
            }
            //Yx:
            if ((Y.y >= placedPos.y && Y.y < (placedPos.y + placedSize.y)) && (Y.z >= placedPos.z && Y.z < (placedPos.z + placedSize.z)) && (placedPos.x + placedSize.x > maxBound[2]))
            {
                newEP[2] = new Vector3D(placedPos.x + placedSize.x, Y.y, Y.z);
                maxBound[2] = placedPos.x + placedSize.x;
            }
            //Yz:
            if ((Y.y >= placedPos.y && Y.y < (placedPos.y + placedSize.y)) && (Y.x >= placedPos.x && Y.x < (placedPos.x + placedSize.x)) && (placedPos.z + placedSize.z > maxBound[3]))
            {
                newEP[3] = new Vector3D(Y.x, Y.y, placedPos.z + placedSize.z);
                maxBound[3] = placedPos.z + placedSize.z;
            }
            //Zx:
            if ((Z.z >= placedPos.z && Z.z < (placedPos.z + placedSize.z)) && (Z.y >= placedPos.y && Z.y < (placedPos.y + placedSize.y)) && (placedPos.x + placedSize.x > maxBound[4]))
            {
                newEP[4] = new Vector3D(placedPos.x + placedSize.x, Z.y, Z.z);
                maxBound[4] = placedPos.x + placedSize.x;
            }
            //Zy:
            if ((Z.z >= placedPos.z && Z.z < (placedPos.z + placedSize.z)) && (Z.x >= placedPos.x && Z.x < (placedPos.x + placedSize.x)) && (placedPos.y + placedSize.y > maxBound[5]))
            {
                newEP[5] = new Vector3D(Z.x, placedPos.y + placedSize.y, Z.z);
                maxBound[5] = placedPos.y + placedSize.y;
            }

        }
        for (int i = 0; i < newEP.length; i++)
        {
          System.out.println("Length "+newEP.length);
          System.out.println("i: "+ i);
          System.out.println("newEP[i]: "+newEP[i]);
            if (Vector3D.getZero().equals(newEP[i]) == false && newEP[i] != null)//We dont want point 0, TODO check if it even give us this case in java Reason first was coded in c unity
            {

                _listEP.add(newEP[i]);
            }
        }
        Collections.sort(EP);
        _listEP = removeDuplicatedEP(EP, newParcel);
    }
    /**
     * Remove elements that are duplicated in the list
     * @param List<Vector3D> ep [List of current ExtremePoints]
     */
    public List<Vector3D> removeDuplicatedEP(List<Vector3D> ep, Parcel p)
{//TODO move it to solution set class maybe
      List<Vector3D> newList = new LinkedList<Vector3D>();
      newList.add(ep.get(0));
      ep.remove(0);
      while(ep.size() != 0)
      {
        Vector3D v = ep.get(0);
        if(newList.get(0).equals(v) == false && p.contains(v) == false)//if its not repeated and doesnt overlap a point then add
            newList.add(v);
        ep.remove(0);
      }
      ep = newList;
      return ep;
    }
public boolean canTakeProjection(Parcel newParcel, Parcel prevParcel, int axis)
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
              System.out.println("Point in array ocuppied " + "X " + i + "Y " + j + "Z "+ k);
              System.out.println(_containerSpace[i][j][k]);
              return false;
            }
          }
        }
      }
      System.out.println("can fit");
      return true;
    }
    System.out.println("can't fit in container");
    return false;
  }
  private int spaceIndex(double size)
  {
    return (int)(size * _scalingArrayConst);
  }
  private List<Parcel> randomizeBaseParcelList()
  {
    int s = rnd.nextInt(_baseParcels.size());
    List<Parcel> newParcels = new LinkedList<Parcel>();
    for(int i = s; i < _baseParcels.size();i++)
    {
      newParcels.add(_baseParcels.get(i));
    }
    for(int i = 0; i < s;i++)
    {
      newParcels.add(_baseParcels.get(i));
    }
    return newParcels;
  }
}
