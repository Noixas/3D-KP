import java.util.*;

/**
 * This class places the parcels utilizing a greedy algorithm. The priority order heuristics that can be chosen are three:
 * -The volume of the parcels in descending order.
 * -The value of the parcels in descending order.
 * -The value density(value/volume) of the parcels in descending order.
 */
public class GreedyAlgorithm extends Algorithm {
  private int amountA = 0;//Amount of parcel A
  private int amountB = 0;//Amount of parcel B
  private int amountC = 0;//Amount of parcel C
  private int parcelTypes = 0; //Counts how many different types of parcels are present
  private Parcel[] parcelOrder = new Parcel[3]; //Array used for setting up the order of the parcels
  private double[] heuristicOrder = new double[3]; //Array used for determining the order of the parcels
  private int[] amountOrder = new int[3]; //Array used to save the amounts available of each parcel used
  private ArrayList<Parcel> parcelList = new ArrayList<Parcel>(); //List of all the parcels that can be used
  private int scalingFactor = 2; //Scaling factor to convert from the Vector3D to the array index
  private boolean parcelFits = true; //Tells if the previous parcels could fit in the container
  private int heuristicID; //The ID of the heuristic used
  private Container container = new Container(); //The container used
  private Vector3D containerSize = container.getSize(); //The container's size
  private Parcel[][][] containerSpace = new Parcel[arrayIndex(containerSize.x)][arrayIndex(containerSize.y)][arrayIndex(containerSize.z)]; //Array representation of the container
  private SolutionSet solution; //The solution found

  /**
   * Start computing solution separated from constructor to be able to configure it with the UI
   */
  public void Start(List<Parcel> list) {
    solution = new SolutionSet(System.currentTimeMillis());
    parcelList = (ArrayList<Parcel>)list;
    countParcels();
    makeLists(heuristicID);
    sortLists();
    makeOrderedParcelList();
    for(Parcel p : parcelList) {
      if(parcelFits || !(p.getClass().equals(parcelList.get(parcelList.indexOf(p)-1).getClass()))) { //Checks if the current one is the same size as the previous one and whether it was placed or not (used for optimization)
        if(placeable(p)) {
          placeInArray(p);
        }
      }
    }
    _done = true;
    parcelFits = true;
    makeParcelSolutionArray();
    solution.endSolution(System.currentTimeMillis());
    _solutions.add(solution);
    display();
    amountA=0;
    amountB=0;
    amountC=0;
    System.out.println("Done");
    System.out.println(solution.getTotalTime());
  }

  /**
   * Places references to the parcel object in the 3D array which rapresents the container.
   * @param parcel the parcel that is getting placed in the container.
   */
  private void placeInArray(Parcel parcel) {
    Vector3D size = parcel.getSize();
    Vector3D pos = parcel.getPosition();
    for(int i=arrayIndex(pos.x); i<arrayIndex(size.x) + arrayIndex(pos.x);i++) {
      for(int j=arrayIndex(pos.y); j<arrayIndex(size.y) + arrayIndex(pos.y); j++) {
        for(int k=arrayIndex(pos.z); k<arrayIndex(size.z) + arrayIndex(pos.z); k++) {
          containerSpace[i][j][k] = parcel;
        }
      }
    }
  }

