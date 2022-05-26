/*
 * Name:       Natasha Sharma
 * 
 * Course:     CS-12 Spring 2018
 *
 * Date:       5/3/18
 *
 * Filename:   DamNS.java  
 * 
 * Purpose:    Adding data checks and utility methods to Dam class . 
 */

public class DamNS {

    // ===================================================
	/* . . . ----------INSTANCE VARIABLES---------- . . .*/
    // ===================================================

     
	// instance variables ------------
    private String name; 	                    // <-- name of the dam: instance variable #1
    private int year;		                    // <-- year which dam was completed: instance variable #2
    private double storage;                     // <-- current water storage in acre/feet: instance variable #3
    private double capacity; 	                // <-- max water storage in acre/feet: instance variable #4
    private double inflow;                      // <-- water inflow rate in cubic-feet/sec: instance variable #5
    private double outflow;                     // <-- water outflow rate in cubic-feet/sec: instance variable #6
    private CS12Date date;                      // <-- date stamp current data: instance variable #7

	// constants --------------------
    // cuFt / AcFt
    private final int CUFT_PER_ACFT = 43560;
    private final int SECS_PER_HOUR = 3600;
    private final int HOURS_PER_DAY = 24;
	
    // other class data -------------
    
    private final double TOL = 0.0001;          // <-- equivalence tolerance
    // formatting for numerics data 
    private String waterFmt = "%,.0f";
   
    
// data above here
// ===================================================

// ===================================================
// methods below here

    // ===================================================
	/* . . . ----------CONSTRUCTORS---------- . . .*/
    // ===================================================

	// default constructor for all <7> variables
    public DamNS () {
        name        = "<default dam>";
        year        = 1900;
        storage     = 0.0;
        capacity    = 0.0;
        inflow      = 0.0;
        outflow     = 0.0;
        date        = new CS12Date();
        
    }//end default constructor                                               
    	
    
	// full constructor for all <7> variables
    public DamNS (String name, int year, double storage, double capacity, 
                  double inflow, double outflow, CS12Date date) {
        this();
        setName(name);
        setYear(year);
        setCapacity(capacity);
        setStorage(storage);
        setInflow(inflow);
        setOutflow(outflow);
        setDate(date);
        
    }//end full constructor                                               
        
    
    // alternate constructor 1: name and year 
    public DamNS (String name, int year) {
        this();
        this.name   = name;
        setYear(year);
         
    }//end name-year constructor                                                
	
    
    //alternate constructor 2: name and capacity
    public DamNS (String name, double capacity) {
        this();
        this.name       = name;
        setCapacity(capacity);
        
    }//end name-capacity constructor                                                   
    
    
    // ===================================================
    /* . . . ----------DISPLAY METHODS---------- . . .*/	
    // ===================================================
    
    
	// toString() shows all 7 instance variables on a single comma separated line
    public String toString() {
        /* return 	name + "  " + year + "  " + 
                String.format(waterFmt, storage) + "  " +
                String.format(waterFmt, capacity) + "  " +
                String.format(waterFmt, inflow) + "  " +
                String.format(waterFmt, outflow) + "  " + date; */
        
        // updated print format for DamReport
   	    return String.format("%n%-8s%12s%12s%12s%12s%12s%12s", name, year, 
                             storage, capacity, inflow, outflow, date);
    }
    
    
	// print() shows all 7 instance variables in a multi-line labeled format  
    public void print() {
        //System.out.println( String.format("%-30s%s\n", "Name: ", getName()));
        System.out.printf("%-30s%s\n", "Name: ", getName());
        System.out.printf("%-30s%s\n", "Year opened: ", getYear());
        System.out.printf("%-30s%s\n", "Date of measurement: ", getDate()); 
        System.out.printf("%-30s%,.0f\n", "Storage {acre/ft]: ", getStorage());        
        System.out.printf("%-30s%,.0f\n", "Capacity {acre/ft]: ", getCapacity());
        System.out.printf("%-30s%,.0f\n", "Inflow {cu/ft]: ", getInflow());
        System.out.printf("%-30s%,.0f\n", "Outflow {cu/ft]: ", getOutflow());
        // 5 new methods 
        System.out.printf("%-30s%d\n", "Age [yrs]", getAge());
        System.out.printf("%-30s%s\n", "Status: ", getStatus());
        System.out.printf("%-30s%.1f%s\n", "% full", getPercentFull(), " %");
        System.out.printf("%-30s%d\n", "Days until dam event: ", getEventDays());
        System.out.printf("%-30s%s\n", "Date of dam event: ", getEventDate());
   	
    }
    
    
	// overloaded print()... prints an additional input text message header 
    public void print(String message) {
        System.out.println("------------------------------------");
        System.out.println(message);
        System.out.println("------------------------------------");
        print();
   	
    }
	
   
    // ===================================================
    /* . . . ----------ACCESSORS & MUTATORS---------- . . .*/
    // ===================================================
    
