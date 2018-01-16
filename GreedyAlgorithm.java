import java.util.*;

/**
 * This class places the parcels utilizing a greedy algorithm. The priority order heuristics that can be chosen are three:
 * -The volume of the parcels in descending order.
 * -The value of the parcels in descending order.
 * -The value density(value/volume) of the parcels in descending order.
 */
public class GreedyAlgorithm extends Algorithm {
  private int amountA = 50;
  private int amountB = 13;
  private int amountC = 10;
  private double VolA;
  private double VolB;
  private double VolC;
  private Parcel[] parcelOrder = new Parcel[3];
  private double[] heuristicOrder = new double[3];
  private int[] amountOrder = new int[3];
  private ArrayList<Parcel> parcelList = new ArrayList<Parcel>();
  private int scalingFactor = 2;
  private boolean parcelFits = true;
  private int heuristicID = 1;
  private Container container = new Container();
  private Vector3D containerSize = container.getSize();
  private Parcel[][][] containerSpace = new Parcel[arrayIndex(containerSize.x)][arrayIndex(containerSize.y)][arrayIndex(containerSize.z)];

  /**
   * Start computing solution separated from constructor to be able to configure it with the UI
   */
  public void Start() {
    makeLists(heuristicID);
    System.out.println("Starting order:");
    printArray(parcelOrder);
    printArray(heuristicOrder);
    printArray(amountOrder);
    System.out.println("lists made");
    orderLists();
    System.out.println("Sorted order:");
    printArray(parcelOrder);
    printArray(heuristicOrder);
    printArray(amountOrder);
    System.out.println("lists order");
    makeParcelList();
    System.out.println("parcels in arraylist");
    for(Parcel p : parcelList) {
      int lol = parcelList.indexOf(p);
      System.out.println(parcelList.get(lol).getClass());
      if(parcelFits || !(p.getClass().equals(parcelList.get(parcelList.indexOf(p)-1).getClass()))) {
        if(placeable(p)) {
          CreateParcel.createParcel(p);
          placeInArray(p);
          System.out.println("parcel placed");
          System.out.println(p.toString());
        } else {System.out.println("not placed");}
      }
    }
    _done = true;
    parcelFits = true;
    System.out.println("done");
    System.out.println("Empty space: " + countEmptySpaces() + " meters cubed");
  }


  /**
   * Places references to the parcel object in the 3D array which rapresents the container.
   * @param parcel the parcel that is getting placed in the container.
   */
  public void placeInArray(Parcel parcel) {
    Vector3D size = parcel.getSize();
    Vector3D pos = parcel.getPosition();
    System.out.println("position:");
    System.out.println(arrayIndex(pos.x));
    System.out.println(arrayIndex(pos.y));
    System.out.println(arrayIndex(pos.z));
    System.out.println("size");
    System.out.println(arrayIndex(size.x));
    System.out.println(arrayIndex(size.y));
    System.out.println(arrayIndex(size.z));
    for(int i=arrayIndex(pos.x); i<arrayIndex(size.x) + arrayIndex(pos.x);i++) {
      for(int j=arrayIndex(pos.y); j<arrayIndex(size.y) + arrayIndex(pos.y); j++) {
        for(int k=arrayIndex(pos.z); k<arrayIndex(size.z) + arrayIndex(pos.z); k++) {
          containerSpace[i][j][k] = parcel;
          System.out.println("placed at " + i + ", " + j + ", " + k);
        }
      }
    }
  }

  /**
   * Decide whether the select parcel fits inside the container also taking into account all the previously placed parcels.
   * @param parcel the parcel that is getting placed in the container.
   * @return either true or false whether if the parcel is placeable or not.
   */
  public boolean placeable(Parcel parcel) {
    for(int i=0; i < containerSpace.length;i++) {
      for(int j=0; j < containerSpace[0].length; j++) {
        for(int k=0; k < containerSpace[0][0].length; k++) {
          Vector3D posIndex = new Vector3D(i, j, k);
          if(containerSpace[i][j][k] == null) {
            System.out.println("(" + i + ", " + j + ", " + k + ") is empty");
            if(isNextTo(posIndex, true, true, true)) {
              if(tryRotations(parcel, posIndex)) {
                parcel.setPosition(new Vector3D((double)posIndex.x/2, (double)posIndex.y/2, (double)posIndex.z/2));
                parcelFits = true;
                return true;
              }
            }else if(isNextTo(posIndex, false, true, true)) {
              if(tryRotations(parcel, posIndex)) {
                parcel.setPosition(new Vector3D((double)posIndex.x/2, (double)posIndex.y/2, (double)posIndex.z/2));
                parcelFits = true;
                return true;
              }
            } /*else if(isNextTo(posIndex, false, false, true)) {
              if(tryRotations(parcel, posIndex)) {
                parcel.setPosition(new Vector3D((double)posIndex.x/2, (double)posIndex.y/2, (double)posIndex.z/2));
                parcelFits = true;
                return true;
              }
            }*/
          } else {System.out.println("(" + i + ", " + j + ", " + k + ") is not empty");}
        }
      }
    }
    parcelFits = false;
    return false;
  }

