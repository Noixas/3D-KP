/*import java.util.*;

public class GreedyAlgorithm extends Algorithm {
  private int amountA;
  private int amountB;
  private int amountC;
  private double VolA;
  private double VolB;
  private double VolC;
  private Box[] boxOrder = new Box[3];
  private double[] volumeOrder = new double[3];
  private int[] amountOrder = new int[3];
  private ArrayList<Box> boxList = new ArrayList<Box>();
  private int index = 0;

  private Container container = new Container();
  private Vector3D containerSize = container.getSize();
  private int containerX = arrayIndex(containerSize.x);
  private int containerY = arrayIndex(containerSize.y);
  private int containerZ = arrayIndex(containerSize.z);
  private Box[][][] containerSpace = new Box[containerX][containerY][containerZ];

  public void Start() {
    makeLists();
    orderLists();
    makeBoxOrder();
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

  public Box[][][] makeBoxSpace(Box box) {
    int sizeX = arrayIndex(box.getSize().x);
    int sizeY = arrayIndex(box.getSize().y);
    int sizeZ = arrayIndex(box.getSize().z);
    Box[][][] boxSpace = new Box[sizeX][sizeY][sizeZ];
    fillSpace(boxSpace, box);
    return boxSpace;
  }

  public void fillSpace(Box[][][] boxSpace, Box box) {
    for(int i=0; i<boxSpace.length;i++) {
      for(int j=0; j<boxSpace[0].length; j++) {
        for(int k=0; k<boxSpace[0][0].length; k++) {
          boxSpace[i][j][k] = box;
        }
      }
    }
  }


  public void placeBox(Box box) {
    int posX = arrayIndex(box.getPosition().x);
    int posY = arrayIndex(box.getPosition().y);
    int posZ = arrayIndex(box.getPosition().z);
    int sizeX = arrayIndex(box.getSize().x);
    int sizeY = arrayIndex(box.getSize().y);
    int sizeZ = arrayIndex(box.getSize().z);
    for(int i=posX; i<=sizeX;i++) {
      for(int j=posY; j<=sizeY; j++) {
        for(int k=posZ; k<=sizeZ; k++) {
          containerSpace[i][j][k] = box;
        }
      }
    }
  }

  public void placeable(Box box) {
    for(int i=0; i<containerSpace.length;i++) {
      for(int j=0; j<containerSpace[0].length; j++) {
        for(int k=0; k<containerSpace[0][0].length; k++) {
          if(containerSpace[i][j][k] == null && isCorner(i, j, k) && canFit(box, i, j, k)) {

          }
        }
      }
    }
  }

  public boolean canFit(Box box, int i, int j, int k) {
    int posX = arrayIndex(box.getPosition().x);
    int posY = arrayIndex(box.getPosition().y);
    int posZ = arrayIndex(box.getPosition().z);
    int sizeX = arrayIndex(box.getSize().x);
    int sizeY = arrayIndex(box.getSize().y);
    int sizeZ = arrayIndex(box.getSize().z);
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

  public void removeBox() {

  }

  public void rotateX(Box box) {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    box.setSize(new Vector3D(sizeX, sizeZ, sizeY));
  }

  public void rotateY(Box box) {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    box.setSize(new Vector3D(sizeZ, sizeY, sizeY));
  }

  public void rotateZ(Box box) {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    box.setSize(new Vector3D(sizeY, sizeX, sizeZ));
  }

  public void makeLists() {
    boxOrder[0] = new BoxA();
    boxOrder[1] = new BoxB();
    boxOrder[2] = new BoxC();
    amountOrder[0] = amountA;
    amountOrder[1] = amountB;
    amountOrder[2] = amountC;
    for(int i=0; i<3; i++) {
      volumeOrder[i] = boxOrder[i].getVolume();
    }
  }

  public void makeBoxOrder() {
    for(int i=0; i<3; i++) {
      for(int j=0; i<amountOrder[i]; j++) {
        boxList.add(boxOrder[i].clone());
      }
    }
  }

  public void orderLists() {
    boolean hasChanged = true;
    double volumeBuffer;
    Box boxBuffer;
    int amountBuffer;
    while(hasChanged) {
      hasChanged = false;
      for(int i=0;i<index;i++) {
        if(volumeOrder[i]<volumeOrder[i+1]) {
          //Volume list ordering
          volumeBuffer = volumeOrder[i];
          volumeOrder[i] = volumeOrder[i+1];
          volumeOrder[i+1] = volumeBuffer;

          //Box list ordering
          boxBuffer = boxOrder[i];
          boxOrder[i] = boxOrder[i+1];
          boxOrder[i+1] = boxBuffer;

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
*/