    // ....the way to get to the private variable is through public accessor/mutator
    
    // Name accessor
    public String getName() { 	                    
        return name;			                    // pulling the variable out and returning a copy of the variable
    }
    // Name mutator
    public void setName(String name) { 	            
        this.name = name;                           // change the value of the instance variable
    }  
    // Name overloaded mutator
    public void setName(boolean guiFlag) {          // <-- Name overload mutator...prompts user for value 
        String data = UtilsNS.readString("Enter new dam name: ", guiFlag);
        setName(data);
    }
    
    
	// Year accessor
    public int getYear() { 	                        
        return year;			
    }
    
	// Year mutator 
    public void setYear(int year) {
        // year cannot be before 1900 
        if (year < 1900) {
            System.out.println("ERROR: year must be >= 1900, value unchaged");
        }
        
        else {
            this.year = year;
        }
    }
    
	// Year overloaded mutator 
    public void setYear(boolean guiFlag) {          
        int data = UtilsNS.readInt("Enter new dam completion year: ", guiFlag);
        setYear(data);
    }
	  
         
    // Storage accessor
    public double getStorage() { 	                
        return storage;			
    }
    
	// Storage mutator
    public void setStorage(double storage) { 
        // input values must be greater than/= to 0.0        
        if (storage < 0.0) {
            System.out.println("ERROR: storage must be >= 0 ac-ft, value unchanged");
        }
        
        // storage amount cannot exceed capacity
        else if (storage > capacity) {
            System.out.println("ERROR: storage must be <= capacity, value unchanged");
        }
        
        else {
            this.storage = storage;
        }
        
    }
    
	// Storage overloaded mutator
    public void setStorage(boolean guiFlag) {        
        double data = UtilsNS.readDouble("Enter new dam storage: ", guiFlag);
        setStorage(data);
    }
       
    
	// Capacity accessor
    public double getCapacity() { 	                
        return capacity;			
    }
    
	// Capacity mutator
    public void setCapacity(double capacity) { 	      
        // input values must be greater than/= to 0.0
        if (capacity < 0.0) {
            System.out.println("ERROR: capacity must be >= 0 ac-ft, value unchanged");
        }
        
        // capacity amount has to be >= storage, storage cannot exceed capacity
        else if (capacity < storage) {
            System.out.println("ERROR: capacity must be >= storage, value unchanged");
        }
        
        else {
            this.capacity = capacity;
        }
    }
    
	// Capacity overloaded mutator
    public void setCapacity(boolean guiFlag) {       
        double data = UtilsNS.readDouble("Enter new dam capacity: ", guiFlag);
        setCapacity(data);
    }
	
	
    // Inflow accessor
    public double getInflow() { 	                 
        return inflow;			
    }
    
	// Inflow mutator
    public void setInflow(double inflow) { 	         
        // input values must be greater than/= to 0.0
        if (inflow < 0.0) {
            System.out.println("ERROR: inflow must be >= 0 cu-ft/sec, value unchanged");
        } 	      
        
        else {
            this.inflow = inflow;
        }    
    }
    
	// Inflow overloaded mutator 
    public void setInflow(boolean guiFlag) {        
        double data = UtilsNS.readDouble("Enter new dam inflow: ", guiFlag);
        setInflow(data);
    }
    
    
    // Outflow accessor
    public double getOutflow() { 	                
        return outflow;			
    }
    
