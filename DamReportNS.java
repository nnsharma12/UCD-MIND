/*
 * Name:       Natasha Sharma
 * 
 * Course:     CS-12 Spring 2018
 *
 * Date:       5/20/18
 *
 * Filename:   DamReportNS.java  
 * 
 * Purpose:    Using arrays to store and print out previous Dam data/objects.   
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DamReportNS {

    public static void main(String [] args) {
        
        // variable declarations
        final int MAX_DAMS = 50;
        char inputChar;

        DamNS [] dams = new DamNS [MAX_DAMS];
        int count = 0;

   
        // menu prompt
        String menu = "-----------------------------------     \n" +
                      "DAM MENU OPTIONS           \n" +
                      "-----------------------------------     \n" +
                      "List of menu options:                   \n" +
                      "Read data from file                  [R]\n" +
                      "Print dam summaries                  [S]\n" +
                      "Print dam details                    [D]\n" +
                      "Overall water status                 [W]\n" +
                      "Quit                                 [Q]\n" +
                      "Enter Option: ";
   
        
        inputChar = UtilsNS.readChar(menu, false);
   
        //if options are not for 'quit', continue with the switch cases
        while ((inputChar != 'Q') && (inputChar != 'q')) {
        
            switch (inputChar) {
                
                //reads data from file [r]
                case 'R':
                case 'r':
                    count = readDataFromFile(dams);
                    break;
                
                //prints dam summaries [s]
                case 'S':
                case 's':
                    printDamSummaries(dams, count);
                    break;
            
                //prints dam details [d]
                case 'D':
                case 'd':
                    printDamDetails(dams, count);              
                    break;
                
                //prints overall water status [w]
                case 'W':
                case 'w':
                    showWaterStatus(dams, count);              
                    break;
                
                //error message if unrecognized option     
                default:
                    System.out.println("ERROR: Unrecognized option " + inputChar + ", please try again");
                    break;
                
            } // end of switch block
            
        //  go back to display menu
        System.out.println();
        inputChar = UtilsNS.readChar(menu,  false); 
            
        } // end of while block
     
        // display terminating message
        System.out.println("Exiting upon user request");
    
   
    } // end of main method
    
    
    // ======================================================
    // 5 additional methods
    // ======================================================
    
    // 1. converts string data into a dam obj
    private static DamNS convertString2Dam(String data) {
    String name;
    int year;
    double storage, capacity, inflow, outflow;
    CS12Date date;
    
    // split strings with comma delimiters
    String [] token1 = data.split(",");
         
    // split strings with / delimiters
    String [] token2 = token1[6].split("/");
        
    // parse objects and trim any whitespace
    name = token1[0].trim();
    year = Integer.parseInt(token1[1].trim());
    storage = Double.parseDouble(token1[2].trim());
    capacity = Double.parseDouble(token1[3].trim());
    inflow = Double.parseDouble(token1[4].trim());
    outflow = Double.parseDouble(token1[5].trim());
    date = new CS12Date(Integer.parseInt(token2[0].trim()),
                        Integer.parseInt(token2[1].trim()),
                        Integer.parseInt(token2[2].trim()));
    
    // return values  
    return new DamNS(name, year, storage, capacity, inflow, outflow, date); 
   
    } // end 
    
    
    // 2. read data from user specified file 
    private static int readDataFromFile(DamNS [] dams) {
        int count = 0; 
        String fileName;
        Scanner scanFile;
        
        // enter user specified filename
        fileName = UtilsNS.readString("Enter text filename containing dam data: ", false);
        // read filename
        File infile = new File(fileName); 
      
        for (int i = 0; i < dams.length; i++) {
            dams[i] = null;
        }
      
        try {
            // scan user specified file 
            scanFile = new Scanner(infile); 
        }
      
        catch (FileNotFoundException e) { 
            // print error message 
            System.out.println("ERROR: File not found! Please enter a valid filename ");
            System.out.println();
            return 0;
        }
      
        while (scanFile.hasNextLine()) { 
            // add count 
            dams[count] = convertString2Dam(scanFile.nextLine());
            count++;
        }
        // read from the file 
        System.out.printf("%5s dams read from file: %15s%n", count, fileName);
        //System.out.println(); 
        scanFile.close();
        
        return count;
    
    } // end


    // print dam summaries [s]
    private static void printDamSummaries(DamNS [] dams, int count) {
        if (count == 0) { 
            // print error message
            System.out.println();
            System.out.println("ERROR: No dam data found. Please read in data from file");
        }
      
        else { 
            // format and print dam data 
            System.out.printf("%n%-8s%12s%12s%12s%12s%12s%12s", "Name", "Year", 
                              "Storage", "Capacity", "Inflow", "Outflow", "Date");
            for (int i=0; i<count; i++) {
                System.out.print(dams[i].toString());
            }
            
            System.out.println();
        }
    }
    
    
    // print dam details [d]
    private static void printDamDetails(DamNS [] dams, int count) {
        if (count == 0) { 
            // print error message 
            System.out.println();
            System.out.println("ERROR: No dam data found. Please read in data from file");
        }
      
        else { 
            // use print() and getName() to print output
            for (int i=0; i<count; i++) {
                System.out.println("\n=========="); 
                System.out.println(dams[i].getName() );
                System.out.println("==========");
                dams[i].print();
            }
        }
    } // end 
    
    
    // prints 'superdam' by combining dam data 
    private static void showWaterStatus(DamNS [] dams, int count) {
        // declare variables and set to 0
        double storage = 0;
        double capacity = 0;
        double inflow = 0;
        double outflow = 0;
      
        if (count == 0) { 
            // print error message
            System.out.println();
            System.out.println("ERROR: No dam data found. Please read in data from file");
        }
      
        else { 
            // add all imported water related data 
            for (int i=0; i<count; i++) {
                storage += dams[i].getStorage();
                capacity += dams[i].getCapacity();
                inflow += dams[i].getInflow();
                outflow += dams[i].getOutflow();
            }
            // print updated values 
            System.out.println("\n====================");
            System.out.println("OVERALL WATER HEALTH");
            System.out.println("====================");
            new DamNS("Super Dam", UtilsNS.today().getYear(), storage, capacity,
                       inflow, outflow, dams[0].getDate()).print();
        }
    } // end
    
    
    
} // end of class 