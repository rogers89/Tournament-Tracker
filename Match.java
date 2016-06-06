public class Match
{
    private Player player1;
    private Player player2;
    private boolean completed;
    private Player winner;
    private Player loser;
    public Match(Player p1, Player p2)
    {
        player1 = p1;
        player2 = p2;
        completed = false;
    }
    public void setWinner(Player win)
    {
        if(win==player1)
        {
            winner = player1;
            loser = player2;
        }
        if(win==player2)
        {
           winner = player2;
           loser = player1;
        }
    }
    public Player getWinner()
    {
        return winner;
    }
    public Player getLoser()
    {
        return loser;
    }
    public Player getP1()
    {
        return player1;
    }
    public Player getP2()
    {
        return player2;
    }
    public String toString()
    {
        String result = player1.getName() + " vs. " + player2.getName();
        return result;
    }
}