	// Outflow mutator
    public void setOutflow(double outflow) { 
        // input values must be greater than/= to 0.0
        if (outflow < 0.0) {
            System.out.println("ERROR: outflow must be >= 0 cu-ft/sec, value unchanged");
        } 	      
        
        else {
            this.outflow = outflow;
        }
    }
    
	// Outflow overloaded mutator
    public void setOutflow(boolean guiFlag) {        
        double data = UtilsNS.readDouble("Enter new dam outflow: ", guiFlag);
        setOutflow(data);
    }
    
       
    // Date accessor
    public CS12Date getDate() {
        // create new CS12Date variable to copy over the dates 	                
        CS12Date junk = new CS12Date();
        junk.setDate( date.getMonth(), date.getDay(), date.getYear() );
        
        return junk;			
    }
    
	// Date mutator
    public void setDate(CS12Date date) {
        // call get/set methods to update date  	         
        (this.date).setMonth(date.getMonth());
        (this.date).setDay(date.getDay());
        (this.date).setYear(date.getYear());
    }
    
	// Date overloaded mutator
    public void setDate(boolean guiFlag) { 
        // declare and initialize date variables
        int month = UtilsNS.readInt("Enter measurement month date: ", guiFlag);
        int day = UtilsNS.readInt("Enter measurement day date: ", guiFlag);
        int year = UtilsNS.readInt("Enter measurement year date: ", guiFlag);           
        // set up date from above inputs
        CS12Date data = new CS12Date(month, day, year);
        // call mutator
        setDate(data);

    }
    
	
    // ===================================================
    /* . . . ----------EQUIVALENCE CHECK---------- . . .*/ 
    // ===================================================
    
	/* == compares memory locations - can return false for same information b/c its stored in 2 different places in memory 
	 .equals compares actual content and can return true because it's comparing stored information only */
    
    
    // -------This is the standard interface for equals()-------  
    public boolean equals(Object obj) {
        // first check whether objects are of the same type (apples = apples?)
        if (! (obj instanceof DamNS) ) {               
            //stop no need to check any further
            return false;                              
        }
        
        else {         
            //typecast into intended object type                   	           
            DamNS d = (DamNS) obj;
            // check field-by-field on ALL fields
            if ((d.getName().equals(this.name)) &&     
                (d.getYear() == this.year) &&
                (Math.abs(d.getStorage() - this.storage) <= TOL) &&
                (Math.abs(d.getCapacity() - this.capacity) <= TOL) &&
                (Math.abs(d.getInflow() - this.inflow) <= TOL) &&
                (Math.abs(d.getOutflow() - this.outflow) <= TOL) &&
                (d.getDate().equals(this.date)) ) {   
                return true;
            }
            
            else {
                return false;
            }
        }
   	
    } // end equals check
    
	
    // ===================================================
	// -------derived data accessors-------	
    // ===================================================
    
    // 1. getAge()...return the age of the dam
    public int getAge(){
        return UtilsNS.getAge( new CS12Date(1, 1, year));
   
    }
    
    // 2. getStatus()...returns the status of the dam
    public String getStatus() {
        String filling = "Filling";
        String emptying = "Emptying";
        String holding = "Holding";
        // filling if inflow is greater than outflow 
        if (inflow > outflow){
            return filling;
        }
        
        // emptying if inflow is less than outflow
        else if (inflow < outflow) {
            return emptying;
        }
        
        // if both are equal, dam is holding
        else {
            return holding;
        }
       
    }
    
    // 3. getPercentFull()...returns how full dam is compared to overall capacity
    public double getPercentFull() {
        double percentage;
        // if capacity is greater than 0, return percentage full 
        if (capacity > 0) {
            percentage = (storage/capacity)*100; 
            return percentage;
        }  
        
        else {
          
            return 0.0;
        }        
    }
    
