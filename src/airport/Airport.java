
package airport;
import java.util.*;
import java.io.*;
//Our class Airport
public class Airport {
    //final varibales
    static final int ArraySize = 30;
    static int count=0;
    //creating a passenger queue1 by calling PassengerQueue class
    static PassengerQueue queue1 = new PassengerQueue();
    //Main program
    public static void main(String[] args) throws IOException {
        //creating passenger array by calling Passenger class
        Passenger[] passengers = new Passenger[ArraySize];
        //creatung passengers in all position of the array 
        Scanner input  = new Scanner(System.in);
        for(int i=0; i< passengers.length;i++){
            passengers[i]= new Passenger();
        }
        String op= "";
        boolean loop = true;
        while (loop == true){
            System.out.println("|------------- Airport Boarding Queue ----------|");
            System.out.println("|-------------------- Menu ---------------------|\n"
                +              "|                                               |\n"
                +              "|A : Add passenger to the passengerQueue        |\n"
                +              "|V : View the passengerQueue                    |\n"
                +              "|D : Delete passenger from the passengerQueue   |\n"
                +              "|S : Store passengerQueue data into a file      |\n"
                +              "|L : Load passengerQueue data back from the file|\n"
                +              "|R : Run the simulation and produce report      |\n"
                +              "|---------------- Q: Quit Program --------------|");
            System.out.println("|-----------------------------------------------|\n");
            System.out.println("Please chose an option from the menu : \n");
            op =input.next();
            //Switch statement in base of option selected calls :
            //the suitable function
            switch (op){
            case "A":
            case "a":
            System.out.println("|----------------- Adding Passenger ------------|\n");   
                addPassenger(passengers);//calling add passenger method
                break; // jumps out of the case statement
            case "V":
            case "v":
                System.out.println("|--------- Diplaying passengers in queue -------|\n");   
                queue1.display();//calling display queue method
                break;
            case "D":
            case "d":
                System.out.println("|-------- Deleting passengers from queue -------|\n");   
                removePassenger();//calling remove passenger method 
                break;
            case "S":
            case "s":
                System.out.println("|--- Storing passengerQueue data into a file ---|\n"); 
                storeDataInFile(passengers);//calling a method to store data 
            
                break;
            case "L"://( To load data in a file )
            case "l":
                System.out.println("|--- Loading passengerQueue data from a file ---|\n");
                loadDataInFile(passengers,queue1); //calling a method to load data 
                break;
            case "R":
            case "r":
                System.out.println("|-------------------- Simulation ---------------|\n");   
                simulation();// ToRun the simulation
                break;
            case "Q": //( To Exit the program )
            case "q":
                System.out.println("|--------------- !!!Exit Program!!! ------------|\n");   
                loop=false;
                break;
            default:
                //checking input 
                System.out.println("Invalid option please enter again ");
                break;   
            }
        }
    }
    //Add passenger method 
    public static void addPassenger(Passenger[] passengerRef){
        Scanner input = new Scanner(System.in);
        String firstname="",lastname="";
        //int ArraySize=6; // to remove after done
        
        if(count< ArraySize){
            Passenger newPassenger = new Passenger();
            System.out.println("Please enter the first name : ");
            firstname = input.nextLine();
            System.out.println("Please enter the surname name : ");
            lastname = input.nextLine();
            newPassenger.setName(firstname, lastname);
            System.out.println("New Passenger : " + newPassenger.getName());
            int seconds = dice(3);
            newPassenger.setSecondsInQueue(seconds);
            passengerRef[count] = newPassenger;
            
            System.out.println("Seconds in queue : "+seconds);
            //System.out.println("count : " + count);
            queue1.addInQueue(newPassenger);
            count++;
        }
        else{
            System.out.println("Queue is full, can't add any more passengers!");
        }   
    }
    //remove passenger method
    public static void removePassenger(){
     if(count>0){
         queue1.removeFromQueue();
         count--;
         //System.out.println("count after removed : " +count);
     }
     else{
         System.out.println("Queue is already empty, can't delete any passenger!");
     }
    }
    
