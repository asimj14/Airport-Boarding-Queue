
package airport;
public class Passenger {
    //attributes private, visible only inside the class
    private String firstName;
    private String surName;
    private int secondsInQueue;

    //constructor    
    public Passenger(){
        this.firstName= "";
        this.surName= "";
        this.secondsInQueue= 0;
    }
    //constructor with variables
    public Passenger(String firstName, String surName){
        this.firstName= firstName;
        this.surName= surName;
        this.secondsInQueue= secondsInQueue;
    }
    //Setter and Getter methods
    public void setName(String firstName, String surName){
        if (firstName.length()>0 && surName.length()>0){
            this.firstName = firstName;
            this.surName = surName;
        }
    }
    public String getName(){
        return firstName + " " + surName;
    }
    public void setSecondsInQueue(int secondsInQueue){
        if(secondsInQueue>0){
            this.secondsInQueue = secondsInQueue;
        }
    }
    public int getSecondsInQueue(){
        return secondsInQueue;
    }
    //display passengers
    public void displayPassenger(){
        System.out.println(firstName +" "+ 
                surName);
    }
  
}
