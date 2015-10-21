import java.io.*;
import java.util.*;

/**
 *
 * @author Barry
 */
public class SortNames {
    public List<String> namesList;
    
    public SortNames(){
        namesList = new ArrayList<>();
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        SortNames listOfNames = new SortNames();
        Scanner action = new Scanner(System.in);
        System.out.println("Welcome!");
        listOfNames.getNamesFromFile();
        boolean goOn = true;
        while(goOn != false){
            System.out.println("Would you like to enter another file to import names? Press 1 for yes and 0 for no.");
            int actionChoice = action.nextInt();
            if(actionChoice == 1){
                listOfNames.getNamesFromFile();
            }
            else if(actionChoice == 0){
                listOfNames.saveListToFile();
                goOn = false;
            }
            else
                System.out.println("Please enter a correct choice.");
        }
    }
   
    
    
    private void getNamesFromFile(){
        Scanner userChoice = new Scanner(System.in);
        System.out.println("Please enter the file directory");
        String fileName = userChoice.nextLine();
        
        try(Scanner inFile = new Scanner(new File(fileName))){

            while (inFile.hasNextLine()){
                    String line = inFile.nextLine();
                    String[] nameArray = line.split(" ");
                    String first = nameArray[0];
                    String last = nameArray[1];

                    String person = (last + " " + first);
                    namesList.add(person);
            }
            inFile.close();
        }
        catch(FileNotFoundException e){
        }
    }
    private void sortList(){
        Collections.sort(namesList);
    }
    
    private void saveListToFile(){
        sortList();
        
        try(PrintWriter outFile = new PrintWriter("all-names.txt")){
            Iterator iterate = namesList.iterator();
            while( iterate.hasNext()){
                String name = iterate.next().toString();
                String[] nameArray = name.split(" ");
                String lastName = nameArray[0];
                String firstName = nameArray[1];
                System.out.println(firstName + " " + lastName);
                outFile.println(firstName + " " + lastName);
            }
            outFile.close();
        }
        catch(FileNotFoundException e){
            
        }
    }
    
}