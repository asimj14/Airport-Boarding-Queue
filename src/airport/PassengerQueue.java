
package airport;
import java.util.*;
import java.io.*;
public class PassengerQueue {
    static final int ArraySize = 20; //class variable : shared by all instances of the class
    //instance variables : queueArray, first , last, maxStayInQueue, 
    //passengersInQueue
    private int first=0,last=0;
    private int maxStayInQueue;
    private int passengersInQueue=0;
    private double averageTime=0;
    private int maxSize=0;
    private Passenger []  queueArray = new Passenger[ArraySize];  
    //constructors
    public PassengerQueue(){
        for(int i=0; i< ArraySize;i++){
            queueArray[i] = new Passenger();
            last=0;
            first=0;
            maxStayInQueue=0;
        }

    }
    //add in queue
    public void addInQueue(Passenger next){
        //add new Passenger, next, in the queueArray
        if(IsFull()){
            System.out.println("Queue is full, can't add any more passengers");
        }
        else{ //can add passengers
            queueArray[last]= next;
            System.out.println("New passenger entered : " +queueArray[last].getName());
            last = (last+1) % queueArray.length;
            passengersInQueue++;
        
        }
    }
    //full queue
    public boolean IsFull(){
        return passengersInQueue == queueArray.length;
    }
    //emptry queue
    public boolean IsEmpty(){
        return passengersInQueue == 0;
    }
    //remove from queue
    public void removeFromQueue(){
        if(IsEmpty()){
            System.out.println("Queue is already empty ");
        }
        else{
                Passenger removed = queueArray[first];
                System.out.println("Passenger " + removed.getName()+" is removed!!!");
                queueArray[first] = null;
                first = (first+1) % queueArray.length;
                passengersInQueue--;
            }
    }
    //statistics methods
    public int getMaxStayInQueue(int max){
        return maxStayInQueue = max;
    }
    public int getMinStayInQueue(int min){
        return maxStayInQueue = min;
    }
    public int getSize(){
        return ArraySize;
    }
    
    public int getMaxSize(){
        int j;
        if (getSize() > maxSize){
            j=getSize();
            maxSize=j;
        }
        return maxSize;
        
    }
    public void setAverage(int time){
        averageTime = averageTime+time;
    }
    public double getAverage(){
        return (averageTime/getSize());
    }
    
    //dispaly queue
    public void display(){
     if (IsEmpty()){
         System.out.println("Queue is empty!!!");
     }
     else{
        for(int i=first;i< last;i++){
           queueArray[i].displayPassenger();
            
        }
    }  
   }
    
}