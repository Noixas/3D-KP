/*import java.util.*;

public class GreedyAlgorithm extends Algorithm {
  private int amountA;
  private int amountB;
  private int amountC;
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
  private int containerX = arrayIndex(containerSize.x);
  private int containerY = arrayIndex(containerSize.y);
  private int containerZ = arrayIndex(containerSize.z);
  private Parcel[][][] containerSpace = new Parcel[containerX][containerY][containerZ];

  public void Start() {
    makeLists();
    orderLists();
    makeParcelOrder();
    while(!isDone()) {
      System.out.println("is not done");
    }
  }

  public boolean containerBoundaries() {
    for(int i=0; i<containerSpace.length;i++) {
      for(int j=0; j<containerSpace[0].length; j++) {
        for(int k=0; k<containerSpace[0][0].length; k++) {

        }
      }
    }
  }

  public Parcel[][][] makeParcelSpace(Parcel parcel) {
    Vector3D size = parcel.getSize();
    Parcel[][][] parcelSpace = new Parcel[arrayIndex(size.x)][arrayIndex(size.y)][arrayIndex(s.y)];
    fillSpace(parcelSpace, parcel);
    return parcelSpace;
  }

  public void fillSpace(Parcel[][][] parcelSpace, Parcel parcel) {
    for(int i=0; i<parcelSpace.length;i++) {
      for(int j=0; j<parcelSpace[0].length; j++) {
        for(int k=0; k<parcelSpace[0][0].length; k++) {
          parcelSpace[i][j][k] = parcel;
        }
      }
    }
  }


  public void placeParcel(Parcel parcel) {
    Vector3D size = parcel.getSize();
    Vector3D pos = parcel.getPosition();
    for(int i=pos.x; i<=size.x;i++) {
      for(int j=pos.y; j<=size.y; j++) {
        for(int k=pos.z; k<=size.z; k++) {
          containerSpace[i][j][k] = parcel;
        }
      }
    }
  }

  public boolean placeable(Parcel parcel) {
    for(int i=0; i<containerSpace.length;i++) {
      for(int j=0; j<containerSpace[0].length; j++) {
        for(int k=0; k<containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] == null && isCorner(i, j, k) && canFit(parcel, i, j, k)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean canFit(Parcel parcel, int i, int j, int k) {
    int posX = arrayIndex(parcel.getPosition().x);
    int posY = arrayIndex(parcel.getPosition().y);
    int posZ = arrayIndex(parcel.getPosition().z);
    int sizeX = arrayIndex(parcel.getSize().x);
    int sizeY = arrayIndex(parcel.getSize().y);
    int sizeZ = arrayIndex(parcel.getSize().z);
    for(int i=posX; i<=posX+sizeX;i++) {
      for(int j=posY; j<=posY+sizeY; j++) {
        for(int k=posZ; k<=containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] != null) {
            return false;
          }
        }
      }
    }
    return true;
  }

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
        xEdge = false;
      }
    }
    if(k != 0) {
      if(containerSpace[i][j][k-1] == null) {
        xEdge = false;
      }
    }
    return xEdge && yEdge && zEdge;
  }

  public void removeParcel() {

  }

  public void rotateX(Parcel parcel) {
    double sizeX = parcel.getSize().x;
    double sizeY = parcel.getSize().y;
    double sizeZ = parcel.getSize().z;
    parcel.setSize(new Vector3D(sizeX, sizeZ, sizeY));
  }

  public void rotateY(Parcel parcel) {
    double sizeX = parcel.getSize().x;
    double sizeY = parcel.getSize().y;
    double sizeZ = parcel.getSize().z;
    parcel.setSize(new Vector3D(sizeZ, sizeY, sizeY));
  }

  public void rotateZ(Parcel parcel) {
    double sizeX = parcel.getSize().x;
    double sizeY = parcel.getSize().y;
    double sizeZ = parcel.getSize().z;
    parcel.setSize(new Vector3D(sizeY, sizeX, sizeZ));
  }

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

  public void makeParcelOrder() {
    for(int i=0; i<3; i++) {
      for(int j=0; i<amountOrder[i]; j++) {
        parcelList.add(parcelOrder[i].clone());
      }
    }
  }

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

  public int arrayIndex(double number) {
    return (int)number*2;
  }
}*/