    // 4. getEventDays()...calculates # of days until some dam event
    public int getEventDays() {
        double convFactor;
        double overflowDay;
        double emptyDay;
        int castDay;
        
        convFactor = ((double)(CUFT_PER_ACFT) / ((SECS_PER_HOUR)*(HOURS_PER_DAY)));
        // if dam if filling, overflow day should be lower value/closer in time 
        if (inflow > outflow) {
             overflowDay = ((capacity - storage) / (inflow - outflow)) * convFactor;
             castDay = (int) Math.rint(overflowDay - 0.5);
        }
        
        // if dam is emptying, overflow day should be higher in value/farther in time
        else if (inflow < outflow) {
             emptyDay = ((0 - storage) / (inflow - outflow)) * convFactor;
             castDay = (int) Math.rint(emptyDay - 0.5);
             
        }
        
        // if dam is holding, return -1
        else {
             castDay = -1;
             
        }
        
        return castDay;
    }
  
    // 5. getEventDate...retrieves the date the event will take place 
    public CS12Date getEventDate() {
        CS12Date event = new CS12Date();
        
        // if getStatus() is "holding" & getEventDays() is -1
        if ( (Math.abs(inflow - outflow) <= TOL) && (getEventDays() == -1) ) {
            
            // get copy of measurement date
            event.setDate( date.getMonth(), date.getDay(), date.getYear() );
            
            // 24 leaps years in 100 years, 76 regular years remain ... 
            // event.laterDate((365*76) + (366*24));
            
            // corrected from homework feedback
            event.setYear(event.getYear() + 100);
        }
        
        else {
        // extracting month, day, year from the instance variable 'date'
        event.setDate( date.getMonth(), date.getDay(), date.getYear() );
        
        // advance date by getEventDays
        event.laterDate( getEventDays());
        
        }
        
        return event;  
    }
	
    
    // ===================================================
    // -------utility methods-------
    // ===================================================
    
    // 6. update()...updates dam values
	public void update(boolean guiFlag) {
        setName(guiFlag);
        setYear(guiFlag);
        setCapacity(guiFlag);
        setStorage(guiFlag);
        setInflow(guiFlag);
        setOutflow(guiFlag);
        setDate(guiFlag);
        
    }
    
    // 7. importWater()...imports water to the dam
    public void importWater(double acreFeet) {
        // requested change amount can't be <= 0
        if (acreFeet < 0) {
            System.out.println("ERROR: cannot import a negative amount, no import");
        }
        
        // storage + added amount can't exceed capacity and can't be < 0
        else if (((storage + acreFeet) > capacity) || ((storage + acreFeet) < 0.0)) {
            System.out.println("ERROR: unable to import that amount without overflowing dam, no import");
        }
        
        else {
            setStorage(storage + acreFeet);
        }
    }
    
    // 8. releaseWater()...releases water from the dam
    public void releaseWater(double acreFeet) {
        // requested change amount can't be <= 0
        if (acreFeet < 0) {
            System.out.println("ERROR: cannot release a negative amount, no release");
        }
        
        // released water cannot put storage into negative amounts 
        else if ( (storage - acreFeet) < 0.0) {
            System.out.println("ERROR: unable to release that amount without draining the dam, no release");
        }
        
        else {
            setStorage(storage - acreFeet);
        }
    }
    
    // 9. increaseOutflow()...increases outflow of the water stored
    public void increaseOutflow(double cuFtSec) {
        // requested change amount can't be <= 0
        if (cuFtSec < 0) {
            System.out.println("ERROR: increase in outflow must be >= 0, not changed");
        } 
        // if there's no water stored, there can't be outflow from 0 water
        else if (storage == 0) {
            System.out.println("ERROR: storage = 0, cannot change outflow, not changed");
        }
        
        else {
            setOutflow(outflow + cuFtSec);
        }
    }
    
    // 10. decreaseOutflow()...decreased outflow of the water stored
    public void decreaseOutflow(double cuFtSec) {
        if (cuFtSec < 0) {
            System.out.println("ERROR: decrease in outflow must be >= 0, not changed");
        }
        
        // if there's 0 water, there can't be any outflow 
        else if (storage == 0) {
            System.out.println("ERROR: unable to reduce outflow below 0, not changed");
        }
        
        else {
            setOutflow(outflow - cuFtSec);
        }
    }
    
    
    // ===================================================
    /* . . . TEST CODE . . .*/	
    // ===================================================
	
