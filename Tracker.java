import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class Tracker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tracker
{
    private ArrayList<Match> resolved;
    private ArrayList<Match> unresolved;
    public Tracker()
    {
        unresolved = new ArrayList<Match>();
        resolved = new ArrayList<Match>();
    }   
    public void storeMatches(ArrayList<Match> a)
    {
        unresolved = a;
    }
    public void removeP(Player leaver)
    {
        
    }
    public void editMatch(int i, Match m)
    {
        resolved.remove(i);
        resolved.add(i, m);
    }
    public void resolveMatch(int i, Match m)
    {
        unresolved.remove(i);
        resolved.add(m);
    }
    public void resolveround()
    {
        
    }
    public ArrayList<Match> getResolved()
    {
        return resolved;         
    }
    public ArrayList<Match> getUnresolved()
    {
        return unresolved;
    }   
}
