/*Filereader Class
 * Yu Han Lin
 * For Questions Assignment
 */
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class FileReader
{
    private Scanner read; 
    private Scanner read1;
    String[] names;
    public FileReader()// empty constructor as no arguments necasary b/c data is grabbed from files
    {

    }
    //open file
    public void openFile()//sets scanners to the same text file to read from, with a try catch if an error were to occur
    {
        boolean error = true;
        do
            try
            {
                String name = JOptionPane.showInputDialog(null,"What is the name of your tournament file? Include extension (.txt).", "Entrants File Name",1);
                read = new Scanner(new File(name));
                read.useDelimiter(",");//both use the delimiter of comma as set the default by our "client"
                read1 = new Scanner(new File(name));
                read1.useDelimiter(",");
                error = false;
            } 
            catch(Exception e)  
            { 
                JOptionPane.showMessageDialog(null, "Error. Could not open file. Check file name and try again.", "Error", 0); 
            } 
        while(error); 
    }
    //reads the file into array 
    public void readFile()
    {
       int acounter = 0;//counter for number of questions otherwise known as size of our array
       while(read1.hasNext())
       {
           read1.next();
           acounter++;
       }
       names = new String[acounter];//sets the length of our array of questions1 objects
       for(int a = 0; read.hasNext(); a++)
       {
          names[a] = read.next();
        }
    }
    public void closeFile()
    {
        read.close();//closes the file
    }
    //returns the read array to be used by other classes: sort, writer
    public String[] getNames()
    {
        return names;
    }
}