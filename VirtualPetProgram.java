// import scanner
import java.util.Scanner;

// main class

public class VirtualPetProgram {
    public static void main(String[] args){

    Scanner input = new Scanner(System.in);
    
    // Name pet
    
    System.out.println("Please name your pet: ");
    
    String myName = input.next();
    
    // initialize pet's data within the class
    
    VirtualPet myPet = new VirtualPet(myName);
    
    // loop for activities with pet
    
    // initialize choice value for loop
    
    int myChoice = 0;
    
    // begin loop
    
    while(myChoice != 4){
        System.out.println("Which action would you like to take?");
        System.out.println("Please enter 1, 2, 3, or 4.");
        System.out.println("1. Walk " + myName + ".");
        System.out.println("2. Feed " + myName + ".");
        System.out.println("3. Clean " + myName + ".");
        System.out.println("4. Exit.");
        myChoice = input.nextInt();
        
        if (myChoice == 1){
            myPet.walk();
        } else if (myChoice == 2){
            myPet.feed();
        } else if (myChoice == 3){
            myPet.clean();
        } else {
            System.out.println("Invalid input.");
        }
    }
    
    // end loop
    
    // get values to return final happy/energy/clean scores
    
    int myHappy = myPet.getHappy();
    int myEnergy = myPet.getEnergy();
    int myClean = myPet.getClean();
    
    // display final results
    
    displaySummary(myName, myHappy, myEnergy, myClean);
    
}
    public static void displaySummary(String n, int h, int e, int c){
    
        // print summary of scores
        System.out.println("Summary of " + n + "'s scores:");
        System.out.println(n + "'s happiness score is " + h + ".");
        System.out.println(n + " wants to do " + e + 
            " things before going to sleep.");
        System.out.println(n + " has dirt on around " + c + 
            " percent of their body.");
    
        // Return a response based on the happiness level
    
        if (h == 100){
            System.out.println(n + " looks delighted!");
        } else if (h > 80){
            System.out.println(n + " looks really happy!");
        } else if (h > 50){
            System.out.println(n + " looks pretty happy.");
        } else if (h > 30){
            System.out.println(n + " doesn't look very happy.");
        } else {
            System.out.println(n + " looks miserable.");
        } 
    }
}

// pet class

class VirtualPet {
    
    // initialize values to keep private
    
    private String name;
    private int happy;
    private int energy;
    private int clean;
    
    // Constructor
    
    public VirtualPet(String newName){
        this.name = newName;
        setHappy();
        setEnergy();
        setClean();
    }
    
    // methods to initialize the happiness/energy/clean values
    
    public void setHappy(){
        this.happy = (int)(Math.random() * (41) + 60);
    }
    
    public void setEnergy(){
        this.energy = (int)(Math.random() * (41) + 60);
    }
    
    public void setClean(){
        this.clean = (int)(Math.random() * (41) + 60);
    }
    
    // methods to return the happiness/energy/clean values
    
    public int getHappy(){     
        return happy;
    }
    
    public int getEnergy(){
        return energy;
    }
    
    public int getClean(){
        return clean;
    }
    
    // method to walk pet
    
    public void walk() {
        
        /* 
        modify happiness, alert player if pet's energy is low such that
        happiness will begin to drop
        */
        
        if (energy > 30){
            System.out.println("You went for a walk with " + name + ".");
            moreHappy();
        } else if (energy <= 30){
            System.out.println("You went for a walk with " + name + 
                ", but they don't seem very active.");
            happy -= 5;
        }
            
        // modify energy, alert player if pet's happiness level is 0
            
        if (energy > 20){
            energy -= 20;
        } else {
            System.out.println(name + " looks absolutely exhausted.");
            energy = 0;
        }
            
        // modify cleanliness, alert player if cleanliness is below 50
            
        if (clean > 50){
            clean -= 5;
        } else if (clean > 5){
            System.out.println(name + " looks kind of sweaty.");
            clean -= 5;
        } else if (clean <= 5){
            System.out.println(name + " smells awful!");
            clean = 0;
        }
    }
    
    // method to feed pet
    
    public void feed(){
        
        // modify energy level, alert player if pet's energy is at max
        
        if (energy < 90) {
            System.out.println(name + " really likes the food.");
            energy += 10;
            moreHappy();
        } else if (energy < 95) {
            System.out.println(name + " is stuffed!");
            energy = 100;
            moreHappy();
        } else if (energy >= 95) {
            System.out.println(name + " doesn't look hungry.");
        }        
    }
    
    // method to clean pet
    
    public void clean(){
        
        // values after cleaning pet
        
        if (energy < 80) {
            if (clean < 70){
               System.out.println("You gave " + name + " a bath.");
               clean += 30;
               moreHappy();
           } else if (clean >= 70){
               System.out.println(name + " is spotless!");
               clean = 100;
               moreHappy();
           }
       } 
        // alert player if pet's energy level is too high to clean
        
        else if (energy >= 80){
           System.out.println(name + " won't hold still in the bath!");
       }
    }
    
    // method to modify happiness
    
    /* 
    because happiness is max 100 and used many times, it is more efficient to
    create a method that will ensure it remains bounded upon being increased
    */
    
    public void moreHappy(){
        if (happy > 95){
            happy = 100;
        } else {
            happy += 5;
        }
    }
}