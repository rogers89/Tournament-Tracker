import java.util.ArrayList;
import java.util.Arrays;
/**
 * Elimination extends from tournament. It contains the same instance variables as Tournament, but they 
 * will be defined within this class. The elimination style tournament involves a player being eliminated
 * once they lose either one or two games depending on the specific tournament style.
 */
public class Elimination extends Tournament
{
   private Player[] stillin;//array that will keep updating --> deleting disqualified until there is one player left
   private Player treefor;
   private int counter;
   public Elimination (String tname)//receives the name of the Elimination Tournament and sends it to tournament constructor
   {
     super (tname);
   }
   public void setEntrants(String[] a)//overrides setting entrants
   {
        standings = new Player[a.length];
        for(int x = 0; x<standings.length; x++)
        {
            standings[x] = new Player(a[x]);
        }
        stillin = standings.clone();
        counter = standings.length-1;
        for(int x = 0; x<stillin.length; x++)//randomizer
        {
           int random = (int)(Math.random()*(stillin.length-1));//randoming part very annoying 
           Player temp = stillin[random]; //gets random player
           stillin[random] = stillin[x]; // puts random player in current index
           stillin[x] = temp; //swaps the person for the index
        }
   }
   public void setRounds()//sets the rounds of a tournament 
   {
      if(!(standings.length%2>0))
      {
          round = (standings.length/2)-1;
      }
      else
      {
          System.out.println("You need an even number of players to run an elimination style tournament.");
      }
   }  
   public void makePairings()//makes pairings for the current round of the tournament
   {
       if(cround == 0)//set starting pairings for first round 
       {
           Match[] tempm = new Match[stillin.length/2];
           for(int x = 0; x<stillin.length; x+=2)
           {
               tempm[x/2] = new Match(stillin[x], stillin[x+1]);// makes pairing based on those who are still in tourney 
           }
           ArrayList<Match> tempa = new ArrayList<Match>(Arrays.asList(tempm));
           track.storeMatches(tempa);
       }
       else if(cround<(round-1))
       {    
           Player[] temp = new Player[stillin.length/2];
           for(int x = 0; x<track.getResolved().size();x++)
           {
               temp[x] = track.getResolved().get(x).getWinner();//goes into resolved array and goes into its matches to grab winners for new pairings
               standings[counter] = track.getResolved().get(x).getLoser();//setting losers from bottom
               counter--;
            }
           stillin = temp;
           Match[] tempm = new Match[stillin.length/2];
           for(int x = 0; x<stillin.length; x+=2)
           {
               tempm[x/2] = new Match(stillin[x], stillin[x+1]);// makes pairing based on those who are still in tourney 
           }
           ArrayList<Match> tempa = new ArrayList<Match>(Arrays.asList(tempm));
           track.storeMatches(tempa);
       }
       if(cround==(round-1))
       {
           Player[] temp = new Player[stillin.length];
           for(int x = 0; x<stillin.length;x+=2)
           {
               temp[x] = track.getResolved().get(x/2).getWinner();//goes into resolved array and goes into its matches to grab winners for new pairings
               temp[x+1] = track.getResolved().get(x/2).getLoser();//gets loser for 3rd place
           }
           treefor = temp[1];
           stillin = temp;
           Match[]tempm = new Match[stillin.length/2];
           for(int x = 0; x<tempm.length; x++)
           {
               tempm[x] = new Match(stillin[x], stillin[x+2]);// makes pairing based on those who are still in tourney 
           }
           ArrayList<Match> tempa = new ArrayList<Match>(Arrays.asList(tempm));
           track.storeMatches(tempa);
       }
       cround++;
   }    
   public void setWinner()//sets the winner of the elimination tournament
   {
       for(int x = 0; x<track.getResolved().size();x++)
       {
           if((track.getResolved().get(x).getP1()==treefor)||(track.getResolved().get(x).getP2()==treefor))//check if third place match, if not then set winner 
           {
               standings[2] = track.getResolved().get(x).getWinner();
               standings[3] = track.getResolved().get(x).getLoser();
           }
           else
           {
               winner = track.getResolved().get(x).getWinner();
               standings[0] = winner;
               standings[1] = track.getResolved().get(x).getLoser();
           }
       }
   }
}