    // -------test driver for this class------- 
    
    public static void main(String [] args) {
   	    // create 4 new DamNS objects for each constructor 
        // default constructor 
        DamNS damDefault = new DamNS();         
        // full constructor
        DamNS damFull = new DamNS("Dam Full", 2018, 100.50, 200.50, 55, 70,  new CS12Date(5, 5, 2018)); 
		// name-year constructor
        DamNS damAlternate1 = new DamNS("Dam Alternate 1", 2020);
		// name-capacity constructor
        DamNS damAlternate2 = new DamNS("Dam Alternate 2", 500.50);
   	    
        
        // Printing outputs of damDefault 
		System.out.println("--> Printing using toString()");
        System.out.println(damDefault);
		System.out.println("\n--> Printing using overloaded print()");
        damDefault.print("Created using default constructor");
        System.out.println("\n*** \tEND OF DEFAULT\t *** \n");			        
        UtilsNS.pause();
		
        
        // Printing outputs of damFull 
        System.out.println("--> Printing using toString()");
        System.out.println(damFull);
		System.out.println("\n--> Printing using overloaded print()");
        damFull.print("Created using full constructor");
        System.out.println("\n*** \tEND OF FULL\t *** \n");
        UtilsNS.pause();   
        
        
        // Printing outputs of damAlternate1 
        System.out.println("--> Printing using toString()");
        System.out.println(damAlternate1);
		System.out.println("\n--> Printing using overloaded print()");
        damAlternate1.print("Created using alternate constructor 1");
        System.out.println("\n*** \tEND OF ALTERNATE 1\t *** \n");
        UtilsNS.pause();   
        
        
        // Printing outputs of damAlternate2
        System.out.println("--> Printing using toString()");
        System.out.println(damAlternate2);
		System.out.println("\n--> Printing using overloaded print()");
        damAlternate2.print("Created using alternate constructor 2");
        System.out.println("\n*** \tEND OF ALTERNATE 2\t *** \n");
        UtilsNS.pause();   
        
        
        // Testing accessors & mutators: show current value, prompt for new value 
        // current name-year constructor dam data 
        System.out.println("Current dam data: " + damAlternate1);
        System.out.println();
        
        // name
        System.out.println("Current dam name: " + damAlternate1.getName());
        damAlternate1.setName(false);
        System.out.println();
        // year
        System.out.println("Current dam year: " + damAlternate1.getYear());
        damAlternate1.setYear(false);
        System.out.println();
        // storage
        System.out.println("Current dam storage: " + damAlternate1.getStorage());
        damAlternate1.setStorage(false);
        System.out.println();
        // capacity
        System.out.println("Current capacity is: " + damAlternate1.getCapacity());
        damAlternate1.setCapacity(false);
        System.out.println();
        // inflow
        System.out.println("Current dam inflow: " + damAlternate1.getInflow());
        damAlternate1.setInflow(false);
        System.out.println();
        // outflow
        System.out.println("Current dam outflow: " + damAlternate1.getOutflow());
        damAlternate1.setOutflow(false);
        System.out.println();
        // date
        System.out.println("Current dam date: " + damAlternate1.getDate());
        damAlternate1.setDate(false);
     
        System.out.println();
        
        
        // Show the changes after using mutators
        damAlternate1.print("Dam Alternate 1 after user inputs");
        UtilsNS.pause();


   	    // -------Test Equality------- 	
   	    // two different objects should differ
        System.out.println("--> Testing objects for equality");
        System.out.println("damDefault equals damFull? \t\t\t" + damDefault.equals(damFull));
   	
   	    // same object is equal to itself 
        System.out.println("damFull equals damFull? \t\t\t" + damFull.equals(damFull));
   	
   	    // a Dam can't equal another type of object
        // test object against string 
        String Tree = new String("Tree");
        System.out.println("damFull equals string Tree? \t\t" + damFull.equals(Tree));
        System.out.println();
        System.out.println("End of test code...!!");
   	 
     
    }
        
        
} // end class 
