import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * Round robin extends tournament. It contains the same instance variables as Tournament, but they 
 * will be defined within this class. This the tournament format where a player plays every other player.
 */
public class RoundRobin extends Tournament
{
   private ArrayList<Player> rrhold1;
   private ArrayList<Player> rrhold2;
   private boolean iseven; 
   private Player mtemp;
   public RoundRobin(String tname)//sets name of roundrobin tournament
   {
     super(tname);
     rrhold1 = new ArrayList<Player>();
     rrhold2 = new ArrayList<Player>();
    }
   public void setEntrants(String[] a)//sets entrants of tournament
   {
       standings = new Player[a.length];
       for(int x = 0; x<standings.length; x++)
       {
           standings[x] = new Player(a[x]);
       }
       Player[] enters = standings.clone();
       for(int x = 0; x<enters.length; x++)//randomizer
        {
           int random = (int)(Math.random()*(enters.length-1));//randoming part very annoying 
           Player temp = enters[random]; //gets random player
           enters[random] = enters[x]; // puts random player in current index
           enters[x] = temp; //swaps the person for the index
        }
       //adds the players to both arrays
        if(standings.length%2==0)    
        {
            for(int x = 0;x<enters.length/2; x++)
            {
                //set two arrays here
                rrhold1.add(enters[x]);
                rrhold2.add(enters[enters.length/2+x]);
                //http://en.wikipedia.org/wiki/Round-robin_tournament#Scheduling_algorithm  wiki life
            }
            iseven = true;//for now
        }
        //new stuff
        else
        {
             for(int x = 0;x<(enters.length+1)/2; x++)
            {
                //set two arrays here
                rrhold1.add(enters[x]);
                if(x<(enters.length+1)/2-1)
                {
                    rrhold2.add(enters[(enters.length+1)/2+x]);
                }
                //http://en.wikipedia.org/wiki/Round-robin_tournament#Scheduling_algorithm  wiki life
            }
            rrhold2.add(null);//add null for the player with the bye 
            iseven = false;
        }
              //check odd or even here if odd, set one of the players to null and below, always check if a player is null regardless of odd or even, if neither of the players are null, add match
       //then rearrange shet again, moving null as well yeye
    }
   public void setRounds()//sets the rounds of the roundrobin tournament
   {
       if(iseven)
       {
           round = standings.length - 1;
       }
       else
       {
           round = standings.length;
       }
   }
   public void makePairings()//makes pairings of the current round 
   { 
       Match[] tempm = new Match[rrhold1.size()];
       if((iseven))
       {
           if(cround==0)
           {
               for(int x = 0;x<rrhold1.size();x++)
               {
                   tempm[x] = new Match(rrhold1.get(x),rrhold2.get(x));
               }
           }
           else if((!(cround==round))&&(!(cround==0)))
           {
               for(int x = 0; x<rrhold1.size();x++)
               {
                   track.getResolved().get(x).getWinner().addScore();
               }
               rrhold1.add(1,rrhold2.get(0));
               rrhold2.remove(0);
               rrhold2.add(rrhold2.size()-1, rrhold1.get(rrhold1.size()-1));                 
               rrhold1.remove(rrhold1.size()-1);
               for(int x = 0;x<rrhold1.size();x++)
               {
                   tempm[x] = new Match(rrhold1.get(x),rrhold2.get(x));
               }
            }
        }
       if(!(iseven))
       {
           tempm = new Match[rrhold2.size()-1];
           if(cround==0)
           {
               for(int x = 0;x<rrhold1.size();x++)
               {
                   if(((rrhold1.get(x)==null))||(((rrhold2.get(x))==null)))
                   {
                       
                   }
                   else
                   {
                       tempm[x] = new Match(rrhold1.get(x),rrhold2.get(x));
                   }
               }
           }
           if((!(cround==round))&&(!(cround==0)))
           {
               for(int x = 0; x<rrhold2.size()-1;x++)
               {
                   track.getResolved().get(x).getWinner().addScore();
               }
               rrhold1.add(1,rrhold2.get(0));
               rrhold2.remove(0);
               rrhold2.add(rrhold2.size()-1, rrhold1.get(rrhold1.size()-1));                 
               rrhold1.remove(rrhold1.size()-1);
               for(int x = 0;x<rrhold2.size();x++)
               {
                   if(((rrhold1.get(x)==null))||(((rrhold2.get(x))==null)))
                   {
                       if(rrhold1.get(x)==null)
                       {
                           mtemp = rrhold2.get(x);
                       }
                       else
                       {
                           mtemp = rrhold1.get(x);
                       }
                   }
                   else
                   {
                       tempm[x] = new Match(rrhold1.get(x),rrhold2.get(x));
                   }
               }              
           }
        }
       ArrayList<Match> tempa = new ArrayList<Match>(Arrays.asList(tempm));
       track.storeMatches(tempa);
       cround++;
   }
   public void setWinner()//sets the winner of the roundrobin tournament
   {
       ArrayList<Player> a = new ArrayList<Player>();
       if(iseven)
       {
            for(int x = 0; x<rrhold1.size();x++)
           {
               track.getResolved().get(x).getWinner().addScore();
           }
           for(int x = 0; x<standings.length/2;x++)
           {
               a.add(track.getResolved().get(x).getP1());
               a.add(track.getResolved().get(x).getP2());
           }
       }
       else if(!(iseven))
       {
            for(int x = 0; x<rrhold2.size()-1;x++)
           {
               track.getResolved().get(x).getWinner().addScore();
           }
           for(int x = 0; x<track.getResolved().size();x++)
           {  
               a.add(track.getResolved().get(x).getP1());
               a.add(track.getResolved().get(x).getP2());       
           }
           a.add(mtemp);
       }
       Collections.sort(a, new PlayerComparator());
       standings = a.toArray(new Player [a.size()]);
       winner = standings[0];
   }
   public String getStandings()//overrides the tournament standings to include points 
    {
        String cstanding = "Tournament Standings\n";
        for(int x = 0; x<standings.length;x++)
        {
            cstanding+= (x+1) + ". " + standings[x].getName() + " points: " + standings[x].getScore() + "\n";
        }
        return cstanding;
    }
}

