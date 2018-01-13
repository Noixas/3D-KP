import java.util.*;

public class GreedyAlgorithm extends Algorithm {
  private int amountA = 5;
  private int amountB = 8;
  private int amountC = 10;
  private double VolA;
  private double VolB;
  private double VolC;
  private Parcel[] parcelOrder = new Parcel[3];
  private double[] volumeOrder = new double[3];
  private int[] amountOrder = new int[3];
  private ArrayList<Parcel> parcelList = new ArrayList<Parcel>();
  private int index = 0;

  private Container container = new Container();
  private Vector3D containerSize = container.getSize();
  private Parcel[][][] containerSpace = new Parcel[arrayIndex(containerSize.x)][arrayIndex(containerSize.y)][arrayIndex(containerSize.z)];

  /**
   * Start computing solution separated from constructor to be able to configure it with the UI
   */
  public void Start() {
    makeLists();
    System.out.println("lists made");
    orderLists();
    System.out.println("lists order");
    makeParcelList();
    System.out.println("parcels in arraylist");
    for(Parcel p : parcelList) {
      if(placeable(p)) {
        CreateParcel.createParcel(p);
        placeInArray(p);
        System.out.println("parcel placed");
        System.out.println(p.toString());
      }
    }
    _done = true;
    System.out.println("done");
  }


  /**
   * Places references to the parcel object in the 3D array which rapresents the container.
   * @param parcel the parcel that is getting placed in the container.
   */
  public void placeInArray(Parcel parcel) {
    Vector3D size = parcel.getSize();
    Vector3D pos = parcel.getPosition();
    int a =1; //logging
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
          System.out.println("placed" + a++);
        }
      }
    }
  }

  /**
   * Decide whether the select parcel fits inside the container also taking into account all the previously placed parcels.
   * @param parcel the parcel that is getting placed in the container.
   */
  public boolean placeable(Parcel parcel) {
    for(int i=0; i < containerSpace.length;i++) {
      for(int j=0; j < containerSpace[0].length; j++) {
        for(int k=0; k < containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] == null && isCorner(i, j, k) && canFit(parcel, i, j, k)) {
            System.out.println("i: " + i);
            System.out.println("j: " + j);
            System.out.println("k: " + k);
            parcel.setPosition(new Vector3D((double)i/2, (double)j/2, (double)k/2));
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Helper method which looks if the parcel can physically fit inside the container.
   * @param parcel the parcel that is getting placed in the container.
   */
  public boolean canFit(Parcel parcel, int i, int j, int k) {
    Vector3D size = parcel.getSize();
    if((i+arrayIndex(size.x))<=arrayIndex(containerSize.x) && (j+arrayIndex(size.y))<=arrayIndex(containerSize.y) && (k+arrayIndex(size.z))<=arrayIndex(containerSize.z)) {
      for(int m=i; m < i+arrayIndex(size.x); m++) {
        for(int n=j; n < j+arrayIndex(size.y); n++) {
          for(int o=k; o < k+arrayIndex(size.z); o++) {
            if(containerSpace[m][n][o] != null) {
              System.out.println("can't fit");
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
   * Helper method which checks if the corner point of the parcels is closest to other parcels or one of the sides of the container.
   * @param
   */
  public boolean isCorner(int i, int j, int k) {
    boolean xEdge = true;
    boolean yEdge = true;
    boolean zEdge = true;
    if(i != 0) {
      if(containerSpace[i-1][j][k] == null) {
        xEdge = false;
      }
    }
    if(j != 0) {
      if(containerSpace[i][j-1][k] == null) {
        yEdge = false;
      }
    }
    if(k != 0) {
      if(containerSpace[i][j][k-1] == null) {
        zEdge = false;
      }
    }
    if(xEdge && yEdge && zEdge) { //logging
      System.out.println("is corner");
    } else {
      System.out.println("is not corner");
    }
    return xEdge && yEdge && zEdge;
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
   * Creates 3 arrays: one of the different sizes of parcels to be placed, one for their amount and the other for their volume.
   * The information for each parcel can be given by their index in the arrays.
   */
  public void makeLists() {
    parcelOrder[0] = new ParcelA();
    parcelOrder[1] = new ParcelB();
    parcelOrder[2] = new ParcelC();
    amountOrder[0] = amountA;
    amountOrder[1] = amountB;
    amountOrder[2] = amountC;
    for(int i=0; i<3; i++) {
      volumeOrder[i] = parcelOrder[i].getVolume();
    }
  }

  /**
   * Orders the 3 lists by the volume of the parcels(biggest to smalles).
   */
  public void orderLists() {
    boolean hasChanged = true;
    double volumeBuffer;
    Parcel parcelBuffer;
    int amountBuffer;
    while(hasChanged) {
      hasChanged = false;
      for(int i=0;i<index;i++) {
        if(volumeOrder[i]<volumeOrder[i+1]) {
          //Volume list ordering
          volumeBuffer = volumeOrder[i];
          volumeOrder[i] = volumeOrder[i+1];
          volumeOrder[i+1] = volumeBuffer;

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


  public int arrayIndex(double number) {
    return (int)(number*2);
  }
}