  /**
   * Decide whether the select parcel fits inside the container also taking into account all the previously placed parcels.
   * @param parcel the parcel that is getting placed in the container.
   * @return either true or false whether if the parcel is placeable or not.
   */
  private boolean placeable(Parcel parcel) {
    for(int u=1; u < 4; u++) {
    for(int i=0; i < containerSpace.length;i++) {
      for(int j=0; j < containerSpace[0].length; j++) {
        for(int k=0; k < containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] == null) {
            Vector3D posIndex = new Vector3D(i, j, k);
              if(tryRotations(parcel, posIndex)) {
                parcel.setPosition(new Vector3D(vectorValue(posIndex.x), vectorValue(posIndex.y), vectorValue(posIndex.z)));
                parcelFits = true;
                return true;
              }
            }
          }
        }
      }
    }
    parcelFits = false;
    return false;
  }

  /**
   * Looks if the parcel can physically fit inside the container.
   * @param parcel the parcel that is getting placed in the container.
   * @param posIndex the position of the parcel within the array.
   */
  private boolean canFit(Parcel parcel, Vector3D posIndex) {
    Vector3D size = parcel.getSize();
    int xIndex = (int)posIndex.x;
    int yIndex = (int)posIndex.y;
    int zIndex = (int)posIndex.z;
    if(xIndex+arrayIndex(size.x)<=arrayIndex(containerSize.x) &&
     yIndex+arrayIndex(size.y)<=arrayIndex(containerSize.y) &&
     zIndex+arrayIndex(size.z)<=arrayIndex(containerSize.z)) {
      for(int m=xIndex; m < xIndex+arrayIndex(size.x); m++) {
        for(int n=yIndex; n < yIndex+arrayIndex(size.y); n++) {
          for(int o=zIndex; o < zIndex+arrayIndex(size.z); o++) {
            if(containerSpace[m][n][o] != null) {
              return false;
            }
          }
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Check for the possible rotations of the parcel for the given coordinates.
   * @param parcel The parcel to rotate.
   * @param posIndex The position the parcel given as a vector.
   * @return Either true or false depending if a possible rotation can fit in the container.
   */
   private boolean tryRotations(Parcel parcel, Vector3D posIndex) {
     Parcel buffer = parcel.clone();
     Vector3D size = parcel.getSize();
     //No rotation
     if(canFit(buffer, posIndex)) { //x y z
       return true;
     }
     rotateX(buffer);
     if(canFit(buffer, posIndex)) { //x z y
       parcel.setSize(buffer.getSize());
       return true;
     }
     buffer = parcel.clone();
     rotateY(buffer);
     if(canFit(buffer, posIndex)) { //z y x
       parcel.setSize(buffer.getSize());
       return true;
     }
     rotateX(buffer);
     if(canFit(buffer, posIndex)) { //z x y
       parcel.setSize(buffer.getSize());
       return true;
     }
     buffer = parcel.clone();
     rotateZ(buffer);
     if(canFit(buffer, posIndex)) { //y x z
       parcel.setSize(buffer.getSize());
       return true;
     }
     rotateX(buffer);
     if(canFit(buffer, posIndex)) { //y z x
       parcel.setSize(buffer.getSize());
       return true;
     }
     return false;
   }

   /**
    * Count how many parcels have been given as an input.
    */
    private void countParcels() {
      for(Parcel p : parcelList) {
        if(p instanceof ParcelA) {
          amountA++;
        } else if(p instanceof ParcelB) {
          amountB++;
        } else if(p instanceof ParcelC) {
          amountC++;
        }
      }
    }

  /**
   * Creates 3 arrays: one of the different sizes of parcels to be placed, one for their amount and the other for their volume.
   * The information for each parcel can be given by their index in the arrays.
   * @param id The ID of the heuristic. 1:volume | 2:value | 3:density
   */
private void makeLists(int id) {
  if(amountA != 0) {
    parcelOrder[0] = parcelList.get(0).clone();
  }
  if(amountB != 0) {
    parcelOrder[1] = parcelList.get(amountA).clone();
  }
  if(amountC != 0) {
    parcelOrder[2] = parcelList.get(amountA + amountB).clone();
  }
    amountOrder[0] = amountA;
    amountOrder[1] = amountB;
    amountOrder[2] = amountC;
    if(id == 1) {
      for(int i=0; i<3; i++) {
        if(parcelOrder[i] != null) {
          heuristicOrder[i] = parcelOrder[i].getVolume();
        }
      }
    } else if(id == 2) {
      for(int i=0; i<3; i++) {
        if(parcelOrder[i] != null) {
          heuristicOrder[i] = parcelOrder[i].getValue();
        }
      }
    } else if(id == 3) {
      for(int i=0; i<3; i++) {
        if(parcelOrder[i] != null) {
          heuristicOrder[i] = parcelOrder[i].getDensityValue();
        }
      }
    }
  }

  /**
   * Sorts the 3 lists by the given heuristic of the parcels.
   */
  private void sortLists() {
    boolean hasChanged = true;
    double heuristicBuffer;
    Parcel parcelBuffer;
    int amountBuffer;
    while(hasChanged) {
      hasChanged = false;
      for(int i=0; i<heuristicOrder.length-1; i++) {
        if(heuristicOrder[i]<heuristicOrder[i+1]) {
          //Heuristic list ordering
          heuristicBuffer = heuristicOrder[i];
          heuristicOrder[i] = heuristicOrder[i+1];
          heuristicOrder[i+1] = heuristicBuffer;

          //Parcel list ordering
          parcelBuffer = parcelOrder[i];
          parcelOrder[i] = parcelOrder[i+1];
          parcelOrder[i+1] = parcelBuffer;

          //Amount list ordering
          amountBuffer = amountOrder[i];
          amountOrder[i] = amountOrder[i+1];
          amountOrder[i+1] = amountBuffer;

          hasChanged = true;
        }
      }
    }
  }

  /**
   * Creates an ArrayList of Parcel objects that need to be placed into the container.
   */
  private void makeOrderedParcelList() {
    parcelList.clear();
    for(int i=0; i<3; i++) {
      for(int j=0; j<amountOrder[i]; j++) {
        parcelList.add(parcelOrder[i].clone());
      }
    }
  }

  /**
   * Rotates the given parcel around the x axis by 90° degrees.
   * @param parcel the parcel that needs to be rotated.
   */
  private void rotateX(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.x, size.z, size.y));
  }

  /**
   * Rotates the given parcel around the y axis by 90° degrees.
   * @param parcel the parcel that needs to be rotated.
   */
  private void rotateY(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.z, size.y, size.x));
  }

  /**
   * Rotates the given parcel around the z axis by 90° degrees.
   * @param parcel the parcel that needs to be rotated.
   */
  private void rotateZ(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.y, size.x, size.z));
  }

  /**
   * This method converts the values for the coordinates and sizes of the parcels and converts them into to integer so that they can be represented in a 3D array.
   * @param number The number that needs to be converted into an array index.
   * @return The number converted into an array index.
   */
  private int arrayIndex(double number) {
    return (int)(number*scalingFactor);
  }

  /**
   * Converts the values for the coordinates and sizes of the parcels and converts them into to integer so that they can be represented in a 3D array.
   * @param number The number that needs to be converted into an array index.
   * @return The number converted into an array index.
   */
  private double vectorValue(double number) {
    return number/(double)scalingFactor;
  }

  private double countOccupiedSpace() {
    double count = 0;
    for(int i=0; i < containerSpace.length;i++) {
      for(int j=0; j < containerSpace[0].length; j++) {
        for(int k=0; k < containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] != null) {
            count++;
          }
        }
      }
    }
    return count/8;
  }

  /**
   * Adds the placed parcels to the solution set array.
   */
  private void makeParcelSolutionArray() {
    for(Parcel p : parcelList) {
      if(p.getPosition() != null) {
        solution.addParcel(p);
      }
    }
  }

  /**
   * Sets the algorithm to the heuristic wanted. Connected to the user interface.
   * @param id the id of the heuristic.
   */
  public void setID(int id) {
    heuristicID = id;
  }

  /**
   * Creates all of the parcels to be displayed in the 3D world.
   */
  public void display() {
    for(int p=0; p<solution.getLength(); p++) {
      CreateParcel.createParcel(solution.get(p));
    }
  }
}
