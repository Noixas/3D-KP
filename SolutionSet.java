import java.util.*;
import java.util.LinkedList;

public class SolutionSet {
private List<Parcel> _solution;
private long _startTime;
private double _value;
private long _endTime;
private long _totalTime;
public SolutionSet(long pStartTime){
        _startTime = pStartTime;
        _solution = new LinkedList<Parcel>();
        _value = 0;
}
public SolutionSet() {
}
public void addParcel(Parcel pParcel)
{
        _solution.add(pParcel);
}
public Parcel get(int i)
{
        return _solution.get(i);
}
public int getLength()
{
        return _solution.size();
}
/**
 * Delete Parcel from solution set
 * @param  Parcel pParcel          [Parcel to be deleted]
 * @return  true if parcel was found and deleted
 */
public boolean deleteParcel(Parcel pParcel)
{
        _solution.remove(pParcel);
        return true;
}
public double getValue()
{
        return _value;
}
public void endSolution(long pEndTime)
{
        _endTime = pEndTime;
        _totalTime = _endTime - _startTime;
        for(int i = 0; i<_solution.size(); i++)
        {
                _value += _solution.get(i).getValue();
        }
}
public void calculateCurrentValue()
{
  _value = 0;
  for(int i = 0; i<_solution.size(); i++)
  {
          _value += _solution.get(i).getValue();
  }
}
public long getTotalTime()
{
        return _totalTime;
}
public int getParcelTypeCount(Parcel pParcel)
{
        //Todo: implement method that return the amount of parcels of type T in solution
        return 0;
}

}
