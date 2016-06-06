/**
 * The MenuGUI class is the main GUI class that faciliates the commands of the programs depending on what event it receives from its ActionListener. 
 * It has a tournamentGUI object which it calls to do its commands.
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class MenuGUI extends JFrame implements ActionListener
{
    //GUI components as well as a TournamentGUI object for functions
    private TournamentGUI tourns;
    private JFrame menu;
    private JButton create;
    private JButton exist;
    private JButton quit;
    public MenuGUI() //Constructor that sets the menu JFrame and adds all its components 
    {
        tourns = new TournamentGUI();
        menu = new JFrame("Tournament menu");
        menu.setLayout(new FlowLayout());
        menu.setPreferredSize(new Dimension(300, 140));
        menu.pack();
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        create = new JButton("Create a new Tournament");
        exist = new JButton("View existing Tournament");
        quit = new JButton("Quit Program");
        
        create.addActionListener(this);
        exist.addActionListener(this);
        quit.addActionListener(this);
        
        menu.add(create);
        menu.add(exist);
        menu.add(quit);
    }
    public void showMenu()//sets the JFrame visibily to true
    {
        menu.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)//Each button under this method does a command that is facilitated by the tournamentGUI object within menuGUI
    {
        if(e.getSource()==create)
        {
            tourns.makeTourney();
        }
        if(e.getSource()==exist)
        {
            tourns.trackTourney();
        }
        if(e.getSource()==quit)
        {
            System.exit(0);
        }
    }
}