import java.util.*;
import java.util.LinkedList;

public abstract class Algorithm {
protected boolean _done = false;
protected List<SolutionSet> _solutions = new LinkedList<SolutionSet>();
/**
 * Start computing solutions separated from constructor to be able to configure it with the UI
 * and use Start button from UI
 */
public abstract void Start(List<Parcel> list);
/**
 * Check if the Algorithm has finished
 * @return true if algorithm has ended
 */
public boolean isDone()
{
        return _done;
}
/**
 * Get list of all SolutionSet found
 * @return [list of solutionSets]
 */
public List<SolutionSet> getSolutions()
{
        return _solutions;
}
public abstract void display();
/**
 * Add a solutionSet to the total solution found by this algorithm
 * @param SolutionSet pSolution [the new solution to be added]
 */
public void AddSolution(SolutionSet pSolution)
{
        _solutions.add(pSolution);
}
}