  /**
   * Helper method which looks if the parcel can physically fit inside the container.
   * @param parcel the parcel that is getting placed in the container.
   * @param posIndex
   */
  public boolean canFit(Parcel parcel, Vector3D posIndex) {
    Vector3D size = parcel.getSize();
    int xIndex = (int)posIndex.x;
    int yIndex = (int)posIndex.y;
    int zIndex = (int)posIndex.z;
    if(xIndex+arrayIndex(size.x)<=arrayIndex(containerSize.x) && yIndex+arrayIndex(size.y)<=arrayIndex(containerSize.y) && zIndex+arrayIndex(size.z)<=arrayIndex(containerSize.z)) {
      System.out.println("parcel can fit in container dimensions");
      for(int m=xIndex; m < xIndex+arrayIndex(size.x); m++) {
        for(int n=yIndex; n < yIndex+arrayIndex(size.y); n++) {
          for(int o=zIndex; o < zIndex+arrayIndex(size.z); o++) {
            if(containerSpace[m][n][o] != null) {
              //System.out.println("can't fit");
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

  /**
   * Checks if the corner point of the parcels is closest to other parcels or one of the sides of the container.
   * @param posIndex The position of the parcel in the array given as a vector.
<<<<<<< HEAD
   * @param x Whether to check on the x axis
   * @param y Whether to check on the y axis
   * @param z Whether to check on the z axis
   * @return Whether the given coordinates satisfy the given conditions
=======
   * @return the
>>>>>>> 45ff030c002f67dfd027dc948db4418c1b9fd77b
   */
  public boolean isNextTo(Vector3D posIndex, boolean x, boolean y, boolean z) {
    boolean xEdge = true;
    boolean yEdge = true;
    boolean zEdge = true;
    int xIndex = (int)posIndex.x;
    int yIndex = (int)posIndex.y;
    int zIndex = (int)posIndex.z;

    if(x && xIndex != 0) {
      if(containerSpace[xIndex-1][yIndex][zIndex] == null) {
        xEdge = false;
      }
    }

    if(y && yIndex != 0) {
      if(containerSpace[xIndex][yIndex-1][zIndex] == null) {
        yEdge = false;
      }
    }
    if(z && zIndex != 0) {
      if(containerSpace[xIndex][yIndex][zIndex-1] == null) {
        zEdge = false;
      }
    }
    if(xEdge && yEdge && zEdge) { //logging
      System.out.println("is corner");
    } /*else {
      System.out.println("is not corner");
    }*/
    return xEdge && yEdge && zEdge;
  }

  /**
   * Check for the possible rotations of the parcel for the given coordinates.
   * @param parcel The parcel to rotate.
   * @param posIndex The position the parcel given as a vector.
   * @return Either true or false depending if a possible rotation can fit in the container.
   */
   public boolean tryRotations(Parcel parcel, Vector3D posIndex) {
     Parcel buffer = parcel.clone();
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
     System.out.println("no possible rotation found for corner (" + posIndex.x + ", " + posIndex.y + ", " + posIndex.z + ")");
     return false;
   }



  /**
   * Creates 3 arrays: one of the different sizes of parcels to be placed, one for their amount and the other for their volume.
   * The information for each parcel can be given by their index in the arrays.
   * @param id The ID of the heuristic. 1:volume | 2:value | 3:density
   */
public void makeLists(int id) {
    parcelOrder[0] = new ParcelA();
    parcelOrder[1] = new ParcelB();
    parcelOrder[2] = new ParcelC();
    amountOrder[0] = amountA;
    amountOrder[1] = amountB;
    amountOrder[2] = amountC;
    if(id == 1) {
      for(int i=0; i<3; i++) {
        heuristicOrder[i] = parcelOrder[i].getVolume();
      }
    } else if(id == 2) {
      for(int i=0; i<3; i++) {
        heuristicOrder[i] = parcelOrder[i].getValue();
      }
    } else if(id == 3) {
      for(int i=0; i<3; i++) {
        heuristicOrder[i] = parcelOrder[i].getDensityValue();
      }
    }
  }

  /**
   * Orders the 3 lists by the given heuristic of the parcels.
   */
  public void orderLists() {
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
  public void makeParcelList() {
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
  public void rotateX(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.x, size.z, size.y));
  }

  /**
   * Rotates the given parcel around the y axis by 90° degrees.
   * @param parcel the parcel that needs to be rotated.
   */
  public void rotateY(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.z, size.y, size.x));
  }

  /**
   * Rotates the given parcel around the z axis by 90° degrees.
   * @param parcel the parcel that needs to be rotated.
   */
  public void rotateZ(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.y, size.x, size.z));
  }

  /**
   * This method converts the values for the coordinates and sizes of the parcels and converts them into to integer so that they can be represented in a 3D array.
   * @param number The number that needs to be converted into an array index.
   * @return The number converted into an array index.
   */
  public int arrayIndex(double number) {
    return (int)(number*scalingFactor);
  }

  /**
   * This method converts the values for the coordinates and sizes of the parcels and converts them into to integer so that they can be represented in a 3D array.
   * @param number The number that needs to be converted into an array index.
   * @return The number converted into an array index.
   */
  public double vectorValue(int number) {
    return (double)number/scalingFactor;
  }

  public double countEmptySpaces() {
    double count = 0;
    for(int i=0; i < containerSpace.length;i++) {
      for(int j=0; j < containerSpace[0].length; j++) {
        for(int k=0; k < containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] == null) {
            count++;
          }
        }
      }
    }
    return count/4;
  }


//logging
  public void printArray(double[] ar) {
    for(int i = 0; i<ar.length; i++) {
      System.out.println(ar[i]);
    }
  }

  public void printArray(Parcel[] ar) {
    for(int i = 0; i<ar.length; i++) {
      System.out.println(ar[i].getClass());
    }
  }

  public void printArray(int[] ar) {
    for(int i = 0; i<ar.length; i++) {
      System.out.println(ar[i]);
    }
  }

  public void setID(int i) {
    heuristicID = i;
  }
}
