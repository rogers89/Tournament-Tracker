
/**
 * Takes in a name parameter from Tournament GUI and is stored 
 * inside of an array inside of each tournament 
 * and called and sorted for standings
 */
public class Player 
{
   private int gamesWon;
   private int gamesLost;
   private double score;
   private String name;
   public Player(String n)//Takes in the string as argument for name 
   {
    name = n;
    score = 0;
    gamesWon = 0;
    gamesLost = 0;
   }
   //return statement for variables 
   public int getGamesWon()
   {
    return gamesWon;
   }
   public int getGamesLost()
   {
    return gamesLost;
   } 
   public double getScore()
   {
    return score;
    }
   public void addScore()//adds score up of player, gets called by match because match declares the adding
   {
       score++;
   }
   public String getName()
   {
    return name;
    } 
   public String toString()
   {
    return (name + " has won " + gamesWon + " and lost " + gamesLost + ".");
    }
    public boolean isSamePlayer(Player other)
    {
        boolean check = false;
        return check;
    }
}


