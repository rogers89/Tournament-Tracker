import java.util.ArrayList;
/**
 * Abstract class Tournament - that holds the basics of any tournament for our program, goes in conjunction with our GUI. Abstract 
 * methods are actions that each Tournament has in common but does differently where as it also contains methods that return things
 * that all tournaments know
 */
public abstract class Tournament
{
    // instance variables - replace the example below with your own
    protected String name;
    protected int prize;
    protected Tracker track;
    protected Player[] standings;
    protected int round;
    protected int cround;
    protected Player winner;
//    protected 
    public Tournament(String tname)//Parameter is the tournament's name
    {
        name = tname;
        track = new Tracker();
        cround = 0;
    }
    abstract protected void setRounds();//method to be overridden that is one that sets # of rounds of a tournament
    abstract protected void makePairings();//method to be overridden that makes pairings for a specific round for each tourney differently
    abstract protected void setWinner();//winner is set differently in each tournament - different meanings of a winner but all have one
    abstract protected void setEntrants(String[] a);//All tournaments sets their entrants in different ways/groups
    public Tracker getTracker()//returns the tournament's tracker 
    {
        return track;
    }
    public int getRound()//returns the tournaments' max # of rounds
    {
        return round;
    }
    public int getCround()//returns the tournaments' current round
    {
        return cround;
    }
    public Player getWinner()//returns the tournaments' winner - all tournaments have one
    {
        return winner;
    }
    public String getStandings()//gets the standings of a tournament - by default it just gets the name although some tournaments override to include points
    {
        String cstanding = "Tournament Standings\n";
        for(int x = 0; x<standings.length;x++)
        {
            cstanding+= (x+1) + ". " + standings[x].getName() + "\n";
        }
        return cstanding;
    }
    public String toString()//returns the name of the tournament
    {
        return name;
    }
}
