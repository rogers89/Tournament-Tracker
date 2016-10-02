/**
 * The TournamentGUI is the main GUI that the user will be interacting with, it keeps tracks of all the matches as well as tournaments going on
 * To organise the code and components, for each different function there is a new JDialog that is called with its appropriate buttons
 * The menuGUI refers back to the TournamentGUI to view previously completed/uncompleted tournaments to see standings and what not 
 */
import java.util.ArrayList;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TournamentGUI implements ActionListener
{
    private JDialog tgui;
    private JDialog ngui;
    private JButton swiss;
    private JButton elimination;
    private JButton roundrobin;
    private JButton quit;
    private JButton choose;
    private JButton remove;
    private JButton cancel;
    private JList tlist;
    private JScrollPane tsp;
    private ArrayList<Tournament> tourneys;
    private String tname;
    //file reader
    private FileReader read;
    public TournamentGUI()//Defines most components of the GUI in its constructor 
    {
        tourneys = new ArrayList<Tournament>();  
        read = new FileReader();
        //for tourney tracker
        tgui = new JDialog();
        tgui.setTitle("Tournament Interface");
        tgui.setLayout(new FlowLayout());
        tgui.setPreferredSize(new Dimension(240,320));
        tgui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tgui.pack();
        tgui.setLocationRelativeTo(null);
        tgui.setModal(true);
        
        choose = new JButton("Select");
        remove = new JButton("Remove");
        cancel = new JButton("Cancel");
        tlist = new JList(tourneys.toArray(new Tournament[tourneys.size()]));
        tlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tsp = new JScrollPane(tlist);
        tsp.setPreferredSize(new Dimension(200, 200));
      
        tgui.add(tsp);
        tgui.add(choose);
        tgui.add(remove);
        tgui.add(cancel);
        
        choose.addActionListener(this);
        cancel.addActionListener(this);
        remove.addActionListener(this);
 
        ngui = new JDialog();
        ngui.setTitle("Tournament Interface");
        ngui.setLayout(new FlowLayout());
        ngui.setPreferredSize(new Dimension(300,110));
        ngui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ngui.pack();
        ngui.setLocationRelativeTo(null);
        ngui.setModal(true);
    
        swiss = new JButton("Swiss");
        elimination = new JButton("Elimination");
        roundrobin = new JButton("RoundRobin");
        quit = new JButton("Quit");    
       
        ngui.add(elimination);
        ngui.add(swiss);
        ngui.add(roundrobin);
        ngui.add(quit);
        
        swiss.addActionListener(this);
        elimination.addActionListener(this);
        roundrobin.addActionListener(this);
        quit.addActionListener(this);
      

        //tgui.add(paneln, "New Tournament");
        //tgui.add(panelt, "Track Tournament");
      
        makeTourney();
    }
    public void makeTourney()//Calls the JDialog that has components to make a new tournament
    {
        if(getInfo())// gathers the tournament name and prize, along with the # of players if user does not press cancel or sumthing
        {
            ngui.setVisible(true);
        }
    }
    public void trackTourney()//calls the jdialog that has the components to track previous tournaments
    {
        tgui.setVisible(true);
    }
    public void setTourney(Tournament a)//Calls the tracker component to track a given tournament 
    {
        current = a;
        
        tracker = new JDialog();
        tracker.setTitle("Match Tracker");
        tracker.setLayout(new FlowLayout());
        tracker.setPreferredSize(new Dimension(450, 425));
        tracker.pack();
        tracker.setLocationRelativeTo(null);
        tracker.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tracker.setModal(true);
        
        jtname = new JLabel(current.toString());
        jtname.setFont(new Font("Times", Font.BOLD, 14));
        
        label = new JLabel("Select match and edit or resolve. When finished all matches, press done.");
        edit = new JButton("Edit");
        resolve = new JButton("Resolve");
        done = new JButton("Done");
        cancelt = new JButton("Cancel");

        edit.addActionListener(this);
        resolve.addActionListener(this);
        done.addActionListener(this);
        cancelt.addActionListener(this);
        
        unfin = new JList(current.getTracker().getUnresolved().toArray(new Match[current.getTracker().getUnresolved().size()]));//Grabs ArrayList and puts to an array for list
        fin = new JList(current.getTracker().getResolved().toArray(new Match[current.getTracker().getResolved().size()]));//same as above 
        unfin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sp1 = new JScrollPane(unfin);
        sp2 = new JScrollPane(fin);
        sp1.setPreferredSize(new Dimension(200, 300));
        sp2.setPreferredSize(new Dimension(200, 300));

        tracker.add(jtname);
        tracker.add(label);
        tracker.add(sp1);
        tracker.add(sp2);
        tracker.add(done);
        tracker.add(resolve);
        tracker.add(edit);
        tracker.add(cancelt);
        
        tracker.setVisible(true);// --> add names of players!!!
    }
    public void showMatcher()//shows matches for a round
    {
        matcher = new JDialog();
        matcher.setTitle("Resolve match");
        matcher.setLayout(new FlowLayout());
        matcher.setPreferredSize(new Dimension(280,128));
        matcher.pack();
        matcher.setLocationRelativeTo(null);
        matcher.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        matcher.setModal(true);
        
        label = new JLabel("Select winner of the match or cancel");
        label2 = new JLabel("VS");
        player1 = new JButton(temp.getP1().getName());
        player2 = new JButton(temp.getP2().getName());
        cancel2 = new JButton("Cancel");
        
        player1.addActionListener(this);
        player2.addActionListener(this);
        cancel2.addActionListener(this); 
        
        matcher.add(label);
        
        matcher.add(player1);
        matcher.add(label2);
        matcher.add(player2);
        matcher.add(cancel2);
        matcher.validate();
        matcher.repaint();
        matcher.setVisible(true);
    }
    public void actionPerformed (ActionEvent event)//ActionListener's method for all the button in all of the GUIs, are seperated by comments
      {
        //new tourney stuff
        if(event.getSource() == quit)
        {
            ngui.dispose();
        }
        if((event.getSource()==elimination)||(event.getSource()==swiss)||(event.getSource()==roundrobin))
        {
            ngui.dispose();
            read.openFile();
            read.readFile();
            read.closeFile();
            if(event.getSource() == swiss) 
            {
                tourneys.add(new Swiss(tname));
                tourneys.get(tourneys.size()-1).setEntrants(read.getNames());
                tourneys.get(tourneys.size()-1).setRounds();
                tourneys.get(tourneys.size()-1).makePairings();
                setTourney(tourneys.get(tourneys.size()-1));
            }
            if (event.getSource() == elimination)
            {
                  if((read.getNames().length&-read.getNames().length)==read.getNames().length)
                  {
                      tourneys.add(new Elimination(tname));
                      tourneys.get(tourneys.size()-1).setEntrants(read.getNames());
                      tourneys.get(tourneys.size()-1).setRounds();
                      tourneys.get(tourneys.size()-1).makePairings();
                      setTourney(tourneys.get(tourneys.size()-1));
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "You need a number of players that is an exponent of two, add or delete entrants, or try another tournament format.", "Invalid", JOptionPane.ERROR_MESSAGE);
                  }
            }
            if (event.getSource() == roundrobin)
            {
              tourneys.add(new RoundRobin(tname));
              tourneys.get(tourneys.size()-1).setEntrants(read.getNames());
              tourneys.get(tourneys.size()-1).setRounds();
              tourneys.get(tourneys.size()-1).makePairings();
              setTourney(tourneys.get(tourneys.size()-1));
            }
        }
        //tracking panel stuff
        if((event.getSource()==choose)||(event.getSource()==remove))
        {
            if(tlist.isSelectionEmpty())
            {
                JOptionPane.showMessageDialog(null, "You have not selected a tournament.", "No Tournament Selected", JOptionPane.ERROR_MESSAGE);
            }
               
            else
            {
                if(event.getSource() == choose)
                {
                    setTourney(tourneys.get(tlist.getSelectedIndex()));
                }
                if(event.getSource() == remove)
                {
                    tourneys.remove(tlist.getSelectedIndex());
                    tlist.setListData(tourneys.toArray(new Tournament[tourneys.size()]));
                    tgui.repaint();
                    tgui.validate();
                }
            }
        }
        if(event.getSource() == cancel)
        {
            tgui.dispose();
        }
        //tracker stuff
        if(event.getSource()==resolve)//add an actionlistener for the button in matchgui?
        {
            if(unfin.isSelectionEmpty())
            {
                JOptionPane.showMessageDialog(null, "You have not selected an unresolved match.", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                rore = 0;
                indexn = unfin.getSelectedIndex();
                temp = current.getTracker().getUnresolved().get(indexn);
                showMatcher();
            }
        }
        if(event.getSource()==edit)
        {
            if(fin.isSelectionEmpty())
            {
                JOptionPane.showMessageDialog(null, "You have not selected a resolved match.", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                rore = 1;
                indexn = fin.getSelectedIndex();
                temp = current.getTracker().getResolved().get(indexn);
                showMatcher();
            }
        }
        if(event.getSource()==done)
        {
            if(current.getTracker().getUnresolved().size()==0)
            {
                if(!(current.getRound()==current.getCround()))
                {
                    current.makePairings();
                    current.getTracker().getResolved().clear();//clears resolved array because nothings resolved
                    unfin.setListData(current.getTracker().getUnresolved().toArray(new Match[current.getTracker().getUnresolved().size()]));//update list
                    fin.setListData(current.getTracker().getResolved().toArray(new Match[current.getTracker().getResolved().size()]));//update list
                    tracker.validate();
                    tracker.repaint();
                }
                else
                {
                    current.setWinner();
                    String temp = current.getWinner().getName() + " has won the tournament!\n" + current.getStandings();
                    JOptionPane.showMessageDialog(tracker, temp, "Complete", JOptionPane.PLAIN_MESSAGE);
                    tracker.dispose();
                    tlist.setListData(tourneys.toArray(new Tournament[tourneys.size()]));
                    tgui.repaint();
                    tgui.validate();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "You still have uncompleted matches.", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(event.getSource()==cancelt)
        {
            tracker.dispose();
            tlist.setListData(tourneys.toArray(new Tournament[tourneys.size()]));
            tgui.repaint();
            tgui.validate();
        }
        //matcher stuff
        if((event.getSource()==player1)||(event.getSource()==player2))
        {   
            if(event.getSource()==player1)
            {
                temp.setWinner(temp.getP1());
                if(rore==0)
                {
                    current.getTracker().resolveMatch(indexn,temp);
                }
                else if(rore==1)
                {
                    current.getTracker().editMatch(indexn,temp);
                }
            }
            if(event.getSource()==player2)
            {
                temp.setWinner(temp.getP2());
                if(rore==0)
                {
                    current.getTracker().resolveMatch(unfin.getSelectedIndex(),temp);
                }
                else if(rore==1)
                {
                    current.getTracker().editMatch(fin.getSelectedIndex(),temp);
                }
            }
            matcher.dispose();//saving space so one dispose only
            unfin.setListData(current.getTracker().getUnresolved().toArray(new Match[current.getTracker().getUnresolved().size()]));//update list
            fin.setListData(current.getTracker().getResolved().toArray(new Match[current.getTracker().getResolved().size()]));//update list
            tracker.validate();//above
            tracker.repaint();
        }
        if(event.getSource()==cancel2)
        {
            matcher.dispose();
        }
     }
    public boolean getInfo()//A simple JOptionPane.showInputDialog that takes the name of the tournament
    {
        do
        {
            tname = JOptionPane.showInputDialog(null,"Please input the name of your tournament.","Tournament Name", 1);
            if(tname==null)
            {
                return false;
            }
        }
        while(tname.trim().length() == 0);
        return true;
    }
    public ArrayList<Tournament> getTournaments()//returns tournamentGUI's ArrayList<Tournament> for future actions in MenuGUI if desired
    {
        return tourneys; 
    }
    private Tournament current;
    private JDialog tracker;
    private JDialog matcher;
    private JLabel label;
    private JLabel jtname;
    private JButton edit;
    private JButton resolve;
    private JButton done; 
    private JButton cancelt;
    private JList unfin;
    private JList fin;
    private JScrollPane sp1;
    private JScrollPane sp2;
    //for the match part
    private JLabel label1;
    private JLabel label2;
    private JButton player1;
    private JButton player2;
    private JButton cancel2;
    private Match temp = null;
    private int rore = 5;
    private int indexn = 0;
}


