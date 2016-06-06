/**
 * PlayerComparator class - overrides compare method to change how the ArrayList's sort works 
 */
import java.util.Comparator;
public class PlayerComparator implements Comparator <Player>
{
  public int compare (Player p1, Player p2)//overriden compare method
   {
     return p2.getScore() < p1.getScore() ? -1: p1.getScore() == p2.getScore() ? 0: 1 ;
   } 
}