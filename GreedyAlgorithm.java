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

  private Container container = new Container();
  private Vector3D containerSize = container.getSize();
  private double containerX = containerSize.x;
  private double containerY = containerSize.y;
  private double containerZ = containerSize.z;
  private Box[][][] containerSpace = new Box[arrayIndex(containerX)][arrayIndex(containerY)][arrayIndex(containerZ)];

  public void Start() {

  }
  public boolean checkDimensions() {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
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
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    Box[][][] boxSpace = new Box[arrayIndex(sizeX)][arrayIndex(sizeY)][arrayIndex(sizeZ)];
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


  public void placeBox() {
    if(true) {
      for(int i=0; i<priorityOrder[0].size(); i++) {
        Box bufferBox = priorityOrder[0].get(i);
      }
    }
  }

  public void removeBox() {

  }

  public void makeOrder() {
    boxOrder[0] = new BoxA();
    boxOrder[1] = new BoxB();
    boxOrder[2] = new BoxC();
    amountOrder[0] = amountA;
    amountOrder[1] = amountB;
    amountOrder[2] = amountC;
    for(int i=0; i<3; i++) {
      volumeOrder[i] = boxOrder[i].getVolume()
    }
  }

  public void makeBoxList() {
    for(int i=0; i<3 i++) {
      for(int j=0; i<amountOrder[i]; j++) {

      }
    }
  }
/*
  public Box[][][] xRotateBox(Box box) {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    Box[][][] rotatedBox = new Box[arrayIndex(sizeX)][arrayIndex(sizeZ)][arrayIndex(sizeY)];
    fillSpace(rotatedBox, box);
    return rotatedBox;
  }

  public Box[][][] yRotateBox(Box box) {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    Box[][][] rotatedBox = new Box[arrayIndex(sizeZ)][arrayIndex(sizeY)][arrayIndex(sizeX)];
    fillSpace(rotatedBox, box);
    return rotatedBox;
  }

  public Box[][][] zRotateBox(Box box) {
    double sizeX = box.getSize().x;
    double sizeY = box.getSize().y;
    double sizeZ = box.getSize().z;
    Box[][][] rotatedBox = new Box[arrayIndex(sizeY)][arrayIndex(sizeX)][arrayIndex(sizeZ)];
    fillSpace(rotatedBox, box);
    return rotatedBox;
  }

  public void makeSetOfBoxesA(int amount) {
    if(amountA != 0) {
      for(int i=0; i<amountA; i++) {
        listA.add(new BoxA());
      }
    }
  }

  public void makeSetOfBoxesB(int amount) {
    if(amountB != 0) {
      for(int i=0; i<amountA; i++) {
        listB.add(new BoxB());
      }
    }
  }

  public void makeSetOfBoxesC(int amount) {
    if(amountC != 0) {
      for(int i=0; i<amountA; i++) {
        listC.add(new BoxC());
      }
    }
  }

  public void orderList() {
    boolean hasChanged = true;
    double volumeBuffer = 0;
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
