import java.util.*;

public class GreedyAlgorithm extends Algorithm {
  private int amountA = 4;
  private int amountB = 6;
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

  public void Start() {
    makeLists();
    System.out.println("lists made");
    orderLists();
    System.out.println("lists order");
    makeParcelList();
    System.out.println("parcels in arraylist");
    System.out.println("loop starts");
    for(Parcel p : parcelList) {
      if(placeable(p)) {
        placeParcel(p);
        System.out.println("parcel placed");
      }
    }
    _done = true;
    System.out.println("done");
  }



  public void placeParcel(Parcel parcel) {
    Vector3D size = parcel.getSize();
    Vector3D pos = parcel.getPosition();
    for(int i=arrayIndex(pos.x); i<=arrayIndex(size.x);i++) {
      for(int j=arrayIndex(pos.y); j<=arrayIndex(size.y); j++) {
        for(int k=arrayIndex(pos.z); k<=arrayIndex(size.z); k++) {
          containerSpace[i][j][k] = parcel;
        }
      }
    }
  }

  public boolean placeable(Parcel parcel) {
    for(int i=0; i < containerSpace.length;i++) {
      for(int j=0; j < containerSpace[0].length; j++) {
        for(int k=0; k < containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] == null && isCorner(i, j, k) && canFit(parcel, i, j, k)) {
            parcel.setPosition(new Vector3D(i, j, k));
            CreateParcel.createParcel(parcel);
            //make 3d parcel
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean canFit(Parcel parcel, int i, int j, int k) {
    Vector3D size = parcel.getSize();
    if((i+arrayIndex(size.x))<=arrayIndex(containerSize.x) && (j+arrayIndex(size.y))<=arrayIndex(containerSize.y) && (k+arrayIndex(size.z))<=arrayIndex(containerSize.z)) {
      for(int m=i; m <= i+arrayIndex(size.x); m++) {
        for(int n=j; n <= j+arrayIndex(size.y); n++) {
          for(int o=k; o <= k+arrayIndex(size.z); o++) {
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
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.x, size.z, size.y));
  }

  public void rotateY(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.z, size.y, size.x));
  }

  public void rotateZ(Parcel parcel) {
    Vector3D size = parcel.getSize();
    parcel.setSize(new Vector3D(size.y, size.x, size.z));
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

  public void makeParcelList() {
    for(int i=0; i<3; i++) {
      for(int j=0; j<amountOrder[i]; j++) {
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
}
