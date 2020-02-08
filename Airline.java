import java.util.Scanner;
public class Airline {
   static Scanner input = new Scanner (System.in);
   private static Flight[] FlightsList = new Flight[100];
   static String error = "Operation is not successful, make sure to enter the informaion in the right format.\n";
   public static void main (String[] args){
      int x, destination, gate;
      String date, departure;
      do{
         System.out.println("What would you like to do?\n(1) Add a Flight\n(2) Find a flight\n(3) List of all flights\n(4) List of flights for a given date\n(5) Update Departure & Arrival Time\n(6) Total number of flights\n(7) Exit");
         x = input.nextInt();
         System.out.println();
         switch (x){
            case 1: 
               
               System.out.println("Choose flight's destinaion\n(1) Dammam\n(2) Jeddah\n(3) Yanbu\n(4) Abha\n(5) Hail\n(6) Tabuk\n(7) Taif");
               destination = input.nextInt();
               System.out.println();
               System.out.print("Date in the format (dd/mm): ");
               date = input.next();
               System.out.print("Departure time in the format (hh:mm) and 24-hours system: ");
               departure = input.next();
               System.out.print("Gate: ");
               gate = input.nextInt();
               if (!addFlight (destination, date, gate, departure)){
                  System.out.println(error);
                  continue;
               }
               System.out.println();
               break;
            case 2:
               System.out.println("Enter flight number:");
               String fn = input.next();
               if (findFlight(fn)!= -1) System.out.println("The index of this flight is "+findFlight(fn));
               else System.out.println("The flight was not found.");
               System.out.println();
               break;
            case 3: 
               printAll();
               System.out.println();
               break;
            case 4:
               System.out.println("Enter the date:");
               String d = input.next();
               printAll(d);
               System.out.println();
               break;
            case 5:
               System.out.println("Enter flight number:");
               fn = input.next();
               System.out.println("Enter new departure time:");
               d = input.next();
               updateTime(fn,d);
               System.out.println();
               break;
            case 6:
               System.out.println("Total number of flights: "+getNumberOfFlights());
               System.out.println();
               break;
            case 7: 
               break;
            default: System.out.print("Invalid input");
         } // end switch
      
      } while (x != 7); // end do while loop
      System.out.print("End of program");
   } // end main

   public static boolean addFlight(int destination,String date,int gate,String departure){
      if (Flight.TotalFlights>=100)
         return false;
      if (destination>7||destination<1)
         return false;
      if(departure.length()!=5||departure.charAt(2)!=':')
         return false;
      int hour=Integer.parseInt(departure.substring(0,2));
      int min=Integer.parseInt(departure.substring(3));
      if (hour>23||hour<0)
         return false;
      if (min<0||min>60)
         return false;
      if(date.charAt(2)!='/'||date.length()!=5)
         return false;
      int day=Integer.parseInt(date.substring(0,2));
      if(day<1||day>31)
         return false;
      int month=Integer.parseInt(date.substring(3));
      if(month<1||month>12)
         return false;
   
      for(int i=0;i<Flight.TotalFlights;i++){
         if(FlightsList[i].getDestination()==destination
         && FlightsList[i].getGate()==gate
         && FlightsList[i].getDate().equals(date)
         && FlightsList[i].getDepartureTime().equals(departure)) 
            return false;
      }
      
      FlightsList[Flight.TotalFlights]= new Flight(destination, date, gate,departure);                                                                                                                                 
      return true;
   }
   
      
   public static int findFlight(String FlightNumber){
   
      for (int i=0; i<Flight.TotalFlights;i++)
         if (FlightsList[i].getFlightNumber().equals(FlightNumber))
            return i;
   
      return -1;
   }   
      
   public static void printAll(){
      for (int i=0; i<Flight.TotalFlights; i++)
         FlightsList[i].printFlightInfo();
   }
   
   public static void printAll (String date){
      for (int i=0;i<Flight.TotalFlights;i++){
         if (FlightsList[i].getDate().equals(date))
            FlightsList[i].printFlightInfo();
      }
   
   }
   
   public static void updateTime( String FlightNumber,String departure){
      if(departure.length()!=5||departure.charAt(2)!=':'){
         System.out.println(error);
         return ;
      }
      int hour=Integer.parseInt(departure.substring(0,2));
      int min=Integer.parseInt(departure.substring(3));
      if (hour>23||hour<0||min<0||min>60){
         System.out.println(error);
         return ;
      }
      int i;
      for(i=0;i<Flight.TotalFlights;i++){
         if((FlightsList[i].getFlightNumber()).equals(FlightNumber))
            break;
         if((FlightsList[i].getFlightNumber()).equals(FlightNumber)){
         FlightsList[i].setDepartureTime(departure);
         FlightsList[i].calculateArrivalTime();
         FlightsList[i].printFlightInfo();
         } else System.out.println("The flight was not found.\n");
      }
   }
   public static int getNumberOfFlights (){
      return Flight.TotalFlights;
   
   }

}