    //Function to Store data in a file 
       //public static void storeDataInFile(PassengerQueue[]queueRef){
        public static void storeDataInFile(Passenger[] passengerRef){
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter the name of the file to store your data :");
            String outputFileName = scan.next();
            //Constructing  Scanner objects for writing
            try(PrintWriter outToFile = new PrintWriter(outputFileName)){
            for(int i=0;i < count;i++){
                System.out.println((i+1)+ " - " +passengerRef[i].getName());
                outToFile.println(passengerRef[i].getName());
            }
            System.out.println("File created and data stored successfully");
            outToFile.close();
        
            }catch(FileNotFoundException e){
                System.out.println("Error: File name already exists!!");  
            }
        }
    //Function to Load Data from file 
        public static void loadDataInFile(Passenger[]  passengerRef,PassengerQueue queue1){
        try{
           Scanner scan = new Scanner(System.in);
           System.out.println("Please enter the name of the file to load your data :");
           String inputFileName = scan.next();
           //Construct your Scanner objects for reading
           File inputFile = new File(inputFileName);
           String[] name;
           String firstname, surname;
           try(Scanner inFromFile = new Scanner(inputFile)){
           //Load Data
           int i=0;
           while(inFromFile.hasNextLine() && i< passengerRef.length){
               String st = inFromFile.nextLine();
               name = st.split(" ");
               firstname = name[0]; 
               surname = name[1];
               passengerRef[i].setName(firstname,surname);
               queue1.addInQueue(passengerRef[i]);
               System.out.println( (i +1)+ "- " + passengerRef[i].getName());
               i++;
            }
               System.out.println("");
               inFromFile.close();
            }
        }catch(FileNotFoundException e){
               System.out.println("Error: File not found! ("+e+")");
          }
        }
        public static int dice(int diceNb){
            int dice ;
            int total = 0;
            for (int i = 0; i<diceNb; i++){
             dice = (int)(Math.random()*6 + 1);
             total = total + dice;
            }
            return total;
        }
        //Simulaion and Report
         public static void simulation()throws IOException{
                File file = new File("passengers.dat");
                Scanner input = new Scanner(file); 
                Passenger[] passengers = new Passenger[ArraySize];
                String firstname;  
                String surname; 
                int i = 0;
                while (input.hasNext()){
                    firstname = input.next();
                    surname = input.next();

                    passengers[i] = new Passenger(firstname, surname);
                    i++;
                }
                int number = 0;
                int totalTime = 0;
                while (number!= passengers.length ){ 
                        int num = dice(1);
                        int time = dice(3);
                        System.out.println("|-----------------------------------------------|\n");
                        System.out.println("new processing delay time :"+time);
                            totalTime = totalTime+time;
                            queue1.setAverage(time);
                            System.out.println("Waiting time of passengers already in the queue  : "+ totalTime);
                            System.out.println("Waiting time of new added passengers: "+ time);
                            System.out.println("New generated passengers to enter :"+num);
                            while(queue1.IsFull()== false && num>0){ 
                                passengers[number].setSecondsInQueue(totalTime);
                                queue1.addInQueue(passengers[number]);
                                number++;
                                num--;
                            }
                        System.out.println("Display current queue: ");
                        queue1.display();
                        System.out.print("After processing completion, ");
                        queue1.removeFromQueue();
                } 
                while (!queue1.IsEmpty()){
                    queue1.removeFromQueue();
                }
                System.out.println("Empty queue, all passengers removed!!!");
                System.out.println("|-------------------- Report -------------------|\n");
                System.out.println("Maximum length of the queue: "+queue1.getMaxSize());
                System.out.println("Maximum waiting time in the queue : "+queue1.getMaxStayInQueue(totalTime));
                System.out.println("Minimum waiting time in the queue : "+ queue1.getMaxStayInQueue(passengers[0].getSecondsInQueue()));
                System.out.println("Average waiting time in the queue : "+queue1.getAverage()+"\n");
                BufferedWriter writer = new BufferedWriter(new FileWriter(
                        "report.dat")); 
                writer.write("|-------------------- Report -------------------|\n");
                writer.write("Maximum length of the queue : "+queue1.getMaxSize()); 
                writer.newLine();
                writer.write("Maximum waiting time in the queue  : "+queue1.getMaxStayInQueue(totalTime));
                writer.newLine();
                writer.write("Minimum waiting time in the queue : "+ queue1.getMaxStayInQueue(passengers[0].getSecondsInQueue()));
                writer.newLine();
                writer.write("Average waiting time in the queue : "+queue1.getAverage()); 
                writer.newLine();

                
                writer.close(); 
            }
    
}
