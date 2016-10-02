
/**
 * Swiss extends tournament. It contains the same instance variables as Tournament, but they 
 * will be defined within this class. This tournament style involves a player playing a set amount of 
 * games (rounds). 
 * 
 * The points are not being added to the players in the matches, when the standings are printed, everyone has 0 points.
 */
import java.util.*;
import javax.swing.JOptionPane;
public class Swiss extends Tournament
{
   JOptionPane pane; 
   ArrayList <Player> pArray;
   ArrayList<Match> tempa;
   Match[] tempm;
   public Swiss(String tname)//gets name for swiss
   {
     super(tname);
     pane = new JOptionPane(); 
    pArray = new ArrayList<Player>();
     tempa = new ArrayList<Match>();
    }
   public void setRounds()//sets the rounds of swiss tournament
   {
      String tempRound = pane.showInputDialog(null, "Enter the number of rounds for this swiss style tournament: " );
      round = Integer.parseInt(tempRound);
    }
   public void setEntrants(String[] a)//sets # of entrants of swiss tournament
   {
       standings = new Player[a.length];
       for(int x = 0; x<standings.length; x++)
       {
           standings[x] = new Player(a[x]);
       }
       
       for(int x = 0; x<standings.length; x++)//randomizer
        {
           int random = (int)(Math.random()*(standings.length-1));//creates a random number that coincides to 0 - length of player array 
           Player temp = standings[random]; //gets random player
           standings[random] = standings[x]; // puts random player in current index
           standings[x] = temp; //swaps the person for the index
        }
   }
   public void makePairings()//makes pairings for current round
   {
       if(cround == 0)//set starting pairings for first round 
       {
           tempm = new Match[standings.length/2];
           for(int x = 0; x<standings.length; x+=2)
           {
               // makes pairing based on those who are still in tourney 
               tempm[x/2] = new Match(standings[x], standings[x+1]);
           }
        }
       // this part sorts the standings array by player score, then makes the pairings
       else if(cround<round)
       {    
           //sort tempa by player points
           //make pairings pairing of the first two players, then the following pairs of players
           //store the arraylist using track.storeMatches
           
           for(int x = 0; x<standings.length/2;x++)
           {
             track.getResolved().get(x).getWinner().addScore();
            }
           pArray.clear();
           for(int i = 0; i < standings.length/2; i++)
           {
             pArray.add(track.getResolved().get(i).getP1());
             pArray.add(track.getResolved().get(i).getP2());
            }
           Collections.sort(pArray, new PlayerComparator()); 
           for(int x = 0; x<standings.length; x+=2)
           {
               // makes pairing based on those who are still in tourney 
               tempm[x/2] = new Match(pArray.get(x), pArray.get(x+1));
           } 
           
       }
       else
       {
        setWinner();
        }
       tempa = new ArrayList(Arrays.asList(tempm));
       track.storeMatches(tempa);
       cround++;  
    } 
   public void setWinner()//sets winner of the tournament
   {
       Collections.sort(pArray, new PlayerComparator());
       standings = pArray.toArray(new Player[pArray.size()]);
       winner = standings[0];// the person at the top of the array is the winner
   }
   public String getStandings()//overrides getStandings to include points
    {
        for(int x = 0; x<standings.length/2;x++)
        {
             track.getResolved().get(x).getWinner().addScore();
        }
        String cstanding = "Tournament Standings\n";
        for(int x = 0; x<standings.length;x++)
        {
            cstanding+= (x+1) + ". " + standings[x].getName() + " points: " + standings[x].getScore() + "\n";
        }
        return cstanding;
    }
}


