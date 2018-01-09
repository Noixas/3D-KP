import java.util.*;
public class SolutionSet{
  private List<Box> _solution;
  private double _startTime;
  private double _value;
  private double _endTime;
  private double _totalTime;
  public SolutionSet(double pStartTime){
    _solution = new LinkeList<Box>();
    _value = 0;
  }
  public void AddBox(Box pBox)
  {
    _solution.add(pBox);
  }
  //*
   * Delete Box from solution set
   * @param  Box pBox          [Box to be deleted]
   * @return     [true if box was found and deleted]
   */
  public boolean deleteBox(Box pBox)
  {
    _solution.remove(pBox);
    return true;
  }
  public double getValue()
  {
    return _value;
  }
  public void EndSolution(double pEndTime)
  {
    _endTime = pEndTime;
    _totalTime = _endTime - _startTime;
    for(int i = 0;i<_solution.size();i++)
    {
      _value += _solution.get(i).getValue();
    }
  }
  public double getTotalTime()
  {
    return _totalTime;
  }
  public int getBoxTypeCount(Box pBox)
  {
    //Todo: implement method that return the amount of boxes of type T in solution
  }

}
