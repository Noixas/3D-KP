import java.util.*;

public class GreedyAlgorithm extends Algorithm {
  private int amountA;
  private int amountB;
  private int amountC;
  private Container container = new Container();
  private Vector3D containerSize = container.getSize();
  private double containerX = containerSize.x;
  private double containerY = containerSize.y;
  private double containerZ = containerSize.z;
  private double VolA;
  private double VolB;
  private double VolC;
  private ArrayList<BoxA> listA = new ArrayList<BoxA>();
  private ArrayList<BoxB> listB = new ArrayList<BoxB>();
  private ArrayList<BoxC> listC = new ArrayList<BoxC>();
  //private ArrayList<Box>[] priorityOrder = new ArrayList[3];
  private double[] volumeOrder = new double[3];
  private int index = 0;

  private Box[][][] containerSpace = new Box[arrayIndex(containerX)][arrayIndex(containerY)][arrayIndex(containerZ)];


  public void Start() {
    makeSetOfBoxesA(amountA);
    makeSetOfBoxesB(amountB);
    makeSetOfBoxesC(amountC);
    if(!listA.isEmpty()) {
      VolA = listA.get(0).getVolume();
      priorityOrder[index] = listA;
      volumeOrder[index] = VolA;
      index++;
    }
    if(!listB.isEmpty()) {
      VolB = listB.get(0).getVolume();
      priorityOrder[index] = listB;
      volumeOrder[index] = VolB;
      index++;
    }
    if(!listC.isEmpty()) {
      VolC = listC.get(0).getVolume();
      priorityOrder[index] = listC;
      volumeOrder[index] = VolC;
      index++;
    }
    orderList();

  }


  public void placeBox() {

  }

  public void removeBox() {

  }

  public void xRotateBox() {

  }

  public void yRotateBox() {

  }

  public void zRotateBox() {

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
    double b = 0;
    ArrayList<Box> buffer;
    while(hasChanged) {
      hasChanged = false;
      for(int i=0;i<index;i++) {
        if(volumeOrder[i]<volumeOrder[i+1]) {
          b = volumeOrder[i];
          buffer = priorityOrder[i];
          volumeOrder[i] = volumeOrder[i+1];
          priorityOrder[i] = priorityOrder[i+1];
          volumeOrder[i+1] = b;
          priorityOrder[i+1] = buffer;
          hasChanged = true;
        }
      }
    }
  }

  public int arrayIndex(double number) {
    return (int)number*2;
  }
}
