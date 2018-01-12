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
  public void updateEP(SolutionSet placedParceles, List<Vector3D> EP, Parcel newParcel)
  {
    double[] maxBound = {-1,-1,-1,-1,-1,-1};
    Vector3D[] newEP = new Vector3D[6];
    for(int i = 0; i<placedParceles.getLength();i++)
    {
      Parcel aParcel = placedParceles.get(i);
      Vector3D aSize = aParcel.getSize();
      Vector3D aPos = aParcel.getPosition();

      Vector3D newParcelPos = newParcel.getPosition();
      Vector3D newParcelSize = newParcel.getSize();
      if(canTakeProjection(newParcel, aParcel, new Vector3D(1,1,0)) && (aPos.x + aSize.x > maxBound[0])){
        newEP[0] = new Vector3D(aPos.x + aSize.x, newParcelPos.y + newParcelSize.y,newParcelPos.z);//Done
        maxBound[0] =  aPos.x + aSize.x;
      }
      if(canTakeProjection(newParcel, aParcel, new Vector3D(0,1,1)) && (aPos.z + aSize.z > maxBound[1])){
        newEP[1] = new Vector3D(newParcelPos.x, newParcelPos.y + newParcelSize.y, aPos.z + aSize.z);
        maxBound[1] =  aPos.z + aSize.z;//DONE
      }
      if(canTakeProjection(newParcel, aParcel, new Vector3D(1,1,0)) && (aPos.y + aSize.y > maxBound[2])){
        newEP[2] = new Vector3D(newParcelPos.x + newParcelSize.x, aPos.y + aSize.y,newParcelPos.z);
        maxBound[2] = aPos.y + aSize.y;//DONE
      }
      if(canTakeProjection(newParcel, aParcel, new Vector3D(1,0,1)) && (aPos.z + aSize.z > maxBound[3])){
        newEP[3] = new Vector3D(newParcelPos.x + newParcelSize.x, newParcelPos.y, aPos.z + aSize.z);
        maxBound[3] =  aPos.z + aSize.z;//Done
      }
      if(canTakeProjection(newParcel, aParcel, new Vector3D(1,0,1)) && (aPos.x + aSize.x > maxBound[4])){
        newEP[4] = new Vector3D(aPos.x + aSize.x, newParcelPos.y ,newParcelPos.z+newParcelSize.z);
        maxBound[4] =  aPos.x + aSize.x;
      }
      if(canTakeProjection(newParcel, aParcel, new Vector3D(0,1,1)) && (aPos.y + aSize.y > maxBound[5])){
        newEP[5] = new Vector3D(newParcelPos.x, aPos.y + aSize.y, newParcelPos.z + newParcelSize.z);
        maxBound[5] = aPos.y + aSize.y;
      }
    }
    for(int i = 0; i < newEP.length; i++)
    {
      EP.add(newEP[i]);
    }
    Collections.sort(EP);
    removeDuplicatedEP(EP);
    //DONE; ordering of EP and deleting duplicated
    //Done; can use comparable interface
  }
  public boolean canTakeProjection(Parcel newParcel, Parcel prevParcel, Vector3D axis)
  {
    if(axis.x != 0 && newParcel.getPosition().x <= prevParcel.getPosition().x == false)//Can't be proyected into x so return
    return false;
    else if(axis.y != 0 && newParcel.getPosition().y <= prevParcel.getPosition().y == false)//Can't be proyected into y so return
    return false;
    else if(axis.z != 0 && newParcel.getPosition().z <= prevParcel.getPosition().z == false)//Can't be proyected into z so return
    return false;
    else
    return true;
  }
  /**
   * Remove elements that are duplicated in the list
   * @param List<Vector3D> ep [List of current ExtremePoints]
   */
  public void removeDuplicatedEP(List<Vector3D> ep)
  {//TODO move it to solution set class maybe
    List<Vector3D> newList = new LinkedList<Vector3D>();
    newList.add(ep.get(0));
    ep.remove(0);
    while(ep.size() != 0)
    {
      if(newList.get(0).equals(ep.get(0)) == false)
          newList.add(ep.get(0));
      ep.remove(0);
    }
    ep = newList;
  }
}
