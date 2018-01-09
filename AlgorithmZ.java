import java.util.*;
import java.util.LinkedList;
import java.util.Collections;
public class AlgorithmZ extends Algorithm
{
  private Container _container;
  private SolutionSet _solution;
  private List<Vector3D> listEP;
  public AlgorithmZ(){
    _solution = new SolutionSet(0);
    _container = new Container();
    listEP = new LinkedList<Vector3D>();
    Start();

  }
  public void Start(){
    System.out.println("Algorithm Z starting...");
  }
  public void updateEP(SolutionSet placedBoxes, List<Vector3D> EP, Box newBox)
  {
    double[] maxBound = {-1,-1,-1,-1,-1,-1};
    Vector3D[] newEP = new Vector3D[6];
    for(int i = 0; i<placedBoxes.getLength();i++)
    {
      Box aBox = placedBoxes.get(i);
      Vector3D aSize = aBox.getSize();
      Vector3D aPos = aBox.getPosition();

      Vector3D newBoxPos = newBox.getPosition();
      Vector3D newBoxSize = newBox.getSize();
      if(canTakeProjection(newBox, aBox, new Vector3D(1,1,0)) && (aPos.x + aSize.x > maxBound[0])){
        newEP[0] = new Vector3D(aPos.x + aSize.x, newBoxPos.y + newBoxSize.y,newBoxPos.z);//Done
        maxBound[0] =  aPos.x + aSize.x;
      }
      if(canTakeProjection(newBox, aBox, new Vector3D(0,1,1)) && (aPos.z + aSize.z > maxBound[1])){
        newEP[1] = new Vector3D(newBoxPos.x, newBoxPos.y + newBoxSize.y, aPos.z + aSize.z);
        maxBound[1] =  aPos.z + aSize.z;//DONE
      }
      if(canTakeProjection(newBox, aBox, new Vector3D(1,1,0)) && (aPos.y + aSize.y > maxBound[2])){
        newEP[2] = new Vector3D(newBoxPos.x + newBoxSize.x, aPos.y + aSize.y,newBoxPos.z);
        maxBound[2] = aPos.y + aSize.y;//DONE
      }
      if(canTakeProjection(newBox, aBox, new Vector3D(1,0,1)) && (aPos.z + aSize.z > maxBound[3])){
        newEP[3] = new Vector3D(newBoxPos.x + newBoxSize.x, newBoxPos.y, aPos.z + aSize.z);
        maxBound[3] =  aPos.z + aSize.z;//Done
      }
      if(canTakeProjection(newBox, aBox, new Vector3D(1,0,1)) && (aPos.x + aSize.x > maxBound[4])){
        newEP[4] = new Vector3D(aPos.x + aSize.x, newBoxPos.y ,newBoxPos.z+newBoxSize.z);
        maxBound[4] =  aPos.x + aSize.x;
      }
      if(canTakeProjection(newBox, aBox, new Vector3D(0,1,1)) && (aPos.y + aSize.y > maxBound[5])){
        newEP[5] = new Vector3D(newBoxPos.x, aPos.y + aSize.y, newBoxPos.z + newBoxSize.z);
        maxBound[5] = aPos.y + aSize.y;
      }
    }
    for(int i = 0; i < newEP.length; i++)
    {
      EP.add(newEP[i]);
    }
    Collections.sort(EP);
    removeDuplicatedEP(EP);
    //TODO; ordering of EP and deleting duplicated
    //TODO; can use comparable interface
  }
  public boolean canTakeProjection(Box newBox, Box prevBox, Vector3D axis)
  {
    if(axis.x != 0 && newBox.getPosition().x <= prevBox.getPosition().x == false)//Can't be proyected into x so return
    return false;
    else if(axis.y != 0 && newBox.getPosition().y <= prevBox.getPosition().y == false)//Can't be proyected into y so return
    return false;
    else if(axis.z != 0 && newBox.getPosition().z <= prevBox.getPosition().z == false)//Can't be proyected into z so return
    return false;
    else
    return true;
  }
  public void removeDuplicatedEP(List<Vector3D> ep)
  {
    List<Vector3D> newList = new LinkedList<Vector3D>();
    newList.add(ep.get(0));
    ep.remove(0);
    while(ep.size() != 0)
    {
      if(newList.get(0) != ep.get(0))
          newList.add(ep.get(0));
      ep.remove(0);
    }
    ep = newList;
  }
}
